//
import java.io.*;
import java.util.*;

//dp[n][0~9] = dp[n-1][인접한애들]의 총합.
public class Main {
//	public static int[][] num = new int[][]{{1,2,3},{4,5,6},{7,8,9},{0}};
	public static long[][] dp;

//	public static int[] dy = {0, -1,1,0,0}; //정지 상하좌우
//	public static int[] dx = {0, 0,0,-1,1}; //정지 상하좌우
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int test_case = 1; test_case <= t; test_case++) {
			int n = sc.nextInt();
			dp = new long[n + 1][10];

			// dp 초기값 세팅
			for (int i = 0; i < 10; i++) {
				dp[1][i] = 1;
			}

			// dp값세팅
			for (int i = 2; i <= n; i++) {
				dp[i][0] += dp[i - 1][7] % 1234567;
				dp[i][1] += dp[i - 1][2] % 1234567 + dp[i - 1][4] % 1234567;
				dp[i][2] += dp[i - 1][1] % 1234567 + dp[i - 1][3] % 1234567 + dp[i - 1][5] % 1234567;
				dp[i][3] += dp[i - 1][2] % 1234567 + dp[i - 1][6] % 1234567;
				dp[i][4] += dp[i - 1][1] % 1234567 + dp[i - 1][5] % 1234567 + dp[i - 1][7] % 1234567;
				dp[i][5] += dp[i - 1][2] % 1234567 + dp[i - 1][4] % 1234567 + dp[i - 1][6] % 1234567
						+ dp[i - 1][8] % 1234567;
				dp[i][6] += dp[i - 1][3] % 1234567 + dp[i - 1][5] % 1234567 + dp[i - 1][9] % 1234567;
				dp[i][7] += dp[i - 1][0] % 1234567 + dp[i - 1][4] % 1234567 + dp[i - 1][8] % 1234567;
				dp[i][8] += dp[i - 1][5] % 1234567 + dp[i - 1][7] % 1234567 + dp[i - 1][9] % 1234567;
				dp[i][9] += dp[i - 1][6] % 1234567 + dp[i - 1][8] % 1234567;

			}

			int answer = 0;
			for (int i = 0; i < 10; i++) {
				answer += dp[n][i];
				answer %= 1234567;
			}

			System.out.println(answer);
		}
	}
}
