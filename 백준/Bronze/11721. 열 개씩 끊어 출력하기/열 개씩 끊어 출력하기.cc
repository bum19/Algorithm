#include <cstdio>

int main(void){
    char c;
    int count=0;
    while((c = getchar()) != EOF){
        putchar(c);
        count++;
        if(count%10==0)
            putchar('\n');
    }
    return 0;
}