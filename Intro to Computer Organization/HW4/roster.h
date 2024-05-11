#include <stdio.h>
int add_student(FILE* in, FILE* out, char* students[], int current_students, int const max_students);
int remove_student(FILE* in, FILE* out, char* students[], int current_students, int const max_students);
void delete_class(FILE* out, char* students[], int* current_students);
void modify_name(FILE* in, FILE* out, char* students[], int current_students);
void display_class(FILE* out, char* students[], int current_students);