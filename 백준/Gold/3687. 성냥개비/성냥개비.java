/*
 * ! 답 봄. 추후 다시 풀자.
 * 최댓값: 1과 7로 이루어진숫자. 성냥개비 짝수면 2로 나눈 몫만큼 1을 나열한 값, 홀수면 맨앞에7을놓고 (성냥개비개수-3)/2만큼 1을 나열한값.
 * 
 * 최솟값: dp[i] = i개를 쓸때, 만들 수 있는 최소 성냥개수.
 * 초기값 설정을 잘 해야한다.
 * 떠올리지 못한이유
 * 1: 우선, 최댓값이 long타입안에 들어갈 수 있다는 사실을 인지하지 못함.(dp로 greedy하게 가면, 최솟값 못해도 15자리수 안에서 끝남.)
 * 2: dp 초기 값 세팅에서, 절었음
 * 3: 값을 갱신할때, dp[k]에 대해, dp[k-1]과 비교, dp[k-5]와 비교.. 이런식으로 비교하는걸 못떠올림.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static final long INF = Long.MAX_VALUE;
	public static int t, n;
	public static String max;
	public static long[] dp; //dp[i] = i개의 성냥개비로 만들수있는 최소숫자. dp[n] = min(dp[n], dp[n-value]*10 + num[value]);
	public static int[] seongNum = {-1,-1,1,7,4,2,0,8};// 인덱스 = 성냥개비, 값 = 만들수 있는 제일 작은 한자리 숫자.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answer = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <=t; test_case++) {
			n = Integer.parseInt(br.readLine());
			StringBuilder sb = new StringBuilder();
			//최댓값 구하기
			if(n % 2 == 0) {
				for(int j = 0; j < n/2; j++) {
					sb.append(1);
				}
			}
			else {
				sb.append(7);
				for(int j = 0; j < (n-3)/2; j++) {
					sb.append(1);
				}
			}
			max = sb.toString();
			
			//최솟값 구하기.
			sb = new StringBuilder();
			if(n > 7)
				dp = new long[n+1];
			else
				dp = new long[8];
			
			for(int i = 0; i <= n; i++) {
				dp[i] = INF;
			}
			dp[2] = 1; dp[3] = 7; dp[4] = 4; dp[5] = 2; dp[6] = 6; dp[7] = 8;
			
			for(int i = 8; i <= n; i++) {
				for(int j = 2; j <= 7; j++) {
					if(dp[i-j] != INF) {
						if(dp[i] > dp[i-j]*10 + seongNum[j]) {
							dp[i] = dp[i-j]*10 + seongNum[j];
						}

					}
				}
			}
			
			answer.append(dp[n]).append(' ').append(max).append('\n');
		}
		System.out.println(answer);
	}	
	
}
