package scala.top

import chisel3._
import chisel3.util._

import mux2_1._
import mux4_1._
import mux_generator._

// a 4 to 1 2bits mux using mux_generator
class top extends Module {
  val io = IO(new Bundle {
    val a = Input(Vec(4, UInt(2.W)))
    val s = Input(UInt(2.W))
    val y = Output(UInt(2.W))
  })
  val mux = Module(new MuxKey(4, 2, 2))
  mux.io.key := io.s
  mux.io.lut(0) := Cat("b00".U, io.a(0))
  mux.io.lut(1) := Cat("b01".U, io.a(1))
  mux.io.lut(2) := Cat("b10".U, io.a(2))
  mux.io.lut(3) := Cat("b11".U, io.a(3))
  io.y := mux.io.out
}

