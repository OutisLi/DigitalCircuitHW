#include "verilated.h"
#include "verilated_vcd_c.h"
#include "Vtop.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

VerilatedContext *contextp = NULL;
VerilatedVcdC *vcd = NULL;

static Vtop *top;

void step_and_dump_wave()
{
    top->eval();
    contextp->timeInc(1);
    vcd->dump(contextp->time());
}
void sim_init(int argc, char **argv)
{
    contextp = new VerilatedContext;
    vcd = new VerilatedVcdC;
    top = new Vtop{contextp};
    Verilated::commandArgs(argc, argv);
    contextp->traceEverOn(true);
    contextp->commandArgs(argc, argv);
    top->trace(vcd, 5);
    vcd->open("waveform.vcd");
}

void sim_exit()
{
    step_and_dump_wave();
    vcd->close();
}

int main(int argc, char **argv)
{
    sim_init(argc, argv);

    top->io_ctrl = 0;  // add
    top->io_a = 0b0100;
    top->io_b = 0b0010;
    step_and_dump_wave();
    top->io_a = 0b0111;
    top->io_b = 0b0111;
    step_and_dump_wave();
    top->io_a = 0b1001;
    top->io_b = 0b0111;
    step_and_dump_wave();
    top->io_a = 0b1001;
    top->io_b = 0b1111;
    step_and_dump_wave();
    top->io_ctrl = 1;  // sub
    top->io_a = 0b0100;
    top->io_b = 0b0010;
    step_and_dump_wave();
    top->io_a = 0b0101;
    top->io_b = 0b0111;
    step_and_dump_wave();
    top->io_a = 0b1000;
    top->io_b = 0b0000;
    step_and_dump_wave();
    top->io_a = 0b1001;
    top->io_b = 0b1111;
    step_and_dump_wave();
    top->io_a = 0b1001;
    top->io_b = 0b0111;
    step_and_dump_wave();

    sim_exit();
    delete top;
    delete contextp;
    delete vcd;
    return 0;
}