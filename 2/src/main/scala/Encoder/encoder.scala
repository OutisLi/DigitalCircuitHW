package Encoder

import chisel3._
import chisel3.util._

class _83encoder extends Module {
  val io = IO(new Bundle {
    val in      = Input(UInt(8.W))
    val out     = Output(UInt(3.W))
    val en      = Input(Bool())
    val nonzero = Output(Bool())
  })
  io.out := 0.U
  when(io.en) {
    when(io.in === 0.U) {
      io.out     := 0.U
      io.nonzero := 0.B
    }.otherwise {
      val i = 0.U
      for (i <- 0 until 8) {
        when(io.in(i) === 1.U) {
          io.out := i.U
        }
      }
      io.nonzero := 1.B
    }
  }.otherwise {
    io.out     := 0.U
    io.nonzero := 0.B
  }
}
