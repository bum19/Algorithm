/*
 *  투포인터 풀
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(sol(arr));
	}
	
	private static int sol(int[] arr) {
		int l = 0; int r = arr.length - 1;
		int answer = Integer.MAX_VALUE;
		while(l < r) {
			int cur = arr[l] + arr[r];
			if(Math.abs(cur) < Math.abs(answer))
				answer = cur;
			if(cur > 0) r--;
			else l++;
		}
		return answer;
	}
}
