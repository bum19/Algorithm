// 재귀패턴 별찍기
#include <iostream>
#include <stdlib.h>
int count = 0;
void recursive_star(char** board,int n,int x,int y);
int main(void){
    int n,x=0,y=0;
    std::cin >> n;
     //malloc -> new
    char **board = (char **)malloc(sizeof(char*) * n);
    //더 효율적인 초기화방법
    for(int i=0;i<n;i++){
        board[i] = (char *)malloc(sizeof(char)*n);
    }
    //더 효율적인 초기화방법
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            board[i][j]='$';
        }
    }
    recursive_star(board,n,x,y);

    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            std::cout << board[i][j];
        }
        std::cout << std::endl;
    }

    for(int i=0;i<n;i++){
        free(board[i]);
    }
    free(board);

}

void recursive_star(char** board,int n,int x,int y){
    if(n>3){
        recursive_star(board,n/3,x,y);
        recursive_star(board,n/3,n/3+x,y);
        recursive_star(board,n/3,n*2/3+x,y);

        recursive_star(board,n/3,x,n/3+y);

        //가운데는무조건공백
        for(int i=x+n/3;i<x+n*2/3;i++){
            for(int j=y+n/3;j<y+n*2/3;j++){
                board[i][j]=' ';
            }
        }
        recursive_star(board,n/3,n*2/3+x,n/3+y);

        recursive_star(board,n/3,x,n*2/3+y);
        recursive_star(board,n/3,n/3+x,n*2/3+y);
        recursive_star(board,n/3,n*2/3+x,n*2/3+y);
    }

    else{
        for(int i=x;i<(x+n);i++){
            for(int j=y;j<(y+n);j++){
                board[i][j]='*';
                if( (i==(x+1)) && (j==(y+1)) ){
                    board[i][j] = ' ';
                }
            }
        }
    }
}