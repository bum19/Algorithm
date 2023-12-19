//
import java.io.*;
import java.util.*;

//1. dfs탐색
public class Main {
	public static final int INF = 1000001;
	public static int n, minDist;
	public static int[][] weight;
	public static int[][] dp;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine().trim());
		
		minDist = Integer.MAX_VALUE;
		weight = new int[n][n];
		visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				weight[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, 0);
		System.out.println(minDist);

	}

	public static void dfs(int cnt, int current, int curDist) {
		if(curDist > minDist) { return;}
		if(cnt == n && current == 0) {
			if(minDist > curDist) minDist = curDist;
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i] && weight[current][i] != 0) {
				visited[i] = true;
				dfs(cnt+1, i, curDist + weight[current][i]);
				visited[i] = false;
			}
		}
	}

}
