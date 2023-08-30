//처음에 시간초과.
//399648kb, 832ms 큐에넣기전에 불가능한곳은 큐에 안넣는식으로 코드 수정했더니 통과 
//
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static int[][] home;
	public static int[] dy = {0,1,1}; //우, 하, 우하
	public static int[] dx = {1,0,1}; //우, 하, 우하
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		home = new int[n+1][n+1]; //인덱스 1부터시작
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					home[i][j] = 1;
				}
			}
		}//입력끝
		
//		bfs(); //파이프 끝지점, 파이프 상태 (0 : 가로, 1 ; 세로, 2 ; 대각선)
//		System.out.println(bfs());
		System.out.println(getCntByDP());
	}
	
	//완탐풀이
	private static int bfs(){
		int answer = 0;
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.offer(new int[] {1,2,0}); //  y좌표, x좌표, 파이프상태.
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[0] == n && cur[1] == n) { //n,n도착하면 해당 파이프로 하는 탐색종료.
				answer++;
			}
			else { //이동 반복.
				for(int dir = 0; dir < 3; dir++) {
					int ny = cur[0] + dy[dir];
					int nx = cur[1] + dx[dir];
					if(ny < 1 || nx < 1 || ny > n || nx > n || home[ny][nx] == 1) continue; //인덱스벗어나거나 탐색범위벗어나면 탐색안함.
					if( (cur[2] == 0 && dir == 1 ) || (cur[2] == 1 && dir == 0) ) continue; //설정할수 없는 방향이면 안감.
					if(  dir == 2 && (home[ny][nx-1] == 1 || home[ny-1][nx] == 1)) continue; //대각선방향이 대각선으로 이동하는데, 1이 껴있으면 안감.
					q.offer(new int[] {ny,nx,dir}); //저 모든 if지옥을 벗어낫다면 갈수있음
				}
			}
			
		}
		return answer;
	}
	
	//dp풀이
	private static int getCntByDP() {
		int[][][] dp = new int[n+1][n+1][3]; //dp[i][j][k]는 (i,j)칸에 k모양으로 들어오는 경우.
		dp[1][2][0] = 1;
		
		for(int i = 1 ; i <= n; i++) {
			for(int j = 1; j <= n; j++) { //열 정보는 3부터 보면된다. 열이 2까지는 전부 0임
				if(home[i][j] == 1) continue; //벽으로 들어가는 경우는 없다.
				dp[i][j][0] += dp[i][j-1][2] + dp[i][j-1][0];
				dp[i][j][1] += dp[i-1][j][2] + dp[i-1][j][1];
				if(home[i-1][j] != 1 && home[i][j-1] != 1) //대각선으로 들어올 경우, 옆에 1을 스치는지 확
					dp[i][j][2] += dp[i-1][j-1][2] + dp[i-1][j-1][1] + dp[i-1][j-1][0];
			}
		}
		
		return dp[n][n][0] + dp[n][n][1] + dp[n][n][2];
	}
}
