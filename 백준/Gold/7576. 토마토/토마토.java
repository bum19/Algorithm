import java.util.*;
import java.io.*;
public class Main {
	public static int n, m, unRipeTomato, curTime;
	public static int[][] box;
//	public static boolean[][] visited;
	public static List<int[]> ripeTomatoes;
	public static Queue<int[]> q;
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken()); //가로
		n = Integer.parseInt(st.nextToken()); //세로
		box = new int[n][m];
		ripeTomatoes = new ArrayList<int[]>();
		q = new ArrayDeque<int[]>();
		curTime = 0;
		for(int i =0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
				if(box[i][j] == 0) unRipeTomato++;
				if(box[i][j] == 1) ripeTomatoes.add(new int[] {i, j});
			}
		}
		//입력끝
		
		//bfs탐색
		for(int[] ripeTomato : ripeTomatoes) { //초기에 익은 토마토 위치 넣기
			q.offer(new int[] {ripeTomato[0], ripeTomato[1], 0});
		}
		
		//익히기 시작
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			curTime = cur[2]; //계속증가할것임.
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || box[ny][nx] != 0) continue; //안익은토마토가 아니면 탐색중단.
				box[ny][nx] = 1;
				unRipeTomato--;
				q.offer(new int[] {ny,nx, cur[2]+1});
			}
		}
		if(unRipeTomato != 0) System.out.println(-1);
		else System.out.println(curTime);
	}

}
