#include <math.h>
#include <stdio.h>

/**
 * These are the function signatures for the 3 required C functions in 
 * this assignment.
 */ 

double dot_product(const double vec1[], int v1_len, const double vec2[], int v2_len);

double angle_between(const double vec1[], int v1_len, const double vec2[], int v2_len);

void subtract(double v1_dir, double v1_mag, double v2_dir, double v2_mag, double* new_dir, double* new_mag);

