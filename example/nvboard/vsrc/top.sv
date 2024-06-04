// Generated by CIRCT firtool-1.66.0
// Standard header to adapt well known macros for register randomization.
`ifndef RANDOMIZE
  `ifdef RANDOMIZE_REG_INIT
    `define RANDOMIZE
  `endif // RANDOMIZE_REG_INIT
`endif // not def RANDOMIZE

// RANDOM may be set to an expression that produces a 32-bit random unsigned value.
`ifndef RANDOM
  `define RANDOM $random
`endif // not def RANDOM

// Users can define INIT_RANDOM as general code that gets injected into the
// initializer block for modules with registers.
`ifndef INIT_RANDOM
  `define INIT_RANDOM
`endif // not def INIT_RANDOM

// If using random initialization, you can also define RANDOMIZE_DELAY to
// customize the delay used, otherwise 0.002 is used.
`ifndef RANDOMIZE_DELAY
  `define RANDOMIZE_DELAY 0.002
`endif // not def RANDOMIZE_DELAY

// Define INIT_RANDOM_PROLOG_ for use in our modules below.
`ifndef INIT_RANDOM_PROLOG_
  `ifdef RANDOMIZE
    `ifdef VERILATOR
      `define INIT_RANDOM_PROLOG_ `INIT_RANDOM
    `else  // VERILATOR
      `define INIT_RANDOM_PROLOG_ `INIT_RANDOM #`RANDOMIZE_DELAY begin end
    `endif // VERILATOR
  `else  // RANDOMIZE
    `define INIT_RANDOM_PROLOG_
  `endif // RANDOMIZE
`endif // not def INIT_RANDOM_PROLOG_

// Include register initializers in init blocks unless synthesis is set
`ifndef SYNTHESIS
  `ifndef ENABLE_INITIAL_REG_
    `define ENABLE_INITIAL_REG_
  `endif // not def ENABLE_INITIAL_REG_
`endif // not def SYNTHESIS

// Include rmemory initializers in init blocks unless synthesis is set
`ifndef SYNTHESIS
  `ifndef ENABLE_INITIAL_MEM_
    `define ENABLE_INITIAL_MEM_
  `endif // not def ENABLE_INITIAL_MEM_
