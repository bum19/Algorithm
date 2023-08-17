//
import java.io.*;
import java.util.*;

public class Solution {
	public static int t, h, w, n;
	public static char[][] map;
	public static int[] input; //상하좌우 이동 숫자로 변환해서 저장. 슈팅명령의경우 제자리 이동으로 설정.
	public static int[] dy = {0,-1,1,0,0}; //슛, 상하좌우 이동
	public static int[] dx = {0,0,0,-1,1}; //슛, 상하좌우 이동
	public static char[] tankIcon = {' ', '^','v','<','>'};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int x = -1, y = -1, dir = -1; //초기화안해주면 컴파일 에러가 떠서 -1로 초기화 
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t ; test_case++) {
			//입력
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			
			for(int i = 0; i < h; i++) {
				String line = br.readLine().trim();
				for(int j = 0; j < w; j++) {
					map[i][j] = line.charAt(j);
					//현재 탱크위치 찾아서, x, y, dir값 넣기
					if(map[i][j] ==  '^') {
						y = i;
						x = j;
						dir = 1;
					}
					else if(map[i][j] == 'v') {
						y = i;
						x = j;
						dir = 2;
					}
					else if(map[i][j] == '<') {
						y = i;
						x = j;
						dir = 3;
					}
					else if(map[i][j] == '>') {
						y = i;
						x = j;
						dir = 4;
					}
				}
			}
			
			n = Integer.parseInt(br.readLine().trim());
			input = new int[n];
			String inputStr = br.readLine().trim();
			
			for(int i = 0; i < n; i++) {
				char tmpC = inputStr.charAt(i);
				switch(tmpC) {
					case 'S':	input[i] = 0; break;
					case 'U': 	input[i] = 1; break;
					case 'D':	input[i] = 2; break;
					case 'L':	input[i] = 3; break;
					case 'R':	input[i] = 4; break;
				}
			}
			//입력끝
			
			//구현
			for(int i = 0; i < n; i++) { // 입력개수만큼 반복
//				//테스트출력
//				for(int a = 0; a < h; a++) {
//					for(int b = 0; b < w; b++) {
//						System.out.print(map[a][b]);
//					}
//					System.out.println();
//				}
//				System.out.println("--------");
				int nx, ny;
				
				if(input[i] == 0) {		//입력이 0이면
					shoot(x, y, dir);	//슈팅
				}
				else {					//입력이 그외면 방향전환후 이동가능하면 이동
					dir = input[i];
					nx = x + dx[dir];
					ny = y + dy[dir];
					
					if(nx >= 0 && ny >= 0  && ny < h && nx < w && map[ny][nx] == '.') {
						map[y][x] = '.';
						x = nx;
						y = ny;
					}
					
					map[y][x] = tankIcon[dir];
					
				}
			}
			
			//출력저장
			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void shoot(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		while(true) {
//			System.out.println("("+ny +", "+nx +")");
			if(ny < 0 || nx < 0 || ny >= h || nx >= w) return; //벽밖으로 포탄 벗어나면 아무일도 일어나지 않는다.
			if(map[ny][nx] == '#') 					   return; //강철벽 만나면 아무일도 일어나지않는다.
			if(map[ny][nx] == '*') { 						   //벽돌벽 만날경우 부시고 종료
				map[ny][nx] = '.';
				return;
			}
			
			nx += dx[dir];
			ny += dy[dir];
		}
	}
}
