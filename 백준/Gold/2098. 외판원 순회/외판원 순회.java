import java.io.*;
import java.util.*;
//dp[00001][0] = 현재 0번노드에서 , 0번 노드를 거쳤을떄,  마저 노드들을 방문한후 0번노드에 도달하는 최소값
//dp[11111][1] = 현재 1번노드에서, 01234번노드를 거쳤을떄, 마저 노드들을 방문한후 0번노드에 도달하는 최솟값.
public class Main {
	public static final int NOTVISIT = Integer.MAX_VALUE - 16000001; //아직 계산안함
	public static final int NOROUTE = 16000001; //경로 없음으로 계산됨
	public static int n;
	public static int[][] adj;
	public static int[][] dp;
	public static void main(String[] args) throws IOException{	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		adj = new int[n][n];
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j ++) {
				adj[i][j] = Integer.parseInt(st.nextToken());
				if(adj[i][j] == 0 ) adj[i][j] = NOROUTE;
			}
		}
		
		//init
		dp = new int[1 << n][n];
		for(int i = 0 ; i < (1 <<n); i++) {
			Arrays.fill(dp[i], NOTVISIT);
		}
		
		for(int i = 1; i < n; i++) {
			dp[(1 << n) - 1][i] = adj[i][0];
		}
		
		System.out.println(recur(1 , 0));
	}
	
	private static int recur(int visit, int cur) {
		if(visit == (1 << n) - 1) return adj[cur][0];
		
		if(dp[visit][cur] != NOTVISIT) {
			return dp[visit][cur];
		}
		
		for(int i = 1; i < n; i++) {
			if( (visit & (1 << i)) == 0 && adj[cur][i] != NOROUTE) { //방문한적 없다면
				dp[visit][cur] = Math.min(dp[visit][cur], recur((visit | (1<<i)), i) + adj[cur][i]);
			}
			
		}
		//만약 NOTVISIT, NOROUTE값을 하나로 퉁치면, 이 for문작업이끝나고도, 같은값이 들어있어서, 위에 있는 if절에 안걸리고 다시 계싼하는 경우가 생길 수 있따.

		return dp[visit][cur];
	}
}
