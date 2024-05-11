int func2(int a, int b) {
    int sum = 0;

    do {
        sum += a;
        b--;
    } while (b >= -1);

    return sum;
}