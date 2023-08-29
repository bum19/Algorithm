//dp[idx][color] = min(dp[idx-1][diffColor1], dp[idx-1][diffColor2]);  //dp[idx][color] : idx번째 집을 color로 색칠할때의 최소비용.
import java.io.*;
import java.util.*;
public class Main {
	public static int[][] dp; // i번째 집에 r,g,b를 칠할 때의 비용.
	public static int n; //집의 개수
	public static int[][] costs; // i번째 집에 r,g,b 비용.

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		dp = new int[n][3];
		costs = new int[n][3];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			costs[i][0] = Integer.parseInt(st.nextToken());
			costs[i][1] = Integer.parseInt(st.nextToken());
			costs[i][2] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(Math.min(Math.min(calMinCost(n-1, 0), calMinCost(n-1, 1)), calMinCost(n-1,2)));
	}
	
	private static int calMinCost(int idx, int color) { //현재 idx를 color색으로 칠할때의 최소 비용.
		int preColor1, preColor2;
		if(idx == 0) {
			dp[idx][color] = costs[idx][color];
		}
			
		if(color == 0) {
			preColor1 = 1;
			preColor2 = 2;
		}
		else if(color == 1) {
			preColor1 = 0;
			preColor2 = 2;
		}
		else {
			preColor1 =  0;
			preColor2 =  1;
		}
		
		if(dp[idx][color] == 0) {
			dp[idx][color] = Math.min(calMinCost(idx-1,preColor1), calMinCost(idx-1,preColor2)) + costs[idx][color];
		}
		
		return dp[idx][color];
	}
}
