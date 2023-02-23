#include <iostream>
//#include <string> 백준서 string 변수 선언 되는지확인
#include <algorithm> // 백준서 sort 쓰이는지 확인
using namespace std;

int main(void){
    cin.tie(0);
    cout.tie(0);

    int sum=0;
    string N;

    cin >> N;
    sort(N.begin(),N.end(),greater<>());
    for(auto o: N){
        sum += o - '0';
    }
    if(sum%3 != 0 || N[N.length()-1] != '0' )
        cout << -1 << '\n';
    else{
        cout << N << '\n';
    }
    return 0;
}