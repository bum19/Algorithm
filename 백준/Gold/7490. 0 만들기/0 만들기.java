import java.io.*;
import java.util.*;
//스택을 이용한 중위 연산 -> 후위 연산. 이때 일반적인 곱하기랑은 다르게 가야함.
public class Main {
	public static char[] arr;
	public static int t, n;
	public static int result;
	public static StringBuilder answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine());
			arr = new char[n + n - 1];
			for(int i = 0; i < arr.length; i += 2) {
				arr[i] = (char)(i/2 + 1 + '0');
			}
			
			answer = new StringBuilder();
			dfs(0);
			sb.append(answer);
			if(test_case != t) {
				sb.append("\n\n");
			}
		}
		System.out.println(sb);
	}
	
	private static void dfs(int depth) {
		if(depth % 2 == 0) {
			dfs(depth+1);
			return;
		}
		
		if(depth == n + n-1) {
			midToPost();
			return;
		}
		
		arr[depth] = ' ';
		dfs(depth+1);
		arr[depth] = '+';
		dfs(depth+1);
		arr[depth] = '-';
		dfs(depth+1);
	}
	private static void midToPost() {
		Stack<Character> postfixStack = new Stack<>();
		char[] seq = new char[arr.length];
		//중위 연산식 -> 후위 연산식
		int arrIdx = 0;
		int seqIdx = 0;
		while(arrIdx < arr.length) {
			if('0' <= arr[arrIdx]  && arr[arrIdx] <= '9') {
				seq[seqIdx++] = arr[arrIdx++];
			}
			else if(arr[arrIdx] == ' ') {
				while(!postfixStack.isEmpty() && postfixStack.peek() == ' ') {
					seq[seqIdx++] = postfixStack.pop();
				}
				postfixStack.push(arr[arrIdx++]);
			}
			else {
				while(!postfixStack.isEmpty()){
					seq[seqIdx++] = postfixStack.pop();
				}
				postfixStack.push(arr[arrIdx++]);
			}
		}
		while(!postfixStack.isEmpty()) {
			seq[seqIdx++] = postfixStack.pop();
		}
		
		cal(seq);
	}
	
	private static void cal(char[] seq) {
		//계산
		Stack<Integer> calStack = new Stack<>();
		for(int i = 0; i < seq.length; i++) {
			if('0' <= seq[i]  && seq[i] <= '9') {
				calStack.push(seq[i] - '0');
			}
			else if(seq[i] == ' ') {
				calStack.push(calStack.pop() + calStack.pop() * 10);
			}
			else if(seq[i] == '+'){
				calStack.push(calStack.pop() + calStack.pop());
			}
			else if(seq[i] == '-'){
				calStack.push(-1 * calStack.pop() + calStack.pop());
			}
			else {
				System.out.println("error, '"+seq[i]+"'");
				return;
			}
		}
		
		if(calStack.pop( ) == 0) {
			if(answer.length() != 0) answer.append('\n'); 
			for(int i = 0; i < arr.length;i++) {
				answer.append(arr[i]);
			}
		}
		return;
	}
}
