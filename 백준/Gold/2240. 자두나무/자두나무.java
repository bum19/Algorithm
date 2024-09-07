/*
 * dp[i][j] = i초까지 j번 위치를 바꿨을때 얻을수 있는 자두의 최댓값.
 * dp[i][j] = Math.max (dp[i-1][j] + 알맞응위치면1아니면0, dp[i-1][j-1] + 알맞응위치면1아니면0) 
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int t,w;
	public static int[][] dp;
	public static int[] tree; //1번 나무에서 열매가 떨어지는지 입력
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		t = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		tree = new int[t];
		
		for(int i = 0; i < t; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			tree[i] = num;
		}
		
		dp = new int[t][w+1];
		
		//dp init
		for(int change = 0; change <= w; change++) {
			if(change % 2 == 0)
				dp[0][change] = tree[0]==1?1:0;
			else
				dp[0][change] = tree[0]==2?1:0;
		}
		
		for(int sec = 1; sec < t; sec++) {
			dp[sec][0] = dp[sec-1][0] + (tree[sec]==1?1:0);
		}
		
		for(int i = 1; i < t; i++) {
			for(int j = 1; j <= w; j++) {
				//j 번 움직였을 때 위치.
				int myLoc = j%2==0?1:2;
				dp[i][j] = Math.max(dp[i-1][j] + (myLoc==tree[i]?1:0), dp[i-1][j-1] + (myLoc==tree[i]?1:0));
			}
		}
		
		int answer = Integer.MIN_VALUE;
		for(int i = 0; i <= w; i++) {
			answer = Math.max(answer, dp[t-1][i]);
		}
		System.out.println(answer);
	}
}
