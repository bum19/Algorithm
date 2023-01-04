//동전 그리디
#include <iostream>
//#include <vector>
#define MAX 10
using namespace std;
int N,K;
int coin[MAX];
int main(void){
    int temp,answer = 0;
    ios_base :: sync_with_stdio(false);
    cin >> N >> K;
    for(int i =0; i<N; i++){
        cin >> coin[i];
    }
    
    temp = K;
    for(int i= N-1; i>=0; i--){
        if(temp >= coin[i]){
            answer += temp/coin[i];
        }
        temp %= coin[i];
        if(temp == 0)
            break;
    }
    cout << answer << '\n';
    return 0;
}