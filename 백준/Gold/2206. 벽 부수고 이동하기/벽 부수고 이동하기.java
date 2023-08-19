//113792kb, 732ms
import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static int[][] board;		//탐색할 맵
	public static boolean[][][] visited;//해당 칸 탐색여부. [i][j][0] : (i,j)칸을 벽뚫은 적 없이 방문한적 있는지 여부. [i][j][1] : (i,j)칸을 벽뚫은 적 있이 방문한적 있는지 여부. 
	public static int[] dy = { -1, 1, 0, 0 }; //상하좌우
	public static int[] dx = { 0, 0, -1, 1 }; //상하좌우
	public static Queue<int[]> q = new ArrayDeque<int[]>(); //bfs용 큐

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		visited = new boolean[n][m][2];
		for (int i = 0; i < n; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < m; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}
		//입력끝 

		//bfs 시작
		System.out.println(bfs(0,0));
	}

	public static int bfs(int sy, int sx) { //bfs. 목표에 도달할 경우 해당 길이 리턴. 도달못할경우 -1 리턴
		int ny, nx, wallCnt;	//다음 탐색할 좌표, 벽만난 횟수를 저장하는 임시변수
		q.clear();
		visited[sy][sx][0] = true;	//0,0은 시작점이므로 방문처리
		visited[sy][sx][1] = true;	//0,0은 시작점이므로 방문처리
		q.offer(new int[] {sy, sx, 1 , 0}); //각각 y좌표, x좌표, 현재경로 길이, 현재 벽만난 횟수

		while (!q.isEmpty()) {
			int[] current = q.poll();
			if(current[0] == n - 1 && current[1] == m - 1) return current[2]; //도착했으면 끝

			for (int dir = 0; dir < 4; dir++) {	//4방탐색
				ny = current[0] + dy[dir];
				nx = current[1] + dx[dir];
				wallCnt = current[3];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx][wallCnt]) continue; //인덱스를 벗어나거나, 현재 상태로 해당칸 방문한적이 있다면 탐색 중지.
			
				if(board[ny][nx] == 1) wallCnt = current[3]+1; //방문하려는 칸이 1이라면, 현재상태에서 벽을 만난횟수 1 증가.
				if(wallCnt == 2) continue;	//벽을 만난 횟수가 2가 된다면, 탐색 중지.
				
				visited[ny][nx][wallCnt] = true; //현재상태로 다음 방문칸 visited처리
				q.offer(new int[] { ny, nx, current[2] + 1, wallCnt}); //다음 탐색할 칸 큐에 넣기
			}
		}

		return -1; // 큐가 빌때까지 탐색했는데 n-1,m-1 도달 못했으면 -1 리턴
	}
}
