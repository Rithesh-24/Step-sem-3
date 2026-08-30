#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int *arr;
int size = 0;
int capacity = 2;

// Double capacity when array is full or requested
void resize() {
    capacity *= 2;
    arr = (int *)realloc(arr, capacity * sizeof(int));
}

// Add student ID to end
void append(int x) {
    if (size == capacity) {
        resize();
    }
    arr[size++] = x;
}

// Insert student ID at specified 0-indexed position
void insert(int pos, int x) {
    if (pos < 0 || pos > size) return; // Out-of-bounds safety check
    if (size == capacity) {
        resize();
    }
    for (int i = size; i > pos; i--) {
        arr[i] = arr[i - 1];
    }
    arr[pos] = x;
    size++;
}

// Remove student ID at specified 0-indexed position
void remove_at(int pos) {
    if (pos < 0 || pos >= size) return; // Out-of-bounds safety check
    for (int i = pos; i < size - 1; i++) {
        arr[i] = arr[i + 1];
    }
    size--;
}

// Find 0-indexed position of a student ID (-1 if not found)
int find(int x) {
    for (int i = 0; i < size; i++) {
        if (arr[i] == x) return i;
    }
    return -1;
}

// Remove duplicates while maintaining initial relative order
void dedup() {
    int unique_count = 0;
    for (int i = 0; i < size; i++) {
        int is_dup = 0;
        for (int j = 0; j < unique_count; j++) {
            if (arr[i] == arr[j]) {
                is_dup = 1;
                break;
            }
        }
        if (!is_dup) {
            arr[unique_count++] = arr[i];
        }
    }
    size = unique_count;
}

int main() {
    arr = (int *)malloc(capacity * sizeof(int));

    int M;
    if (scanf("%d", &M) != 1) return 0;

    int find_results[100];
    int find_count = 0;

    for (int i = 0; i < M; i++) {
        char cmd[20];
        scanf("%s", cmd);

        if (strcmp(cmd, "APPEND") == 0) {
            int x;
            scanf("%d", &x);
            append(x);
        } else if (strcmp(cmd, "INSERT") == 0) {
            int pos, x;
            scanf("%d %d", &pos, &x);
            insert(pos, x);
        } else if (strcmp(cmd, "REMOVE") == 0) {
            int pos;
            scanf("%d", &pos);
            remove_at(pos);
        } else if (strcmp(cmd, "FIND") == 0) {
            int x;
            scanf("%d", &x);
            find_results[find_count++] = find(x);
        } else if (strcmp(cmd, "DEDUP") == 0) {
            dedup();
        } else if (strcmp(cmd, "RESIZE") == 0) {
            resize();
        }
    }

    // Output all FIND results
    printf("FIND Results:\n");
    for (int i = 0; i < find_count; i++) {
        printf("%d\n", find_results[i]);
    }

    // Output final course roster
    printf("\nFinal Roster:\n");
    for (int i = 0; i < size; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    free(arr);
    return 0;
}

