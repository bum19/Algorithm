import java.io.*;
import java.util.*;
/*
 * 매번 슬라임중 가장 작은것 2개를 곱한다.
 */
public class Main {
	public static int t,n;
	public static PriorityQueue<Long> slimes;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		t =  Integer.parseInt(br.readLine());
		
		for(int test_case = 1; test_case <=t; test_case++) {
			n = Integer.parseInt(br.readLine());
			slimes = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n; i++) {
				slimes.add(Long.parseLong(st.nextToken()));
			}
			
			if(n == 1) {
				sb.append(1).append("\n");
				continue;
			}
			
			long cost = 1;
			while(slimes.size() > 1) {
				long slime1 = slimes.poll();
				long slime2 = slimes.poll();
				long energy = slime1 * slime2;
				cost *= energy % 1000000007;
				cost %= 1000000007;
				slimes.add(energy);
			}
			sb.append(cost).append("\n");
		}
		
		System.out.println(sb);
	}
}
