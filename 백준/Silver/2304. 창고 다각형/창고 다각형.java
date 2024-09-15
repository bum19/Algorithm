/*
 * 예전에 내가푼방식: 제일 긴거부터 탐색
 * 이번에 풀 방식 : 진용방식
 * 시간복잡도 : O(n * maxH)
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int[][] board;
	public static int[][] input;
	public static int n, maxH;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		input = new int[n][2];
		
		maxH = -1;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken());
			input[i][1] = Integer.parseInt(st.nextToken());
			maxH = Math.max(maxH, input[i][1]);
		}
		
		Arrays.sort(input, (a1, a2)->(Integer.compare(a1[0],a2[0])));
		
		int answer =0;
		for(int i = 1; i <= maxH; i++) {
			int left = 0;
			int right = n-1;
			while(left < n) {
				if(input[left][1] >= i) {
					break;
				}
				left++;
			}
			while(right >= 0) {
				if(input[right][1] >= i) {
					break;
				}
				right--;
			}
			if(left <= right) {
				answer += input[right][0]-input[left][0]+1;
			}
		}
		System.out.println(answer);
	}
}
