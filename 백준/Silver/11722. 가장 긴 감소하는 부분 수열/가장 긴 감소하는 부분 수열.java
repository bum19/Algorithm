/*
 * LIS 감소 버전
 * dp[i] = i번째에서 끝나는 가장 긴 감소하는 부분수열
 * dp[i] = dp[1~(i-1)]중 가장길면서, arr[0~(i-1)]이 arr[0~(i-1)]보다 큰수.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int[] arr;
	public static int[] dp;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		dp = new int[n];
		dp[0] = 1;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			int longest = 0;
			for(int j = 0; j <= i-1; j++) {
				if(longest < dp[j] && arr[j] > arr[i]) {
					longest = dp[j];
				}
				dp[i] = longest + 1;
			}
		}
		
		int answer = 1;
		for(int i = 0;  i < n ; i++) {
			if(answer < dp[i]) answer = dp[i];
		}
		
		System.out.println(answer);
	}
	
	
}
