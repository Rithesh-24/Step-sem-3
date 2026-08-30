#include <stdio.h>
#include <stdlib.h>

int *arr = NULL;
int capacity = 2;
int size = 0;

void resize() {
    capacity *= 2;
    arr = (int*)realloc(arr, capacity * sizeof(int));
    printf("Resized capacity to %d\n", capacity);
}

void append(int x) {
    if (size == capacity) resize();
    arr[size++] = x;
    printf("Appended %d\n", x);
}

void insert(int pos, int x) {
    int i;
    if (pos < 1 || pos > size + 1) { printf("Invalid position\n"); return; }
    if (size == capacity) resize();
    for (i = size; i >= pos; i--) arr[i] = arr[i - 1];
    arr[pos - 1] = x;
    size++;
    printf("Inserted %d at pos %d\n", x, pos);
}

void removeAt(int pos) {
    int i;
    if (pos < 1 || pos > size) { printf("Invalid position\n"); return; }
    for (i = pos - 1; i < size - 1; i++) arr[i] = arr[i + 1];
    size--;
    printf("Removed element at pos %d\n", pos);
}

int find(int x) {
    int i;
    for (i = 0; i < size; i++) {
        if (arr[i] == x) return i + 1; // 1-based position
    }
    return -1;
}

void dedup() {
    int i, j, k;
    for (i = 0; i < size; i++) {
        for (j = i + 1; j < size; ) {
            if (arr[j] == arr[i]) {
                for (k = j; k < size - 1; k++) arr[k] = arr[k + 1];
                size--;
            } else {
                j++;
            }
        }
    }
    printf("Duplicates removed\n");
}

void display() {
    int i;
    if (size == 0) { printf("Roster is empty\n"); return; }
    printf("Roster: ");
    for (i = 0; i < size; i++) printf("%d ", arr[i]);
    printf("\n");
}

int main() {
    int ch, val, pos, res;
    arr = (int*)malloc(capacity * sizeof(int));

    while (1) {
        printf("\n1.Append 2.Insert 3.Remove 4.Find 5.Dedup 6.Display 7.Exit\nChoice: ");
        if (scanf("%d", &ch) != 1) break;

        switch (ch) {
            case 1:
                printf("Enter value: "); scanf("%d", &val);
                append(val); break;
            case 2:
                printf("Enter pos and value: "); scanf("%d %d", &pos, &val);
                insert(pos, val); break;
            case 3:
                printf("Enter pos: "); scanf("%d", &pos);
                removeAt(pos); break;
            case 4:
                printf("Enter value to find: "); scanf("%d", &val);
                res = find(val);
                if (res != -1) printf("Found %d at position %d\n", val, res);
                else printf("Value %d not found (Position: -1)\n", val);
                break;
            case 5: dedup(); break;
            case 6: display(); break;
            case 7: free(arr); exit(0);
            default: printf("Invalid choice!\n");
        }
    }
    return 0;
}
