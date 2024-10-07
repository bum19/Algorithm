/*
 * 풀이 1. 이분탐색으로 0에 가까워지게 반복하기
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static int[] input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		input = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = n-1;
		
		int min = Integer.MAX_VALUE;
		int minL= 0, minR =0;
		while(l < r) {
			int cur = input[l] + input[r];
			if(Math.abs(cur) < Math.abs(min)) {
				min = cur;
				minL = input[l];
				minR = input[r];
			}
			if(cur < 0) l++;
			else if(cur == 0) {
				System.out.println(input[l]+" "+input[r]);
				return;
			}
			else {
				r--;
			}
		}
		
		System.out.println(minL +" "+ minR);
	}
}
