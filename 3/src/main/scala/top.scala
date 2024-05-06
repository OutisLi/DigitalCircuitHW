package scala.top

import chisel3._
import ALU._

class top extends Module {
  val io = IO(new Bundle {
    val func     = Input(UInt(3.W))
    val A        = Input(SInt(4.W))
    val B        = Input(SInt(4.W))
    val out      = Output(SInt(4.W))
    val zero     = Output(Bool())
    val overflow = Output(Bool())
    val carry    = Output(Bool())
  })
  val alu = Module(new ALU(4))
  alu.io.func := io.func
  alu.io.A    := io.A
  alu.io.B    := io.B
  io.out      := alu.io.out
  io.zero     := alu.io.zero
  io.overflow := alu.io.overflow
  io.carry    := alu.io.carry
}
