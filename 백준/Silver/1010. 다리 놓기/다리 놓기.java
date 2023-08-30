//
import java.io.*;
import java.util.*;
public class Main {
	public static int t, n, m; //문제는 mCn구하기. dp안써도 되긴함.
	public static int[][] dp; // dp[i][j]는 iCj
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		t = Integer.parseInt(br.readLine().trim());
	
		for(int test_case = 1;  test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			dp = new int[m+1][n+1];
			
			
			for(int i = 0 ; i <= m; i++) {
				for(int j = 0; j <= Math.min(i, n); j++) {
//					System.out.println("i : "+i+", j :"+j);
					if(j == 0 || i == j) dp[i][j] = 1;
					else dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
				}
			}
			
			sb.append(dp[m][n]).append("\n");
		}
		
		System.out.println(sb);
	}

}
