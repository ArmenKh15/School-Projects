#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "vector.h"
#include <math.h>

#define ARRAY_MAX 5


void read_array(FILE*, double*, int*);
void display_array(FILE* out, double* vec, int len);


int main( int argc, char* argv[] )
{
	if ( argc != 3 )
	{
		fprintf(stderr, "Usage: hw2_vector <input-file> <output-file>\n");
		exit(1);
	}
	
	FILE* in;
	in = fopen(argv[1], "r");
	FILE* out;
	out = fopen(argv[2], "w");

	char command[10];
	

	double* array1 = calloc(ARRAY_MAX, sizeof(double));
	double* array2 = calloc(ARRAY_MAX, sizeof(double));

	int len1 = -1;
	int len2 = -1;

	double mag1 = NAN;
	double mag2 = NAN;
	double dir1 = NAN;
	double dir2 = NAN;


	fscanf(in, "%s", command );


	while( !feof(in) )
	{
		if ( strcmp( command, "dot" ) == 0 )
		{
			fprintf(out, "Command: dot_product\n");
			
			read_array(in, array1, &len1);
			read_array(in, array2, &len2);
			
			display_array(out, array1, len1);
			display_array(out, array2, len2);

			double val = dot_product(array1, len1, array2, len2);
			
			fprintf(out, "\tThe dot product of the two vectors is %0.3lf\n\n", val);
			
			free(array1);
			free(array2);

			len1 = -1;
			len2 = -1;
			array1 = calloc(ARRAY_MAX, sizeof(double));
			array2 = calloc(ARRAY_MAX, sizeof(double));
				
		}
		else if ( strcmp( command, "angle" ) == 0 )
		{
			fprintf(out, "Command: angle_between\n");	

			read_array(in, array1, &len1);
			read_array(in, array2, &len2);

			display_array(out, array1, len1);
			display_array(out, array2, len2);
				
			double angle = angle_between(array1, len1, array2, len2);
			fprintf(out, "\tThe angle between the two vectors is %0.3lf degrees\n\n", angle);

			free(array1);
                        free(array2);

                        len1 = -1;
                        len2 = -1;
                        array1 = calloc(ARRAY_MAX, sizeof(double));
                        array2 = calloc(ARRAY_MAX, sizeof(double));
			
		}
		else if ( strcmp( command, "subtract" ) == 0 )
		{
			fprintf(out, "Command: subtract\n");

			double new_dir = NAN;
			double new_mag = NAN;
			
			fscanf(in, "%lf %lf %lf %lf", &dir1, &mag1, &dir2, &mag2);
			
			fprintf(out, "\tV1 Direction: %0.3lf\t V1 Magnitude: %0.3lf\n", dir1, mag1);
			fprintf(out, "\tV2 Direction: %0.3lf\t V2 Magnitude: %0.3lf\n", dir2, mag2);
			
			subtract(dir1, mag1, dir2, mag2, &new_dir, &new_mag);
			

			fprintf(out, "\tThe resultant vector has a direction of %0.3lf degrees and a magnitude of %0.3lf\n\n",
					new_dir, new_mag);
			
			dir1 = mag1 = dir2 = mag2 = new_dir = new_mag = NAN;
		}
		else
		{
			fprintf( stderr, "Unknown command: %s\n", command );
		}
		fscanf(in, "%s", command );

	}

	free(array1);
	free(array2);

	fclose(in);
	fclose(out);

	return 0;
}


void read_array(FILE* in, double* vec, int* len)
{
	int i = 0;
	char temp[10] = {0}; 

	fscanf(in, " <%s", temp);

	while (temp[strlen(temp) - 1] != '>') 
	{
		
		temp[strlen(temp) - 1] = '\0';
		vec[i] = atof(temp);
		i++;

		fscanf(in, "%s", temp);
	}

	fscanf(in, " ");
	temp[strlen(temp) - 1] = '\0';
	vec[i] = atof(temp);

	*len = ++i;
}


void display_array(FILE* out, double* vec, int len)
{
	fprintf(out, "\t<");
	for (int i = 0; i < len - 1; i++)
	{
		fprintf(out, "%0.3lf, ", vec[i]);
	}
	fprintf(out, "%0.3lf>\n", vec[len - 1]);
}
