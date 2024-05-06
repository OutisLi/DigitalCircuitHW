package Comparator

import chisel3._
import chisel3.util._
import Adder._

// if A < B then out = 1 else out = 0
// if A = B then eq = 1 else eq = 0
class Comparator(width: Int) extends Module {
  val io = IO(new Bundle {
    val A   = Input(SInt(width.W))
    val B   = Input(SInt(width.W))
    val out = Output(UInt(1.W))
    val eq  = Output(UInt(1.W))
  })
  val adder = Module(new Adder(width))
  adder.io.a    := io.A
  adder.io.b    := io.B
  adder.io.ctrl := 1.U
  val res      = adder.io.res
  val overflow = adder.io.overflow
  val zero     = adder.io.zero
  io.out := Mux(~overflow, Mux(res(width - 1), 1.U, 0.U), Mux(res(width - 1), 0.U, 1.U))
  io.eq  := Mux(zero, 1.U, 0.U)
}
