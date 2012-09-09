#include <stdio.h>
#include <stdlib.h>
#include "gcd.c"

struct fraction {
  int numerator;
  int denumerator;
};

struct fraction addFraction(struct fraction r, struct fraction s) {
  //int upper = a * d + b * c;
  //int lower = c * d;
  int upper = r.numerator * s.denumerator + s.numerator * r.denumerator;
  int lower = r.denumerator * s.denumerator;
  if (!lower) {
    struct fraction temp;
    return temp;
  }
  int simplifier = 1;
  if (upper) {
    simplifier = gcd(upper, lower);
  }
  printf("Fraction sum: %d/%d\n", upper/simplifier, lower/simplifier);
  struct fraction result;
  result.numerator = upper/simplifier;
  result.denumerator = lower/simplifier;
  return result;
}

int main(int argc, char **argv) {
  int a = atoi(argv[1]);
  int c = atoi(argv[2]);
  int b = atoi(argv[3]);
  int d = atoi(argv[4]);
  struct fraction first, second;
  first.numerator = a;
  first.denumerator = c;
  second.numerator = b;
  second.denumerator = d;
  struct fraction result = addFraction(first, second);
  return 0;
}
