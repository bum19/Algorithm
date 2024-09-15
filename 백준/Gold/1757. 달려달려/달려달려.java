/*
 * 진용이 풀이
 * dp[i][j] = i분에 j지침일때 최댓값.
 * 한번 쉬면 지침지수 0 될때까지 계속 쉬어야하므로, [i+j][0]값을 갱신해나감
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,m;
	public static int[] dist;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dist = new int[n];
		dp = new int[n+1][m+1];
		
		for(int i = 0; i < n; i++) {
			dist[i] = Integer.parseInt(br.readLine().trim());
		}
		
		dp[0][0] = 0;
		
		for(int i = 1;  i<= n; i++) {
			dp[i][0] = Math.max(dp[i][0], dp[i-1][0]);
			for(int j = 1; j <= m; j++) {
				dp[i][j] = dp[i-1][j-1] + dist[i-1];
				if(i + j <= n) {
					dp[i+j][0] = Math.max(dp[i][j], dp[i+j][0]);
				}
			}
		}
		
		System.out.println(dp[n][0]);
	}
}
