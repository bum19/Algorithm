/*
 * 가중치가 음수가없고, 최솟값을 찾는것이므로 오른쪽, 아래만 가능한다고 생각했으나, 0이있어서 불가능. dp 철회. 사실 0 아니어도 불가능
 * 다익스트라. 노드수 125*125, 간선수 125*125*4;
 */
import java.io.*;
import java.util.*;
public class Main{
	public static final int INF = 2501;
	public static int n;
	public static int[][] map;
	public static int[][] distance;

	public static int[] dy = {-1,0,1,0}; //up right down left
	public static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		int test_case = 1;
		while(n != 0) {
			//init and input
			map = new int[n][n];
			distance = new int[n][n];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dijk();
			
			sb.append("Problem ").append(test_case++).append(": ").append(distance[n-1][n-1]).append("\n");

			//n input
			n = Integer.parseInt(br.readLine());
		}
		
		System.out.println(sb);
	}
	
	private static void dijk() {
		distance = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(distance[i], INF);
		}
		
		distance[0][0] = map[0][0];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
		
		pq.add(new int[] {0,0, distance[0][0]});

		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			if(cur[2] > distance[cur[0]][cur[1]]) continue;
			if(cur[0] == n-1 && cur[1] == n-1) return;
			
			for(int dir = 0 ; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >=n || nx >= n) continue;
				
				if(distance[ny][nx] > distance[cur[0]][cur[1]] + map[ny][nx]) {
					distance[ny][nx] = distance[cur[0]][cur[1]] + map[ny][nx];
					pq.add(new int[] {ny,nx, distance[ny][nx]});
				}
			}
		}
	}
}