`endif // not def SYNTHESIS

module Light(	// @[src/main/scala/Light/light.scala:6:7]
  input         clock,	// @[src/main/scala/Light/light.scala:6:7]
                reset,	// @[src/main/scala/Light/light.scala:6:7]
  output [15:0] io_led	// @[src/main/scala/Light/light.scala:7:14]
);

  reg [31:0] cntReg;	// @[src/main/scala/Light/light.scala:10:23]
  reg        ledReg_0;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_1;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_2;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_3;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_4;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_5;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_6;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_7;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_8;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_9;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_10;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_11;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_12;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_13;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_14;	// @[src/main/scala/Light/light.scala:11:23]
  reg        ledReg_15;	// @[src/main/scala/Light/light.scala:11:23]
  reg        tmp;	// @[src/main/scala/Light/light.scala:12:23]
  always @(posedge clock) begin	// @[src/main/scala/Light/light.scala:6:7]
    if (reset) begin	// @[src/main/scala/Light/light.scala:6:7]
      cntReg <= 32'h0;	// @[src/main/scala/Light/light.scala:10:23]
      ledReg_0 <= 1'h1;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_1 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_2 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_3 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_4 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_5 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_6 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_7 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_8 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_9 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_10 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_11 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_12 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_13 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_14 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      ledReg_15 <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :11:23]
      tmp <= 1'h0;	// @[src/main/scala/Light/light.scala:6:7, :12:23]
    end
    else if (cntReg == 32'hC34FFF) begin	// @[src/main/scala/Light/light.scala:10:23, :14:15]
      cntReg <= 32'h0;	// @[src/main/scala/Light/light.scala:10:23]
      ledReg_0 <= tmp;	// @[src/main/scala/Light/light.scala:11:23, :12:23]
      ledReg_1 <= ledReg_0;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_2 <= ledReg_1;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_3 <= ledReg_2;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_4 <= ledReg_3;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_5 <= ledReg_4;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_6 <= ledReg_5;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_7 <= ledReg_6;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_8 <= ledReg_7;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_9 <= ledReg_8;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_10 <= ledReg_9;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_11 <= ledReg_10;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_12 <= ledReg_11;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_13 <= ledReg_12;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_14 <= ledReg_13;	// @[src/main/scala/Light/light.scala:11:23]
      ledReg_15 <= ledReg_14;	// @[src/main/scala/Light/light.scala:11:23]
    end
    else begin	// @[src/main/scala/Light/light.scala:14:15]
      cntReg <= cntReg + 32'h1;	// @[src/main/scala/Light/light.scala:10:23, :21:22]
      tmp <= ledReg_15;	// @[src/main/scala/Light/light.scala:11:23, :12:23]
    end
  end // always @(posedge)
  `ifdef ENABLE_INITIAL_REG_	// @[src/main/scala/Light/light.scala:6:7]
    `ifdef FIRRTL_BEFORE_INITIAL	// @[src/main/scala/Light/light.scala:6:7]
      `FIRRTL_BEFORE_INITIAL	// @[src/main/scala/Light/light.scala:6:7]
    `endif // FIRRTL_BEFORE_INITIAL
    logic [31:0] _RANDOM[0:1];	// @[src/main/scala/Light/light.scala:6:7]
    initial begin	// @[src/main/scala/Light/light.scala:6:7]
      `ifdef INIT_RANDOM_PROLOG_	// @[src/main/scala/Light/light.scala:6:7]
        `INIT_RANDOM_PROLOG_	// @[src/main/scala/Light/light.scala:6:7]
      `endif // INIT_RANDOM_PROLOG_
      `ifdef RANDOMIZE_REG_INIT	// @[src/main/scala/Light/light.scala:6:7]
        for (logic [1:0] i = 2'h0; i < 2'h2; i += 2'h1) begin
          _RANDOM[i[0]] = `RANDOM;	// @[src/main/scala/Light/light.scala:6:7]
        end	// @[src/main/scala/Light/light.scala:6:7]
        cntReg = _RANDOM[1'h0];	// @[src/main/scala/Light/light.scala:6:7, :10:23]
        ledReg_0 = _RANDOM[1'h1][0];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_1 = _RANDOM[1'h1][1];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_2 = _RANDOM[1'h1][2];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_3 = _RANDOM[1'h1][3];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_4 = _RANDOM[1'h1][4];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_5 = _RANDOM[1'h1][5];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_6 = _RANDOM[1'h1][6];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_7 = _RANDOM[1'h1][7];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_8 = _RANDOM[1'h1][8];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_9 = _RANDOM[1'h1][9];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_10 = _RANDOM[1'h1][10];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_11 = _RANDOM[1'h1][11];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_12 = _RANDOM[1'h1][12];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_13 = _RANDOM[1'h1][13];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_14 = _RANDOM[1'h1][14];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        ledReg_15 = _RANDOM[1'h1][15];	// @[src/main/scala/Light/light.scala:6:7, :11:23]
        tmp = _RANDOM[1'h1][16];	// @[src/main/scala/Light/light.scala:6:7, :11:23, :12:23]
      `endif // RANDOMIZE_REG_INIT
    end // initial
    `ifdef FIRRTL_AFTER_INITIAL	// @[src/main/scala/Light/light.scala:6:7]
      `FIRRTL_AFTER_INITIAL	// @[src/main/scala/Light/light.scala:6:7]
    `endif // FIRRTL_AFTER_INITIAL
  `endif // ENABLE_INITIAL_REG_
  assign io_led =
    {ledReg_15,
     ledReg_14,
     ledReg_13,
     ledReg_12,
     ledReg_11,
     ledReg_10,
     ledReg_9,
     ledReg_8,
     ledReg_7,
     ledReg_6,
     ledReg_5,
     ledReg_4,
     ledReg_3,
     ledReg_2,
     ledReg_1,
     ledReg_0};	// @[src/main/scala/Light/light.scala:6:7, :11:23, :25:20]
endmodule

module top(	// @[src/main/scala/top.scala:6:7]
  input         clock,	// @[src/main/scala/top.scala:6:7]
                reset,	// @[src/main/scala/top.scala:6:7]
  output [15:0] io_led	// @[src/main/scala/top.scala:7:14]
);

  Light light (	// @[src/main/scala/top.scala:11:21]
    .clock  (clock),
    .reset  (reset),
    .io_led (io_led)
  );	// @[src/main/scala/top.scala:11:21]
endmodule

