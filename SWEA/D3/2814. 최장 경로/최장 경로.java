import java.io.*;
import java.util.*;

public class Solution {
	public static int t, n, m, maxDist;
	public static boolean[][] w;
	public static boolean[] isVisited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int node1, node2;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = new boolean[n+1][n+1];
			isVisited = new boolean[n+1];
			maxDist = -1;
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				node1 = Integer.parseInt(st.nextToken());
				node2 = Integer.parseInt(st.nextToken());
				w[node1][node2] = true;
				w[node2][node1] = true;
			}
			
			for(int i = 1; i <= n; i++) {
				dfs(i,1);
			}

			sb.append("#").append(test_case).append(" ").append(maxDist).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int current, int cnt) {
		isVisited[current] = true;
		
		for(int i = 1; i <= n; i++) {
			if(w[current][i] == true && !isVisited[i]) {
				dfs(i, cnt+1);
			}
		}
		
		isVisited[current] = false;
		
		//갈곳이 없으면 최댓값 갱신.
		if(cnt > maxDist) maxDist = cnt;
	}
}
