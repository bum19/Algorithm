/*
 * 가능한지 안한지 동전dp 를 응용해 풀기.
 * isPossible[i] = i원을 만들 수 있는지 탐색.
 * cnt[i] = i원을 만드는데, 현재 탐색중인 동전이 몇개 쓰였는지 저장함.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static boolean[] isPossible;
	public static int[] cnt;
	public static Coin[] coins;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int i = 0; i < 3; i++) {
			n = Integer.parseInt(br.readLine());
			coins = new Coin[n];
			int sum = 0;
			for(int j = 0; j < n ; j++) {
				st = new StringTokenizer(br.readLine());
				int price = Integer.parseInt(st.nextToken());
				int cnt = Integer.parseInt(st.nextToken());
				coins[j] = new Coin(price, cnt);
				sum += price * cnt;
			}
			
			if(sum%2 != 0) {
				sb.append(0).append('\n');
				continue;
			}
			
			isPossible = new boolean[sum/2 + 1];
			cnt = new int[sum/2 + 1];
			isPossible[0] = true;
			for(int j = 0; j < n; j++) {
				Arrays.fill(cnt, 0);
				for(int k = 0; k <= sum/2; k++) {
					if(isPossible[k]) continue; //이미 만들 수 있으면 넘어가기.	
					if(k - coins[j].price < 0) continue; // 현재 동전 종류보다 작은 가격이면 못만들기 때문에 넘어가기.	
					
					if(isPossible[k - coins[j].price] && cnt[k - coins[j].price] < coins[j].cnt) {
						isPossible[k] = true;
						cnt[k] = cnt[k - coins[j].price] + 1;
					}
				}
				
				if(isPossible[sum/2]) {
					break;
				}
			}
			
			if(isPossible[sum/2]) {
				sb.append(1).append('\n');
			}
			else {
				sb.append(0).append('\n');
			}
		}
		
		System.out.println(sb);
	}
	
	
	public static class Coin{
		int price;
		int cnt;
		public Coin(int price, int cnt) {
			this.price = price;
			this.cnt = cnt;
		}
	}
}
