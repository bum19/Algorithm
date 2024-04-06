import java.io.*;
import java.util.*;

/*
 * 이분탐색
 */

public class Main {
	
	public static int n,m, maxVal, answer;
	public static int[] input; 
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		input = new int[n];
		maxVal = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			if(maxVal < input[i]) maxVal = input[i];
		}
		
		m = Integer.parseInt(br.readLine());
		
		
		int i = 1;
		int j = m;
		while(i <= j) {
		  int curLimit = (i+j)/2;
		  if(check(curLimit)) {
			  answer = curLimit;
			  i = curLimit+1;
		  }
		  else {
			  j = curLimit-1;
		  }
		}
		
		if(answer > maxVal) answer = maxVal;
		System.out.println(answer);
	}
	
	//현재 상한선으로 가능한지 확인.
	public static boolean check(int limitVal) {
		int total = 0;
		for(int i = 0 ; i < n; i++) {
			//limitVal보다 크면 lmitVal만 쓰기
			if(input[i] > limitVal) {
				total += limitVal;
			}
			else
				total += input[i];
		}
		
		if(total <= m) {
			return true;
		}
		else {
			return false;
		}
	}
}

