import java.io.*;
import java.util.*;
/*
 * LIS 응용.
 * 전체 개수 - LIS 수.
 */
public class Main {
	public static int n, lis;
	public static int[] arr;
	public static int[] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		arr = new int[n];
		dp = new int[n];
		for(int i = 0 ; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine().trim());
		}
		
		//LIS
		for(int i = 0; i < n; i++) {
			int maxLen = 0;
			for(int j = 0; j < i; j++) {
				if(arr[j] < arr[i] && maxLen < dp[j]) {
					maxLen = dp[j];
				}
			}
			dp[i] = maxLen + 1;
		}
		
		//LIS중 최댓값 찾기.
		lis = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			if(lis < dp[i]) lis = dp[i];
		}
	
		System.out.println(n-lis);
	}
}
