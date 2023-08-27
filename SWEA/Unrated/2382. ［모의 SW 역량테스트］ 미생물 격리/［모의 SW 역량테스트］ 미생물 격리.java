import java.io.*;
import java.util.*;
public class Solution {
	public static int t, n, m, k, totalCnt;
	public static int[][][] board; //총 미생물개수, 제일 큰 미생물 군집수, 미생물 방향
	public static List<int[]> meesengmuls; //배열은 좌표, 미생물 수, 미생물 방향
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t  = Integer.parseInt(br.readLine().trim());
			
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			
			board = new int[n][n][3]; //총 미생물개수, 제일 큰 미생물 군집수, 미생물 방향
			meesengmuls = new ArrayList<int[]>();
			
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());	
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int msCnt = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1; //입력 1234 -> 0123으로
				meesengmuls.add(new int[] {y,x,msCnt,dir});
			}
			
			totalCnt = 0;
			for(int i = 0; i < m; i++) {
				move(); //미생물 이동. 절반삭제로직도 같이.
				updateMs(); //미생물 합치기
			}
			countMs(); //남은 미생물들 다 더하기
			
			sb.append("#").append(test_case).append(" ").append(totalCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void move() { //이동,방향변경,절반삭제
		//미생물 이동.
		for(int[] ms : meesengmuls) {
			ms[0] = ms[0] + dy[ms[3]];
			ms[1] = ms[1] + dx[ms[3]];
			if(ms[0] == 0 || ms[1] == 0 || ms[0] == n-1 || ms[1] == n-1) {//약품지역 들어가면
				if(ms[3] == 0) 		ms[3] = 1;
				else if(ms[3] == 1) ms[3] = 0;
				else if(ms[3] == 2) ms[3] = 3;
				else if(ms[3] == 3) ms[3] = 2;
				ms[2] /= 2; //미생물 수 감소
			}
		}
	}
	
	public static void updateMs() { //미생물 합치기.
		//좌표초기화.
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n; j++) {
				board[i][j][0] = 0; //총 미생물개수 초기화
				board[i][j][1] = 0; //최고 미생물 수 초기화
				board[i][j][2] = 0; //미생물 방향  초기화
			}
		}
		
		//현재 미생물들 좌표에 입력. 입력하면서 합치기 및 삭제진행
		for(int[] ms : meesengmuls) {
			board[ms[0]][ms[1]][0] += ms[2];
			if(board[ms[0]][ms[1]][1] < ms[2]) {
				board[ms[0]][ms[1]][1] = ms[2]; //제일큰놈들어왔으면 최고 미생물 수 갱신
				board[ms[0]][ms[1]][2] = ms[3]; //미생물방향 최고로 큰 미생물거로 바꿈.
			}
		}
		//좌표에 입력된 미생물들만 미생물에 추가.
		meesengmuls.clear();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(board[i][j][0] > 0) {
					meesengmuls.add(new int[] {i, j, board[i][j][0], board[i][j][2]});
				}
			}
		}
	}
	
	public static void countMs() {
		for(int [] ms : meesengmuls) {
			totalCnt += ms[2];
		}
	}
}
