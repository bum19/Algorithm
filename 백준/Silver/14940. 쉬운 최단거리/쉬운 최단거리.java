/*
 * 그냥 bfs할줄아세요? 문제
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,m;
	public static int[][] map;
	public static int[][] answer;
	public static int[] dy = {-1,0,1,0}; //상우하좌
	public static int[] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		answer = new int[n][m];
		for(int i = 0; i < n;  i++) {
			Arrays.fill(answer[i], -1);
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					answer[i][j] = 0;
					q.add(new int[] {i,j,0});
				}
				else if(map[i][j] == 0) {
					answer[i][j] = 0;
				}
			}
		}
		
		//bfs
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >=n || nx >= m || answer[ny][nx] != -1) continue;
				answer[ny][nx] = cur[2] + 1;
				q.add(new int[] {ny,nx,cur[2]+1});
			}
		}
		
		//출력
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(answer[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
}
