import java.io.*;
import java.util.*;
public class Main {
	public static int n,m;
	public static int[][] board;
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		for(int i = 0; i< n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		int hour  = 0;
		int preCnt = 0;
		board[0][0] = -1;
		while(true) {
			checkAir();
			int cnt = checkCheese(); //return left cheese
			if(cnt == 0) break;			
			preCnt = cnt;
			meltCheese();
			hour++;
		}
		
		System.out.println(hour+"\n"+preCnt);
		
	}
	
	private static void checkAir() {
		Queue<int[]> q = new ArrayDeque<>();
		for(int i = 0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(board[i][j] == -1) q.add(new int[] {i,j});
			}
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir =0 ; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny>=n || nx >= m || board[ny][nx] != 0) continue;
				board[ny][nx] = -1;
				q.offer(new int[] {ny,nx});
			}
		}
	}
	
	private static int checkCheese() {
		int cnt = 0;
		for(int i = 1; i < n-1; i++) {
			for(int j = 1; j < m-1; j++) {
				if(board[i][j] > 0) {
					cnt++;
					for(int dir = 0; dir <4; dir++) {
						int ny = i + dy[dir];
						int nx = j + dx[dir];
						if(board[ny][nx] == -1) {
							board[i][j] = 2;
							break;
						}
					}
				}
			}
		}
		return cnt;
	}
	
	
	private static void meltCheese() {
		for(int i = 1; i < n-1; i++) {
			for(int j = 1; j < m-1; j++) {
				if(board[i][j] == 2)
					board[i][j] = 0;
			}
		}
	}
	
	private static void show() {
		for(int i = 0; i < n; i++) {
			for(int j =0; j < m ;j++) {
				System.out.print((board[i][j]==-1?9:board[i][j]) + " ");
			}
				System.out.println();
		}
		System.out.println("--------------------");
	}
}
