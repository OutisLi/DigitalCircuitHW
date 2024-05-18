#include <nvboard.h>
#include <Vtop.h>
#include <unistd.h>

static TOP_NAME dut;

void nvboard_bind_all_pins(TOP_NAME *top);

static void single_cycle()
{
  dut.clock = 0;
  sleep(0.5);
  dut.eval();
  dut.clock = 1;
  sleep(0.5);
  dut.eval();
}

int main()
{
  nvboard_bind_all_pins(&dut);
  nvboard_init();

  while (1)
  {
    nvboard_update();
    single_cycle();
  }
}
