//메모리	: 42,628 kb
//실행시간 : 933 ms
import java.util.*;
import java.io.*;

public class Solution {
	public static int t, n, maxCnt; // 테스트케이스, 게임판크기, 핀볼최댓값
	public static int[][] board;// 게임판 담을 배열
	public static int[][][] warmhole; // 웜홀담을 배열. 1번째 인덱스 웜홀 번호, 2번째 인덱스 웜홀 쌍, 3번째 인덱스  y,x좌표
	public static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	public static int[] dx = { 0, 0, -1, 1 }; // 상하좌우
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim()); //trim안쓸경우 NumberFormatException 발생.

		for (int test_case = 1; test_case <= t; test_case++) {
			
			//입력시작
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n + 2][n + 2]; //입력인덱스시작이 1~n이고, 
			for(int i = 0 ; i < n+2; i++) { //벽에 부딪히는 로직을 위해 배열전체를 5로 감싸준다.
				board[0][i] = 5;
				board[i][0] = 5;
				board[n+1][i] = 5;
				board[i][n+1] = 5;
			}
			warmhole = new int[11][2][2]; // [11] : 웜홀번호. 0~5까지는 안쓰고, 6~11만쓴다.	// [2] : 웜홀쌍 0,1  //[2] : y,x좌표 
			maxCnt = Integer.MIN_VALUE;	//테스트케이스별 최댓값 초기화.
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 1; j <= n; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (6 <= tmp && tmp <= 10) { //웜홀 입력
						int warmIdx = 0; // 웜홀 쌍 중 첫번째인지 두번째인지
						if (warmhole[tmp][warmIdx][0] != 0) //해당 웜홀번호 입력받은적있으면, 1번재 쌍에 저장.
							warmIdx++;
						warmhole[tmp][warmIdx][0] = i;
						warmhole[tmp][warmIdx][1] = j;
					}
					board[i][j] = tmp;
				}
			}
			//입력끝
			
			//게임판 위에 0,0인곳에서 시작. n*n 각칸을 순회하며, 0일경우에 이동 시작.
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (board[i][j] != 0)
						continue;
					move(i, j);
				}
			}
			
			//출력 저장.
			sb.append("#").append(test_case).append(" ").append(maxCnt).append("\n");
		}
		
		//출력
		System.out.print(sb);
	}

	//핀볼 움직이는 메소드. 시작좌표를 인자로 받는다.
	public static void move(int y, int x) {
		int cnt = 0, dir;
		
		for(int i = 0 ; i < 4; i++) {
			dir = i;
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			//탐색
			while(true) {
				if(board[ny][nx] == -1 || (ny == y && nx ==x) ) { // 종료 조건. 시작좌표로 돌아오거나 블랙홀을 만날경우.
					break;
				}
				else if(board[ny][nx] == 5) { // 벽이거나 5번블록일 때, cnt 값 현재왔던경로 *2 + 1값 으로 갱신하고 탐색 종료.
					cnt = cnt*2 +1;
					break;
				}
				else if(board[ny][nx] == 1) {// 1번블록
					cnt++;
					if(dir == 1) 	  dir = 3;
					else if(dir == 2) dir = 0;
					else if(dir == 3) dir = 2;
					else if(dir == 0) dir = 1;
				}
				else if(board[ny][nx] == 2) {// 2번블록
					cnt++;
					if(dir == 0)	  dir = 3;
					else if(dir == 2) dir = 1;
					else if(dir == 1) dir = 0;
					else if(dir == 3) dir = 2;
				}
				else if(board[ny][nx] == 3) { // 3번블록
					cnt++;
					if(dir == 3)	  dir = 1;
					else if(dir == 0) dir = 2;
					else if(dir == 1) dir = 0;
					else if(dir == 2) dir = 3;
				}
				else if(board[ny][nx] == 4 ) { // 4번블록
					cnt++;
					if(dir == 3)	  dir = 0;
					else if(dir == 1) dir = 2;
					else if(dir == 0) dir = 1;
					else if(dir == 2) dir = 3;
				}
				else if(board[ny][nx] >= 6 && board[ny][nx] <= 10){ //웜홀 만났을때
					int tmp = board[ny][nx];
					int warmIdx = 0;
					if(warmhole[tmp][warmIdx][0] == ny && warmhole[tmp][warmIdx][1] == nx) //현재 웜홀말고 페어 웜홀 좌표찾기. 
						warmIdx++;
					ny = warmhole[tmp][warmIdx][0];		//페어 웜홀 좌표로 y갱신
					nx = warmhole[tmp][warmIdx][1];		//페어 웜홀 좌표로 x갱신
				}
				
				//다음방향으로 이동
				ny = ny + dy[dir];
				nx = nx + dx[dir];
			}
			
			//현재cnt값과 maxCnt값 비교
			maxCnt = Math.max(maxCnt, cnt);
			//cnt 탐색끝나면 초기화.
			cnt = 0;
		}
	}
}
