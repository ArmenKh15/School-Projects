#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

struct Mesa
{
	unsigned int start;
	unsigned int end;
	unsigned int height;
	unsigned int weight;
	bool valid;
};

struct Mesa find_mesa(FILE* in, unsigned int *numbers, unsigned int count, unsigned int *length, unsigned int *height );
void display_mesa( FILE* out, struct Mesa m );
