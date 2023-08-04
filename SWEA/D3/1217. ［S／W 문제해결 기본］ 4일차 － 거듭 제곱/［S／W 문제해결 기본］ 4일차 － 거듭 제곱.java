import java.io.*;
import java.util.*;
public class Solution {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n, m;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			sc.nextInt();
			n = sc.nextInt();
			m = sc.nextInt();
			sb.append("#").append(test_case).append(" ").append(power(n, m)).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static int power(int n, int m) {
		if(m == 0) {
			return 1;
		}
		else if(m == 1) {
			return n;
		}
		else if(m%2 == 0) {
			return power(n, m/2)*power(n,m/2); 
		}
		else
			return power(n, m/2)*power(n,m/2+1);
		
	}

}
