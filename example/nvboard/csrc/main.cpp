#include <nvboard.h>
#include <Vtop.h>

static TOP_NAME dut;

void nvboard_bind_all_pins(TOP_NAME* top);

static void single_cycle()
{
  dut.clock = 0;
  dut.eval();
  dut.clock = 1;
  dut.eval();
}

int main() {
  nvboard_bind_all_pins(&dut);
  nvboard_init();
  dut.reset = 1;
  single_cycle();
  dut.reset = 0;

  while(1) {
    nvboard_update();
    single_cycle();
  }
}
