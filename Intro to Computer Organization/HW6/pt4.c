int func4(int a) {
    int sum = 0;
    if (a < 0) {
        return 0;
    } else {
        for (int i = 0; i < a; i++) {
            sum += i;
        }
    }
    return sum;
}