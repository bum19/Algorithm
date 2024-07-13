/*
 * 1)a+b <= N
 * 	a >= b
 * 		a == 1
 * 		b, 1,1,1,..., b-1,b-2, ... 1
 *  	a != 1	
 *  	1,1,1, ..., 1, 2, 3, ... a-1, a, b-1, b-2, ... 1
 *  a < b
 *  1, 1, 1, ..., 1, 2, 3, ... a-1, b, b-1, b-2, ... 1
 *   
 * 2)a+b >= N
 * 	
 * 	a + b -1 != n
 * 	-1
 * 
 *  a >= b
 *  1, 2, 3, ... a, b-1 ,b-2 ,b-3, ... 1
 * 
 *  a < b
 *  1, 2, 3, ... a-1, b, b-1, b-2, ... 1
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n, a, b;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		
		if(a+b <= n) {
			if(a == 1) {
				sb.append(b).append(' ');
				for(int i = 0; i < n - (a+b-1); i++) {
					sb.append(1).append(' ');
				}
				for(int i = b-1; i >= 1; i--) {
					sb.append(i).append(' ');
				}
			}
			else {
				for(int i = 0; i < n - (a+b-1); i++) {
					sb.append(1).append(' ');
				}
			
				for(int i = 1; i <= a-1; i++) {
					sb.append(i).append(' ');
				}
				
				if(a >= b) sb.append(a).append(' ');
				else	   sb.append(b).append(' ');
					
				for(int i = b-1; i >= 1; i--) {
					sb.append(i).append(' ');
				}
			}
	
		}
		else {
			if(a + b - 1 != n){
				System.out.println(-1);
				return;
			}
			
			for(int i = 1; i <= a-1; i++) {
				sb.append(i).append(' ');
			}
			
			if(a >= b) sb.append(a).append(' ');
			else	   sb.append(b).append(' ');
				
			for(int i = b-1; i >= 1; i--) {
				sb.append(i).append(' ');
			}			
		}
		
		System.out.println(sb);
	}
}
