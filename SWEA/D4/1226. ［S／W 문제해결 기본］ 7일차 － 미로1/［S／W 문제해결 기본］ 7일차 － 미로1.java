//
import java.io.*;
import java.util.*;

public class Solution {
	public static int sy, sx, ey, ex;
	public static int[][] maze = new int[16][16];
	public static boolean[][] visited;
	public static Queue<int[]> q = new ArrayDeque<>();
	public static int[] dy = {-1,0,1,0} ; //상우하좌
	public static int[] dx = {0,1,0,-1} ; //상우하좌
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			br.readLine(); //testcase 숫자 입력. 따로 저장할 필요없다.
			
			visited = new boolean[16][16];
			
			for(int i = 0; i < 16; i++) {
				String str = br.readLine().trim();
				for(int j = 0; j < 16; j++) {
					maze[i][j] = str.charAt(j) - '0';
					if(maze[i][j] == 2) {
						sy = i;
						sx = j;
					}
					if(maze[i][j] == 3) {
						ey = i;
						ex = j;
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(bfs()).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int bfs() {
		q.clear();
		q.offer(new int[] {sy,sx});
		while(!q.isEmpty()) {
			int ny, nx;
			int[] cur = q.poll();
			if(cur[0] == ey && cur[1] == ex) return 1;
			
			for(int dir = 0; dir < 4; dir++) {
				ny = cur[0] + dy[dir];
				nx = cur[1] + dx[dir];
				if(ny <= 0 || nx <= 0 || ny >= 16 || nx >= 16 || visited[ny][nx] || maze[ny][nx] == 1) continue;
				visited[ny][nx] = true;
				q.offer(new int[] {ny,nx});
			}
		}
		
		return 0;
	}
}
