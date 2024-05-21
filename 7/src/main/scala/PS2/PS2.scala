package PS2

import chisel3._
import chisel3.util._

class PS2Keyboard extends Module {
  val io = IO(new Bundle {
    val clrn       = Input(Bool())
    val ps2_clk    = Input(Bool())
    val ps2_data   = Input(Bool())
    val nextdata_n = Input(Bool())
    val data       = Output(UInt(8.W))
    val ready      = Output(Bool())
    val overflow   = Output(Bool())
  })

  val ready_reg    = RegInit(false.B)
  val overflow_reg = RegInit(false.B)
  io.overflow := overflow_reg
  io.ready    := ready_reg

  // internal signals
  val buffer       = RegInit(VecInit(Seq.fill(11)(0.U(1.W)))) // 11位宽的寄存器数组，每个位初始化为0
  val fifo         = Reg(Vec(8, UInt(8.W)))
  val w_ptr        = RegInit(0.U(3.W))
  val r_ptr        = RegInit(0.U(3.W))
  val count        = RegInit(0.U(4.W))
  val ps2_clk_sync = RegInit(0.U(3.W))

  // detect falling edge of ps2_clk
  ps2_clk_sync := Cat(ps2_clk_sync(1, 0), io.ps2_clk)

  val sampling = ps2_clk_sync(2) & ~ps2_clk_sync(1)

  when(!io.clrn) {
    count        := 0.U
    w_ptr        := 0.U
    r_ptr        := 0.U
    overflow_reg := false.B
    ready_reg    := false.B
  }.otherwise {
    when(ready_reg) {
      when(!io.nextdata_n) { // read next data
        // wait for w_ptr to move
        when(r_ptr < w_ptr - 1.U || (r_ptr === 7.U && w_ptr === 1.U)) {
          r_ptr := r_ptr + 1.U(3.W)
        }
        when(w_ptr === (r_ptr + 1.U)) { // empty
          ready_reg := false.B
        }
      }
    }
    when(sampling) {
      when(count === 10.U) {
        when(
          (buffer(0) === 0.U) && // start bit
            (io.ps2_data) && // stop bit
            ((buffer.slice(1, 10).reduce(_ ^ _)).asBool) // odd parity
        ) {
          fifo(w_ptr)  := Cat(buffer.slice(1, 9).reverse) // kbd scan code
          buffer(10)   := io.ps2_data
          w_ptr        := w_ptr + 1.U(3.W)
          ready_reg    := true.B
          overflow_reg := r_ptr === (w_ptr + 1.U)
        }
        count := 0.U // for next
      }.otherwise {
        buffer(count) := io.ps2_data.asUInt // store ps2_data
        count         := count + 1.U
      }
    }
  }

  io.data  := fifo(r_ptr)
}

class PS2KeycodeToASCII extends Module {
  val io = IO(new Bundle {
    val keycode = Input(UInt(8.W))
    val ascii   = Output(UInt(8.W))
  })

  io.ascii := 0.U // Default value

  switch(io.keycode) {
    is("h15".U) { io.ascii := "h51".U } // Q
    is("h1d".U) { io.ascii := "h57".U } // W
    is("h24".U) { io.ascii := "h45".U } // E
    is("h2d".U) { io.ascii := "h52".U } // R
    is("h2c".U) { io.ascii := "h54".U } // T
    is("h35".U) { io.ascii := "h59".U } // Y
    is("h3c".U) { io.ascii := "h55".U } // U
    is("h43".U) { io.ascii := "h49".U } // I
    is("h44".U) { io.ascii := "h4f".U } // O
    is("h4d".U) { io.ascii := "h50".U } // P
    is("h1c".U) { io.ascii := "h41".U } // A
    is("h1b".U) { io.ascii := "h53".U } // S
    is("h23".U) { io.ascii := "h44".U } // D
    is("h2b".U) { io.ascii := "h46".U } // F
    is("h34".U) { io.ascii := "h47".U } // G
    is("h33".U) { io.ascii := "h48".U } // H
    is("h3b".U) { io.ascii := "h4a".U } // J
    is("h42".U) { io.ascii := "h4b".U } // K
    is("h4b".U) { io.ascii := "h4c".U } // L
    is("h1a".U) { io.ascii := "h5a".U } // Z
    is("h22".U) { io.ascii := "h58".U } // X
    is("h21".U) { io.ascii := "h43".U } // C
    is("h2a".U) { io.ascii := "h56".U } // V
    is("h32".U) { io.ascii := "h42".U } // B
    is("h31".U) { io.ascii := "h4e".U } // N
    is("h3a".U) { io.ascii := "h4d".U } // M
    // Numbers
    is("h45".U) { io.ascii := "h30".U } // 0
    is("h16".U) { io.ascii := "h31".U } // 1
    is("h1e".U) { io.ascii := "h32".U } // 2
    is("h26".U) { io.ascii := "h33".U } // 3
    is("h25".U) { io.ascii := "h34".U } // 4
    is("h2e".U) { io.ascii := "h35".U } // 5
    is("h36".U) { io.ascii := "h36".U } // 6
    is("h3d".U) { io.ascii := "h37".U } // 7
    is("h3e".U) { io.ascii := "h38".U } // 8
    is("h46".U) { io.ascii := "h39".U } // 9
  }
}
