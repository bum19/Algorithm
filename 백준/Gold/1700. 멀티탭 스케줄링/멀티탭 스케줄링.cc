#include <iostream>
#include <vector>
using namespace std;

int N,K,answer;
vector<int> seq;
vector<int> plugged;
vector<int> multi_tab;
int main(void){
    int temp;
    cin >> N >> K;
    plugged.assign(K+1,0);
    for(int i=0;i<K;i++){
        cin >> temp;
        seq.push_back(temp);
    }

    for(int i=0;i<K;i++){
        if(!plugged[seq[i]]){
            if(multi_tab.size() == N){
                //꽂힌애 중에 더나중에나올놈or 다신안나올놈 뽑기
                pair<int,int> last = {-1,-1}; //멀티탭인덱스
                for(int j=0;j<N;j++){ //멀티탭꽂힌놈들 하나씩 돌면서 나중에 나올지 다신안나올지 체크
                    int distance = 0;
                    for(int k = i+1; k<K; k++){     //멀티탭에 꽂힌 제품별 얼마뒤에 나오는지 확인
                        if(multi_tab[j] == seq[k]){
                            break;
                        }
                        distance++;
                    }
                    if(distance > last.second){  //현재까지 탐색한 꽂힌 제품중 제일 늦게 나오면 갱신
                        last.first = j;
                        last.second = distance;
                    }
                }
                //멀티탭 탐색후 멀티탭인덱스, 가장 늦게나오는위치가 last에 기록되있어야함.

                //가장늦게나오는 제품 뽑음.(해당제품종류의 Plugged를 false로)
                plugged[multi_tab[last.first]] = false;
                multi_tab[last.first] = seq[i];
                plugged[seq[i]] = true;
                answer++;
            }
            else{
                multi_tab.push_back(seq[i]);
                plugged[seq[i]] = true;
            }
        }
    }
    cout << answer << endl;
    return 0;
}