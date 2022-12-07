#include <cstdio>

//EOF 가 -1이므로, (c= getchar) +1 로 조건 달아도됨.
int main(void){
    char c;
    while( (c= getchar()) != EOF)
    putchar(c);
    return 0;
}