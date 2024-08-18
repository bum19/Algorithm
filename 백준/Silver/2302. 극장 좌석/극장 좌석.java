/*
 * 자리는 a,b가 서로 교체하는 경우만 가능하다.
 * 한번 교체한 사람이 다시 교체되는 경우는 불가능하다.
 * n명이 연속으로 앉아있을때, 가능한 조합 구하기 --> 피보나치
 * vip를 기준으로 구한 조합값들을 다 곱하기
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[] count;
	public static int n,m;
	public static void main(String[] args) throws IOException{
		init();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		int preNum = 0;
		int answer = 1;
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			answer *= count[num-preNum-1];
			preNum = num;
		}
		answer *= count[n-preNum];
		System.out.println(answer);
	}
	
	
	private static void init() {
		count = new int[41];
		count[0] = 1;
		count[1] = 1;
		count[2] = 2;
		for(int i = 3; i<=40; i++) {
			count[i] = count[i-2] + count[i-1];
		}
	}
	
}
