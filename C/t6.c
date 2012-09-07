#include <stdio.h>

// Maailman hyödyllisin funktio T: Tony
int doShit(int i) {
  return abs(i);
}

int main (void) {
  printf("Abs of -1: %d \n", doShit(-1));
  printf("Abs of 1: %d \n", doShit(1));
}
