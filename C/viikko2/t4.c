#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void shuffle_ints(int* array, int len) {
  srand ( time(NULL) );
  for (int i = len - 1; i >= 0; i--) {
    int random = rand() % (i+1);
    srand ( time(NULL) );
    printf("rand: %d\n", random);
    int temp = array[random];
    array[random] = array[i];
    array[i] = temp;
  }
}

int main(void) {
  int nums[] = { 0, 1, 2, 3, 4, 5, 6 };
  shuffle_ints(nums, 7);

  for (int i = 0; i < 7; i++) {
    printf("%d, ", nums[i]);
  }
  printf("\n");
}
