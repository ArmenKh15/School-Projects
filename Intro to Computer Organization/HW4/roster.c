#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "roster.h"

int add_student(FILE* in, FILE* out, char* students[], int current_students, int const max_students)
{

    char name[100], last[100];
    int remaining;
    fscanf(in, "%s %s", name, last);
    strcat(name, " ");
    strcat(name, last);    

    if (current_students == max_students)
    {
        fprintf(out, "Command: add %s\n", name);
        fprintf(out, "    Student %s not added. The class is already at capacity.\n", name);
        return current_students;
    }
    
    students[current_students] = (char*)malloc((strlen(name) + 1) * sizeof(char));
    strcpy(students[current_students], name);

    current_students++;

    remaining = max_students - current_students;
    fprintf(out, "Command: add %s\n", name);
    fprintf(out, "    %s was added. %d spot(s) remain.\n", name, remaining);

    return current_students;
}


int remove_student(FILE* in, FILE* out, char* students[], int current_students, int const max_students)
{
    char name[100], last[100];
    int remaining;
    fscanf(in, "%s %s", name, last);
    strcat(name, " ");
    strcat(name, last);
    int found = 0;

    for (int i = 0; i < current_students; i++)
    {
        if (strcmp(students[i], name) == 0)
        {
            free(students[i]);
            

            for (int j = i; j < current_students - 1; j++)
            {
                students[j] = students[j+1];
            }

            current_students--;

            remaining = max_students - current_students;
            fprintf(out, "Command: remove %s\n", name);
            fprintf(out, "    Student %s removed. The course now has %d seats remaining.\n", name, remaining);

            found = 1;
            break;
            
        }
    }

    if (!found)
    {
        fprintf(out, "Command: remove %s\n", name);
        fprintf(out, "    No student named %s was found to remove.\n", name);
    }
    
    return current_students;

}


void delete_class(FILE* out, char* students[], int* current_students)
{
    fprintf(out, "Command: delete\n");
    for (int i = 0; i < *current_students; i++)
    {
        fprintf(out, "    %s removed in class delete.\n", students[i]);
        free(students[i]);
    }
    fprintf(out, "    Class was restarted and reopened for enrollment.\n\n");

    *current_students = 0;
}


void modify_name(FILE* in, FILE* out, char* students[], int current_students)
{
    char name[100], modname[100];
    if (fscanf(in, "%100[^:]:%100[^\n]", name, modname) != 2) 
    {
        fprintf(stderr, "Error reading name modification command.\n");
        return;
    }

    fprintf(out, "Command: modify %s:%s\n", name, modname);

    for (int i = 0; i < current_students; i++)
    {
        if (strcmp(students[i], name) == 0)
        {
            free(students[i]);
            students[i] = (char*)malloc((strlen(modname) + 1) * sizeof(char));
            strcpy(students[i], modname);

            fprintf(out, "    Student %s name modified to %s.\n", name, modname);
            return;
        }
    }

    fprintf(out, "    No student with name %s found.\n", name);
}


void display_class(FILE* out, char* students[], int current_students)
{
    fprintf(out, "Command: display\n");
    fprintf(out, "    Currently Enrolled:\n");

    for (int i = 0; i < current_students; i++)
    {
        fprintf(out, "        Student %d: %s\n", i+1, students[i]);
    }
}