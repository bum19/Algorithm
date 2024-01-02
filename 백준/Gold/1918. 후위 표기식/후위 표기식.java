import java.io.*;
import java.util.*;

public class Main {
	public static String input;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		input = br.readLine().trim();
		Map<Character, Integer> priority = new HashMap<>();
		Stack<Character> stack = new Stack<>();
		priority.put('+', 0);
		priority.put('-', 0);
		priority.put('*', 1);
		priority.put('/', 1);
		
		for(int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			if(cur >= 'A' && cur <= 'Z') {
				sb.append(cur);
			}
			else if(cur == '(') {
				stack.push(cur);
			}
			else if(cur == ')') {
				char tmp;
				while((tmp = stack.pop()) != '(') {
					sb.append(tmp);
				}
			}
			else { //연산자일경우.
				if(stack.isEmpty()) stack.push(cur);
				else {
					char tmp;
					while(!stack.isEmpty()) {
						tmp = stack.peek();
						//괄호거나 cur 우선순위가 더 높으면 종료.
						if(tmp == '(' || priority.get(tmp) < priority.get(cur)) {
							break;
						}
						else { //아니면 안에는거 꺼내서 쓰기
							sb.append(stack.pop());
						}
					}
					stack.push(cur);
				}
			}//end 연산자일경우
			
		}
		
		//스택에 남은거 다 뽑기
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.println(sb);
	}
}
