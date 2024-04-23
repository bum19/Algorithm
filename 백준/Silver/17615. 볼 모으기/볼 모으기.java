import java.io.*;
import java.util.*;
public class Main {
	
	public static int n, answer;
	public static String input;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		input = br.readLine();
		
		answer = Integer.MAX_VALUE;
		check('B');
		check('R');
		
		System.out.println(answer);
	}
	
	public static void check(char c) {
		int count  = 0;
		int i = 0;
		
		//왼쪽으로 모을경우
		for(i = 0; i < n; i++) {
			if(input.charAt(i) != c) break;
				
		}
		for(; i < n; i++) {
			if(input.charAt(i) == c)
				count++;
		}
		if(answer > count) answer = count;
		
		
		//오른쪽으로 모을 경우
		count  = 0;
		i = n-1;
		for(i = n-1; i >= 0; i--) {
			if(input.charAt(i) != c) break;
				
		}
		for(; i >= 0; i--) {
			if(input.charAt(i) == c)
				count++;
		}
		if(answer > count) answer = count;
	}
}
