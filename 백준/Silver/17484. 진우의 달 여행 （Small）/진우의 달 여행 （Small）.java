/*
 * dp[i][j][dir] = 이전방향이 dir으로 i,j까지 오는데 필요한 최솟값
 * top down방식으로해야 중복탐색안함.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static final int INF = 1000001;
	public static final int UNVISITED = INF * 2 ;
	public static int n,m,answer;
	public static int[][] map;
	public static int[][][] dp;
	
	public static int[] dy = {1,1,1}; //우하,하, 좌하
	public static int[] dx = {-1,0,1};//우하, 하,좌하
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		dp = new int[n][m][3];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dp init
		for(int i = 0; i<n; i++){
			for(int j = 0 ; j < m; j++) {
				//첫줄은 제대로
				if(i == 0)
					Arrays.fill(dp[i][j], map[i][j]);
				else
					Arrays.fill(dp[i][j], UNVISITED);
			}
		}
		
		//맨밑애들로 top-down recur 시작
		answer = Integer.MAX_VALUE;
		for(int i = 0 ; i < m; i++) {
			for(int dir = 0; dir < 3; dir++) {
				int tmp = recur(n-1,i, dir);
				if(answer > tmp) answer = tmp;
			}
		}
		
		System.out.println(answer);
	}
	
	private static int recur(int y, int x, int dir) {
		if(dp[y][x][dir] != UNVISITED) { //갱신된적있다면 바로 리턴
			return dp[y][x][dir];
		}
		
		int minVal = INF;
		int py = y - dy[dir];
		int px = x - dx[dir];
		
		//현재방향으로 올 수 있는 이전 칸이 없다면
		if(py < 0 || px < 0 || py >= n || px >= m)
			return dp[y][x][dir] = INF;
		
		for(int i = 0; i < 3; i++) {
			if(dir == i) continue;
			int prevVal = recur(py, px, i);
			if(prevVal != INF && minVal > prevVal + map[y][x]) {
				minVal = prevVal + map[y][x];
			}
		}
		
		return dp[y][x][dir] = minVal;
	}
}
