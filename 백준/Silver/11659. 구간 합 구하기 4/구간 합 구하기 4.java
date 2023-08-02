import java.util.*;
public class Main {
	public static int n,m;
	public static int[] input;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int n, m, startIdx, endIdx, sum = 0;
		
		n = sc.nextInt();
		m = sc.nextInt();
		input = new int[n];
		
		for(int i = 0 ; i < n; i++) {
			sum += sc.nextInt();
			input[i] = sum;
		}
		
		for(int i = 0; i < m; i++) {
			startIdx = sc.nextInt() - 1 ;
			endIdx = sc.nextInt() - 1;
			
			if(startIdx == 0) {
				sb.append(input[endIdx]).append("\n");
			}
			else {
				sb.append(input[endIdx] - input[startIdx-1]).append("\n");
			}
			
		}
		
		System.out.println(sb);
	}
}