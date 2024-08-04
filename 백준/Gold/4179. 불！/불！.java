import java.io.*;
import java.util.*;

public class Main {
	
	public static char[][] maze;
	public static int r,c;
	
	public static int[] dy = {-1,0,1,0}; //상우하좌
	public static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args )throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		maze = new char[r][c];
		List<int[]> baseFire = new ArrayList<>();
		int[] jStart = null;
		
		for(int i = 0; i < r; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < c ; j++){
				maze[i][j] = tmp.charAt(j);
				if(maze[i][j] == 'F') baseFire.add(new int[]{i,j});
				if(maze[i][j] == 'J') jStart = new int[]{i,j};
			}
		}
		
		int time = bfs(jStart,baseFire);
		if(time == -1) {
			System.out.println("IMPOSSIBLE");
		}
		else {
			System.out.println(time);
		}
	}
	
	private static int bfs(int[] jStart, List<int[]> baseFire) {
		Queue<int[]> q = new ArrayDeque<>(); //y,x, time
		
		q.add(new int[] {jStart[0],jStart[1],0});
		for(int[] fire : baseFire) {
			q.add(new int[] {fire[0],fire[1], 0});
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir <4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(maze[cur[0]][cur[1]] == 'J') {
					if(ny < 0 || nx < 0 || ny>=r || nx >= c) {
						return cur[2] + 1;
					}
					if(maze[ny][nx] == '.') {
						maze[ny][nx] = 'J';
						q.add(new int[] {ny,nx,cur[2]+1});
					}
				}
				else {
					if(ny < 0 || nx < 0 || ny >= r || nx >= c || maze[ny][nx] == '#' || maze[ny][nx] == 'F') continue;
					maze[ny][nx] = 'F';
					q.add(new int[] {ny,nx,cur[2]+1});
				}
			}
		}
		
		return -1;
	}
}
