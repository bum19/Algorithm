/*
 * bfs + visited를 벽부신 횟수 + 거리 만큼해서 적용
 * - bfs특성상 특정칸에 i번 부신채로 방문할 때, 더적은 거리만큼 이동해서 방문할 수 없으므로, minDist -> visited로 수정 
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		//input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n,m,k;
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		boolean[][] map = new boolean[n][m];
		boolean[][][] visited = new boolean[n][m][k+1];
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j <m ; j++) {
				if(tmp.charAt(j) == '0') {
					map[i][j] = true;
				}
				else {
					map[i][j] = false;
				}
			}
		}
		
		//sol
		System.out.println(bfs(map, visited));
		
	}
	
	private static int bfs(boolean[][] map, boolean[][][] visited) {
		int n = map.length;
		int m = map[0].length;
		int k = visited[0][0].length - 1;
		
		//queue for bfs
		Queue<int[]> q = new ArrayDeque<>(); // element : y,x, curDist, breakNum;
		if(0 == n-1 && 0 == m-1) {
			return 1;
		}
		visited[0][0][0] = true;
		q.add(new int[] {0,0,1,0});
		
		
		//bfs
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			int curDist = cur[2];
			int breakNum = cur[3];
//			System.out.println("("+ y+", "+ x + ") breakNum : "+breakNum+", curDist : " + curDist);
			for(int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				
				//there is a wall
				if(!map[ny][nx]) {
					//check breakNum
					if((breakNum < k) && !visited[ny][nx][breakNum+1]) {
						visited[ny][nx][breakNum+1] = true;
						q.offer(new int[] {ny,nx,curDist+1, breakNum+1});
					}
				}
				//there is not wall
				else {
					if(!visited[ny][nx][breakNum]) {
						if(ny == n-1 && nx == m-1) return curDist +1;
						visited[ny][nx][breakNum] = true;
						q.offer(new int[] {ny,nx,curDist+1,breakNum});
					}
				}
			}
		}
		
		return -1;
	}
}
