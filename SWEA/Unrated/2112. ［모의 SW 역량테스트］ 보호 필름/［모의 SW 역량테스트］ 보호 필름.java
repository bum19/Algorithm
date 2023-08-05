//메모리 : 
//실행시간 : 
import java.util.*;
import java.io.*;
//모든 케이스 약품주입생각은 함
//각 케이스별 깊은복사후, 원상복귀로직생각이 어려워 답 참조
public class Solution {
	static int t, d, w, k;
	static int minInject = -1;
	static int board[][];
	static int temp[][];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			board = new int[d][w];
			temp = new int[d][w];
			minInject = -1;
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < w; j++) {
					board[i][j] = temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i <= k; i++) {
				dfs(0, i, 0);
				if(minInject != -1) break;
			}
			
			sb.append("#").append(test_case).append(" ").append(minInject).append("\n");	
		}
		
		System.out.println(sb);
		
	}
	
	//약품 주입 칸 d줄 중 injectNum줄 투입하는 조합재귀구현.
	private static void dfs(int cnt, int injectNum, int start) {

		//이미 성능검사통과했으면 탐색멈출것.
		if(minInject != -1 ) return;
		

		if(cnt == injectNum) {
			if(checkBoard()) {
				minInject = cnt;
			}
			return;
		}
		
		
		for(int i = start ; i < d; i++) {
			//a주입
			for(int j = 0; j < w; j++) temp[i][j] = 0;
			dfs(cnt+1,injectNum,i+1);
			
			//b주입
			for(int j = 0; j < w; j++) temp[i][j] = 1;
			dfs(cnt+1,injectNum,i+1);
			
			//원상복구
			for(int j = 0; j < w; j++) temp[i][j] = board[i][j];

		}
	}

	//성능검사 통과하는지 확인하는 로직.
	private static boolean checkBoard() {
		int serialCnt;
		
		for(int x = 0 ; x < w; x++) {
			serialCnt = 1;
			for(int y = 1; y < d; y++) {
				
				if(temp[y-1][x] == temp[y][x]) serialCnt++;
				else serialCnt = 1;
				//해당 x줄이 성능검사 통과했으면 해당줄 탐색 종료
				if(serialCnt >= k) break;
			}
			
			if(serialCnt < k) return false;
		}
		
		//모든줄이 별 이상 없으면 성능검사 통과.
		return true;
	}
	
	
}
