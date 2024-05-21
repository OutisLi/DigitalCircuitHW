package scala.top

import chisel3._
import chisel3.util._
import Display._
import Keyboard._

class top extends Module {
  val io = IO(new Bundle {
    val clrn     = Input(Bool())
    val ps2_clk  = Input(Bool())
    val ps2_data = Input(Bool())
    val out0     = Output(UInt(7.W))
    val out1     = Output(UInt(7.W))
    val out2     = Output(UInt(7.W))
    val out3     = Output(UInt(7.W))
    val out4     = Output(UInt(7.W))
    val out5     = Output(UInt(7.W))
  })

  val display = Module(new Display)
  display.io.clrn     := ~io.clrn
  display.io.ps2_clk  := io.ps2_clk
  display.io.ps2_data := io.ps2_data
  io.out0             := display.io.out0
  io.out1             := display.io.out1
  io.out2             := display.io.out2
  io.out3             := display.io.out3
  io.out4             := display.io.out4
  io.out5             := display.io.out5
}