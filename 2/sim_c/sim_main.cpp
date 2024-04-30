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

    top->io_en = 0;
    top->io_in = 0b11011100;
    step_and_dump_wave();
    top->io_en = 1;
    step_and_dump_wave();
    top->io_in = 0b00011100;
    step_and_dump_wave();
    top->io_in = 0b00001100;
    step_and_dump_wave();
    top->io_in = 0b10011100;
    step_and_dump_wave();
    top->io_in = 0b00000010;
    step_and_dump_wave();
    top->io_in = 0b00010100;
    step_and_dump_wave();
    top->io_in = 0b00000001;
    step_and_dump_wave();
    top->io_in = 0b00000000;
    step_and_dump_wave();

    sim_exit();
    delete top;
    delete contextp;
    delete vcd;
    return 0;
}