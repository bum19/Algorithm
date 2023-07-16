//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//외판원 순회
//순회므로 출발지점은 어디든 상관없다.
import java.util.*;
//dp값 0으로 초기화되잇을거임. 안되있나 확인한번만
//sc neverclosed
public class Main{
	public static final int NOROUTE = 16000001;
	public static final int NOTVISIT = NOROUTE*2;
	public static int n;
	public static int[][] w;
	public static int[][] dp;
	//visit는 현재까지 방문한정점목록, current는 현재 위치
	//tsp의 리턴값 : 현재위치에서 출발점으로 돌아오는 최소경로
	public static int tsp(int visit, int current) {
//		System.out.println("tspstart");
		//모든정점을 방문했을경우 
		if(visit == (1<<n)-1) {
			if(w[current][0] != 0) return w[current][0];
			//그냥 NOTVISIT과 값을똑같이 줘버리면 경로가없는경우를 중복탐색하게됨.
			else				   return NOROUTE;
		}
		
		//메모이제이션. dp값이 존재하는 경우 for문탐색하지않는다.
		if(dp[visit][current] != NOTVISIT) return dp[visit][current];
		
		//dfs탐색
		for(int i = 0; i< n; i++) {
			//만약 i노드 아직방문안했고, i로가는 경로가 있을경우 탐색 수행
			if( (visit & (1 << i)) == 0 && w[current][i] != 0 ){
				dp[visit][current] = Math.min(tsp(visit | (1<<i), i) + w[current][i], dp[visit][current]);
			}					
		}
		
		return dp[visit][current];
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		w = new int[n][n];
		dp = new int[1<<n][n];
		int result;
		for(int i =0; i<n;i++) {
			for(int j=0;j<n;j++) {
				w[i][j] = sc.nextInt();
			}
		}
		
		//dp배열 값 최대값으로 초기화
		for(int i = 0;i < dp.length;i++) {
			Arrays.fill(dp[i], NOTVISIT);
		}
		
		result = tsp(1,0);
		System.out.println(result);
	}
}