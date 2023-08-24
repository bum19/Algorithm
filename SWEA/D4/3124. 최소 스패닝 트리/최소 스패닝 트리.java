//
import java.io.*;
import java.util.*;

public class Solution {
	
	public static int t, v, e;
	public static long result;
	public static int[] parents;
	public static PriorityQueue<Edge> pq;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			pq = new PriorityQueue<Edge>((e1,e2) ->  {
				return Integer.compare(e1.dist, e2.dist);
			});
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				pq.add(new Edge(idx1, idx2, weight));
			}
			result = 0;
			kruskal();
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void kruskal() {
		parents = new int[v+1];
		for(int i = 1; i <= v; i++) {
			parents[i] = i;
		}
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			if(union(edge.node1, edge.node2)) {
				result += (long)edge.dist;
			}
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
	
	static class Edge{
		int node1;
		int node2;
		int dist;
		
		public Edge(int node1, int node2, int dist) {
			this.node1 = node1;
			this.node2 = node2;
			this.dist = dist;
		}
	}

}
