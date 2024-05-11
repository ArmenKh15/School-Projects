/* 
 * CS:APP Data Lab 
 * 
 * <Armen Khachatryan armenk@vt.edu>
 * <Daniel Jagga djagga@vt.edu
 * 
 * bits.c - Source file with your solutions to the Lab.
 *          This is the file you will hand in to your instructor.
 *
 * WARNING: Do not include the <stdio.h> header; it confuses the dlc
 * compiler. You can still use printf for debugging without including
 * <stdio.h>, although you might get a compiler warning. In general,
 * it's not good practice to ignore compiler warnings, but in this
 * case it's OK.  
 */

#if 0
/*
 * Instructions to Students:
 *
 * STEP 1: Read the following instructions carefully.
 */

You will provide your solution to the Data Lab by
editing the collection of functions in this source file.

INTEGER CODING RULES:
 
  Replace the "return" statement in each function with one
  or more lines of C code that implements the function. Your code 
  must conform to the following style:
 
  int Funct(arg1, arg2, ...) {
      /* brief description of how your implementation works */
      int var1 = Expr1;
      ...
      int varM = ExprM;

      varJ = ExprJ;
      ...
      varN = ExprN;
      return ExprR;
  }

  Each "Expr" is an expression using ONLY the following:
  1. Integer constants 0 through 255 (0xFF), inclusive. You are
      not allowed to use big constants such as 0xffffffff.
  2. Function arguments and local variables (no global variables).
  3. Unary integer operations ! ~
  4. Binary integer operations & ^ | + << >>
    
  Some of the problems restrict the set of allowed operators even further.
  Each "Expr" may consist of multiple operators. You are not restricted to
  one operator per line.

  You are expressly forbidden to:
  1. Use any control constructs such as if, do, while, for, switch, etc.
  2. Define or use any macros.
  3. Define any additional functions in this file.
  4. Call any functions.
  5. Use any other operations, such as &&, ||, -, or ?:
  6. Use any form of casting.
  7. Use any data type other than int.  This implies that you
     cannot use arrays, structs, or unions.

 
  You may assume that your machine:
  1. Uses 2s complement, 32-bit representations of integers.
  2. Performs right shifts arithmetically.
  3. Has unpredictable behavior when shifting if the shift amount
     is less than 0 or greater than 31.


EXAMPLES OF ACCEPTABLE CODING STYLE:
  /*
   * pow2plus1 - returns 2^x + 1, where 0 <= x <= 31
   */
  int pow2plus1(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     return (1 << x) + 1;
  }

  /*
   * pow2plus4 - returns 2^x + 4, where 0 <= x <= 31
   */
  int pow2plus4(int x) {
     /* exploit ability of shifts to compute powers of 2 */
     int result = (1 << x);
     result += 4;
     return result;
  }



NOTES:
  1. Use the dlc (data lab checker) compiler (described in the handout) to 
     check the legality of your solutions.
  2. Each function has a maximum number of operations (integer, logical,
     or comparison) that you are allowed to use for your implementation
     of the function.  The max operator count is checked by dlc.
     Note that assignment ('=') is not counted; you may use as many of
     these as you want without penalty.
  3. Use the btest test harness to check your functions for correctness.
  4. The maximum number of ops for each function is given in the
     header comment for each function. If there are any inconsistencies 
     between the maximum ops in the writeup and in this file, consider
     this file the authoritative source.

/*
 * STEP 2: Modify the following functions according the coding rules.
 * 
 *   IMPORTANT. TO AVOID GRADING SURPRISES:
 *   1. Use the dlc compiler to check that your solutions conform
 *      to the coding rules.
 */


#endif

//1
/* 
 * bitXor - x^y using only ~ and & 
 *   Example: bitXor(4, 5) = 1
 *   Legal ops: ~ &
 *   Max ops: 14
 *   Rating: 1
 */
int bitXor(int x, int y) 
{
   int a = ~(x & y);
   int b = ~((~x) & (~y));

   return a & b;
}
//2
/* 
 * fitsBits - return 1 if x can be represented as an 
 *  n-bit, two's complement integer.
 *   1 <= n <= 32
 *   Examples: fitsBits(5,3) = 0, fitsBits(-4,3) = 1
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 15
 *   Rating: 2
 */
int fitsBits(int x, int n) {
// return !(x >> n);    <-------- WRONG

   // find the sign bit (of 32-bit int) while add the sign bit all the way
      // either all 111's or all 000's
   int sign = x >> 31;
   // shift by moving n - 1 positions till only the MSB is left while adding the sign bit (aka the MSB) to left
   int shifted = x >> (n + ~0);
   // XOR the shifted x against the sign bit to make sure all bits past nth digit
      // are all the same as the sign bit, if not XOR will show a 1 in the place
      // we want to make sure that all digits are the same past nth bit to show
      // that x fits in n bits
   int do_the_bits_fit = shifted ^ sign;
   // finaly, return with the lgoical NOT so that 1 means yes and 0 means no
   return !(do_the_bits_fit);
}
//3
/* 
 * rotateRight - Rotate x to the right by n
 *   Can assume that 0 <= n <= 31
 *   Examples: rotateRight(0x87654321,4) = 0x187654321
 *   Legal ops: ~ & ^ | + << >> !
 *   Max ops: 25
 *   Rating: 3 
 */
int rotateRight(int x, int n) {
   // this will make n 0's in the least significant bits and the rest 1's *edit* Make them all 1s except most sig, make that a 0
    int mask = ~(1 << 31);

    // Shift x to the left by 32-n bits
    int stored_bits = x << 32 + (~n + 1);
    
    //Clear most significant bits that were shifted by left shift
    mask >>= (n+((~1)+1));
    // shift x n bits
    x >>= n;
    //Apply mask and only keep relevant bits
    x = x & mask;
    

    // use the mask to add the digits that were copied and return *edit* Combine new x with most sig bits
    return (x | stored_bits);
}
//4
/*
 * bitReverse - Reverse bits in a 32-bit word
 *   Examples: bitReverse(0x80000002) = 0x40000001
 *             bitReverse(0x89ABCDEF) = 0xF7D3D591
 *   Legal ops: ! ~ & ^ | + << >>
 *   Max ops: 45
 *   Rating: 4
 */
int bitReverse(int x) 
{
// Define masks for swapping operations
int mask16 = (0xFF << 8) | 0xFF;     // Mask to isolate lower 16 bits
int mask8 = mask16 ^ (mask16 << 8);  // Mask for swapping bytes
int mask4 = mask8 ^ (mask8 << 4);    // Mask for swapping nibbles/half bytes
int mask2 = mask4 ^ (mask4 << 2);    // Mask for swapping groups of 2 bits
int mask1 = mask2 ^ (mask2 << 1);    // Mask for swapping individual bits

// Swap upper and lower 16 bits
x = x << 16 | ((x >> 16) & mask16);

// Swap even and odd bytes
x = (x & mask8) << 8 | ((x >> 8) & mask8);

// Swap even and odd nibbles
x = (x & mask4) << 4 | ((x >> 4) & mask4);

// Swap even and odd groups of 2 bits
x = (x & mask2) << 2 | ((x >> 2) & mask2);

// Swap even and odd individual bits
x = (x & mask1) << 1 | ((x >> 1) & mask1);

return x;
}
