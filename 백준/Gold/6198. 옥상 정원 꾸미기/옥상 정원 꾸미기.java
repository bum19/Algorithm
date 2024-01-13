import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[] input;
	public static int[] output;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		input = new int[n];
		output = new int[n];
		for(int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(br.readLine().trim());
			input[i] = tmp;
		}
		
		Stack<int[]> stack = new Stack<>();
		long answer = 0;
		for(int i = n-1; i>= 0; i--) {
			if(stack.isEmpty() || stack.peek()[1] >= input[i]) {
				output[i] = 0;
				stack.push(new int[]{i,input[i]});
			}
			else {
				while(!stack.isEmpty() && stack.peek()[1] < input[i]) {				
					int idx = stack.pop()[0];
					output[i] += output[idx];
					output[i] += 1; //idx번 건물 포함.
				}
				stack.push(new int[] {i, input[i]});
				answer += output[i];
			}
		}
		System.out.println(answer);
	}

}
