//
import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 300001;
	public static int V, E, start;
	public static int[] dist;
	public static List<int[]>[] adjs;
	public static PriorityQueue<int[]> pq;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(br.readLine().trim());
		
		dist = new int[V+1]; //0번인덱스는 사용하지 않는다.
		adjs = new List[V+1]; //0번인덱스는 사용하지 않는다.
		
		for(int i = 1; i <= V; i++) {
			adjs[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int idx1 = Integer.parseInt(st.nextToken());
			int idx2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjs[idx1].add(new int[] {weight, idx2});
		}
		
		dijk(start);
		for(int i = 1; i <= V; i++) {
			if(dist[i] == INF) System.out.println("INF");
			else			   System.out.println(dist[i]);
		}

	}
	
	public static void dijk(int start) {
		pq = new PriorityQueue<>( (a1, a2) -> {
			return Integer.compare(a1[0], a2[0]);
		});
		
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.offer(new int[] {0, start});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[1];
			int curDist = cur[0];
			
			if(dist[curNode] != curDist) continue; //제일 최신 갱신값 아니면 해당 큐에서뽑은값 무시
			
			for(int[] adj :adjs[curNode]) {
				int node = adj[1];
				int nodeDist = adj[0];
				//dist거리보다 뽑아서 더한게 더 작으면 갱신
				if(dist[node] > dist[curNode] + nodeDist) {
					dist[node] = dist[curNode] + nodeDist;
					pq.offer(new int[] {dist[node], node});
				}
			}
		}
	}
}
