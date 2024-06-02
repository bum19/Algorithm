/*
 * 1. 연결그래프인지 비연결그래프인지 확인
 * 2. 간선으로 MST 만들기
 * 3. MST만들면서 간선집합 만들기
 * 4. 간선 하나 제거
 * 5. 남은 간선들로 인접리스트만들기
 * 6. BFS돌면서 출력 만들기
 * 7. 서로 다른 크기면 안됨<< 주의
 */
import java.io.*;
import java.util.*;

public class Main {

	public static int n, m;
	public static Edge[] edges;
	public static boolean[] visited;
	public static int[] parents;
	
	public static List<Edge> filteredEdges;
	public static List<Edge>[] adjs; //adjs[i].get(0~k) i노드에 인접한 간선들
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder finalSb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edges = new Edge[m+1];
		
		for(int i = 1; i <= m ; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(u,v,i);
		}
		//input done
		
		//정점 한개일경우 종료
		if(n == 1) {
			System.out.println(-1);
			return;
		}
		
		//init
		int visitCnt = 0;
		visited= new boolean[n+1];
		filteredEdges = new ArrayList<>();
		parents = new int[n+1];
		for(int i = 1; i<=n; i++)
			parents[i] = i;
		
		//mst만들기(kruskal이었나?)
		for(int i = 1; i<=m; i++) {
			if(union(edges[i].n1, edges[i].n2)) { //간선이 mst에 포함되면
				filteredEdges.add(edges[i]);
			}
			if(!visited[edges[i].n1]) {
				visited[edges[i].n1] = true;
				visitCnt++;
			}
			if(!visited[edges[i].n2]) {
				visited[edges[i].n2] = true;
				visitCnt++;
			}
			if(visitCnt == n) break;
		}
		
		//parent 3명 이상이면 -1출력
		Set set = new HashSet<>();
		for(int i = 1; i <=n ; i++) {
			set.add(find(i));
		}
		if(set.size()>2) {
			System.out.println(-1);
			return;
		}
		
		//연결그래프면 하나 제거
		if(set.size() == 1) {
			filteredEdges.remove(filteredEdges.size()-1);
		}
		
		//MST에 사용된 간선만 가지고 인접리스트 만들기.
		adjs = new List[n+1]; //0번 idx는 버린다.
		for(int i = 1; i <= n; i++) {
			adjs[i] = new ArrayList<>();
		}
		for(int i = 0; i < filteredEdges.size(); i++) {
			int n1 = filteredEdges.get(i).n1;
			int n2 = filteredEdges.get(i).n2;
			
			adjs[n1].add(filteredEdges.get(i));
			adjs[n2].add(filteredEdges.get(i));
		}
		
		//BFS로 출력값만들기
		visited = new boolean[n+1];
		int cnt = 0;
		for(int i = 1; i<= n; i++) {
			if(!visited[i]) {
				int nodeNum = 0;
				StringBuilder nodeSb = new StringBuilder();
				StringBuilder edgeSb = new StringBuilder();
				nodeSb.append(i).append(' ');
				
				Queue<Integer> q = new ArrayDeque<>();
				visited[i] = true;
				nodeNum++;
				q.add(i);
				while(!q.isEmpty()) {
					int cur = q.poll();
					for(Edge e: adjs[cur]) {
						int nextNode = e.n1==cur? e.n2:e.n1;
						if(!visited[nextNode]) {
							visited[nextNode] = true;
							nodeSb.append(nextNode).append(' ');
							edgeSb.append(e.edgeNum).append(' ');
							nodeNum++;
							q.add(nextNode);
						}
					}
				}
				if(cnt == 0) {
					//서로 같은 크기면 안됨
					if(nodeNum == n- nodeNum) {
						System.out.println(-1);
						return;
					}
					finalSb.append(nodeNum).append(' ').append(n-nodeNum).append('\n').append(nodeSb).append('\n');
					if(edgeSb.toString().length() !=0 ) {
						finalSb.append(edgeSb).append('\n');
					}
				}
				else
					finalSb.append(nodeSb).append('\n').append(edgeSb);
				cnt++;
			}
		}
		System.out.print(finalSb);
	}
	
	private static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA == rootB) return false;
		
		parents[rootA] = rootB;
		return true;
	}
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

	
	public static class Edge{
		int n1, n2, edgeNum;
		public Edge(int n1, int n2, int edgeNum) {
			this.n1 = n1;
			this.n2 = n2;
			this.edgeNum = edgeNum;
		}
		
		public String toString() {
			return edgeNum+"번 : "+n1+"-"+n2;
		}
	}
}
