/*
 * 1. sort each m/fm asc
 * 2. slide smaller gender over bigger gender
 * 3. O(m * (n-m))
 * > 연속되게 매핑되지 않음.
 * 
 * 힌트보고 dp접근
 * 1. sort
 * 2. use dp  (lcs 응용)
 * 3. dp[i][j] =   i > j :  min(dp[i-1][j], dp[i-1][j-1] + abs(b[i] - g[j]))
 * 				   i == j : dp[i-1][j-1] + abs(b[i] - g[j])
 * 				   i < j : min(dp[i][j-1] , dp[i-1][j-1] + abs(b[i] - g[j]))
 * 
 * * sort를 안하면 dp[i-1][j-1]에 b[i] + g[i] 한 값이 최선이라 장담할 수 없음 
 */

import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		

		
		st = new StringTokenizer(br.readLine());
		int[] boys = new int[n];
		for(int i = 0; i < n; i++) {
			 boys[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		int[] girls = new int[m];
		for(int i = 0; i < m; i++) {
			girls[i] = Integer.parseInt(st.nextToken());
		}
		

		Arrays.sort(boys);
		Arrays.sort(girls);
		
		System.out.println(sol(boys, girls));
	}
	
	private static int sol(int[] boys, int[] girls) {
		int n = boys.length;
		int m = girls.length;
		int[][] dp = new int[n][m];
		
		dp[0][0] = Math.abs(boys[0] - girls[0]);
		for(int i = 1 ; i < n; i++) {
			dp[i][0] = Math.min(dp[i-1][0] , Math.abs(boys[i] - girls[0])); 
		}
		for(int j = 1; j < m; j++) {
			dp[0][j] = Math.min(dp[0][j-1], Math.abs(boys[0] - girls[j]));
		}
		
		for(int i = 1; i < n ; i++) {
			for(int j = 1; j < m; j++) {
				if(i > j) {
					dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1] + Math.abs(boys[i] - girls[j]));
				}
				else if(i == j) {
					dp[i][j] = dp[i-1][j-1] + Math.abs(boys[i] - girls[j]);
				}
				else {
					dp[i][j] = Math.min(dp[i][j-1] , dp[i-1][j-1] + Math.abs(boys[i] - girls[j]));
				}
			}
		}
	
		return dp[n-1][m-1];
	}
	
	
}
