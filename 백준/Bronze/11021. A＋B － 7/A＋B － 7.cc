#include <cstdio>

int main(void){
    int A,B,T,x=1;
    scanf("%d",&T);
    while(T--){
        scanf("%d %d",&A,&B);
        printf("Case #%d: %d\n",x++,A+B);
    }
    return 0;
}
