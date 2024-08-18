/*
 * 그냥 완탐시 시 대략 O(2^1000 * 1000)
 * dp식을 세우고, 지각일수는 추후 반영했다.
 * A는 3연속오면 안됨.
 * L은 한번만 올수있음
 * 지각 고려시 오버플로우 발생. long형으로 한다.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static final int O = 0;
	public static final int A = 1;
	public static int n;
	public static long[][] dp; // dp[i][O] = i일이 O로 끝날때 가능한 출결정보 개수(지각은 고려 x)
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		if(n == 0) {
			System.out.println(0);
			return;
		}
		
		if(n == 1) {
			System.out.println(3);
			return;
		}
		
		dp = new long[n+1][2];
		
		dp[0][O] = 1; //0일때  출결정보값은 1로 해서 지각고려시 계산 용이하게 하고, dp[2][A]값이 2가 나오도록 세팅
		dp[0][A] = 0;
		dp[1][O] = 1;
		dp[1][A] = 1;
		
		for(int i = 2; i <= n; i++) {
			dp[i][O] = (dp[i-1][O] + dp[i-1][A]) % 1000000;
			dp[i][A] = ((dp[i-2][O] * 2) + dp[i-2][A]) % 1000000;
		}
		
		long answer = dp[n][O]+ dp[n][A];
		
		//지각고려. 지각을 1일에 했을떄 ~ n일에 했을때를 고려한다.
		for(int l = 1; l <=n; l++) {
			long left = dp[l-1][O] + dp[l-1][A];
			long right = dp[n-l][O] + dp[n-l][A];
			answer += left * right; //이때 오버플로우 발생가능.
			answer %= 1000000;
		}	
		System.out.println(answer);
	}
}
