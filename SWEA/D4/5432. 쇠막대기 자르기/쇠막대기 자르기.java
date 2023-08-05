import java.util.*;
import java.io.*;
//메모리 32,260 kb
//실행시간 194 ms
public class Solution {

//	Stack<E> stack
//	public static Stack<Character> stack = new Stack<Character>();
	public static int cnt, currentOpenParNum;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t;
		String inputStr;
		
		t = Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			inputStr = br.readLine();
			breakStick(inputStr);
			sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
			cnt = 0;
			currentOpenParNum = 0;
		}
		
		System.out.println(sb);
		
	}
	
	private static void breakStick(String pars) {
//		char prePar = stack.push(pars.charAt(0));
		char prePar = pars.charAt(0);
		currentOpenParNum++;
		
		//스택에 괄호 하나씩 넣으면서 탐색
		for(int i = 1; i < pars.length(); i++) {
			//")"만났을경우
			//직전 괄호가 "("면 레이저 쏘기 : "(" pop하고, 스택크기만큼 cnt 증가
			//직전 괄호가 ")"면 스택맨위 "(" pop, cnt 1만큼 증가.
			if(pars.charAt(i)== ')') {
				if(prePar == '(') {
//					stack.pop();
//					cnt += currentOp;
					currentOpenParNum--;
					cnt += currentOpenParNum;
				}
 
				else {
//					stack.pop();
					currentOpenParNum--;
					cnt += 1;
				}
			}
																																																																																																
			//"("만났을경우
			//스택에 넣는다.
			else {
//				stack.push(pars.charAt(i));
				currentOpenParNum++;
			}
			
			//pre값 갱신해주기.
			prePar = pars.charAt(i);
		}
		
//		stack.clear();
	}
	
}
