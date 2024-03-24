import java.io.*;
import java.util.*;
/*
 * 그냥 dfs, bfs완탐시 터짐.
 * dp로 저장하면서 진행함
 * 
 * dp[k][0] = k번째까지 탐색했을때, k를 안고른 경우 최댓값
 * dp[k][1] = k번쨰까지 탐색했을떄, k를 고르고, k-1를 안 고른 경우 최댓값
 * dp[k][2] = k번째까지 탐색했을때, k를 고르고, k-1도 고른 경우 최댓값
 * 
 * 
 */
public class Main {
	public static int n;
	public static int[][] dp;
	public static int[] wine;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		dp = new int[n][3];
		wine = new int[n];
		
		for(int i = 0 ; i < n; i++) {
			wine[i] = Integer.parseInt(br.readLine().trim());
		}
		
		//초기화
		dp[0][0] = 0;
		dp[0][1] = wine[0];
		dp[0][2] = -1; //안쓰이는 값.
		for(int i = 1; i < n; i++) {
			dp[i][0] = Math.max(Math.max(dp[i-1][0], dp[i-1][1]), dp[i-1][2]);
			dp[i][1] = dp[i-1][0] + wine[i];
			dp[i][2] = dp[i-1][1] + wine[i];
		}
		
		//최종 답
		int answer =  Math.max(Math.max(dp[n-1][0], dp[n-1][1]), dp[n-1][2]);
		
		System.out.println(answer);
	}
}
