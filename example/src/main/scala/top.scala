package scala.top

import chisel3._
import light._

class top extends Module {
  val io = IO(new Bundle {
    val led   = Output(UInt(16.W))
  })

  val light = Module(new Light)
  io.led := light.io.led
}
