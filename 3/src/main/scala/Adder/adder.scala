package Adder

import chisel3._
import chisel3.util._

class Adder(width: Int) extends Module {
  val io = IO(new Bundle {
    val a        = Input(SInt(width.W))
    val b        = Input(SInt(width.W))
    val ctrl     = Input(UInt(1.W)) // 0 for add, 1 for sub
    val carry    = Output(Bool())
    val overflow = Output(Bool())
    val zero     = Output(Bool())
    val res      = Output(SInt(width.W))
  })
  val ext_ctrl  = Fill(width, io.ctrl)
  val t_add_Cin = ((ext_ctrl ^ io.b.asUInt) + io.ctrl.asUInt).asSInt
  val sum       = io.a +& t_add_Cin
  io.res      := sum(width - 1, 0).asSInt
  io.carry    := (sum(width) ^ io.ctrl).asBool
  io.overflow := ((io.a(width - 1) === t_add_Cin(width - 1)) && (io.res(width - 1) =/= io.a(width - 1))).asBool
  io.zero     := io.res === 0.S
}
