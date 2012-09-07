#include <stdio.h>

int gcd(int a, int b) {
  while(1) {
    if (a < b) {
      int temp = a;
      a = b;
      b = temp;
    }
    int r = a % b;
    a = a/b;
    if (r == 0) {
      return b;
    }
    a = b;
    b = r;
  }
}

int main(int argc, char *argv[]) {
  int first = atoi(argv[1]);
  int second = atoi(argv[2]);
  printf("First argument: %d\n", first);
  printf("Second argument: %d\n", second);
  printf("GCD: %d \n", gcd( first, second));
}
