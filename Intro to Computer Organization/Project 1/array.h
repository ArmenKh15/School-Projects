#include <stdio.h>
#include <stdlib.h>

unsigned int* create_array( FILE* in, unsigned int *size );
unsigned int* free_memory( unsigned int* pointer );
void display_data( FILE* out, unsigned int count, unsigned int* data );
void read_data( FILE* in, unsigned int *data, unsigned int count );
