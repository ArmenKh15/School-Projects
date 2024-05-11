#include "mesa.h"

struct Mesa find_mesa(FILE* in, unsigned int *numbers, unsigned int count, unsigned int *length, unsigned int *height) {
    struct Mesa result = {0, 0, 0, 0, false};

    // Read minimum length and height requirements
    fscanf(in, "%u", length);
    fscanf(in, "%u", height);

    // Iterate through the array of numbers
    for (unsigned int i = 0; i < count; i++) {
        unsigned int currL = 1;
        unsigned int currH = numbers[i];
        unsigned int currW = currL * currH;

        // Check if the current number forms a potential Mesa
        while (i + currL < count && (numbers[i + currL] <= currH && numbers[i + currL] >= currH)) {
            currH = numbers[i + currL];
            currW = currL * currH;

            // Update the largest valid Mesa found
            if (currW + currH > result.weight) {
                result.start = i;
                result.end = i + currL;
                result.height = currH;
                result.weight = currW + currH;
            }

            currL++;
        }

        // Check if the current potential Mesa meets the minimum requirements
        if (currL >= *length && currH >= *height) {
            result.valid = true;
        }
    }

    return result;
}

//Display mesa data
void display_mesa( FILE* out, struct Mesa m )
{
    fprintf(out, "     ");
    fprintf(out, "Start       End\tHeight    Weight\n");
    
    fprintf(out, " %9u%10u%10u%10u\n\n", m.start, m.end, m.height, m.weight);
}