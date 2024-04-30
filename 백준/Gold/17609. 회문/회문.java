import java.io.*;
import java.util.*;
/*
 * 메모리초과
 * lcs로 유사회문 검사하면 안됨.
 * greedy한 접근
 */
public class Main {
	public static int t;
	public static String input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= t; test_case++) {
			input = br.readLine();
			sb.append(check(input)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int check(String input) {
		
		boolean isDifferent = false; //둘이 다른거 존재.
		int left = 0; int right = input.length()-1;
		
		while(left < right) {
			//처음 다른거 나왔을때, 일단 break
			if(input.charAt(left) != input.charAt(right)) {
				isDifferent = true;
				break;
			}
			
			left++;
			right--;
		}
		
		if(!isDifferent) return 0; //달랐던적없으면 회문
		
		//왼쪽 제거
		int left1 = left+1;
		int right1 = right;
		
		isDifferent = false;
		while(left1 < right1) {
			if(input.charAt(left1) != input.charAt(right1)) {
				isDifferent = true;
				break;
			}
			left1++;
			right1--;
		}
		
		if(!isDifferent) return 1;
		
		//오른쪽 제거
		int left2 = left;
		int right2 = right-1;
		
		isDifferent = false;
		while(left2 < right2) {
			if(input.charAt(left2) != input.charAt(right2)) {
				isDifferent = true;
				break;
			}
			left2++;
			right2--;
		}
		
		if(!isDifferent) return 1;
		
		return 2;
	}
}
