//메모리	:
//실행시간 :
import java.util.*;
import java.io.*;

public class Solution {
	public static int t, n, cnt, maxCnt, startx, starty, dir;
	public static int[][] board;
	public static int[][][] warmhole; // 1번째 인덱스 웜홀 번호, 3번째 0인덱스 y좌표, 3번째 1인덱스 x좌표
	public static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	public static int[] dx = { 0, 0, -1, 1 }; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			//입력시작
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n + 2][n + 2]; //입력인덱스시작이 1~n이고, 벽에 부딪히는 로직을 위해 배열전체를 5로 감싸준다.
			for(int i = 0 ; i < n+2; i++) {
				board[0][i] = 5;
				board[i][0] = 5;
				board[n+1][i] = 5;
				board[i][n+1] = 5;
			}
			warmhole = new int[11][2][2]; // 1번째 인덱스 웜홀 번호, 3번째 인덱스 0,1 좌표. warmhole[6][1][0] : 6번웜홀 쌍중 첫번째 웜홀의 y좌표.
			cnt = 0;
			maxCnt = Integer.MIN_VALUE;
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 1; j <= n; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (6 <= tmp && tmp <= 10) {
						int warmIdx = 0; // 웜홀 쌍 중 첫번째인지 두번째인지
						if (warmhole[tmp][warmIdx][0] != 0)
							warmIdx++;// 웜홀 입력받은적 있는지 없는지확인.
						warmhole[tmp][warmIdx][0] = i;
						warmhole[tmp][warmIdx][1] = j;
//						System.out.println("warmhole["+tmp+"]["+warmIdx+"][0] : "+i);
//						System.out.println("warmhole["+tmp+"]["+warmIdx+"][1] : "+j);
					}
					board[i][j] = tmp;
				}
			}
			
//게임판 위에 0,0인곳에서 시작.
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (board[i][j] != 0)
						continue;
					starty = i;
					startx = j;
					move(i, j);
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
		}
		System.out.print(sb);
	}

	public static void move(int y, int x) {

		for(int i = 0 ; i < 4; i++) {
			dir = i;
//			System.out.println("(y, x) :" + "("+y+", "+x+")");
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			//탐색
			while(true) {
				if(board[ny][nx] == -1 || (ny == y && nx ==x) ) { // 종료 조건
//					System.out.println("탈출");
					break;
				}
				else if(board[ny][nx] == 5) { // 벽이거나 5번블록일 때
//					System.out.println("벽");
					cnt++;
					if(dir == 0)	  dir = 1;
					else if(dir == 1) dir = 0;
					else if(dir == 2) dir = 3;
					else if(dir == 3) dir = 2;
				}
				else if(board[ny][nx] == 1) {// 1번블록
//					System.out.println("1블록");
					cnt++;
					if(dir == 1) 	  dir = 3;
					else if(dir == 2) dir = 0;
					else if(dir == 3) dir = 2;
					else if(dir == 0) dir = 1;
				}
				else if(board[ny][nx] == 2) {// 2번블록
//					System.out.println("2블록");
					cnt++;
					if(dir == 0)	  dir = 3;
					else if(dir == 2) dir = 1;
					else if(dir == 1) dir = 0;
					else if(dir == 3) dir = 2;
				}
				else if(board[ny][nx] == 3) { // 3번블록
//					System.out.println("3블록");
					cnt++;
					if(dir == 3)	  dir = 1;
					else if(dir == 0) dir = 2;
					else if(dir == 1) dir = 0;
					else if(dir == 2) dir = 3;
				}
				else if(board[ny][nx] == 4 ) { // 4번블록
//					System.out.println("4블록");
					cnt++;
					if(dir == 3)	  dir = 0;
					else if(dir == 1) dir = 2;
					else if(dir == 0) dir = 1;
					else if(dir == 2) dir = 3;
				}
				else if(board[ny][nx] >= 6 && board[ny][nx] <= 10){ //6~10일때
//					System.out.println("위잉웜홀");
					int tmp = board[ny][nx];
					int warmIdx = 0;
					if(warmhole[tmp][warmIdx][0] == ny && warmhole[tmp][warmIdx][1] == nx)// 현재 인덱스가 웜홀 다른 쌍이 6~10이 아니라면
						warmIdx++;
					ny = warmhole[tmp][warmIdx][0];
					nx = warmhole[tmp][warmIdx][1];
				}
				
				//다음방향으로 이동
				ny = ny + dy[dir];
				nx = nx + dx[dir];
//				System.out.println("작업후current 위치 : ("+current[0]+", "+current[1]+")"+", dir : "+dir+" ny,nx : ("+ny+", "+nx+")");
			}
			//비교
			maxCnt = Math.max(maxCnt, cnt);
			//cnt 탐색끝나면 초기화.
			cnt = 0;
		}

	}
}
