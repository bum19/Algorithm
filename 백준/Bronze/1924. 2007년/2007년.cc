#include <iostream>
using namespace std;
int main(void){
    int x,y, days = 0;
    cin >> x >> y;
    days += y;
    while(--x){
        switch(x){
            case 1:case 3:case 5:case 7: case 8: case 10: case 12:
                days += 31;
                break;
            case 2:
                days += 28;
                break;
            default :       
                days += 30;     
                break;
        }
    }
    switch(days%7){
        case 1:
            cout << "MON"<< '\n';
            break;
        case 2:
            cout << "TUE" << '\n';
            break;
        case 3:
            cout << "WED" << '\n';
            break;
        case 4:
            cout << "THU" << '\n';
            break;
        case 5:
            cout << "FRI" << '\n';
            break;
        case 6:
            cout << "SAT" << '\n';
            break;
        case 0:
            cout << "SUN" << '\n';
            break;
    }
    return 0;
}