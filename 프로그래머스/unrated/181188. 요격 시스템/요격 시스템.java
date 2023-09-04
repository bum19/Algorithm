import java.util.*;
class Solution {
    public static PriorityQueue<int[]> pq;
    public int solution(int[][] targets) {
        int answer = 0;
 

        pq = new PriorityQueue<int[]>( (a1, a2) ->{
            if(a1[0] == a2[0]){
                return Integer.compare(a1[1],a2[1]);
            }
            else{
                return Integer.compare(a1[0],a2[0]);
            }
        });
        
       // pq에 targets 넣기. 시간초과나면 그냥 바로 target을 조질것.
        for(int i = 0; i < targets.length; i++){
            
            pq.offer(targets[i]);
        }
        
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curS = cur[0];
            int curE = cur[1];
            while(!pq.isEmpty()){ //pq를 확인하면서, 현재 pq에있는 ㄴqhek
                if(curE > pq.peek()[0]){ //현재값의 e보다 pq에있는값의 s가 더 작으면,
                    if(curE > pq.peek()[1]) //뽑으려는애의 e가 현재 e보다 작으면(범위가 더 짧으면)
                        curE = pq.poll()[1]; //pq에있는값을 뽑고, curE값 을 갱신해준다. 동시에 겹쳐야하므로
                    else
                        pq.poll();
                }
                else break;
            }
            answer++;
        }

        
        return answer;
    }
}