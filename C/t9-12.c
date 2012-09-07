#include <stdio.h>
#include <stdlib.h>
#include "gcd.c"

int divFraction(int a, int c, int b, int d) {
  int upper = a * d;
  int lower = c * b;

  if (!lower) {
    return 0;
  }
  int simplifier = 1;
  if (upper) {
    simplifier = gcd(upper, lower);
  }
  printf("Fraction division: %d/%d\n", upper/simplifier, lower/simplifier);
  return 1;
}

int mulFraction(int a, int c, int b, int d) {
  int upper = a * b;
  int lower = c * d;

  if (!lower) {
    return 0;
  }
  int simplifier = 1;
  if (upper){
    simplifier = gcd(upper, lower);
  }
  printf("Fraction multiplication: %d/%d\n", upper/simplifier, lower/simplifier);
  return 1;
}

int subFraction(int a, int c, int b, int d) {
  int upper = a * d - b * c;
  int lower = c * d;

  if (!lower) {
    return 0;
  }
  int simplifier = 1;
  if (upper) {
    simplifier = gcd(upper, lower);
  }
  printf("Fraction sub: %d/%d\n", upper/simplifier, lower/simplifier);
  return 1;
}

int addFraction(int a, int c, int b, int d) {
  int upper = a * d + b * c;
  int lower = c * d;
  if (!lower) {
    return 0;
  }
  int simplifier = 1;
  if (upper) {
    simplifier = gcd(upper, lower);
  }
  printf("Fraction sum: %d/%d\n", upper/simplifier, lower/simplifier);
  return 1;
}

int main(int argc, char **argv) {
  int a = atoi(argv[1]);
  int c = atoi(argv[2]);
  int b = atoi(argv[3]);
  int d = atoi(argv[4]);

  int successful = addFraction(a, c, b, d);
  printf("Addition Succesfulness: %d\n", successful);
  successful = subFraction(a, c, b, d);
  printf("Subtraction successfulness: %d\n", successful);
  successful = mulFraction(a, c, b, d);
  printf("Multiplication successfulness: %d\n", successful);
  successful = divFraction(a, c, b, d);
  printf("Division successfulness: %d\n", successful);
  return 0;
}
