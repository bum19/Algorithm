/*
 * 경사로
 * 가로세로가 겹치는 거는 상관없는
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n, l;
	public static int[][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		board = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int answer = 0;
		for(int i = 0; i < n; i++) {
			answer += check('>', i);
			answer += check('v', i);
		}
		System.out.println(answer);
	}
	
	private static int check(char dir, int start) {
		int sx = 0, sy = 0, dy = 0, dx = 0;
		if(dir == '>') {
			sx = 0;
			sy = start;
			
			dx = 1;
			dy = 0;
		}
		if(dir == 'v') {
			sx = start;
			sy = 0;
			
			dx = 0;
			dy = 1;
		}
		
		int flatCnt = 1;
		int pre = board[sy][sx];

		for(int i = 1; i < n; i++) {
			if(Math.abs(board[sy + i*dy][sx + i*dx] - pre) > 1) return 0;
			if(board[sy + i*dy][sx + i*dx] > pre) { // 이전보다 높은 칸일경우 
				if(flatCnt >= l) {
					flatCnt = 1;
					pre = board[sy + i * dy][sx + i * dx];
				}
				else {
					return 0;
				}
			}
			else if(board[sy + i*dy][sx + i*dx] < pre) { //이전보다 낮은 칸일 경우
				int idx = 0;
				while(idx <l) {
					if(i+idx >= n || board[sy + i*dy][sx + i*dx] != board[sy + (i+idx)*dy][sx + (i+idx)*dx]) return 0;
					idx++;
				}
				i = i + l -1;
				flatCnt = 0; //현재위치까지 경사로가 설치되어있으므로 0으로 cnt
				pre = board[sy + i*dy][sx + i*dx];
			}
			else { //이전과 같은 칸일 경우
				flatCnt++;
			}
		}
		return 1;
	}
}
