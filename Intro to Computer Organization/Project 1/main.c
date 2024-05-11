#include <stdio.h>
#include <stdlib.h>

#include "array.h"
#include "mesa.h"

int main(int argc, char *argv[])
{
    if (argc != 3)
    {
        fprintf(stderr, "Usage: %s <input-file> <output-file>\n", argv[0]);
        exit(1);
    }

    FILE *in = fopen(argv[1], "r");
    FILE *out = fopen(argv[2], "w");

    //Check to see if input/output files are valid
    if (in == NULL || out == NULL)
    {
        fprintf(stderr, "Error: Unable to open files.\n");
        exit(1);
    }

    unsigned int set = 1;

    while (!feof(in))
    {
        //Set number and dashed line
        fprintf(out, "Set %u\n", set++);
        for (int i = 0; i < 55; i++)
            fputc('-', out);
        fputs("\n", out);

        //Create array of current set
        unsigned int size;
        unsigned int *data = create_array(in, &size);

        //Find largest mesa in set and display required data
        unsigned int length, height;
        struct Mesa largestMesa = find_mesa(in, data, size, &length, &height);
        fprintf(out, "Min Length: %u\t", length);
        fprintf(out, "Min Height: %u\n", height);
        display_data(out, size, data);

        //Check to see if largest mesa is valid per min length/min height requirements
        if (largestMesa.valid)
        {
            display_mesa(out, largestMesa);
        }
        else
        {
            fprintf(out, "No valid mesa found.\n");
            fprintf(out, "\n");
        }

        //Free memory
        free_memory(data);
    }

    fclose(in);
    fclose(out);
    
    return 0;
}