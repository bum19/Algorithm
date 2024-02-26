import java.io.*;
import java.util.*;

public class Main {

	/*
	 * 
	 * 1초가 지난 폭탄 리스트,
	 * 2초가 지난 폭탄 리스트를 관리한다.
	 * 
	 * 매 초 마다 격자판을 탐색한다. r*c*n 은 최대 800만
	 */
	public static int r,c,n;
	public static boolean installBombFlag;
	public static int[][] board; //값을 폭탄 시간으로 설정한다.
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		board = new int[r][c];
		
		for(int i = 0; i < r; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < c ; j++) {
				if(tmp.charAt(j) == '.') {
					board[i][j] = -1;
				}
				else
					board[i][j] = 0;
			}
		}
		//input end
		
		installBombFlag = true;
		
		//1초. 아무것도 하지않는다. 시간만 흐른다.
		n--;
		for(int i = 0; i < r ; i++) {
			for(int j = 0; j < c; j++) {
				if(board[i][j] == 0) board[i][j]++;
			}
		}
		
		
		for(int i = 0; i < n ; i++) {
			if(installBombFlag) { //현재 폭탄설치타이밍인지 확인
				for(int y = 0; y < r; y++) {
					for(int x = 0; x < c; x++) {
						board[y][x]++;
					}
				}
				installBombFlag = false;
			}
			else if(!installBombFlag){ //폭탄설치타이밍 아니면 기존 폭탄 타이머만 증가시키고 넘어가
				for(int y = 0; y < r; y++) {
					for(int x = 0; x < c; x++) {
						if(board[y][x] != -1)board[y][x]++;
					}
				}
				installBombFlag = true; 
			}
			
			//3초짜리 있으면 폭파시킨다.
			for(int y = 0; y < r; y++) {
				for(int x =0; x< c; x++) {
					if(board[y][x] == 3) {
						//5방 -1만들기
						bomb(y,x);
						
					}
				}
			}
		}
			
		
		StringBuilder sb = new StringBuilder();
		//sysout
		for(int  y = 0; y <r; y++) {
			for(int x = 0; x < c; x++) {
				if(board[y][x] == -1) sb.append(".");
				else sb.append("O");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
		
	}
	private static void bomb(int y, int x) {
		board[y][x] = -1;
		
		for(int dir = 0; dir <4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(ny < 0 || nx < 0 || ny >= r || nx >= c) continue;
			if(board[ny][nx] == 3) bomb(ny, nx);
			else board[ny][nx] = -1;
		}
	}
}
