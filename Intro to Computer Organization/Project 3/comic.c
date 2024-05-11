#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "comic.h"


void load(FILE *in, FILE *out, struct Comic_List *comicList)
{
    //Declare csv variable
    char filename[100];
    int countload = 0;

    //Scan in csv name
    fscanf(in, "%s", filename);

    //Open csv
    FILE* file = fopen(filename, "r");

    //Print command text
    fprintf(out, "Command: load %s\n", filename);

    //Check if file is valid
    if (file == NULL)
    {
        printf("load file error");
        exit(1);
    }

    //Slip the first line
    fscanf(file, "%*[^\n]\n");

    // Declare variables
    char date[100], code[100], publisher[100], title[1000], cost[10];


    //Begin to read csv file
    while (1) {

        // Assign values to variables from csv
        if (fscanf(file, "%99[^,],%99[^,],%99[^,],%999[^,],%9s\n", date, code, publisher, title, cost) == EOF) {
            break;
        }

        // Create comic
        struct Comic *comic = (struct Comic *)malloc(sizeof(struct Comic));

        //Allocate memory and set variables in Comic
        // comic->date = (char*)malloc((strlen(date) + 1) * sizeof(char));
        comic->date = strdup(date);

        // comic->code = (char*)malloc((strlen(code) + 1) * sizeof(char));
        comic->code = strdup(code);

        //comic->publisher = (char*)malloc((strlen(publisher) + 1) * sizeof(char));
        comic->publisher = strdup(publisher);

        //comic->title = (char*)malloc((strlen(title) + 1) * sizeof(char));
        comic->title = strdup(title);
        
        //comic->cost = (char*)malloc((strlen(cost) + 1) * sizeof(char));
        comic->cost = strdup(cost);

        //Add comic to list
        add_comic(comicList, comic);
        countload++;
    }

    //Print number of comics
    fprintf(out, "	Number of comics: %d\n", countload);
    fclose(file);
}

void add_comic(struct Comic_List *comicList, struct Comic *comic)
{
    //Check and update size
    if (comicList->count >= comicList->size)
    {
        update_size(comicList);
    }

    //Add the comic to list and update count
    comicList->list[comicList->count++] = comic;
}

void update_size(struct Comic_List *comicList)
{
    //Double size
    comicList->size *= 2;

    //Reallocate memory
    comicList->list = (struct Comic **)realloc(comicList->list, comicList->size * sizeof(struct Comic*));
}

void free_Comic(struct Comic *comic)
{
    //Free comic memory
    free(comic->date);
    free(comic->code);
    free(comic->publisher);
    free(comic->title);
    free(comic->cost);

    free(comic);
}

void free_ComicList(struct Comic_List *comicList)
{
    //Free comics in list memory
    for (int i = 0; i < comicList->count; i++)
    {
        free_Comic(comicList->list[i]);
    }
    //Free list
    free(comicList->list);
}

void display(struct Comic_List *comicList, FILE *out)
{
    //Print command text
    fprintf(out, "Command: display\n");
    
    //Check if list is empty
    if (comicList->count == 0)
    {
        fprintf(out, "List is currently empty.\n");
    }
    else{

        //Iterate through comicList
        for (int i = 0; i < comicList->count; i++)
        {
            fprintf(out, "Comic Number: %d\n", i+1);
            display_comic(comicList->list[i],out);
            //fprintf(out, "\n");
        }
    }
}

void display_comic(struct Comic *comic, FILE *out)
{
    //Print comic info
    fprintf(out, "\tDate: %s\n", comic->date);
    fprintf(out, "\tCode: %s\n", comic->code);
    fprintf(out, "\tPublisher: %s\n", comic->publisher);
    fprintf(out, "\tTitle: %s\n", comic->title);
    fprintf(out, "\tCost: %s\n", comic->cost);    
}

void find(FILE *in, FILE *out, struct Comic_List *comicList)
{
    //Declare index variable
    int index;

    //Scan in index variable
    fscanf(in, "%d", &index);

    //Print command text
    fprintf(out, "Command: find %d\n", index);

    //If index is out of range
    if (comicList->count < index || index < 0)
    {
        fprintf(out, "There is no comic at index #%d in the list.\n", index);
        return;
    }

    //If index is in range
    else
    {
        display_comic(comicList->list[index],out);
    }
}

