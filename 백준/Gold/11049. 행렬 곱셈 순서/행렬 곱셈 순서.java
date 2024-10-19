/*
 * 연쇄행렬최소곱셈알고리즘에 대해 참고해서 풀이 진행
 * dp[i][j] = i번째행렬부터 j번째 행렬까지 곱할때 필요한 최소 연산 개수
 */
import java.io.*;
import java.util.*;

public class Main {
	public static final int NOTVISIT = -1;
	public static final int INF = Integer.MAX_VALUE;
	public static int n;
	public static int[][] matrix;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		matrix = new int[n][2];
		dp = new int[n][n];
		
		for(int i = 0; i < n;i++) {
			Arrays.fill(dp[i],NOTVISIT);
		}
		
		for(int i = 0; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			matrix[i][0] = r;
			matrix[i][1] = c;
			dp[i][i] = 0;
		}
		
		//init
		for(int i = 0; i < n-1; i++) {
			dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
		}
		
		System.out.println(recur(0,n-1));
		
	}
	
	
	private static int recur(int start, int end) {
		if(dp[start][end] != NOTVISIT) {
			return dp[start][end];
		}
		
		dp[start][end] = INF;
		
		for(int i = 0; start+i+1 <= end; i++) {
			int tmp = recur(start, start+i) + recur(start+i+1,end) + matrix[start][0] * matrix[start+i][1] * matrix[end][1];
			if(dp[start][end] > tmp) {
				dp[start][end] = tmp;
			}
		}
		
		return dp[start][end];
	}
}
