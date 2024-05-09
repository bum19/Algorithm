import java.io.*;
import java.util.*;
/*
 * 가능한 레벨 이분탐색(파라미터 서치). BFS응용
 * 
 * 타일뛰어넘을때 방향 전환 불가 조건이 있다.
 * 문제를 잘읽자!!!!!!!
 * 
 * 1. 한 블록을 여러번 뛰어넘을수 있다.
 * 2. 뛰어넘은곳이 레벨이 높을 수 있다.
 * 3. 시작위치가 반드시 현재레벨보다 낮아야한다.
 */
public class Main {
	public static int n,m, answer;
	public static int[][] barrack;
	public static boolean[][][] visited;
	
	public static int[] dy = {0,1,0,-1}; //우하좌상
	public static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		barrack = new int[n][m];
		
		int minLevel = Integer.MAX_VALUE;
		int maxLevel = Integer.MIN_VALUE;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				barrack[i][j] = Integer.parseInt(st.nextToken());
				if(minLevel > barrack[i][j]) minLevel = barrack[i][j];
				if(maxLevel < barrack[i][j]) maxLevel = barrack[i][j];
			}
		}
		//input done
		
		while(minLevel <= maxLevel) {
			int curLevel = (minLevel + maxLevel)/2;
			if(check(curLevel)){
				answer = curLevel;
				maxLevel = curLevel-1;
			}
			else {
				minLevel = curLevel+1;
			}
		}
		
		System.out.println(answer);
	}
	
	private static boolean check(int curLevel) {
		
		if(barrack[0][0] > curLevel) return false;
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[n][m][2];
		
		visited[0][0][0] = true;
		visited[0][0][1] = true;
		
		q.add(new int[] {0,0,0}); // y,x,특수장비사용카운트
		q.add(new int[] {0,0,1}); 
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			//도착하면 탈출 true 리턴
			if(cur[0] == n-1 && cur[1] == m-1) {
				return true;
			}
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				//범위 check
				if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
				
				//level이 높을때 갈수 있는지 확인
				if(barrack[ny][nx] > curLevel) {
					//현재 내 특수장비 카운트가 0이 아니면 불가능
					if(cur[2] != 0) continue;
					
					//뛰어넘은곳 갈 수 있는지 확인
					int nny = ny + dy[dir];
					int nnx = nx + dx[dir];
					//뛰어넘은곳이 갈 수 없는 곳이면 continue
					if(nny < 0 || nnx < 0 || nny >= n || nnx >= m || visited[nny][nnx][1] || barrack[nny][nnx] > curLevel) continue;
					visited[nny][nnx][1] = true;
					q.add(new int[] {nny,nnx,1});
				}
				
				//level이 낮을때
				if(barrack[ny][nx] <= curLevel) {
					//방문한적 없으면 현재 내상태로 넣기
					if(!visited[ny][nx][cur[2]]) {
						visited[ny][nx][cur[2]] = true;
						q.add(new int[] {ny,nx,cur[2]});
					}	
				}
			}
		}
		
		//도달 못했으면 false 리턴.
		return false;
	}

}
