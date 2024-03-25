import java.io.*;
import java.util.*;
/*
    dp[i] = i일에 끝날떄, 가장 최대수익

    1. 상담을 끝나는 일수로 정렬을한다.
    2. 그다음 첫번째부터 탐색시작
        ㄱ. 마지막탐색 위치~ 현재위치까지 갱신
        ㄴ. dp[i] = min(dp[i], dp[i-arr[i].T]+ arr[i].P);
*/
public class Main {
    public static PriorityQueue<Consult> pq;
    public static int n, lastDay, curDay;
    public static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<Consult>((c1,c2)->{
            return Integer.compare(c1.start+c1.time, c2.start+c2.time);
        });

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            pq.add(new Consult(i, time, profit));
        }
        
        dp = new int[n+2]; //n일차에 1일치 일하는거까지 ok, 즉 dp[n+1]까지가능
        Arrays.fill(dp,-1);
        dp[0] = 0;
        lastDay = 0;
        curDay = 0;
        while(!pq.isEmpty()){
            Consult curConsult = pq.poll();
            curDay = curConsult.start + curConsult.time;
            //퇴사이후는 고려 x
            if(curDay > n+1){
                //lastDay~ n+1까지 갱신
                for(int i = lastDay +1; i <=n+1; i++){
                    dp[i] = dp[lastDay];
                }
                break;
            }
            
            //lastDay~ curDay까지 갱신
            for(int i = lastDay + 1; i <= curDay; i++){
                dp[i] = dp[lastDay];
            }

            //curDay갱신
            dp[curDay] = Math.max(dp[curDay], dp[curConsult.start] + curConsult.profit);
            lastDay = curDay;

            // System.out.println(Arrays.toString(dp));
        }

        System.out.println(dp[n+1]);
    }

    public static class Consult{
        int start;
        int time;
        int profit;
        public Consult(int start, int time, int profit){
            this.start = start;
            this.time = time;
            this.profit = profit;
        }
    }
}
