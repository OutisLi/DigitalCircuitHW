package Keyboard

import chisel3._
import chisel3.util._

class PS2KeyboardModel(kbdClkPeriod: Int = 6) extends Module {
  val io = IO(new Bundle {
    val code     = Input(UInt(8.W))
    val send     = Input(Bool())
    val ps2_clk  = Output(Bool())
    val ps2_data = Output(Bool())
  })

  // Registers for the clock and data signals
  val ps2_clk_reg  = RegInit(true.B)
  val ps2_data_reg = RegInit(true.B)

  // Default outputs
  io.ps2_clk  := ps2_clk_reg
  io.ps2_data := ps2_data_reg

  // Clock period
  val half_period = (kbdClkPeriod / 2).U

  val send_buffer = Reg(Vec(11, Bool()))
  val bit_counter = RegInit(0.U(4.W))
  val timer = RegInit(0.U(32.W))

  // Initialize the send buffer with the provided code
  val parity_bit = ~io.code.xorR
  send_buffer := VecInit((0.U +: io.code.asBools) :+ parity_bit :+ true.B)

  when(io.send === true.B) {
    when(bit_counter < 11.U) {
      ps2_data_reg := send_buffer(bit_counter)
    }.otherwise {
      bit_counter := 0.U
      ps2_clk_reg := true.B
    }
    timer := timer + 1.U
    when(timer === half_period - 1.U) {
      ps2_clk_reg := false.B
    }
    when(timer === kbdClkPeriod.U - 1.U) {
      timer       := 0.U
      ps2_clk_reg := true.B
      bit_counter := bit_counter + 1.U
    }
  }.otherwise {
    ps2_data_reg := true.B
    ps2_clk_reg  := true.B
  }
}
