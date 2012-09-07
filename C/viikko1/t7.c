#include <stdio.h>

int gcd(int a, int b) {
  a = abs(a);
  b = abs(b);
  if (a < b) {
    int temp = a;
    a = b;
    b = temp;
  }
  int remainder = a % b;
  a = a/b;

  if (remainder == 0) {
    return b;
  }

  return gcd(b, remainder);
}

int main(int argc, char *argv[]) {
  int first = atoi(argv[1]);
  int second = atoi(argv[2]);
  printf("First argument: %d\n", first);
  printf("Second argument: %d\n", second);
  printf("GCD: %d \n", gcd( first, second));
}
