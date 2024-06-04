package light

import chisel3._
import chisel3.util._

class Light extends Module {
  val io = IO(new Bundle {
    val led = Output(UInt(16.W))
  })
  val cntReg = RegInit(0.U(32.W))
  val ledReg = RegInit(VecInit(1.U(16.W).asBools))
  val tmp    = RegInit(0.U(1.W))

  when(cntReg === 4999999.U) {
    cntReg := 0.U
    for (i <- 15 to 1 by -1) {
      ledReg(i) := ledReg(i - 1)
    }
    ledReg(0) := tmp
  }.otherwise {
    cntReg := cntReg + 1.U
    tmp    := ledReg(15)
  }
  io.led := ledReg.asUInt
}
