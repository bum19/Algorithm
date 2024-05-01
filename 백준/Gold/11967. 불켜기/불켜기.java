import java.io.*;
import java.util.*;
/*
 * 불킬떄마다 불킨 칸에 대해 확인작업하기
 */
public class Main {
	
	public static int n, m;
	public static boolean[][] isVisited;
	public static boolean[][] isOn;
	public static List<int[]>[][] switches; //각 방별 스위치리스트	
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		isVisited = new boolean[n+1][n+1]; //인덱스 1부터시작
		isOn = new boolean[n+1][n+1];
		switches = new List[n+1][n+1];
		
		for(int i = 0; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if(switches[x][y] == null) {
				switches[x][y] = new ArrayList<>();
			}
			switches[x][y].add(new int[]{a,b});
		} //input done
		
		bfs();
		
		//불켜진 방 세기
		int answer = 0;
		for(int i = 1; i <=n; i++) {
			for(int j = 1; j <= n; j++) {
				if(isOn[i][j]) {
					answer++;
				}
			}
		}
		
		System.out.println(answer);
	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		isOn[1][1] = true;
		isVisited[1][1] = true;
		q.add(new int[] {1,1});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			//1. 스위치로 불켜고, 불을 킨 곳에 갈 수 있는지 확인
			if(switches[cur[0]][cur[1]] != null) {
				for(int[] sc : switches[cur[0]][cur[1]]) {
					//불켜기
					isOn[sc[0]][sc[1]] = true;
					
					//근처까지 경로가 열려있는지 확인
					boolean isAccessible = false;
					for(int dir = 0; dir < 4; dir++) {
						int ny = sc[0] + dy[dir];
						int nx = sc[1] + dx[dir];
						if(ny > 0 && nx > 0 && ny <= n && nx <= n && isVisited[ny][nx]) {
							isAccessible = true;
							break;
						}
					}
					
					//근처까지 경로 열려있고, 방문한적 없으면, 방문하기
					if(isAccessible && !isVisited[sc[0]][sc[1]]) {
						isVisited[sc[0]][sc[1]] = true;
						q.add(new int[] {sc[0],sc[1]});
					}
				}
			}
			//2. 현재 위치에서 이동할수 있는곳 확인
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				//범위 밖이거나, 방문했거나, 불꺼져있으면 넘어가기
				if(ny <= 0 || nx <= 0 || ny > n || nx > n || isVisited[ny][nx] || !isOn[ny][nx]) continue;
				
				isVisited[ny][nx] = true;
				q.add(new int[] {ny, nx});
				
			}
		}
		
	}
}
