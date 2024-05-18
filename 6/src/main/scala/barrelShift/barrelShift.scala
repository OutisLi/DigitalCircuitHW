package barrelShift

import chisel3._
import chisel3.util._

class barrelShift extends Module {
  val io = IO(new Bundle {
    val din   = Input(UInt(8.W))
    val shamt = Input(UInt(3.W))
    val lr    = Input(Bool()) // 0: left, 1: right
    val al    = Input(Bool()) // 0: arithmetic, 1: logical
    val dout  = Output(UInt(8.W))
  })
  val stage = Wire(Vec(3, UInt(8.W)))

  // first stage shamt(0) 1 step
  stage(0) := Mux(
    io.shamt(0),
    Mux(io.lr, Cat(Mux(io.al, 0.U(1.W), io.din(7)), io.din(7, 1)), Cat(io.din(6, 0), 0.U(1.W))),
    io.din
  )
  // second stage shamt(1) 2 step
  stage(1) := Mux(
    io.shamt(1),
    Mux(io.lr, Cat(Mux(io.al, 0.U(2.W), Cat(io.din(7), io.din(7))), io.din(7, 2)), Cat(io.din(5, 0), 0.U(2.W))),
    stage(0)
  )
  // third stage shamt(2) 4 step
  stage(2) := Mux(
    io.shamt(2),
    Mux(io.lr, Cat(Mux(io.al, 0.U(4.W), Fill(4, io.din(7))), io.din(7, 4)), Cat(io.din(3, 0), 0.U(4.W))),
    stage(1)
  )
  io.dout := stage(2)
}
