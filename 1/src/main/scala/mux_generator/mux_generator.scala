package scala.mux_generator

import chisel3._
import chisel3.util._

class MuxKeyInternal(val nrKey: Int, val keyLen: Int, val dataLen: Int, val hasDefault: Boolean) extends Module {
  val io = IO(new Bundle {
    val out        = Output(UInt(dataLen.W))
    val key        = Input(UInt(keyLen.W))
    val defaultOut = Input(UInt(dataLen.W))
    val lut        = Input(Vec(nrKey, UInt((keyLen + dataLen).W)))
  })

  val pairList = Wire(Vec(nrKey, UInt((keyLen + dataLen).W)))
  val dataList = Wire(Vec(nrKey, UInt(dataLen.W)))
  val keyList  = Wire(Vec(nrKey, UInt(keyLen.W)))

  for (n <- 0 until nrKey) {
    pairList(n) := io.lut(n)
    dataList(n) := pairList(n)(dataLen - 1, 0)
    keyList(n)  := pairList(n)(keyLen + dataLen - 1, dataLen)
  }

  val lutOut = Wire(UInt(dataLen.W))
  val hit    = Wire(Bool())
  lutOut := 0.U
  hit    := false.B

  for (i <- 0 until nrKey) {
    when(io.key === keyList(i)) {
      lutOut := dataList(i)
      hit    := true.B
    }
  }

  io.out := Mux(hasDefault.asBool && !hit, io.defaultOut, lutOut)
}

class MuxKey(nrKey: Int, keyLen: Int, dataLen: Int) extends Module {
  val io = IO(new Bundle {
    val out = Output(UInt(dataLen.W))
    val key = Input(UInt(keyLen.W))
    val lut = Input(Vec(nrKey, UInt((keyLen + dataLen).W)))
  })

  val mux = Module(new MuxKeyInternal(nrKey, keyLen, dataLen, hasDefault = false))
  mux.io.key        := io.key
  mux.io.defaultOut := 0.U // Dummy default for modules without a default
  mux.io.lut        := io.lut
  io.out            := mux.io.out
}

class MuxKeyWithDefault(nrKey: Int, keyLen: Int, dataLen: Int) extends Module {
  val io = IO(new Bundle {
    val out        = Output(UInt(dataLen.W))
    val key        = Input(UInt(keyLen.W))
    val defaultOut = Input(UInt(dataLen.W))
    val lut        = Input(Vec(nrKey, UInt((keyLen + dataLen).W)))
  })

  val mux = Module(new MuxKeyInternal(nrKey, keyLen, dataLen, hasDefault = true))
  mux.io.key        := io.key
  mux.io.defaultOut := io.defaultOut
  mux.io.lut        := io.lut
  io.out            := mux.io.out
}
