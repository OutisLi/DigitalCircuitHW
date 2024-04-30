package scala.top

import chisel3._
import chisel3.util._
import Encoder._
import LED._

class top extends Module {
  val io = IO(new Bundle {
    val in      = Input(UInt(8.W))
    val out     = Output(UInt(3.W))
    val segout  = Output(UInt(7.W))
    val en      = Input(Bool())
    val nonzero = Output(Bool())
  })

  val encoder = Module(new _83encoder)
  encoder.io.in := io.in
  encoder.io.en := io.en
  io.out        := encoder.io.out
  io.nonzero    := encoder.io.nonzero
  val ledseg = Module(new led)
  ledseg.io.in := Cat(0.U(1.W), encoder.io.out)
  io.segout    := ledseg.io.out
}
