#include "verilated.h"
#include "verilated_vcd_c.h"
#include "Vtop.h"
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>

VerilatedContext *contextp = NULL;
VerilatedVcdC *vcd = NULL;

static Vtop *top;

void step_and_dump_wave(int n = 1)
{
    for (int i = 0; i < n; i++)
    {
        top->clock = 0;
        top->eval();
        contextp->timeInc(1);
        vcd->dump(contextp->time());
        top->clock = 1;
        top->eval();
        contextp->timeInc(1);
        vcd->dump(contextp->time());
    }
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
    delete top;
    delete contextp;
    delete vcd;
}

int main(int argc, char **argv)
{
    sim_init(argc, argv);

    top->reset = 1;
    step_and_dump_wave(10);
    top->reset = 0;
    step_and_dump_wave(100);

    sim_exit();
    return 0;
}
