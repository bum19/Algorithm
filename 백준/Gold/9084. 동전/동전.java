/*
 * 완탐으로 풀면 정확한 계산은 되지않으나, 터짐(백트래킹은 시간복잡도를 확 줄이진 않으므로)
 * dp접근
 * 동전사용을 하나씩 추가하면
 * dp[k] = dp[k] + dp[k-ci]; 
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[] price;
	public static int[] coins;
	public static int t, n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		t = Integer.parseInt(br.readLine().trim());
		price = new int[10001];
		
		for(int test_case = 1; test_case <=t; test_case++) {
			n = Integer.parseInt(br.readLine().trim());
			coins = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int target = Integer.parseInt(br.readLine().trim());
				
			Arrays.fill(price,0);
			sol(target);
			
			sb.append(price[target]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void sol(int target) {
		for(int i = 0; i < coins.length; i++) {
			int c = coins[i];
			price[c]++;
			for(int j = 0; j <= target; j++) {
				if(j >= c) price[j] += price[j-c];
			}
		}
	}
}
