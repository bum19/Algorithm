/*
 * bfs, 단, pq를 이용해 가까운 녀석부터 탐색하기
 * test_case를 보고, 시작점 visited 처리 안한것을 깨달음
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int t,n,d,c;
	public static boolean[] visited;
	public static List<Data>[] adjs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			adjs = new List[n+1];
			for(int i = 1; i<= n; i++) {
				adjs[i] = new ArrayList<Data>();
			}
			
			for(int i = 0; i < d; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				adjs[b].add(new Data(a,s));
			}
			//input done
			
			int[] answer = sol();
			
			sb.append(answer[0]).append(" ").append(answer[1]).append("\n");
		}
		System.out.println(sb);
	}
	
	public static int[] sol() {
		visited = new boolean[n+1];
		int cnt = 1;
		int time = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>( (d1,d2) -> Integer.compare(d1[1],d2[1])); //node, infestedTime
		
		visited[c] = true;
		for(int i = 0; i < adjs[c].size(); i++) {
			pq.add(new int[] {adjs[c].get(i).node, time + adjs[c].get(i).time});
		}
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curNode = cur[0];
			int curTime = cur[1];
			
			if(visited[curNode]) continue;			
			visited[curNode] = true;
			cnt++;
			time = curTime;
			
			for(int i = 0; i < adjs[curNode].size(); i++) {
				int nextNode = adjs[curNode].get(i).node;
				int nextTime = adjs[curNode].get(i).time;
				if(!visited[nextNode]) {
					pq.add(new int[] {nextNode,time + nextTime});
				}
			}
		}
		
		
		return new int[] {cnt,time};
	}
	
	
	public static class Data{
		int node, time;
		public Data(int node, int time) {
			this.node = node;
			this.time = time;
		}
	}
}
