//
import java.io.*;
import java.util.*;
public class Main {
	public static int r, c, maxCnt;
	public static boolean[][] visited;
	public static boolean[] alphabetVisited;
	public static char[][] board;
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new char[r][c];
		visited = new boolean[r][c];
		alphabetVisited = new boolean[26];
		maxCnt = Integer.MIN_VALUE;
		
		for(int i = 0; i < r; i++) {
			String str = br.readLine().trim();
			for(int j = 0; j < c; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		
		alphabetVisited[board[0][0]-'A'] = true;
		visited[0][0] = true;
		dfs(0,0,1);
		
		System.out.println(maxCnt);
		
	}
	
	private static void dfs(int y, int x, int cnt) {
		
		int ny, nx;
		for(int dir = 0; dir < 4; dir++) {
			ny = y + dy[dir];
			nx = x + dx[dir];
			if(ny < 0 || nx < 0 || ny >= r || nx >= c || alphabetVisited[board[ny][nx]-'A'] || visited[ny][nx]) continue;
			visited[ny][nx] = true;
			alphabetVisited[board[ny][nx]-'A'] = true;
			dfs(ny, nx, cnt +1);
			visited[ny][nx] = false;
			alphabetVisited[board[ny][nx]-'A'] = false;
		}
			
		//갈곳이없으면 비교
		if(cnt > maxCnt) maxCnt = cnt;
		
	}

}
