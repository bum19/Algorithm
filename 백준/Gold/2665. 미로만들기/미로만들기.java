//검은색만나면 무작정뚫고가기
//탐색 다끝나고나서, 가장 적은 visited true인거 확인.
//최대탐색수 n^2 * 2498(가능한검은색개수) 임
//n이 1일때 확인 안해줌.
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static boolean[][][] visited;
	public static int[][] map;
	public static final int WHITE = 1;
	public static final int BLACK = 0;
	
	public static int[] dy = {-1,0,1,0}; //urdl
	public static int[] dx = {0,1,0,-1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		if(n==1) {
			System.out.println(0);
			return;
		}
		
		map = new int[n][n];
		for(int i =0 ; i < n;  i++) {
			String input = br.readLine().trim();
			for(int j = 0; j < n; j++) {
				if(input.charAt(j)-'0' == WHITE) {
					map[i][j] = WHITE;
					
				}
				else {
					map[i][j] = BLACK;
				}
			}
		}
		
		
		bfs();
		
		for(int i = 0; i < 99; i++) {
			if(visited[n-1][n-1][i]) {
				System.out.println(i);
				return;
			}
		}
	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[n][n][99]; //흰색으로 바꿔서 갈수있는 검정색은 최대 98개다.
		q.add(new int[] {0,0,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir= 0; dir <4 ; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				if(ny < 0 || nx <0 || ny >=n || nx >= n) continue;
				if(visited[ny][nx][cur[2]]) continue; //현재 검은색 부신횟수로 간적 있으면 넘어가기
				
				visited[ny][nx][cur[2]] = true;
				
				if(map[ny][nx] == WHITE) {
					q.add(new int[] {ny,nx,cur[2]});
				}
				else {
					if (cur[2]+1 < 99) {
						q.add(new int[] {ny,nx,cur[2] + 1});
					}
				}
			}
		}
	}
}