package ALU

import chisel3._
import chisel3.util._
import Adder._
import Comparator._

class ALU(width: Int) extends Module {
  val io = IO(new Bundle {
    val func     = Input(UInt(3.W))
    val A        = Input(SInt(width.W))
    val B        = Input(SInt(width.W))
    val out      = Output(SInt(width.W))
    val zero     = Output(Bool())
    val overflow = Output(Bool())
    val carry    = Output(Bool())
  })
  // default outputs
  io.out      := 0.S
  io.zero     := 0.B
  io.overflow := 0.B
  io.carry    := 0.B

  when(io.func === "b000".U || io.func === "b001".U) {
    val adder = Module(new Adder(width))
    adder.io.a    := io.A
    adder.io.b    := io.B
    adder.io.ctrl := Mux(io.func === "b000".U, 0.U, 1.U)
    io.out        := adder.io.res
    io.zero       := adder.io.zero
    io.overflow   := adder.io.overflow
    io.carry      := adder.io.carry
  }.elsewhen(io.func === "b010".U) {
    io.out := ~io.A
  }.elsewhen(io.func === "b011".U) {
    io.out := io.A & io.B
  }.elsewhen(io.func === "b100".U) {
    io.out := io.A | io.B
  }.elsewhen(io.func === "b101".U) {
    io.out := io.A ^ io.B
  }.elsewhen(io.func === "b110".U || io.func === "b111".U) {
    val comparator = Module(new Comparator(width))
    comparator.io.A := io.A
    comparator.io.B := io.B
    io.out          := Mux(io.func === "b110".U, comparator.io.out.pad(width).asSInt, comparator.io.eq.pad(width).asSInt)
  }
}
