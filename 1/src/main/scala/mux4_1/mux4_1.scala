package scala.mux4_1

import chisel3._

class mux4_1 extends Module{
    val io = IO(new Bundle{
        val a = Input(UInt(4.W))
        val s = Input(UInt(2.W))
        val y = Output(UInt(1.W))
    })

    when(io.s === 0.U){
        io.y := io.a(0)
    }.elsewhen(io.s === 1.U){
        io.y := io.a(1)
    }.elsewhen(io.s === 2.U){
        io.y := io.a(2)
    }.otherwise{
        io.y := io.a(3)
    }
}
