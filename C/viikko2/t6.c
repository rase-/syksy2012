#include <stdio.h>

struct dListNode {
  int value;
  struct dListNode * next;
  struct dListNode * prev;
}


dListNode* insertElement(dListNode *list, dListNode *pos, int value) {
  struct dListNode addition = dListNode;
  if (list == NULL) {
    return addition;
  }
  struct dListNode first = *list;
  addition.value = value;

  struct dListNode current = first;
  while(current.next != NULL) {

  }
}

int main(void) {

}
