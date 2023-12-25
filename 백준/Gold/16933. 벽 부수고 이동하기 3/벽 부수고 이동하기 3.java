import java.io.*;
import java.util.*;
//현재 이동거리 홀/짝으로 낮밤 판단
//bfs 바탕으로, 부신횟수별 visited 설정하기.
public class Main{
	public static int n, m, k, minDist;
	public static int[][] map;
	public static boolean[][][] visited;
	public static int[] dy = {0,1,0,-1}; //우하좌상
	public static int[] dx = {1,0,-1,0}; //우하좌상
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n+1][m+1];
		visited = new boolean[n+1][m+1][k+1];
		
		for(int i = 1; i <= n; i++) {
			String tmp = br.readLine().trim();
			for(int j = 1; j <= m; j++) {
				map[i][j] = tmp.charAt(j-1) - '0';
			}
		}
		//input done
		
		minDist = -1;
		//bfs
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {1,1, 0, 1}); //y,x, breakWallCnt, dist;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int y = cur[0];
			int x = cur[1];
			int curBreakWallCnt = cur[2];
			int curDist = cur[3];
			
			if(y == n && x == m) {
				minDist = curDist;
				break;
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = y + dy[dir];
				int nx = x + dx[dir];
				if(ny <= 0 || nx <= 0 || ny > n || nx > m) continue; //out of index
				//no wall, not visited
				if(map[ny][nx] == 0 && !visited[ny][nx][curBreakWallCnt]) {
					visited[ny][nx][curBreakWallCnt] = true;
					q.add(new int[] {ny,nx, curBreakWallCnt, curDist + 1});
				}
				//yes wall, breakable, not visited
				if(map[ny][nx] == 1 && curBreakWallCnt + 1 <= k && !visited[ny][nx][curBreakWallCnt + 1]) {
					if((curDist & 1) == 1) { //day
						visited[ny][nx][curBreakWallCnt + 1] = true;
						q.add(new int[] {ny,nx,curBreakWallCnt+1,curDist +1});
					}
					else { //night
						q.add(new int[] {y,x,curBreakWallCnt,curDist +1});
					}
				}
			}
		}
		
		System.out.println(minDist);
	}
}