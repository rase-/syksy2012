#include <stdio.h>

int gcd(int a, int b) {
  a = abs(a);
  b = abs(b);
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

