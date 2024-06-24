/*
 * 답보고 다시 품.
 * 큰애부터 줄 세우기. 왼쪽에 큰애만큼의 인덱스위치로 넣으면 된다. 
 * 한번삽입시 최대 1+2+3+4+...+10회 이동이 일어날 수 있으므로, 최대 시간복잡도 55.
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int[] input = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
	
		List<Integer> answer = new ArrayList<>();
		answer.add(n);
		for(int i = n-2; i >= 0; i--) {
			answer.add(input[i], i+1);
		}
		
		//print
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n-1; i++) {
			sb.append(answer.get(i)).append(' ');
		}
		sb.append(answer.get(n-1));
		System.out.println(sb);
	}
}
