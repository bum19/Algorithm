//
import java.io.*;
import java.util.*;
public class Main {
	public static int n, m, isAvailable;
	public static List<Integer>[] adj;
	public static boolean[] visited;
//	public static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		adj = new List[n];
		for(int i = 0; i < n; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			
			adj[idx1].add(idx2);
			adj[idx2].add(idx1);
			
		}
		
		for(int i = 0; i < n; i++) {
			if(adj[i].size() == 0) continue; //연결된 정점이 없다 == 친구가 없다.
			Arrays.fill(visited, false);
			visited[i] = true;
			dfs(i,1);
			if(isAvailable == 1) break;
		}
		
		System.out.println(isAvailable);

	}
	
	public static void dfs(int start, int cnt) {
		if(isAvailable == 1) return;
		
		if(cnt == 5) {
			isAvailable = 1;
			return;
		}
		
		for(int next : adj[start]) {
			if(!visited[next]) {
				visited[next] = true;
				dfs(next, cnt+1);
				visited[next] = false;
			}
		}
	}

}
