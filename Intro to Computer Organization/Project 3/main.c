#include <string.h>
#include <stdlib.h>
#include <stdio.h>

#include "comic.h"

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

    struct Comic_List comicList;
    comicList.size = 10;
    comicList.count = 0;
    comicList.list = (struct Comic **)malloc(comicList.size * sizeof(struct Comic *));

    struct Comic_List purchaseList;
    purchaseList.size = 10;
    purchaseList.count = 0;
    purchaseList.list = (struct Comic **)malloc(purchaseList.size * sizeof(struct Comic *));
    
    char cmd[10];

    while(!feof(in))
    {
        fscanf(in, "%s ", cmd);
        
        //Load
        if (cmd[0] == 'l')
        {
            load(in, out, &comicList);
        }

        //Display
        else if (cmd[0] == 'd')
        {
            display(&comicList, out);
        }

        //Find
        else if (cmd[0] == 'f')
        {
            find(in, out, &comicList);
        }

        //Remove
        else if (cmd[0] == 'r')
        {
            remove_comic(in, out, &comicList);
        }

        //checkout
        else if (cmd[0] == 'c' && cmd[1] == 'h')
        {
            checkout(&purchaseList, out);
        }

        //Clear
        else if (cmd[0] == 'c')
        {
            clear(out, &comicList);
        }
    
        //Save
        else if (cmd[0] == 's')
        {
            save(in, out, &comicList);
        }

        //Buy
        else if (cmd[0] == 'b')
        {
            buy(in, out, &comicList, &purchaseList);
        }
    }

    free_ComicList(&comicList);
    free_ComicList(&purchaseList);
    fclose(in);
    fclose(out);

}