import java.io.*;
import java.util.*;
public class Main {
	public static int n,k, count;
	public static int[] coins;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		coins = new int[n];
		
		for(int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine().trim());
		}
		
		for(int i = n-1; i>=0; i--) {
			if(k == 0) break;
			count += k/coins[i];
			k %= coins[i]; 
		}
		
		System.out.println(count);
		
	}

}
