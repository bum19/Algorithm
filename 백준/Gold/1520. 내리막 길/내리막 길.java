import java.io.*;
/*
 * 높이로 갈수있는지 여부를 판단하므로, 절대로 사이클은 발생할 수 없다. dp+ 재귀 적용
 */
import java.util.*;
public class Main {
	public static int m,n;
	public static int[][] map;
	public static int[][] dp; //dp[i][j] = i,j칸에서 목적지까지 이동가능한 경우의 수
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine().trim());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		map = new int[m][n];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dp 초기화
		dp = new int[m][n];
		for(int i =0 ; i < m; i++) {
			Arrays.fill(dp[i], -1);
		}
		dp[m-1][n-1] = 1;
		//dp 재귀 탐색
		System.out.println(recur(0,0));
	}
	
	public static int recur(int y, int x) {
		//이미 계산된 값 있으면
		if(dp[y][x] != -1) {
			return dp[y][x];
		}
		
		//4방순회하면서 가능한곳 더하기.
		int total = 0;
		for(int dir = 0; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(ny < 0 || nx < 0 || ny >= m || nx >= n || map[y][x] <= map[ny][nx]) continue;
			
			total += recur(ny, nx);
		}
		
		return dp[y][x] = total;
	}
}
