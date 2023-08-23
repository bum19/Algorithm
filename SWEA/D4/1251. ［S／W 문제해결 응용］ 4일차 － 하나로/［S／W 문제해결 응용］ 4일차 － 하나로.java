//
import java.io.*;
import java.util.*;
//간선개수 n(n-1)/2 = 최대 49만9천개
//O(ElogE) = 50만 * 25 12 6 3 2 1 5000 2500 1250. 무조건 프림쓰긴해야겠따.
//일단 크루스칼로 풀어보자.
public class Solution {
	public static int t, n;
	public static double e;
	public static long totalDist;
	public static int[][] islands;
	public static int[] parents;
	public static PriorityQueue<Edge> edges;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			
			n = Integer.parseInt(br.readLine().trim());
			islands = new int[n+1][2]; //[n][0] : x좌표, [n][1] : y좌표
			edges = new PriorityQueue<Edge>();
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				islands[i][0] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= n; i++) {
				islands[i][1] = Integer.parseInt(st.nextToken());
			}
			
			e = Double.parseDouble(br.readLine().trim());
			
			//간선 설정
			for(int i = 1 ; i <= n; i++) {
				for(int j = i+1; j <= n; j++) {
					edges.add(new Edge(i, j, dist(i, j)));
				}
			}
			
			totalDist = 0;
			
			kruskal();
			
			sb.append("#").append(test_case).append(" ").append(String.format("%.0f", totalDist*e)).append("\n");
		}
		System.out.println(sb);
		
	}
	
	private static void kruskal() {
		parents = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
			
		int edgeSelectCnt = 0;
		while(!edges.isEmpty()) {
			//현재간선의 두 정점이 같은 그룹인지 확인.
			 Edge edge = edges.poll();
			 int node1 = edge.node1;
			 int node2 = edge.node2;
			 
			 if(union(node1, node2)) {
				 totalDist += edge.dist;
				 edgeSelectCnt++;
			 }
			 
			 if(edgeSelectCnt == n-1) break;
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
	
	public static long dist(int i, int j) {
		long  width = islands[i][0] - islands[j][0];
		long  height = islands[i][1] - islands[j][1];
		return width*width + height*height;
	}
	
	public static class Edge implements Comparable<Edge>{
		int node1;
		int node2;
		long dist;
		
		public Edge(int node1, int node2, long dist) {
			this.node1 = node1;
			this.node2 = node2;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge e) {
			return Long.compare(dist, e.dist);
		}
	}
}
