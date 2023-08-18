
import java.io.*;
import java.util.*;
public class Main {
	public static int n, m;
	public static boolean[] visited;
//	public static List<Integer>[] adj; 
	public static int[][] adj; //정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문. 인접행렬을 사용
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int v;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n+1];
		
		adj = new int[n+1][n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1, idx2;
			idx1 = Integer.parseInt(st.nextToken());
			idx2 = Integer.parseInt(st.nextToken());
			
			adj[idx1][idx2] = 1;
			adj[idx2][idx1] = 1;
		}
		
		visited[v] = true;
		dfs(v);
		
		Arrays.fill(visited, false);
		visited[v] = true;
		
		sb.append("\n");
		bfs(v);
		
		System.out.println(sb);
	}
	
	public static void dfs(int node) {
		sb.append(node).append(" ");
		
		for(int i = 1; i <= n; i++) {
			if(adj[node][i] != 0 && !visited[i]) {
				visited[i] = true;
				dfs(i);
			}
		}
		
	}
	
	public static void bfs(int node) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		
		q.offer(node);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			sb.append(current).append(" ");
			for(int i = 1; i <= n; i++) {
				if(adj[current][i] != 0 && !visited[i]) {
					visited[i] = true;
					q.offer(i);
				}
			}
		}
		
	}

}
