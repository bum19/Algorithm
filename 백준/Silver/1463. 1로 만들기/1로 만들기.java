//
import java.util.*;
public class Main {
	
	public static final int INF = 1000001;
	public static int[] memo; //인덱스에서 1까지 만드는데 필요한 연산 횟수
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		memo = new int[n+1];
		Arrays.fill(memo, INF);
		memo[1] = 0;
		
		System.out.println(getCalCnt(n));

	}
	
	
	public static int getCalCnt(int n) {
		//1이되면 탈출
		if(n == 1) {
			return memo[1];
		}
		
		if(memo[n] == INF) { //calCnt[n]값이 적힌 적없다면, 3가지 경우의수중 가장 큰수
			if(n%3 == 0 && n/3 > 0 && memo[n] > 1 + getCalCnt(n/3)) { //n이 3으로 나누어떨어지고, n/3이 0보다 크다면
				memo[n]  = 1 + memo[n/3];

			}
			if(n%2 == 0 && n/2 > 0 && memo[n] > 1 + getCalCnt(n/2)) { //n이 2로 나누어떨어지고, n/2가 0보다 크다면 
				memo[n] = 1 + memo[n/2];
			}
			if(n-1 > 0 && memo[n] > 1 + getCalCnt(n-1)) {
				memo[n] = 1 + memo[n-1];
			}
		}
		
		return memo[n]; 
	}

}
