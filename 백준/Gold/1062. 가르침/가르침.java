/*
 * 20개의 알파벳 중에 k개 골라서 n개 단어 사용해보기
 * 20C10 * N * 15
 * 완탐으로 알파벳 고른뒤, 단어체크 
 * 
 */
import java.io.*;
import java.util.*;
public class Main {
	public static String[] words;
	public static int[] input;
	public static int n,k,answer;
	public static boolean[] alpha;
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		words = new String[n];
		for(int i =0 ; i < n; i++) {
			String tmp = br.readLine().trim();
			words[i] = tmp.substring(4, tmp.length()-4);
		}
		
		alpha = new boolean[26];
		alpha['a'-'a'] = true;
		alpha['c'-'a'] = true;
		alpha['i'-'a'] = true;
		alpha['n'-'a'] = true;
		alpha['t'-'a'] = true;
		
		if(k < 5) {
			System.out.println(0);
			return;
		}
		
		//select k-5 letters
		input = new int[k-5];
		answer = 0;
		comb(0,0,k-5);
		
		System.out.println(answer);
	}
	
	
	private static void comb(int start,int depth, int target) {
		if(depth == target) {
			check();
			return;
		}
		
		for(int i = start; i < 26; i++) {
			if(alpha[i] == true) continue;
			alpha[i] = true;
			comb(i+1, depth + 1, target);
			alpha[i] = false;
		}
	}
	
	private static void check() {
		int cnt = 0;
		for(int i = 0; i < words.length; i++) {
			//단어가 antatica인 경우 
			if(words[i].length() == 0) {
				cnt++;
				continue;
			}
			for(int j = 0; j < words[i].length(); j++) {
				if(!alpha[words[i].charAt(j)-'a']) break;
				if(j == words[i].length()-1) cnt++;
			}
		}
		
		answer = Math.max(answer, cnt);
	}
}
