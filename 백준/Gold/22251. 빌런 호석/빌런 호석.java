/*
 *  0~9가 서로 변환하는데 필요한 반전수를 2차원배영로저장.
 *  1~N까지 탐색하면서, X층에서 P번내로 바꿀수있으면 카운트
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,k,p,x;
	public static String[] numbers;
	public static int[][] reverse;
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		
		
		init();
		
		int cnt = 0;
		for(int i = 1; i <= n; i++) {
			if(i == x) continue;
			if(check(i)) cnt++;
		}
		System.out.println(cnt);
	}	
	
	private static void init() {
		numbers = new String[] {"1111110", "0110000","1101101","1111001","0110011","1011011","1011111","11100000","1111111","1111011"};
		reverse = new int[10][10];
		
		for(int i = 0; i < 10; i++) {
			for(int j = i+1; j < 10; j++) {
				int reverseCnt = 0;
				for(int k = 0; k < 7; k++) {
					if(numbers[i].charAt(k) != numbers[j].charAt(k)) {
						reverseCnt++;
					}
				}
				reverse[i][j] = reverseCnt;
				reverse[j][i] = reverseCnt;
			}
		}
	}
	
	private static boolean check(int targetNum) {
		//한자리씩 비교.
		int idx = 0;
		int curNum = x;
		int reverseCnt = 0;
		while(idx < k) { //오른쪽부터 비
			int cur = curNum % 10;
			int target = targetNum % 10;
			reverseCnt += reverse[cur][target];
			curNum /= 10;
			targetNum /= 10;
			idx++;
		}
		if(reverseCnt <= p) return true;
		return false;
	}
}
