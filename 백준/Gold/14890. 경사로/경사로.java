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
			answer += check('x', i);
			answer += check('y', i);
		}
		System.out.println(answer);
	}
	
	private static int check(char dir, int start) {
		
		int flatCnt = 1;
		int pre;
		
		if(dir == 'x') {
//			System.out.println(start+"->행 탐색 시작");
			pre = board[start][0];
			for(int i = 1; i < n; i++) {
//				System.out.println(i+"번재 칸 탐색 , pre : "+pre+", cur :"+board[i][start]);
				if(Math.abs(board[start][i] - pre) > 1) return 0;
				if(board[start][i] > pre) { // 이전보다 높은 칸일경우 
					if(flatCnt >= l) {
						flatCnt = 1;
						pre = board[start][i];
					}
					else {
						return 0;
					}
				}
				else if(board[start][i] < pre) { //이전보다 낮은 칸일 경우
//					System.out.println(" pre > cur");
					int tmp = board[start][i];
					int idx = 0;
					while(idx <l) {
						if(i+idx >= n || board[start][i] != board[start][i+idx]) return 0;
						idx++;
					}
					i = i + l -1;
					flatCnt = 0; //현재위치까지 경사로가 설치되어있으므로 0으로 cnt
					pre = board[start][i];
//					System.out.println("i : "+i+", board[i][start] :"+board[i][start]);
//					System.out.println("pre : " + pre);
				}
				else { //이전과 같은 칸일 경우
					flatCnt++;
				}
			}
		}
		else{
//			System.out.println(start+"v행 탐색 시작");
			pre = board[0][start];
			for(int i = 1; i < n; i++) {
//				System.out.println(i+"번재 칸 탐색 , pre : "+pre+", cur :"+board[i][start]);
				if(Math.abs(board[i][start] - pre) > 1) return 0;
				if(board[i][start] > pre) { // 이전보다 높은 칸일경우 
					if(flatCnt >= l) {
						flatCnt = 1;
						pre = board[i][start];
					}
					else {
						return 0;
					}
				}
				else if(board[i][start] < pre) { //이전보다 낮은 칸일 경우
//					System.out.println(" pre > cur");
					int idx = 0;
					while(idx <l) {
						if(i+idx >= n || board[i][start] != board[i+idx][start]) return 0;
						idx++;
					}
					i = i + l - 1;
					flatCnt = 0; //현재위치까지 경사로가 설치되어있으므로 0으로 cnt
					pre = board[i][start];
//					System.out.println("i : "+i+", board[i][start] :"+board[i][start]);
//					System.out.println("pre : " + pre);

				}
				else { //이전과 같은 칸일 경우
					flatCnt++;
				}
			}
		}
		
		return 1;
	}
}
