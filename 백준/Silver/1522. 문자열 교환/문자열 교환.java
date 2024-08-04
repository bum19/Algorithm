/*
 * a가 감싸는 모양은 총 a개. a..b..a <- a개
 * aabb / abba / bbaa
 * 
 * b가 감싸는 모양은 총 b개. b..a..b <- b개
 * aabb / baab / bbaa
 * 
 * 모든 경우의수를 비교하기. aaaa..bbb, bbb...aa 총 2가지 형태의 중복비교는 사소하므로 넘어가기.
 * O(1000*1000);
 */
import java.io.*;
import java.util.*;
public class Main {

	public static String input;
	public static int answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		input = br.readLine().trim();
		int aCnt = 0, bCnt = 0;
		for(int i =0 ; i< input.length(); i++) {
			if(input.charAt(i) == 'a') {
				aCnt++;
			}
			if(input.charAt(i) == 'b') {
				bCnt++;
			}
		}
		
		answer = Integer.MAX_VALUE;
		//a..b..a
		for(int i = 0 ; i <= aCnt; i++) {
			int leftA = i;
			int diff = 0;
			for(int j = 0; j < leftA; j++) {
				if(input.charAt(j) != 'a') diff++;
			}
			for(int j = leftA; j< leftA+bCnt; j++) {
				if(input.charAt(j) != 'b') diff++;
			}
			for(int j = leftA+bCnt; j < input.length(); j++) {
				if(input.charAt(j) != 'a') diff++;
			}
			answer = Math.min(answer, diff/2);
		}

		//b..a..b
		for(int i = 0 ; i <= bCnt; i++) {
			int leftB = i;
			int diff = 0;
			for(int j = 0; j < leftB; j++) {
				if(input.charAt(j) != 'b') diff++;
			}
			for(int j = leftB; j< leftB+aCnt; j++) {
				if(input.charAt(j) != 'a') diff++;
			}
			for(int j = leftB+aCnt; j < input.length(); j++) {
				if(input.charAt(j) != 'b') diff++;
			}
			answer = Math.min(answer, diff/2);
		}
		
		System.out.println(answer);
	}
}
