import java.io.*;
import java.util.*;

public class Main {
	public static int n, curStart, cur;
	public static int[] answer;
	public static int[] input;
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("/Users/joonbum/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		 
		n = Integer.parseInt(br.readLine().trim());
		
		answer = new int[n];

		Stack<int[]> stack = new Stack<int[]>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
			int cur = Integer.parseInt(st.nextToken());
			
			if(stack.isEmpty() || stack.peek()[1] >= cur) {
				stack.push(new int[] {i,cur});
			}
			else if(stack.peek()[1] < cur) {
				while(!stack.isEmpty() && stack.peek()[1] < cur) {
					answer[stack.pop()[0]] = cur;
				}
				stack.push(new int[]{i,cur});
			}
		}
		
		while(!stack.isEmpty()) {
			answer[stack.pop()[0]] = -1;
		}
		
		for(int i = 0; i < n; i++) {
			if(i != n-1)
				sb.append(answer[i]).append(" ");
			else
				sb.append(answer[i]).append("\n");
		}
		
		System.out.println(sb);
		
	}
}
