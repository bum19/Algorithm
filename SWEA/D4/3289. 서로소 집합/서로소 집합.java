
import java.io.*;
import java.util.*;
public class Solution {
	public static int t, n, m;
	public static int[] parents;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t ; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			make();
			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int state = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(state == 0) {
					union(a, b);
				}
				else if(state == 1) {
					sb.append(check(a, b));
				}
			}
			
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static void make() {
		parents = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		return true;
	}
	
	private static int check(int a, int b) {
		if(find(a) == find(b)) return 1;
		else return 0;
	}
	

}
