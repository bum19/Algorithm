#include <string>
#include <vector>
#define MAX 20
using namespace std;
int answer = 0;
void back(vector<int> numbers,int target,int dep,int sum){
    if(dep == numbers.size()){
        if(sum == target){
             answer++;
            return;
        }
        else
            return;
    }
    back(numbers,target,dep+1,sum- numbers[dep]);
    back(numbers,target,dep+1,sum+ numbers[dep]);
}
int solution(vector<int> numbers, int target) {
    back(numbers,target,0,0);
    return answer;
}