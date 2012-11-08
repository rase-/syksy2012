#include <stdio.h>

struct dListNode {
  int value;
  struct dListNode * next;
  struct dListNode * prev;
}


dListNode* insertElement(dListNode *list, dListNode *pos, int value) {
  struct dListNode addition = dListNode;
  addition.value = value;
  if (*list == NULL) {
    return addition;
  }
  struct dListNode beforeAddition = *pos;
  struct dListNode next = beforeAddition.next;
  beforeAddition.next = addition;
  addition.prev = beforeAddition;
  addition.next = next;
}

int main(void) {

}
