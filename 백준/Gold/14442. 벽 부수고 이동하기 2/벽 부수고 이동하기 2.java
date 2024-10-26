/*
 * bfs + visited를 벽부신 횟수 + 거리 만큼해서 적용
 */
import java.io.*;
import java.util.*;
public class Main {
	public static final int INF = Integer.MAX_VALUE;
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
		int[][][] minDist = new int[n][m][k+1];
		
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
		
		//init
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j <m ;j++) {
				Arrays.fill(minDist[i][j], INF);
			}
		}
		
		//sol
		System.out.println(bfs(map, minDist));
		
	}
	
	private static int bfs(boolean[][] map, int[][][] minDist) {
		int n = map.length;
		int m = map[0].length;
		int k = minDist[0][0].length - 1;
		
		//queue for bfs
		Queue<int[]> q = new ArrayDeque<>(); // element : y,x, curDist, breakNum; 
		minDist[0][0][0] = 1;
		q.add(new int[] {0,0,1,0});
		
		
		//bfs
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			int curDist = cur[2];
			int breakNum = cur[3];
			for(int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				
				//there is a wall
				if(!map[ny][nx]) {
					//check breakNum
					if((breakNum < k) && (minDist[ny][nx][breakNum+1] > curDist+1)) {
						minDist[ny][nx][breakNum+1] = curDist+1;
						q.offer(new int[] {ny,nx,curDist+1, breakNum+1});
					}
				}
				//there is not wall
				else {
					if(minDist[ny][nx][breakNum] > curDist+1) {
						minDist[ny][nx][breakNum] = curDist+1;
						q.offer(new int[] {ny,nx,curDist+1,breakNum});
					}
				}
			}
		}
		
		//get answer
		int answer = INF;
		for(int i = 0; i <= k; i++) {
			if(answer > minDist[n-1][m-1][i]) {
				answer = minDist[n-1][m-1][i];
			}
		}
		
		return answer == INF?-1:answer;
	}
}
