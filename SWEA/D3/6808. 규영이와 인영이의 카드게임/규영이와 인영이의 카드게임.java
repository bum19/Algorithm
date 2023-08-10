//메모리 :  
//실행시간 : 
import java.util.*;
import java.io.*;
public class Solution {
//	public static int kyScore, iyScore;
	public static int[] kyCards = new int[9];
	public static int[] nonSelectIyCards = new int[9];
	public static int[] iyCards = new int[9];
	public static boolean[] isSelected = new boolean[9];
	public static boolean[] isKyCard = new boolean[18];
	public static int t,winCnt, loseCnt;
	public static StringBuilder sb = new StringBuilder();
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			isSelected = new boolean[9];
			isKyCard = new boolean[18];
			winCnt = 0;
			loseCnt = 0;
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 9; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				isKyCard[tmp-1] = true;
			}
			
			int kyIdx =0, iyIdx = 0;
			for(int i = 0; i < 18; i++) {
				if(isKyCard[i]) kyCards[kyIdx++] = i+1;
				else nonSelectIyCards[iyIdx++] = i+1;
			}
			
			dfs(0);
			sb.append("#").append(test_case).append(" ").append(winCnt).append(" ").append(loseCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	public static void dfs(int cnt) {
		if(cnt == 9) {
			int kyScore =0;
			int iyScore =0;
			for(int i = 0; i < 9; i++) {
				if(kyCards[i] > iyCards[i]) {
					kyScore += kyCards[i]+iyCards[i];
				}
				else {
					iyScore += kyCards[i]+iyCards[i];
				}
			}
			if(kyScore > iyScore) winCnt++;
			else if(kyScore < iyScore) loseCnt++;
			
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				iyCards[cnt] = nonSelectIyCards[i];
				dfs(cnt+1);
				isSelected[i] = false;
			}
		}
	}

}
