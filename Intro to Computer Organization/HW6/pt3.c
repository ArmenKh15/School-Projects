void func3(int a, int b, int c) {
    int x = a;
    int y = b;
    int z = c;

    if (x <= y)
        y -= x;
    else if (y > z)
        z = y;
    else
        x = z + y;

}