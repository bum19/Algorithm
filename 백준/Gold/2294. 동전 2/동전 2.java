import java.io.*;
import java.util.*;
public class Main {
	public static final int INF = 10001;
	public static int n,k;
	public static int[][] dp;
	public static int[] coins;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[n+1][k+1];
		coins = new int[n+1];
		
		coins[0] = 0; //dp를 위한 마진
		
		for(int i = 1; i <= n; i++) {
			coins[i] = Integer.parseInt(br.readLine().trim());
		}
		
		//INF로 초기화
		for(int i = 0; i <= n; i++) {
			Arrays.fill(dp[0], INF);
		}
		Arrays.fill(dp[0], INF);
		dp[0][0] = 0;
		
		for(int i = 1 ; i <= n; i++) {
			for(int j = 0; j <= k; j++) {
				if(j < coins[i])
					dp[i][j] = dp[i-1][j];
				else{
					dp[i][j] = Math.min(dp[i][j-coins[i]] + 1, dp[i-1][j]);
				}
			}
		}
		
//		for(int i = 0; i <= n; i++) {
//			for(int j = 0; j <= k; j++) {
//				if(dp[i][j] == INF) System.out.print("INF ");
//				else
//					System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		if(dp[n][k] == INF) System.out.println(-1);
		else System.out.println(dp[n][k]);
		
	}
}
