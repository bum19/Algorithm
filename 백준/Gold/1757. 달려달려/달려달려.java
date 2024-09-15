/*
 * 한번 쉬기시작했다면 다시 달릴수 없는 조건을 넣어야함.
 * dp[i][j][k] = i분에 j의 지침지수일때 직전에 쉬었는지 전에달렸는지에 따른 최대거리
 * 
 * dp[i][0][달림] = MIN
 * dp[i][0][쉼] = dp[i-1][0][쉼], dp[i-1][1][쉼]
 * dp[0][j][쉼/달] = MIN
 * 
 * dp[i][j][달림] = dp[i-1][j-1][달림] + 현재거리
 * dp[i][j][쉼] = Math.max(dp[i-1][j+1][달림], dp[i-1][j+1][쉼]);
 */
import java.io.*;
import java.util.*;
public class Main {
	public static final int MIN = -10000000;
	public static final int RUN = 0, REST = 1; 
	public static int n,m;
	public static int[] dist;
	public static int[][][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dist = new int[n]; //  dist[i] = i~ i+1분동안 달린거리
		dp = new int[n+1][m+1][2];
		for(int i = 0; i < n; i++) {
			dist[i] = Integer.parseInt(br.readLine().trim());
		}
		
		for(int i = 0; i<= m; i++) {
			Arrays.fill(dp[0][i], MIN);
		}
		
		Arrays.fill(dp[0][0], 0);
					
		for(int i = 1 ; i <= n; i++) {
			dp[i][0][REST] = Math.max(Math.max(dp[i-1][0][REST], dp[i-1][1][REST]), dp[i-1][1][RUN]);
			dp[i][0][RUN] = dp[i][0][REST]; // j가 0일때는 구분하지 않는다. 어차피 그다음에 뛸수 있으므로.
			for(int j = 1; j < m; j++) {
				dp[i][j][RUN] = dp[i-1][j-1][RUN] + dist[i-1];
				dp[i][j][REST] = Math.max(dp[i-1][j+1][REST], dp[i-1][j+1][RUN]);
			}
			dp[i][m][RUN] = dp[i-1][m-1][RUN]==MIN?MIN:dp[i-1][m-1][RUN]+dist[i-1];
			dp[i][m][REST] = Integer.MIN_VALUE;
		}
		
		System.out.println(dp[n][0][RUN]);
	}
}
