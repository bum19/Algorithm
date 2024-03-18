import java.io.*;
import java.util.*;
/*
 * dp[i] = i번째수열까지 중 가장 긴 부분 수열의 길이
 * dp[i] = Math.max(dp[k] + 1),  0 <= k < i,  arr[k] < arr[i] 
 * 
 * 기존에 내가 푼방식은, 한번 정렬을한뒤, 갱신하는 느낌 dp[i]를 덮어쓰는게 아닌, 다음 행에 쓰는 느낌.
 * 거기에 존재하는 값만 인덱스에 넣은게 아니라, 죄다 넣음
 * 보편적인 풀이 방법이라 생각함. 
 */
public class Main {
	public static int n;
	public static int[] a;
	public static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		a = new int[n];
		dp = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
//		현재 위치보다 이전 위치면서, 현재 위치의 값보다 적은 값을 가진 곳의 길이.		
		for(int loc = 0; loc < n; loc++) {
			int val = a[loc];
			int maxLen = 0;
			for(int i = 0; i < loc; i++) {
				if(a[i] < val && dp[i] > maxLen) maxLen = dp[i];
			}
			dp[loc] = maxLen+1;
		}
		
		int answer = 0;
		for(int i = 0; i < n; i++) {
			if(answer < dp[i]) answer= dp[i];
		}
		
		System.out.println(answer);

	}
}
