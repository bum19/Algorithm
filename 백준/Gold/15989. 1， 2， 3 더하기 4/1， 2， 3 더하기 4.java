import java.io.*;
import java.util.*;
/*
 * 동전 dp응용.
 * 가능하면 cnt 증가시키기.
 */
public class Main {
	public static int[] count;
	public static int n,t;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine().trim());
			count = new int[n+1];
			count[0] = 1;
			for(int i = 1; i <= 3; i++) {
				for(int j = 0; j <= n; j++) {
					if(j-i >=0 && count[j-i] != 0) {
						count[j] += count[j-i];
					}
				}
			}

			sb.append(count[n]).append('\n');
		}
		System.out.println(sb);
	}
}
