import java.io.*;
import java.util.*;
/*
 * dp[i] = i를 만드는데 가능한 방법
 * dp[i] = dp[i-1]에서 1원추가 + dp[i-2]에서 2원추가 + d[i-3]에서 3원추가.
 */
public class Main {
	public static int t, n;
	public static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine().trim());
			dp = new int[n+1];
			dp[0] = 1;
			dp[1] = 1;
			if(n >= 2) dp[2] = 2;
			for(int i = 3; i <= n; i++) {
				dp[i] = dp[i-1]+ dp[i-2] + dp[i-3];
			}
			sb.append(dp[n]).append("\n");
		}
		System.out.println(sb);
	}
}
