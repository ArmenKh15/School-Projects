/* Testing Code */

#include <limits.h>
#include <math.h>

/* Routines used by floation point test code */

/* Convert from bit level representation to floating point number */
float u2f(unsigned u) {
  union {
    unsigned u;
    float f;
  } a;
  a.u = u;
  return a.f;
}

/* Convert from floating point number to bit-level representation */
unsigned f2u(float f) {
  union {
    unsigned u;
    float f;
  } a;
  a.f = f;
  return a.u;
}

//1
int test_bitXor(int x, int y)
{
  return x^y;
}
//2
int test_fitsBits(int x, int n)
{
  int TMin_n = -(1 << (n-1));
  // This convoluted way of generating TMax avoids overflow
  int TMax_n = (int) ((1u << (n-1)) - 1u);
  return x >= TMin_n && x <= TMax_n;
}
//3
int test_rotateRight(int x, int n) {
  unsigned u = (unsigned) x;
  int i;
  for (i = 0; i < n; i++) {
      unsigned lsb = (u & 1) << 31;
      unsigned rest = u >> 1;
      u = lsb | rest;
  }
  return (int) u;
}
//4
int test_bitReverse(int x) {
    int result = 0;
    int i;
    for (i = 0; i < 32; i++) {
 int bit = (x >> i) & 0x1;
 int pos = 31-i;
 result |= bit << pos;
    }
    return result;
}
