package scala.mux2_1

import chisel3._

class mux2_1 extends Module {
  val io = IO(new Bundle {
    val a   = Input(UInt(1.W))
    val b   = Input(UInt(1.W))
    val s = Input(Bool())
    val y   = Output(UInt(1.W))
  })

  io.y := Mux(io.s, io.b, io.a)
}
