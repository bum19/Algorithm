import java.io.*;
import java.util.*;
/*
 * dp[i][j] = i-1칸을 깔끔하게 다 채우고, i칸은 j만큼 채워져 있는 경우의 수
 * 그림을 그려가면서 점화식을 짰다.
 */
public class Main {
	public static int n;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		dp= new int[n][4];
		
		if(n >= 2) {
			dp[1][0] = 0;
			dp[1][1] = 2;
			dp[1][2] = 0;
			dp[1][3] = 3;
		}
		

		for(int i = 2; i <n; i++) {
			dp[i][0] = dp[i-1][3];
			dp[i][1] = dp[i-1][2];
			dp[i][2] = dp[i-1][3] * 2 + dp[i-1][1];
			dp[i][3] = dp[i-1][2] + dp[i-1][0];
		}
		
		
		System.out.println(dp[n-1][3]);
	}
}
