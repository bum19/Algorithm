/*
 * 정직하게 세면 O(n*(n+1)/2) == O(n^2) == 100억이라 안됨!
 * 
 * 투포인터 활용
 * 
 * 착안 아이디어 1)
 * 1 2 3 4 5 에서
 * 1 , 12, 123, 1234, 12345가 가능하면, 2, 23, 234, 2345, ...3,34,345, 4,45 , 5 모두 가능하다는점에서 착안.
 * 
 * 디버깅 : 항상 자료형 범위를 생각하자.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,lIdx;
	public static long cnt;
	public static int[] lastIdx; //lastIdx[k] : k가 마지막으로 나온 인덱스
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		//init
		lastIdx = new int[100002];
		Arrays.fill(lastIdx, -1);
		cnt = 0;
		lIdx = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0;  i < n; i++) {
			int curNum = Integer.parseInt(st.nextToken());
			if(lastIdx[curNum] < lIdx) { //중복된 숫자가 안나왔을때
				lastIdx[curNum] = i;
			}
			else { //중복된 숫자가 나왔을때
				
				//중복전까지 가능했던 경우의 수 더하기
				cnt += ((long)(i-lIdx)*(i-lIdx + 1))/2 - ((i-lastIdx[curNum]-1)*(i-lastIdx[curNum]))/2;
				
				//왼쪽 인덱스 갱신
				lIdx = lastIdx[curNum] + 1;
				
				//lastIdx 갱신
				lastIdx[curNum] = i;
			}
		}
		
		//탐색이 끝나면, 마저 더하기
		cnt += (long)(n-1 - lIdx + 1)*(n-1 - lIdx + 1 + 1) /2;
		
		System.out.println(cnt);
	}
}
