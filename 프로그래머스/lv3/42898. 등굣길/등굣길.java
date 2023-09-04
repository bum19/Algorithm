class Solution {
    public static long[][] dp;
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        //dp 초기화.
        dp = new long[n+1][m+1];
        dp[1][1]= 1;
        //물웅덩이는 -1처리
        for(int[] puddle : puddles){
            dp[puddle[1]][puddle[0]] = -1; //물웅덩이 있는 dp[y][x]에 값 -1 넣기.
        }
        
        for(int i = 1; i <= n; i++){
            for(int j = 1 ; j <= m; j++){
                if(dp[i][j] == -1) continue;
                if(dp[i-1][j] != -1) dp[i][j] = (dp[i][j] + dp[i-1][j])%1000000007;
                if(dp[i][j-1] != -1) dp[i][j] = (dp[i][j] + dp[i][j-1])%1000000007;
            }
        }

        answer = (int)dp[n][m];
        
        return answer;
        
    }
}