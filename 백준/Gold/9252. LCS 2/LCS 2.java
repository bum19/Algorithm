import java.io.*;
import java.util.*;
/*
 *  최장길이 공통 부분 문자열
	lcs[i][j] = 문자열A의 i번째 문자와, 문자열B의 j번째 숫자까지 비교했을 때, 연속되는 문자열의 길이.
	점화식
	if(문자열A의 i번쨰 문자 == 문자열B의 j번째 문자)
		lcs[i][j] = lcs[i-1][j-1] + 1;
	else(다르면)
		lcs[i][j] = max(lcs[i-1][j], lcs[i][j-1]; // 공통부분문자열이면 그냥 값 0으로 해버려.
 */
public class Main {
	public static int[][] lcs; //문자열도 출력해야하므로, 이전 공통된부분을 가르키도록 하는 변수를 추가한다. int형으로 저장.
	public static String input1, input2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input1 = br.readLine().trim();
		input2 = br.readLine().trim();
		
		lcs = new int[input1.length()+1][input2.length()+1];
		
		//init
		for(int i = 0; i <= input2.length(); i++) {
			lcs[0][i] = 0;
		}
		for(int i = 0; i <= input1.length(); i++) {
			lcs[i][0] = 0;
		}
		
		for(int i = 1; i <= input1.length(); i++) {
			for(int j = 1; j <= input2.length(); j++) {
				if(input1.charAt(i-1) == input2.charAt(j-1)) {
					lcs[i][j] = lcs[i-1][j-1] + 1;
				}
				else
					lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
			}
		}
		
		//최장길이
		int maxLen = lcs[input1.length()][input2.length()];
		
		//문자열 만들기
		StringBuilder sb = new StringBuilder();
		makeString(sb, input1.length() , input2.length());
		
		System.out.println(maxLen);
		System.out.println(sb);
	}
	
	private static void makeString(StringBuilder sb, int y, int x) {
		if(y == 0 || x == 0) return;
		if(input1.charAt(y-1) == input2.charAt(x-1)) {
			makeString(sb, y-1, x-1);
			sb.append(input1.charAt(y-1));
		}
		else {
			//둘중 더 값이 큰걸로
			if(lcs[y-1][x] > lcs[y][x-1]) {
				makeString(sb,y-1,x);
			}
			else {
				makeString(sb,y,x-1);
			}
		}
	}
}
