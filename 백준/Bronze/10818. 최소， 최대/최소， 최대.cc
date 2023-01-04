#include <iostream>
using namespace std;
int main(void){
    ios_base :: sync_with_stdio(false);
    int N, temp, max = -1000001, min = 1000001;
    cin >> N;
    while(N--){
        cin >> temp;
        if(temp > max)
            max = temp;
        if(temp < min)
            min = temp;
    }
    cout << min<< " " << max <<'\n';
    return 0;
}