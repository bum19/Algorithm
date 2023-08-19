
//
import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, cnt, minCnt;
	public static int[][] board;
	public static boolean[][][] visited;
	public static int[] dy = { -1, 1, 0, 0 };
	public static int[] dx = { 0, 0, -1, 1 };
	public static Queue<int[]> q = new ArrayDeque<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		visited = new boolean[n][m][2];
		minCnt = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			String str = br.readLine().trim();
			for (int j = 0; j < m; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}

		// 완탐
		System.out.println(bfs(0,0));
	}

	public static int bfs(int sy, int sx) {
		int ny, nx, wallCnt;
		q.clear();
		visited[sy][sx][0] = true;
		visited[sy][sx][1] = true;
		q.offer(new int[] { sy, sx, 1 , 0}); //각각 y좌표, x좌표, 카운트, 1카운트

		while (!q.isEmpty()) {
			int[] current = q.poll();
//			System.out.println("("+current[0]+", "+current[1]+"), "+current[2]+", wallmet: "+current[3]);
			if(current[0] == n - 1 && current[1] == m - 1) return current[2]; //도착했으면 끝

			for (int dir = 0; dir < 4; dir++) {
				ny = current[0] + dy[dir];
				nx = current[1] + dx[dir];
				wallCnt = current[3];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || visited[ny][nx][current[3]]) continue; //내가 1밟은적 있거나 없거나 여부에 따라 다르게 방문 여부 처리
				if(board[ny][nx] == 1) wallCnt = current[3]+1;
				if(wallCnt == 2) continue;
				
				visited[ny][nx][wallCnt] = true; 
				q.offer(new int[] { ny, nx, current[2] + 1, wallCnt});
			}
		}

		return -1; // q가 빌때까지 탐색했는데 n-1,m-1 도달 못했으면 -1 리턴
	}
}
