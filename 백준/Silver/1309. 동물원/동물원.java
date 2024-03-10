import java.io.*;
import java.util.*;
/*
 * dp[n] = n칸일때 가능한 경우의수
 * dp[n] = dp[n-2] * 3 + (dp[n-1] - dp[n-2]) * 2
 * 		 = dp[n-2] + dp[n-1] * 2
 * 
 */
public class Main {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		int[] dp = new int[n+1];
		dp[1] = 3;
		if(n > 1)
			dp[2] = 7;
		for(int i = 3; i <= n; i++) {
			dp[i] = (dp[i-2] + dp[i-1] * 2) % 9901;
		}
		
		System.out.println(dp[n]);
	}
}
