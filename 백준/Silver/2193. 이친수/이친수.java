//
import java.io.*;
import java.util.*;
//bj2193와 같은 문제
/**
f(n)은 n개를 나열했을 때 가능한 경우의수
z(n)은 f(n)중 0으로 끝나는 숫자배열
o(n)은 f(n)중 1로 끝나는 숫자배열
z(n) = z(n-1) + o(n-1) = f(n-1);
o(n) = z(n-1) = f(n-2);
f(n) = z(n) + o(n) = f(n-1) + f(n-2)
**/
public class Main {
	public static long[] dp = new long[91];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Arrays.fill(dp, -1);
		dp[0] = 0;
		dp[1] = 1; //아파트는 여기값 2로
		dp[2] = 1; //아파트는 여기 값 3으로
		
		System.out.println(f(n));
	}
	
	public static long f(int n) {
		if(n <= 2) return dp[n];
		
		if(n > 2 && dp[n] == -1) {
			dp[n] = f(n-1) + f(n-2);
		}
		return dp[n];
	}
}
