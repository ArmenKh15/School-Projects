int func3(int x, int y, int z) {
    x = y - z;
    y = y + x;
    x = y + x;
    z = x + z;
    y = x;
    x = x << 3;
    return y;
}