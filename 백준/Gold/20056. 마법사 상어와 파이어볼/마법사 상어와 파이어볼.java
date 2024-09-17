/*
 * 마법사 상어와 파이어볼.
 * 매 작업마다 n*n번 돌면서 좌표를 깨끗이해주는 작업, 파이어볼 리스트를 clear해주는 작업이 필요하다.
 * 격자밖을 안벗어남에 주의.
 * 격자밖 안벗어나게 할때 연산 잘해야함 주의.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static Board[][] board;
	public static List<FireBall> fireBalls;
	public static int[] dy = {-1,-1,0,1,1,1,0,-1};//상~
	public static int[] dx = {0,1,1,1,0,-1,-1,-1};
	
	public static int n,m,k;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		board = new Board[n][n];
		initBoard(board);
		
		fireBalls = new ArrayList<>();
		
		for(int i = 0; i < m; i ++) {
			int r,c,m,s,d;
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			m = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			fireBalls.add(new FireBall(r,c,m,s,d));
		}
		
		
		for(int i = 0; i < k; i++) {
			move();
			updateFireBalls();
		}
		int answer = 0;
		for(FireBall fb : fireBalls) {
			answer += fb.m;
		}
		
		System.out.println(answer);
	}
	
	private static void initBoard(Board[][] board) {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j<n; j++) {
				if(board[i][j] == null) {
					board[i][j]= new Board(-1,0,0);
				}
				else {
					board[i][j].fbCnt = 0;
					board[i][j].sameDir = true;
					board[i][j].d = -1;
					board[i][j].s = 0;
					board[i][j].m = 0;
				}
			}
		}
	}
	
	private static void move() {
		initBoard(board);
		for(FireBall fb : fireBalls) {
			int nr = ((fb.r + dy[fb.d]*fb.s)%n + n) % n;
			int nc = ((fb.c + dx[fb.d]*fb.s)%n + n) % n;
//			if(nr < 0 || nc < 0 || nr >=n || nc >= n) continue;
			
			if(board[nr][nc].d != -1 && board[nr][nc].sameDir == true) {
				board[nr][nc].sameDir = board[nr][nc].d % 2 == fb.d % 2?true:false;
			}
			
			board[nr][nc].fbCnt++;
			board[nr][nc].m += fb.m;
			board[nr][nc].s += fb.s;
			board[nr][nc].d = fb.d;
		}
	}
	
	private static void updateFireBalls() {
		fireBalls.clear();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j].fbCnt == 0) continue;
				if(board[i][j].fbCnt > 1) {
					//질량이 0 이상인지 체크
					int m = board[i][j].m/5;
					if(m == 0) continue;
					int s = board[i][j].s/board[i][j].fbCnt;
					int d = board[i][j].sameDir?0:1;
					
					for(int dir = 0; dir < 8; dir+=2) {
						fireBalls.add(new FireBall(i,j,m,s,d+dir));
					}
				}
				else {
					fireBalls.add(new FireBall(i,j,board[i][j].m, board[i][j].s, board[i][j].d));
				}
			}
		}
	}
	public static class FireBall{
		int r,c,m,s,d;
		public FireBall(int r,int c, int m, int s, int d) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	public static class Board{
		boolean sameDir;
		int d,s,m,fbCnt;
		public Board(int d,int s,int m) {
			this.fbCnt = 0;
			this.sameDir = true;
			this.d = d;
			this.s = s;
			this.m = m;
		}
	}
}
