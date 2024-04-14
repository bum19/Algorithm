import java.io.*;
import java.util.*;
/*
 * 1. replace 함수를 쓰면 새로운 string이 생성되어서 메모리초과
 *  새로운 String을 만들지 말자.
 * 2. 폭발시키기위해 한번 폭발시키고 전체 문자열 탐색하면 시간초과.
 *  폭발하면, 문자열 탐색하는 인덱스를 뒤로 민다.
 * 3.여전히 중복으로 탐색하는 부분이 존재해, 시간초과.
 *  문자별로 폭발 문자열의 어느 부분까지 만족하는지 저장한다. (뒤에서부터 고려)
 * 4.삭제시 연산이 오래걸림. 스택에 새롭게 결과를 저장하는 방식으로 진행함.(힌트봄)
 */
public class Main {
	public static String str;
	public static String bombStr;
	public static Stack<Data> stack;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		str = br.readLine().trim();
		bombStr = br.readLine().trim();
				
		stack = new Stack<Data>();
		int bombIdx = 0;
		
		//입력받은 문자열 순회
		for(int i = 0 ; i < str.length(); i++) {
			char strC = str.charAt(i);
			char bombC = bombStr.charAt(bombIdx);
			int satisfyIdx = -1;
			
			//문자 일치하는지 확인.
			if(strC != bombC) {
				bombIdx = 0;
				bombC = bombStr.charAt(bombIdx);
			}
			
			//바뀐 bombIdx로 재검사.
			if(strC == bombC) {
				satisfyIdx = bombIdx;
				bombIdx++;
			}
			
			//폭발 문자열 전부 일치하면, 스택에 있던 폭발문자열 전부 제거.
			if(bombIdx == bombStr.length()) {
				for(int j = 0; j < bombStr.length()-1; j++) {
					stack.pop();
				}
				
				//bombIdx 초기화.
				if(!stack.isEmpty() && stack.peek().satisfyIdx != -1) {
					bombIdx= stack.peek().satisfyIdx + 1;
				}
				else {
					bombIdx = 0;
				}
				
			}
			else {
				stack.push(new Data(strC, satisfyIdx));
			}
		}
		
		
		if(stack.isEmpty()) {
			System.out.println("FRULA");
		}
		else {
			StringBuilder sb = new StringBuilder();
			makeString(sb);
			System.out.println(sb);
		}

	}
	
	public static void makeString(StringBuilder sb) {
		if(stack.isEmpty()) return;
		char c = stack.pop().c;
		makeString(sb);
		sb.append(c);
	}
	
	public static class Data{
		char c;
		int satisfyIdx; //현재 문자가 폭발문자열의 어느 인덱스와 일치하는지 저장.
		public Data(char c, int satisfyIdx) {
			this.c = c;
			this.satisfyIdx = satisfyIdx;
		}
	}
	
}
