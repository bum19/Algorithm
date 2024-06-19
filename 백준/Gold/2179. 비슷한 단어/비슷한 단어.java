/*
 * 모든 단어별로, 한글자, 두글자, ... 100글자에 대해 키를 설정하고, value에 해당 단어의 인덱스를원소로가지는 리스트에 추가.
 * 해당 자료구조는 최대 100 * 20000 == 4000000크기를 가질 수 있다.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n, wordIdx1, wordIdx2;
	public static String[] words;
	public static Map<String, List<Integer>> map; // String의 접두사를 갖는 단어 리스트
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		words = new String[n];
		map = new HashMap<>();
		
		for(int i = 0; i < n; i++) {
			String input = br.readLine();
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < input.length(); j++) {
				sb.append(input.charAt(j));
				String key = sb.toString();
				if(map.get(key)== null) {
					map.put(key, new ArrayList<>());
				}
				map.get(key).add(i);
			}
			
			words[i] = input;
		}//input done
		
		
		
		//check similar words
		int maxM = 0;
		wordIdx1= -1;
		wordIdx2 = -1;
		
		for(int i = 0; i < n;  i ++) {
			String cur = words[i];
			//maxM보다 적은길이의 단어 들어오면 스킵.
			if(maxM >= cur.length()) continue;
			int curM = 0;
			int idx1 = i;
			int idx2 = -1;
			StringBuilder sb = new StringBuilder();
			for(int j = 0; j < cur.length(); j++) {
				sb.append(cur.charAt(j));
				String key = sb.toString();
				if(map.get(key).size() > 1) {
					curM++;
					idx2 = map.get(key).get(0) == i?map.get(key).get(1):map.get(key).get(0);
				}
				else {
					break;
				}
			}
			
			if(maxM < curM) {
				maxM = curM;
				wordIdx1= idx1;
				wordIdx2 = idx2;
			}
		}
		
		//아예 겹치는 단어없는경우, 인덱스 0, 1 단어 출력
		if(maxM == 0) {
			wordIdx1 = 0;
			wordIdx2 = 1;
		}
		
		System.out.println(words[wordIdx1]);
		System.out.println(words[wordIdx2]);
	}
}
