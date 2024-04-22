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
    // VerilatedContext *contextp = new VerilatedContext;

    // Verilated::commandArgs(argc, argv);
    // Verilated::traceEverOn(true); // 启用波形跟踪

    // contextp->commandArgs(argc, argv);
    // Vtop *top = new Vtop{contextp};

    // VerilatedVcdC *vcd = new VerilatedVcdC; // 创建一个 VCD 文件对象
    // top->trace(vcd, 5);                     // 设置跟踪等级，5 是常用的深度
    // vcd->open("waveform.vcd");              // 打开 VCD 文件用于写入波形数据

    sim_init(argc, argv);

    top->io_sel = 0;
    top->io_a = 0;
    top->io_b = 0;
    step_and_dump_wave(); // 将s，a和b均初始化为“0”
    top->io_b = 1;
    step_and_dump_wave(); // 将b改为“1”，s和a的值不变，继续保持“0”，
    top->io_a = 1;
    top->io_b = 0;
    step_and_dump_wave(); // 将a，b分别改为“1”和“0”，s的值不变，
    top->io_b = 1;
    step_and_dump_wave(); // 将b改为“1”，s和a的值不变，维持10个时间单位

    top->io_sel = 1;
    top->io_a = 0;
    top->io_b = 0;
    step_and_dump_wave(); // 将s，a，b分别变为“1,0,0”，维持10个时间单位
    top->io_b = 1;
    step_and_dump_wave();
    top->io_a = 1;
    top->io_b = 0;
    step_and_dump_wave();
    top->io_b = 1;
    step_and_dump_wave();

    sim_exit();
    delete top;
    delete contextp;
    delete vcd;
    return 0;
}