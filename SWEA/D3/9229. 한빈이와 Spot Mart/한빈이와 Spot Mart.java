//메모리 :
//실행시간 :
import java.util.*;
import java.io.*;

public class Solution{
	static int t, n, m, max;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			arr = new int[n];
			max = -1;
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 0; i < n-1; i++) {
				for(int j = i+1; j < n; j++ ) {
					int tmp = arr[i] + arr[j];
					if(tmp <= m)
						max = Math.max(tmp, max);
				}
			}
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		
		System.out.println(sb);
	}

}
