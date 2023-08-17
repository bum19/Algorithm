//
import java.io.*;
import java.util.*;
public class Solution {
	static int t, n, minDist;
	static int[][] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			
			n = Integer.parseInt(br.readLine().trim());
			arr = new int[n+2][2]; //회사 + 고객 + 집 좌표
			
			st = new StringTokenizer(br.readLine());
			arr[0][0] = Integer.parseInt(st.nextToken());
			arr[0][1] = Integer.parseInt(st.nextToken());
			arr[n+1][0] = Integer.parseInt(st.nextToken());
			arr[n+1][1] = Integer.parseInt(st.nextToken());
			
			for(int i = 1; i <= n; i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			minDist = Integer.MAX_VALUE;
			find();
			sb.append("#").append(test_case).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void find() {
		int[][] tmpArr = new int[n+2][2]; //모든 경우의수 저장할 배열 선언
		tmpArr[0][0] = arr[0][0]; //회사 x좌표 입력
		tmpArr[0][1] = arr[0][1]; //회사 y좌표 입력
		tmpArr[n+1][0] = arr[n+1][0]; //집 x 좌표 입력
		tmpArr[n+1][1] = arr[n+1][1];
		
		int[] p = new int[n];
		for(int i = 0; i < n; i++) {
			p[i] = i+1;
		}
		
		do {
			for(int i = 0; i < n; i++) {
				tmpArr[i+1][0] = arr[p[i]][0];
				tmpArr[i+1][1] = arr[p[i]][1];
			}
			
			minDist = Math.min(calc(tmpArr) , minDist);
		}while(NP(p));
		
		
	}
	
	private static boolean NP(int[] p) {
		int i = n-1, j = n-1, k = n-1;
		
		while(i>0 && p[i-1] >= p[i]) i--;
		
		if(i==0) return false;
		
		while(p[i-1] >= p[j]) j--;
		swap(p, i-1, j);
		
		while(i < k) {
			swap(p,i++,k--);
		}
		
		return true;
	}
	
	private static int calc(int[][] tmpArr) {
		int sum = 0;
		for(int i = 0; i < n+1; i++) {
			sum += Math.abs(tmpArr[i+1][0] - tmpArr[i][0]) + Math.abs(tmpArr[i+1][1] - tmpArr[i][1]);
		}
		
		return sum;
	}
	
	private static void swap(int[] p, int a, int b) {
		int tmp = p[a];
		p[a] = p[b];
		p[b] = tmp;
	}

}
