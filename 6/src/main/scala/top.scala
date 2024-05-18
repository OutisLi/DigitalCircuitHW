package scala.top

import chisel3._
import LFSR._
import LED._

class top extends Module {
  val io = IO(new Bundle {
    val control = Input(UInt(3.W))
    val seed    = Input(UInt(8.W))
    val out     = Output(UInt(8.W))
    val outSeg0 = Output(UInt(7.W))
    val outSeg1 = Output(UInt(7.W))
  })

  val LFSR = Module(new LFSR)
  LFSR.io.control := io.control
  LFSR.io.seed    := io.seed
  io.out          := LFSR.io.out
  val LED0 = Module(new led)
  LED0.io.in := io.out(3, 0)
  io.outSeg0 := LED0.io.out
  val LED1 = Module(new led)
  LED1.io.in := io.out(7, 4)
  io.outSeg1 := LED1.io.out
}
