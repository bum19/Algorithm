/*
 * n이 10이므로, 멍청하게 순열떄려도 10! * 10^2(확인시간)이므로 충분히 시간내에 확인가능
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static int[] arr;
	public static int[] input;
	public static boolean[] visited;
	public static boolean isAnswerFound;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		input = new int[n];
		visited = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		isAnswerFound = false;
		perm(0);
	}
	
	
	private static void perm(int depth) {
		if(isAnswerFound) return;
		
		if(depth == n) {
			check();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[depth] = i;
				perm(depth+1);
				visited[i] = false;
			}
		}
	}
	
	private static void check() {
		for(int i = 0 ; i< n; i++) {
			int curH = arr[i];
			//현재 키가, 왼쪽에 몇명이나 있나 확인.
			int tallCnt = 0;
			for(int j = 0; j < i; j++) {
				if(arr[j] > curH)
				tallCnt++;
			}
			if(tallCnt != input[curH]) {
				return;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			if(i != n-1) {
				sb.append(arr[i]+1).append(' ');
			}
			else
				sb.append(arr[i]+1);
		}
		
		System.out.println(sb);
		isAnswerFound = true;
	}
}
