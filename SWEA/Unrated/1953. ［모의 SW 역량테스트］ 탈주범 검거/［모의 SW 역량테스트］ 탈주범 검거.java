//
import java.io.*;
import java.util.*;
public class Solution {
	public static int t, n, m, r, c, l, answerCnt;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dy = {-1, 0, 1, 0}; //상우하좌
	public static int[] dx = {0, 1, 0, -1}; //상우하좌
	public static boolean[][] tunnels = { {}, 
			{true,true,true,true},
			{true,false,true,false},
			{false,true,false,true},
			{true,true,false,false},
			{false,true,true,false},
			{false,false,true,true},
			{true,false,false,true}
	}; //터널별 상하좌우에 대해 뚫려있는지 여부
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			answerCnt = 0;
			
			map = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < m; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			bfs();
			
			sb.append("#").append(test_case).append(" ").append(answerCnt).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited[r][c] = true;
		q.offer(new int[] {r,c,1});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curTunnel = map[cur[0]][cur[1]];
			answerCnt++;
			
			if(cur[2] == l) continue;//l시간 만큼 왔으면 탐색 멈춤.
			
			for(int dir = 0; dir < 4; dir++) {
				
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx] || !tunnels[curTunnel][dir] || map[ny][nx] == 0 || !tunnels[map[ny][nx]][(dir+2)%4]) continue; //만약 못가는곳이면 가지않기
				visited[ny][nx] = true;
				q.offer(new int[] {ny,nx, cur[2]+1});
			}
		}
	}

}
