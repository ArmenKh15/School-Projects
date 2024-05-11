void func4(int x, int y) {
    int var1 = y;
    int var2 = x + 7;
    y = y | var2;
    var1 = var1 + y;
    x = x + 1;
}