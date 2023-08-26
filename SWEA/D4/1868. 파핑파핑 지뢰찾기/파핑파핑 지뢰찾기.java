//
import java.io.*;
import java.util.*;
public class Solution {
	public static int t, n, cleanCnt, clickCnt; //테스트케이스, 크기, 남은.개수, 클릭횟수
	public static char[][] board;
	public static boolean[][] visited;
	public static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1}; //상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	public static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1}; //상, 우상, 우, 우하, 하, 좌하, 좌, 좌상
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			//입력시작
			n = Integer.parseInt(br.readLine().trim());
			board = new char[n][n];
			visited = new boolean[n][n];
			cleanCnt = 0;
			for(int i = 0; i < n; i++) {
				String str = br.readLine();
				for(int j = 0; j < n; j++) {
					board[i][j] = str.charAt(j);
					if(board[i][j] == '.') cleanCnt++;
				}
			}
			
			clickCnt = 0;
			//입력끝

			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(board[i][j] == '.' && checkZero(i,j)) { //8방에대해 0칸인지 확인후 작업진행.
						clickCnt++; //0이면 클릭수 증가.
						markNum(i, j); //현재칸이 0일때만 시작함.
					}
				}
			}
			clickCnt += cleanCnt; //남은 .개수만큼 더하기
			
			sb.append("#").append(test_case).append(" ").append(clickCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	private static boolean checkZero(int y,int x) { //해당칸이 방문한적 없는 zero인지 확인.
		//8방 확인.
		for(int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny >=0 && nx >= 0 && ny < n && nx <n && board[ny][nx] == '*') return false; //8방에 지뢰있으면 0이아님
		}
		
		//8방에 지뢰 없으면 해당 위치 0임.
		return true;
	}
	
	private static void markNum(int y, int x) {
		visited[y][x] = true; //방문처리.
		board[y][x] = '-';
		cleanCnt--; //남은 .개수 감소
		
		//8방확인. 0이아니면 추가 탐색 제외
		if(!checkZero(y, x)) return;
		
		//8방에 대해 재귀적으로 탐색 진행.
		for(int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx]) continue; //이미 탐색한곳이거나, 인덱스벗어났으면 추가로 탐색 안함.
			markNum(ny, nx);
		}
	}
	
}
