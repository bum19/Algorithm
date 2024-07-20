/*
 * 왼쪽에서한번, 오른쪽에서 한번 스택을 이용하여 값 채우기
 * 출력번호는 가까운순, 작은번호순
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[] input;
	public static Data[] datas;
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		
		input = new int[n];
		datas = new Data[n];
		for(int i = 0; i < n; i++) {
			datas[i] = new Data();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		
		
		Stack<int[]> stack = new Stack<>(); //idx와 input을 넣는다.
		//왼쪽 탐색
		for(int i = 0; i < n; i++) {
			while(!stack.isEmpty()) {
				if(stack.peek()[1] <= input[i]) {
					stack.pop();
				}
				else {
					break;
				}
			}
			
			datas[i].lCnt = stack.size();
			if(stack.size() != 0) datas[i].lnum = stack.peek()[0];
			
			stack.push(new int[] {i+1,input[i]});
		}
		
		//오른쪽 탐색
		stack.clear();
		for(int i = n-1; i >= 0; i--) {
			while(!stack.isEmpty()) {
				if(stack.peek()[1] <= input[i]) {
					stack.pop();
				}
				else {
					break;
				}
			}
			
			datas[i].rCnt = stack.size();
			if(stack.size() != 0) datas[i].rnum = stack.peek()[0];
			
			stack.push(new int[] {i+1,input[i]});
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(datas[i].lCnt + datas[i].rCnt);
			if(datas[i].lnum == 1000000 && datas[i].rnum == 1000000) {				
				sb.append("\n");
			}
			else {
				if(Math.abs(datas[i].rnum - (i+1)) < Math.abs(datas[i].lnum - (i+1))) {
					sb.append(" ").append(datas[i].rnum).append("\n");
				}
				else {
					sb.append(" ").append(datas[i].lnum).append("\n");
				}
			}
		}
		System.out.println(sb);
	}
	
	public static class Data{
		int lnum= 1000000, lCnt = 0, rnum= 1000000, rCnt = 0;
	}
}
