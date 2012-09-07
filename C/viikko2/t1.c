#include <stdio.h>

int main(void) {
  int i, j = 25;
  // Here i = 0, j = 25
  int  *pi, *pj = &j;
  //Here pi = something random and pj = address of j
  *pj = j+5;
  //pj still the same, *pj is now 30
  i = *pj+5;
  //i = 35
  pi = pj;
  // pi now points to j also, *pi is now 30
  *pi=i+j;
  // Now *pi = 35 + 30 = 65, pi still points to j
}
