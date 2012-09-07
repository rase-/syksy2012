#include <stdio.h>

int main(void) {
  int i = 7;
  float f = 5.5;
  char c = 'w';
  printf("i+f as double: %f\n", (double) i + (double) f);
  printf("i+c as int: %d\n", i + (int) c);
  printf("i+c-'0' as int: %d\n", i + ( (int) c - (int) '0' ) );
  printf("(i+c) - (2*f/5) as double: %f\n", ((double) i + (double) c) - (2.0 * (double) f / 5));
}
