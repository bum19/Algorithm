//메모리	:
//실행시간	:
import java.io.*;
import java.util.*;
//최소경로를 찾을때는 dfs로 할경우 시간초과가 날수있다(완탐이므로). 무조건 bfs로 풀자.
public class Main {
	static int n, m, cnt;
	static int[][] maze;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	static Queue<int[]> q = new ArrayDeque<int[]>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maze = new int[n][m];
		for(int i = 0 ; i < n; i++) {
			String str = br.readLine().trim();
			for(int j = 0; j < m; j++) {
				maze[i][j] = str.charAt(j) - '0';
			}
		}
		
		bfs();
		System.out.println(cnt);
	}
	
	public static void bfs() {

		q.offer(new int[] {0,0,1});
		while(!q.isEmpty()) {	
			int[] current = q.poll();
			if(current[0] == n-1 && current[1] == m-1) {
				cnt = current[2];
				break;
			}
			for(int dir = 0; dir < 4; dir++) {
				int ny = current[0] + dy[dir];
				int nx = current[1] + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= m ||  maze[ny][nx] != 1) continue;
				maze[ny][nx] = 0;
				q.offer(new int[] {ny, nx, current[2]+1});
			}
		}
	}

}
