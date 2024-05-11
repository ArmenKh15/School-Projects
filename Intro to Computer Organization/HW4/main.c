#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "roster.h"

int main(int argc, char* argv[])
{
    if (argc != 3)
    {
        fprintf(stderr, "Incorrect number of arguments.\n");
        fprintf(stderr, "USAGE: ./%s <input_file> <output_file>\n", argv[0]);
        return 1;
    }

    FILE* in = fopen(argv[1], "r");
    FILE* out = fopen(argv[2], "w");

    int max_students = 0;
    fscanf(in, "%d\n", &max_students);
    char** students = (char**)malloc(max_students * sizeof(char*));
    int current_students = 0;

    char cmd[10];
    
    while (!feof(in))
    {
        fscanf(in, "%s ", cmd);
        if (cmd[0] == 'a')
        {
            current_students = add_student(in, out, students, current_students, max_students);
        }
        else if (cmd[0] == 'r')
        {
            current_students = remove_student(in, out, students, current_students, max_students);
        }
        else if (cmd[0] == 'd' && cmd[1] == 'i')
        {
            display_class(out, students, current_students);
        }
        else if (cmd[0] == 'm')
        {
            modify_name(in, out, students, current_students);
        }
        else if (cmd[0] == 'd')
        { 
            delete_class(out, students, &current_students);
            free(students);
            
            fscanf(in, "\n%d\n", &max_students);
            students = (char**)malloc(max_students * sizeof(char*));
        }
    }

    fclose(in);
    fclose(out);
    free(students);

    return 0;
}
