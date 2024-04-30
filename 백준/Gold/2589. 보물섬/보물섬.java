import java.io.*;
import java.util.*;
public class Main {
	public static int n,m;
	public static char[][] map;
	public static int maxDist;
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < m; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		
		//모든 정점에서 bfs탐색
		maxDist = -1;
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 'W') continue;
				bfs(i, j);
			}
		}
		
		System.out.println(maxDist);
		
	}
	
	public static void bfs(int y, int x) {
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[n][m];
		visited[y][x] = true;
		q.add(new int[] {y,x,0});
		
		while(!q.isEmpty()){
			int cur[] = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >=n || nx >=m || map[ny][nx] == 'W' || visited[ny][nx]) continue;
				visited[ny][nx] = true;
				if(maxDist < cur[2] + 1) maxDist = cur[2] + 1;
				q.add(new int[] {ny,nx,cur[2]+1});
			}
		}
	}
}
