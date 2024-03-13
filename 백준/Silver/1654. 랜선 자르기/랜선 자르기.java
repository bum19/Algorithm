import java.io.*;
import java.util.*;
/*
이분탐색
가능한 최대값 범위 1~ 가장 큰 막대의 길이
1 ~ 10
최댓값 5일때 n개막대기 만들기 가능? ㅇㅇ 가능 6 ~ 10
8일떄 가능? ㄴㄴ 불가능 6 ~ 7
6일떄 가능? ㄴㄴ 불가능 7 ~ 7
7일때 가능? ㄴㄴ 불가능. 7 ~ 6
시작 > 끝 되면, 탈출.
제일 마지막에 가능했던 값이 답.
시간복잡도 k * log(2^32-1)

이분탐색시 1 + 2^32-1이 int값을 넘는것에 유의해야함.
 */
public class Main {
	public static int maxLen;
	public static int k, n;
	public static Integer[] lans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		lans = new Integer[k];
		long start = 1, end = 0;
		
		for(int i = 0; i < k; i++) {
			lans[i] = Integer.parseInt(br.readLine().trim());
			if(end < lans[i]) end = lans[i];
		}
		
		while(start <= end) {
			long cur = (start+end)/2;
			int count = 0;
			for(int i = 0; i < k; i++) {
				count += lans[i]/cur;
			}
			if(count >= n) {
				maxLen = (int)cur;
				start = cur+1;
			}
			else {
				end = cur - 1;
			}
		}
		
		System.out.println(maxLen);
	}
}
