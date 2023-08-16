//참고코드 : 박종우님 풀이
//메모리:		
//실행시간:		
import java.io.*;
import java.util.*;

public class Main {
	public static int[][] matches;
//	public static int winCnt, loseCnt, drawCnt;
	public static int[] win, lose, draw;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
			
		for(int test_case = 1; test_case <= 4; test_case++) { // 총 4가지 경우에 대해 확인함.
			//초기화
//			winCnt = loseCnt = drawCnt =0;
			win = new int[6]; 	//입력받은 경기결과 저장
			lose = new int[6];	//입력받은 경기결과 저장
			draw = new int[6];	//입력받은 경기결과 저장
			matches = new int[15][2];	//매치별 팀 기록.
			int matchIdx = 0;
			for(int i = 0; i < 6; i++) {
				for(int j = i+1; j < 6; j++) {
					matches[matchIdx][0] = i;
					matches[matchIdx][1] = j;
					matchIdx++;
				}
			}
			
			boolean isTotalFive = true; //경기수가 5인지 확인.
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++) {
				win[j] = Integer.parseInt(st.nextToken());
				draw[j] = Integer.parseInt(st.nextToken());
				lose[j] = Integer.parseInt(st.nextToken());
				if(win[j]+draw[j]+lose[j] !=5) isTotalFive = false;
			}
			
			if(isTotalFive && dfs(0))	sb.append(1);
			else						sb.append(0);
			sb.append(" ");
		}
		System.out.println(sb);
	}
	
	private static boolean dfs(int matchCnt) {
		if(matchCnt == 15) {
			//모든값이 성공적으로 적용되면서 15까지 왔으면 참  return.
			return true;
		}
		
		int t1 = matches[matchCnt][0];
		int t2 = matches[matchCnt][1];
		
		//매치가 승리할경우 탐색
		if(win[t1] > 0 && lose[t2] > 0) {	//승리가 가능한경우에만 승리경우의 수 탐색
			win[t1]--;	//t1 승리횟수 감소
			lose[t2]--;	//t2 패배횟수 감소
			if(dfs(matchCnt+1))	 return true; //다음 매치 재귀호출 및 참인경우에만 탐색 종료
			win[t1]++;	//t1 승리푓수 복구
			lose[t2]++; //t2 패배횟수 복구
		}
		//매치가 패배할경우 탐색
		if(lose[t1] > 0 && win[t2] > 0) { //패배가 가능한 경우에만 패배경우의 수 탐색
			lose[t1]--;	//t1 패배횟수 감소
			win[t2]--;	//t2 패배횟수 감소
			if(dfs(matchCnt+1)) return true;
			lose[t1]++;	//t1 승리횟수 복구
			win[t2]++;	//t2 승리횟수 복구
		}
		//무승부일때 탐색
		if(draw[t1] > 0 && draw[t2] > 0) { //무승부가 가능한 경우에만 무숭부인 경우의 수 탐색
			draw[t1]--; //t1 무승부 횟수 감소
			draw[t2]--; //t1 무승부 횟수 감소
			if(dfs(matchCnt+1)) return true;
			draw[t1]++; //t1 무승부 횟수 복구
			draw[t2]++; //t2 무승부 횟수 복구
		}
		
		//매치수 15 이전에 승패무가 불가능한상황이면, 값이 맞지 않는 경우이므로 false 리턴
		return false;
	}
	

}
