#include <stdio.h>
#pragma warning(disable:4996)
int main(void) {
	int n;
	scanf("%d", &n);
	for (int i = n; i >= 1; i--)
	printf("%d\n", i);
	return 0;
}