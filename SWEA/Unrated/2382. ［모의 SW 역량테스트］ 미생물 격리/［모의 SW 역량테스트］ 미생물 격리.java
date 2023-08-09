import java.util.*;
import java.io.*;
//메모리 : 
//실행시간 : 
public class Solution {
	//좌표별로 미생물 총합, 미생물중 제일 큰수, , 미생물 방향 저장.
	public static int[][][] board;
	public static List<Mesengmul> mesengmuls = new ArrayList<>();
	public static int[] dy = {0,-1,1,0,0}; //상하좌우 1234
	public static int[] dx = {0,0,0,-1,1};
	public static int t, n, m, k, sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		t = Integer.parseInt(st.nextToken());
		for(int test_case = 1; test_case <= t; test_case++){
			//초기화
			sum = 0;
			mesengmuls.clear();

			//입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			board = new int[n][n][3];
			//미생물 입력
			for(int i = 0; i < k; i++){
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				mesengmuls.add(new Mesengmul(y, x, num, dir));
			}


			//m시간동안 미생물 움직이기
			for(int i = 0; i < m; i++){
				move();
			}

			//미생물 개수 다 더하기.
			for(Mesengmul m : mesengmuls){
				sum += m.num;
			}

			//출력 저장
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}

		//출력
		System.out.println(sb);
	}

	static void move(){
//		System.out.println("move: mesenmuls.size()"+ mesengmuls.size() );
		//미생물 이동, 방향전환 및 개수 감소
		for(Mesengmul ms : mesengmuls){
//			System.out.println("움직이기전 좌표 숫자 방향");
//			System.out.println("("+ms.y+" "+ms.x+"), "+" "+ms.dir);
			//이동하면서 미생물이 기존에 있던 보드 초기화
			board[ms.y][ms.x][0] = 0;
			board[ms.y][ms.x][1] = 0;
			board[ms.y][ms.x][2] = 0;
			//이동
			ms.y = ms.y + dy[ms.dir];
			ms.x = ms.x + dx[ms.dir];

			if(ms.y == 0 || ms.x == 0 || ms.y == n-1 || ms.x == n-1) { //약품지역 들어왔을경우 방향 변경, 개수 줄이기.
				if (ms.dir == 1) ms.dir = 2;
				else if (ms.dir == 2) ms.dir = 1;
				else if (ms.dir == 3) ms.dir = 4;
				else ms.dir = 3;
				ms.num /= 2;
			}

//			System.out.println("움직인 뒤 좌표 숫자 방향");
//			System.out.println("("+ms.y+" "+ms.x+"), "+" "+ms.dir);
		}

		//미생물을 보드에 기록하고, 이과정에서 합치기
//		System.out.println(+"번째시간 체크");
		for(Mesengmul ms : mesengmuls) {
//			System.out.println("(y, x)"+ms.y+", "+ms.x);
			board[ms.y][ms.x][0] += ms.num; //현재 셀의 미생물 총합 갱신
 			if( ms.num > board[ms.y][ms.x][1]) {//현재 셀의 가장 큰 군집, 방향 갱신
				 board[ms.y][ms.x][1] = ms.num;
				 board[ms.y][ms.x][2] = ms.dir;
			}
		}
		//미생물 리스트 초기화. 합쳐진것들이 있기때문에.
		mesengmuls.clear();
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(board[i][j][0] > 0) {//미생물 존재하면 리스트에 추가
					mesengmuls.add(new Mesengmul(i, j, board[i][j][0], board[i][j][2]));
				}
			}
		}
	}
}

//
class Mesengmul{
	int y;
	int x;
	int num;
	int dir;
	Mesengmul(int y, int x, int num, int dir){
		this.y = y;
		this.x = x;
		this.num = num;
		this.dir = dir;
	}
}