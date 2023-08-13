
import java.io.*;
import java.util.*;
public class Solution {
	public static int t, winCnt, loseCnt, totalScore = 18*19/2, cnt;
	public static boolean[] isKyCard;	//1~18중 규영이 카드 확인.
	public static int[] kyCard;			//규영 카드
	public static int[] iyCard; 		//인영 카드 저장
	public static boolean[] isSelected; //인영이 카드중 선택되었는지 확인
	public static int[] currentIyCard; //현재 고른 인영이 카드
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int tmp;
		
		
		kyCard = new int[9];
		iyCard = new int[9];
		currentIyCard = new int[9];
		
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= t; test_case++) {
			//초기화
			isKyCard = new boolean[18]; //인덱스를 카드값이랑 맞추기위해 1추가해서 선언
			isSelected = new boolean[9]; //인덱스를 카드위치랑 맞추기위해 1추가해서 선언
			winCnt = 0;
			loseCnt = 0;
			
			//규영이 카드 입력
			 st = new StringTokenizer(br.readLine());
			 for(int i = 0; i < 9; i++) {
				 tmp = Integer.parseInt(st.nextToken());
				 isKyCard[tmp-1] = true;
				 kyCard[i] = tmp; 
			 }
			 
			 //인영이 카드 넣기
			 int idx = 0;
			 for(int i = 0; i < 18; i++) {
				 if(!isKyCard[i]) iyCard[idx++] = i+1;
			 }
			 
			 
			 //완전탐색으로 해결
			 dfs(0);
			 		 
			 sb.append("#").append(test_case).append(" ").append(winCnt).append(" ").append(loseCnt).append("\n");
		}
		System.out.println(sb);
		
	}
	
	public static void dfs(int depth) {
		if(depth == 9) {
			//게임시작
			int kyScore = 0, iyScore = 0;
			for(int i = 0; i < 9; i++) {
				if(kyCard[i] > currentIyCard[i]) {
					kyScore += kyCard[i] + currentIyCard[i];
				}
				else {
					iyScore += kyCard[i] + currentIyCard[i];
				}
			}
			if( kyScore > iyScore) winCnt++;
			else if( iyScore > kyScore) loseCnt++;
			
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				currentIyCard[depth] = iyCard[i];
				dfs(depth+1);
				isSelected[i] = false;
			}
		}
	}

}
