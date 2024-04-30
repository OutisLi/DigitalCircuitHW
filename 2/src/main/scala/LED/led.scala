package LED

import chisel3._
import chisel3.util._

class led extends Module {
  val io = IO(new Bundle {
    val in  = Input(UInt(4.W))
    val out = Output(UInt(7.W))
  })
  when(io.in === 0.U) {
    io.out := "b1000000".U
  }.elsewhen(io.in === 1.U) {
    io.out := "b1111001".U
  }.elsewhen(io.in === 2.U) {
    io.out := "b0100100".U
  }.elsewhen(io.in === 3.U) {
    io.out := "b0110000".U
  }.elsewhen(io.in === 4.U) {
    io.out := "b0011001".U
  }.elsewhen(io.in === 5.U) {
    io.out := "b0010010".U
  }.elsewhen(io.in === 6.U) {
    io.out := "b0000010".U
  }.elsewhen(io.in === 7.U) {
    io.out := "b1111000".U
  }.elsewhen(io.in === 8.U) {
    io.out := "b0000000".U
  }.elsewhen(io.in === 9.U) {
    io.out := "b0010000".U
  }.otherwise {
    io.out := "b1111111".U
  }
}
