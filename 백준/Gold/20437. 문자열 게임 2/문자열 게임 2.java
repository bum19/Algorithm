/*
 * 26개짜리 리스트배열만들기
 */
import java.io.*;
import java.util.*;

public class Main {
	public static List<Integer>[] alphabets;// 알파벳별 인덱스를 저장하는 리스트 배열
	public static int k, t, min, max;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		alphabets = new List[26];
		for(int i = 0 ; i< 26; i++) {
			alphabets[i] = new ArrayList<>();
		}
		
		for(int test_case = 1; test_case <= t; test_case++) {
			
			String input = br.readLine().trim();
			k = Integer.parseInt(br.readLine().trim());
			
			for(int i = 0; i < 26; i++) {
				alphabets[i].clear();
			}
		
			//가장 짧은/긴 연속문자열 구하기
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			for(int i = 0; i < input.length(); i++) {
				int curC = input.charAt(i)-'a';
				
				alphabets[curC].add(i);
				
				if(alphabets[curC].size() >= k) {
					//min,max 갱신
					int curLength = alphabets[curC].get(alphabets[curC].size()-1) - alphabets[curC].get(alphabets[curC].size()-k);
					
					if(min > curLength) min = curLength;
					if(max < curLength) max = curLength;
				}
			}
			
			if(min == Integer.MAX_VALUE) {
				sb.append(-1).append('\n');
			}
			else {
				sb.append(min+1).append(' ').append(max+1).append('\n');
			}
		}
		System.out.println(sb);
	}
}