Project 3: Comic List Processor
Overview
In this program, you will process a list(s) of comics from a CSV file. We’ll be able to read them from a file, 
store them in an in-memory list, add to that list, maybe read more than 1 file. We will be able to “buy” comics 
and see how much that will cost. We can even save our in-memory list as a new CSV file that would allow us to reload that file into memory later on.

We will read and store the information in a struct called a Comic_List. A Comic_List is an array of Comic structs 
along with a maximum capacity for the array and a number of Comics currently in the array. 

Details
You will be creating 2 structs in this assignment. It is a list of the Comics

struct Comic_List
{
    struct Comic* list;
    int size;
    int count;
};
This is the one used in the sample solution. You do not need to use these exact field names, 
but you do have to store a dynamic array of the Comic struct, and you will need to keep track 
of the current number of comics in the list, count, and the current capacity for the list, size.

The Comic information needs to store the date, code, publisher, title, and cost. Here is the one from the sample solution:

struct Comic
{
    char* date;
    char* code;
    char* publisher;
    char* title;
    char *cost;
};
Again, you may choose different names for your fields but you need to store all of this information. 
The cost is a char* because some of the costs are not numbers but rather are listed as AR


Commands

load

The load command should read in each line of the CSV file and store it into a Comic. That Comic 
should then be put into the Comic_List representing all of the comics in the current CSV file.

You may not read the line and simply store the line into the struct. Each piece must be broken 
up into its own field in the struct.

buy

The buy command should add the comic at a given list position to another list containing all of 
the comics we wish to buy. Note: In most implementations, you will have 2 lists (not required, but recommended): 

one list representing all the comics in the loaded csv file
another list representing just the comics you wish to buy
If load is called multiple times before clear, you will just keep increasing the size of the list. 
Consider whether you should use a deep copy or a shallow copy when adding to the purchase list.

checkout

The checkout command will purchase all of the comics in the list of comics we wish to buy. They should 
be printed along with a receipt with the subtotal, tax (5%) and total after tax. Note that this command 
does not affect any comics loaded into the list representing the current csv file; it o The purchase list 
will be empty after this command runs. Comics with "AR" listed as the price will still be printed in the 
output file but will not be added to the subtotal of the purchase.

display

The display command will display or output each comic in turn from the list into the output file. See the 
example output for how you are to display the comic. Be sure to reference how to print both when the list 
is empty and when the list contains entries.

save

The save command will write the current list of comics into the file that is given. The format should match 
the format of the original CSV file. You should be able to load, then save, and then load the newly created 
file without a difference.

clear

This empties the list. Be careful to make sure you don’t lose memory. We will be checking Valgrind reports.

find

If the given index is valid, then you will display the information about the comic at the given index position.

remove

If the given index is valid, then the comic at that index will be removed and the comics at indices that are 
higher than the given index will all be shifted down 1 index position. Be careful to clean up your memory.

NOTE: C already has a function called remove(), so it will complain if you just try to name the function remove(). 
You will need to call it something else for your code to compile without warnings.
