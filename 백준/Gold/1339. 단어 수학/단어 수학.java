/*
 * 수학
 * 다더한다.
 * 제일 많이 나온 알파벳부터 9,8,7 삽입한다.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static Integer[] alphas; //알파벳별 개수
	public static String[] input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine().trim());
		input = new String[n];
		for(int i = 0 ; i < n; i++) {
			input[i] = br.readLine().trim();
		}
		//input done
		
		alphas = new Integer[26];
		Arrays.fill(alphas, 0);
		for(int i = 0; i < n; i++) {
			setAlpha(input[i]);
		}
		Arrays.sort(alphas, Collections.reverseOrder());
		
		int answer = 0;
		int num = 9;
		for(int i = 0; i < 26; i++) {
			if(alphas[i] == 0 || num == 0) break;
			answer += alphas[i] * num--;
		}
		System.out.println(answer);
	}
	
	private static void setAlpha(String str) {
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			alphas[c-'A'] += power(10,str.length() -1-i);
		}
	}
	
	private static int power(int num, int exp) {
		if(exp == 0)
			return 1;
		if(exp == 1)
			return num;
		
		if(exp %2 == 0) return power(num, exp/2) * power(num, exp/2);
		else return num * power(num, exp/2) * power(num, exp/2);
	}
}
