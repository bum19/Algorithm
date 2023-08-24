//
import java.io.*;
import java.util.*;
//prim 알고리즘
//비방문 정점과 방문정점목록들 사이의 거리를 갱신한다.
public class Solution {
	
	public static int t, v, e;
	public static long result;	//mst 총 가중치
	public static int[] minEdge; //비방문정점들의 방문정점목록과의 거리. 최종적으로 최소값으로 갱신된다.
	public static boolean[] visited; //방문정점인지 확인.
	public static List<int[]>[] adjs; //인접리스트
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			minEdge = new int[v+1];
			visited = new boolean[v+1];
			adjs = new List[v+1];
			for(int i = 1; i <= v; i++) {
				adjs[i] = new ArrayList<int[]>();
			}
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				adjs[idx1].add(new int[] {idx2, weight});
				adjs[idx2].add(new int[] {idx1, weight});
			}
			
			result = 0;
			
			prim();
			
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void prim() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a1,a2) ->{
			return Integer.compare(a1[1],a2[1]);
		}); //크기 2인 정수배열 원소로가짐. 0번인덱스는 정점번호, 1번인덱스는 현재 거리.
		int visitCnt = 0;
		
		
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		minEdge[1] = 0;
		
		pq.offer(new int[] {1, minEdge[1]});
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int minVertex = cur[0];	//현재 미방문정점 중 가장 방문정점목록에 가까운 정점.
			int minDist = cur[1];	//현재 미방문정점까지의 거리.
			
			if(visited[minVertex]) continue; //이미 방문한 정점목록이면 체크하지 않는다.
			
			visited[minVertex] = true;
			result += (long)minDist;	//현재 포함시키는 정점과 연결된 거리 가중치에 더함.
			if(++visitCnt == v)break;	//정점 전부 탐색했으면 탐색 종료
				
			for(int[] adj : adjs[minVertex]) {
				if(!visited[adj[0]] && minEdge[adj[0]] > adj[1]) { //지금 본 간선 길이가 미방문정점과 정점목록사이의 현재거리보다 짧으면 갱신?
					minEdge[adj[0]] = adj[1]; 
					pq.offer(new int[] {adj[0], minEdge[adj[0]]}); //갱신했으면 값 넣어주기.
				}
			}
		}
	}

}
