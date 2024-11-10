/*
 * 뭔가 1차원 dp로도 있을거같은데 그냥 2차원dp로 품
 * dp[i][j] = i번째칸까지 오는데 얻은 최대점수. 이때 j는 연속된 계단수(값 1,2만 사용)
 * dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
 * dp[i][2] = dp[i-1][1] + stairs[i];  
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine().trim());
		
		int[] stairs = new int[n+1];
		int[][] dp = new int[n+1][3];
		
		for(int i = 1; i <= n; i++) {
			stairs[i] = Integer.parseInt(br.readLine().trim());
		}
		
		Arrays.fill(dp[0], 0);
		
		//init
		dp[1][1] = stairs[1];
		dp[1][2] = Integer.MIN_VALUE; //불가능한 값.
		
		//dp
		for(int i = 2; i <= n; i++) {
			dp[i][1] = Math.max(dp[i-2][1], dp[i-2][2]) + stairs[i];
			dp[i][2] = dp[i-1][1] + stairs[i];
		}		
		
		System.out.println(Math.max(dp[n][1], dp[n][2]));
	}
}
