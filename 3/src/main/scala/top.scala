package scala.top

import chisel3._
import Adder._

class top extends Module {
  val io = IO(new Bundle {
    val a        = Input(SInt(4.W))
    val b        = Input(SInt(4.W))
    val ctrl     = Input(UInt(1.W))
    val carry    = Output(UInt(1.W))
    val overflow = Output(Bool())
    val zero     = Output(Bool())
    val res      = Output(SInt(4.W))
  })

  val adder = Module(new Adder(4))
  adder.io.a    := io.a
  adder.io.b    := io.b
  adder.io.ctrl := io.ctrl
  io.carry      := adder.io.carry
  io.overflow   := adder.io.overflow
  io.zero       := adder.io.zero
  io.res        := adder.io.res
}
