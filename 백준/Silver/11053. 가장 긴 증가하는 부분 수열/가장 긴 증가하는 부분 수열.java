import java.io.*;
import java.util.*;

/*
 * 수열 최대길이 N
 * 가능한 최대 숫자 A
 * dp[n][a] = n번째 숫자까지 포함했을때, 끝값이 a일때 최대 길이.
 * dp를 전부 채우는데 걸리는 시간 : O(NA)
 * dp에서 최대값을 찾는데 걸리는 시간 : O(NA)
 * 시간복잡도 : O(NA) 
 */
public class Main {
	public static int n;
	public static int[] a;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		a = new int[n+1]; //dp 시작을 위한 마진을 준다.
		int max = -1;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n ; i++) {
			a[i] = Integer.parseInt(st.nextToken());
			if(max < a[i]) max = a[i];
		}

		
		dp = new int[n+1][max+1];
		
		for(int i = 1; i <= n; i++) {
			int curMaxLen = 0;
			
			for(int val = 0; val <= max; val++) {
				dp[i][val] = dp[i-1][val];
				if(curMaxLen <= dp[i][val] && val < a[i]) curMaxLen = dp[i][val];
			}
			
			dp[i][a[i]] = Math.max(dp[i][a[i]], curMaxLen+1);
		}
		
		//최대길이 찾기
		int answer = 0;
		for(int i = 1; i <= n; i++) {
			if(answer < dp[n][a[i]]) answer = dp[n][a[i]]; 
		}
		
		System.out.println(answer);
	}
}
