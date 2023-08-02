import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
	public static int[] input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int n, m, startIdx, endIdx, sum = 0;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		input = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n; i++) {
			sum += Integer.parseInt(st.nextToken());
			input[i] = sum;
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			startIdx = Integer.parseInt(st.nextToken()) -1;
			endIdx = Integer.parseInt(st.nextToken()) -1;
			
			if(startIdx == 0) {
				sb.append(input[endIdx]).append("\n");
			}
			else {
				sb.append(input[endIdx] - input[startIdx-1]).append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}