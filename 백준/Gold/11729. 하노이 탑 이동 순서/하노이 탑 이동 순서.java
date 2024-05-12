/*
 * 반갑다 하노이
 * 추억이다 하노이
 */
import java.io.*;
public class Main {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		sb.append((1<<n)-1).append("\n");
		hanoi(n, 1, 3, 2, sb);
		
		System.out.println(sb);
	}
	
	private static void hanoi(int n, int from, int to, int tmp, StringBuilder sb) {
		if(n == 0) return;
		
		//n-1개이동
		hanoi(n-1, from, tmp, to, sb);
		
		//1개이동
		sb.append(from).append(' ').append(to).append('\n');
		
		//n-1개 이동
		hanoi(n-1, tmp, to, from, sb);
	}
}
