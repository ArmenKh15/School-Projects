#include "array.h"

unsigned int* create_array(FILE *in, unsigned int *size)
{
    //Check to see if input file is valid
    if (in == NULL)
    {
        fprintf(stderr, "File pointer is not valid\n");
        exit(1);
    }

    //Check size of array
    fscanf(in, "%u", size);

    //Create dynamic array
    unsigned int* arr = (unsigned int* )malloc(*size * sizeof(unsigned int));
    if (arr == NULL)
    {
        fprintf(stderr, "Memory is not valid\n");
        exit(1);
    }

    //Read each value in the array from input and store in arr
    for (unsigned int i = 0; i < *size; i++)
    {
        fscanf(in, "%u", &arr[i]);
    }
    return arr;
}

//Free memory
unsigned int* free_memory(unsigned int* pointer)
{
    free(pointer);
    return NULL;
}


void read_data(FILE* in, unsigned int* data, unsigned int count)
{
    for (unsigned int i = 0; i < count; i++)
    {
        fscanf(in, "%u", &data[i]);
    }
    fscanf(in, "%*c");
}

//Display values from input file
void display_data(FILE* out, unsigned int count, unsigned int* data)
{
    if (out == NULL)
    {
        fprintf(stderr, "Output is not valid\n");
        exit(1);
    }

    fprintf(out, "         ");

    //Column numbers
    for (unsigned int i = 0; i < 10 && i < count; i++)
    {
        fprintf(out, "%-5u", i);
    }
    fprintf(out, "\n");

    unsigned int numr = (count + 9) / 10;

    //Create row numbers and enter values from arr
    for (unsigned int row = 0; row < numr; row++)
    {
        fprintf(out, "\t%-3u", row);

        for (unsigned int col = 0; col < 10; col++)
        {
            unsigned int index = row * 10 + col;
            if (index < count)
            {
                fprintf(out, "%-5u", data[index]);
            }
            else
            {
                fprintf(out, "      ");
            }
        }

        fprintf(out, "\n");
    }
}
