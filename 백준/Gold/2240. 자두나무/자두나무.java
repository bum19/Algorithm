/*
 * dp[i][j] = i초에서 j번 위치를 바꿨을때 얻을수 있는 자두의 최댓값.
 * dp[i][j] = Math.max (dp[i-1][j], dp[i-1][j-1] + ( j-1에 해당하는 값에 대해 i~ n-1까지 남은 개수를 빼고, j에 해당하는 값에 대해 i~n-1까지 남은 개술를 더한값))
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int t,w;
	public static int[][] dp;
	public static int[] remain; //현재 칸부터 떨어질 남은 1번 자두나무의 개수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		t = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		remain = new int[t];
		
		for(int i = 0; i < t; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			remain[i] = num==1?1:0;
		}
		
		//입력값을 누적값으로 바꾸기
		for(int i = t-2; i>=0; i--) {
			remain[i] += remain[i+1]; 
		}
		
		dp = new int[t][w+1];
		
		//dp init
		for(int j = 0; j <= w; j++) {
			if(j % 2 == 0)
				dp[0][j] = remain[0];
			else
				dp[0][j] = t - remain[0];
			
		}
		
		for(int i = 0; i < t; i++) {
			dp[i][0] = remain[0];
		}
		
		for(int i = 1; i < t; i++) {
			for(int j = 1; j <= w; j++) {
				int plus, minus;
				if((j-1) %2 == 1)
					plus = remain[i];
				else
					plus = (t-i) - remain[i];
				minus = (t-i) - plus;
				
				dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-1] - minus + plus);
			}
		}
		
		int answer = Integer.MIN_VALUE;
		for(int i = 0; i <= w; i++) {
			answer = Math.max(answer, dp[t-1][i]);
		}
		System.out.println(answer);
	}
}
