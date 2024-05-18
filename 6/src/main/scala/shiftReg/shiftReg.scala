package shiftReg

import chisel3._
import chisel3.util._

class shiftReg extends Module {
  val io = IO(new Bundle {
    val in      = Input(UInt(1.W))
    val control = Input(UInt(3.W))
    val init    = Input(UInt(8.W))
    val out     = Output(UInt(8.W))
  })

  val state = RegInit(0.U(8.W))
  when(io.control === "b000".U) {
    state := 0.U(8.W)
  }.elsewhen(io.control === "b001".U) {
    state := io.init
  }.elsewhen(io.control === "b010".U) {
    state := Cat(0.U(1.W), state(7, 1))
  }.elsewhen(io.control === "b011".U) {
    state := Cat(state(6, 0), 0.U(1.W))
  }.elsewhen(io.control === "b100".U) {
    state := Cat(state(7), state(7, 1))
  }.elsewhen(io.control === "b101".U) {
    state := Cat(io.in, state(7, 1))
  }.elsewhen(io.control === "b110".U) {
    state := Cat(state(0), state(7, 1))
  }.elsewhen(io.control === "b111".U) {
    state := Cat(state(6, 0), state(7))
  }
  io.out := state
}
