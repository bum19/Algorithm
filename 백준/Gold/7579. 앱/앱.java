import java.io.*;
import java.util.*;
/*
 *  dp[i][j] = i번째 앱까지 확인했을때, 비용 j로 얻을수 있는 최대 메모리
 *  dp[i][j] = max(dp[i-1][j-cost[i]] + mem[i], dp[i-1][j]);
 */
public class Main {
	
	public static int n, m;
	public static App[] apps;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		apps = new App[n+1]; //각 메모리크기, 코스트. 0번째는 마진값
		for(int i = 0; i <= n; i++) {
			apps[i] = new App();
		}
		
		//메모리크기 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			apps[i].mem = Integer.parseInt(st.nextToken());
		}
		
		//코스트입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			apps[i].cost = Integer.parseInt(st.nextToken());
		}
		
		//최대 코스트 계산
		int maxCost = 0;
		for(int i = 1; i <= n; i++) {
			maxCost += apps[i].cost;
		}
		
		dp = new int[n+1][maxCost+1]; //0번째는 마진값
		
		//dp 채우기
		for(int i = 0; i <= n ; i++) {
			for(int j = 0; j <= maxCost; j++) {
				if(i == 0) continue;
				if(j - apps[i].cost < 0) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j- apps[i].cost] + apps[i].mem, dp[i-1][j]);
				}
			}
		}

		//최소되는 가격 찾기
		for(int i = 0; i <= maxCost; i++) {
			if(dp[n][i] >= m) {
				System.out.println(i);
				return;
			}
		}
	}
	
	private static class App{
		int mem;
		int cost;
	}
}
