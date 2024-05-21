package Display

import chisel3._
import chisel3.util._
import PS2._
import LED._

class Display extends Module {
  val io = IO(new Bundle {
    val clrn        = Input(Bool())
    val ps2_clk     = Input(Bool())
    val ps2_data    = Input(Bool())
    val dataTemp    = Output(UInt(8.W))
    val out0        = Output(UInt(7.W))
    val out1        = Output(UInt(7.W))
    val out2        = Output(UInt(7.W))
    val out3        = Output(UInt(7.W))
    val out4        = Output(UInt(7.W))
    val out5        = Output(UInt(7.W))
  })
  val keyboard = Module(new PS2Keyboard)
  keyboard.io.clrn     := io.clrn
  keyboard.io.ps2_clk  := io.ps2_clk
  keyboard.io.ps2_data := io.ps2_data
  val nextdata_n = RegInit(true.B)
  keyboard.io.nextdata_n := nextdata_n

  val data      = Reg(UInt(8.W))
  val data_last = RegInit(0.U(8.W))
  val times     = RegInit(0.U(8.W))
  val ready     = Wire(Bool())
  val overflow  = Wire(Bool())
  data      := keyboard.io.data
  ready     := keyboard.io.ready
  overflow  := keyboard.io.overflow

  when(ready) {
    nextdata_n := false.B
    when(data === "hF0".U && data_last =/= "hF0".U) {
      times := times + 1.U
    }
    data_last := data
  }.otherwise {
    nextdata_n := true.B
  }
  val led0 = Module(new LED)
  led0.io.in := data(3, 0)
  io.out0    := led0.io.out
  val led1 = Module(new LED)
  led1.io.in := data(7, 4)
  io.out1    := led1.io.out
  val ascii = Module(new PS2KeycodeToASCII)
  val led2  = Module(new LED)
  val led3  = Module(new LED)
  ascii.io.keycode := data
  led2.io.in       := ascii.io.ascii(3, 0)
  led3.io.in       := ascii.io.ascii(7, 4)
  io.out2          := led2.io.out
  io.out3          := led3.io.out
  val led4 = Module(new LED)
  led4.io.in := times(3, 0)
  io.out4    := led4.io.out
  val led5 = Module(new LED)
  led5.io.in := times(7, 4)
  io.out5    := led5.io.out

  io.dataTemp := data
}
