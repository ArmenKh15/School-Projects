#include <stdio.h>

struct Comic
{
    char* date;
    char* code;
    char* publisher;
    char* title;
    char *cost;
};

struct Comic_List
{
    struct Comic** list;
    int size;
    int count;
};

void load(FILE *in, FILE *out, struct Comic_List *comicList);

void add_comic(struct Comic_List *comicList, struct Comic *comic);

void update_size(struct Comic_List *comicList);

void free_Comic(struct Comic *comic);

void free_ComicList(struct Comic_List *comicList);

void display(struct Comic_List *comicList, FILE *out);

void display_comic(struct Comic *comic, FILE *out);

void find(FILE *in, FILE *out, struct Comic_List *comicList);

void remove_comic(FILE *in, FILE *out, struct Comic_List *comicList);

void clear(FILE *out, struct Comic_List *comicList);

void save(FILE *in, FILE *out, struct Comic_List *comicList);

void buy(FILE *in, FILE *out, struct Comic_List *comicList, struct Comic_List *purchaseList);

void copy_comic(struct Comic *dest, struct Comic *src);

void checkout(struct Comic_List *purchaseList, FILE *out);
