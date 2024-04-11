import java.io.*;
import java.util.*;
/*
classic dp
*/
public class Main {
	public static int n;
	public static int[] p;
	public static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		p = new int[n+1];
		dp = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		//하나씩 포함시키면서 탐색
		for(int i = 1 ; i <= n; i++) {
			for(int j = 1; j<=n; j++) {
				if(j >= i) {
					dp[j] = Math.max(dp[j-i] + p[i], dp[j]);
				}
			}
		}
		
		System.out.println(dp[n]);
	}
}
