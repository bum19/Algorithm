import java.io.*;
import java.util.*;
/*
 *  dp[i][j] = i번째 앱까지 확인했을때, 비용 j로 얻을수 있는 최대 메모리
 *  dp[i][j] = max(dp[i-1][j-cost[i]] + mem[i], dp[i-1][j]);
 */
public class Main {
	
	public static int n, m;
	public static int[][] app;
	public static int[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		app = new int[n+1][2]; //각 메모리크기, 코스트. 0번째는 마진값
		
		//메모리크기 입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			app[i][0] = Integer.parseInt(st.nextToken());
		}
		
		//코스트입력
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			app[i][1] = Integer.parseInt(st.nextToken());
		}
		
		//최대 코스트 계산
		int maxCost = 0;
		for(int i = 1; i <= n; i++) {
			maxCost += app[i][1];
		}
		
		dp = new int[n+1][maxCost+1]; //0번째는 마진값
		
		//dp 채우기
		for(int i = 0; i <= n ; i++) {
			for(int j = 0; j <= maxCost; j++) {
//				System.out.println("done");
				if(i == 0) continue;
				
				if(j - app[i][1] < 0) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j- app[i][1]] + app[i][0], dp[i-1][j]);
				}
			}
		}

//		for(int i = 0; i <= n; i ++) {
//			for(int j = 0; j <= maxCost;j++) {
//				System.out.print(dp[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		//최소되는 가격 찾기
		for(int i = 0; i <= maxCost; i++) {
			if(dp[n][i] >= m) {
				System.out.println(i);
				return;
			}
		}
	}
}
