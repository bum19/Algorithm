import java.io.*;
import java.util.*;
/*
 * 앞에서부터 각 차레마다 증가하는 부분수열 최대길이구하고(LIS)
 * 뒤에서부터 각 차례마다 증가하는 부분수열 최대길이구한뒤(LIS)
 * 둘의 합 최대인 값 출력
 */
public class Main {
	public static int n;
	public static int[] arr;
	public static int[] dp;
	public static int[] reverseDp;
	
	public static int answer;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		arr = new int[n];
		dp = new int[n];
		reverseDp = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		//dp
		for(int i = 0; i < n; i++) {
			int tmpMaxLen = 0;
			for(int j = 0; j < i; j++) {
				if(dp[j] > tmpMaxLen && arr[j] < arr[i]) tmpMaxLen = dp[j];
			}
			dp[i] = tmpMaxLen + 1;
		}
		
		//reversedp
		for(int i = n-1; i >= 0; i--) {
			int tmpMaxLen = 0;
			for(int j = n-1; j > i; j--) {
				if(reverseDp[j] > tmpMaxLen && arr[j] < arr[i]) tmpMaxLen = reverseDp[j];
			}
			reverseDp[i] = tmpMaxLen + 1;
		}
		
		answer = 0;
		for(int i = 0; i <n; i++) {
			if(answer < dp[i]+reverseDp[i]) answer = dp[i]+ reverseDp[i]-1; 
		}
		
		System.out.println(answer);
	}
	
}
