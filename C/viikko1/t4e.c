#include <stdio.h>

int main(void) {
  int i = 1;
  float x = 1.0;
  char c = 'c';

  printf("integer: %lu \n", sizeof i);
  printf("float: %lu \n", sizeof x);
  printf("char: %lu \n", sizeof c);
}
