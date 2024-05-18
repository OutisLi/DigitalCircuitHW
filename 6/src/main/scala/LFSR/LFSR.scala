package LFSR

import chisel3._
import chisel3.util._
import shiftReg._

class LFSR extends Module {
  val io = IO(new Bundle {
    val control = Input(UInt(3.W))
    val seed    = Input(UInt(8.W))
    val out     = Output(UInt(8.W))
  })
  when(io.seed === 0.U) {
    io.out := 0.U
  }.otherwise {
    val shiftReg = Module(new shiftReg)
    shiftReg.io.control := io.control
    shiftReg.io.in      := shiftReg.io.out(4) ^ shiftReg.io.out(3) ^ shiftReg.io.out(2) ^ shiftReg.io.out(0)
    shiftReg.io.init    := io.seed
    io.out              := shiftReg.io.out
  }
}