void remove_comic(FILE *in, FILE *out, struct Comic_List *comicList)
{
    //Declare index variable
    int index;

    //Scan in index variable
    fscanf(in, "%d", &index);

    //Print command text
    fprintf(out, "Command: remove %d\n", index);

    //If index is out of range
    if (comicList->count < index || index < 0)
    {
        fprintf(out, "Comic at index %d was not removed\n", index);
        return;
    }

    //If index is in range
    else
    {
        //Free comic memory
        free_Comic(comicList->list[index]);

        //Shift list
        for (int j = index; j < comicList->count; j++)
        {
            comicList->list[j] = comicList->list[j+1];
        }

        //Update count
        comicList->count--;

        //Command text
        fprintf(out, "Comic at index %d successfully removed\n", index);
}
}

void clear(FILE *out, struct Comic_List *comicList)
{
    //Print command text
    fprintf(out, "Command: clear\n");

    //Iterate and remove all commics
    for (int i = 0; i < comicList->count; i++)
    {
        free_Comic(comicList->list[i]);
    }

    //Update count and size
    comicList->count = 0;

    comicList->size = 10;
}

void save(FILE *in, FILE *out, struct Comic_List *comicList)
{

    //Declare csv variable
    char filename[100];

    //Scan in csv name
    fscanf(in, "%s", filename);

    //Print command text
    fprintf(out, "Command: save %s\n", filename);

    //Open new csv
    FILE *outcsv = fopen(filename, "w");

    //Print columns
    fprintf(outcsv, "DATE,CODE,PUBLISHER,TITLE,PRICE\n");

    //Iterate through list
    for (int i = 0; i < comicList->count; i++)
        {
            // Print comic info horizontally
            fprintf(outcsv, "%s,%s,%s,%s,%s\n", comicList->list[i]->date, comicList->list[i]->code, comicList->list[i]->publisher, comicList->list[i]->title, comicList->list[i]->cost);
        }

    //Close file
    fclose(outcsv);
}

void buy(FILE *in, FILE *out, struct Comic_List *comicList, struct Comic_List *purchaseList)
{
    //Declare index variable
    int index;

    //Scan in index variable
    fscanf(in, "%d", &index);

    //Print command text
    fprintf(out, "Command: buy %d\n", index);

    //If index is out of range
    if (comicList->count < index || index < 0)
    {
        fprintf(out, "Unable to buy comic #%d\n", index);
        return;
    }

    //If index is in range
    else
    {
        //Check and update size
        if (purchaseList->count >= purchaseList->size)
        {
            update_size(purchaseList);
        }

        //Create comic
        struct Comic *buycomic = (struct Comic *)malloc(sizeof(struct Comic));

        // Check if memory allocation was successful
        if (buycomic == NULL)
        {
            printf("Memory allocation failed.\n");
            return;
        }

        //Copy and add comic
        copy_comic(buycomic, comicList->list[index]);
        add_comic(purchaseList, buycomic);


        //Print commmand text
        fprintf(out,"Comic #%d added to purchase list\n", index);
    }
}

void copy_comic(struct Comic *dest, struct Comic *src)
{
    //Copy data from src to dest
    dest->date = strdup(src->date);
    dest->code = strdup(src->code);
    dest->publisher = strdup(src->publisher);
    dest->title = strdup(src->title);
    dest->cost = strdup(src->cost);
}

void checkout(struct Comic_List *purchaseList, FILE *out)
{
    //Declare variables
    float subtotal = 0.0;
    float total = 0.0;
    float tax = 0.0;
    
    //Print command text
    fprintf(out, "Command: checkout\n");
    fprintf(out, "Comics in Purchase List\n");

    //Check if list is empty
    if (purchaseList->count == 0)
    {
        fprintf(out, "List is currently empty.\n");
    }

    else
    {
        //Iterate through purchaseList
        for (int i = 0; i < purchaseList->count; i++)
        {
            fprintf(out, "Comic Number: %d\n", i+1);
            display_comic(purchaseList->list[i],out);
            fprintf(out, "\n");

            //Add price to total if valid
            if (strcmp(purchaseList->list[i]->cost, "AR") != 0)
            {
                printf("%s\n", purchaseList->list[i]->cost + 1);
                subtotal += atof(purchaseList->list[i]->cost + 1);
            }
        }
    }

    //Calculate total after tax and tax
    total = subtotal * (1.05);
    tax = subtotal * (0.05);

    //Print info
    fprintf(out, " Subtotal:  $%.2f\n", subtotal );
    fprintf(out, "      Tax:  $%.2f\n", tax);
    fprintf(out, "    Total:  $%.2f\n", total);
}