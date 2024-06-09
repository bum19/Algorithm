import java.io.*;
import java.util.*;
/*
 * Dijkstra 응용
 */
public class Main {
	public static long INF = 70000000000L;
	public static int n,m;
	public static Map<Integer, List<Integer>>[] adjs; // adjs[i].get(j).get(k); i노드의 인접한 j노드와 연결된 k번째 값.
	public static long[] distance;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		
		adjs = new Map[n+1]; //0번 노드 버린다.
		
		//input
		for(int i = 0 ; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			//데이터 삽입
			if(adjs[n1] == null) {
				adjs[n1] = new HashMap<>();
			}
			if(adjs[n2] == null) {
				adjs[n2] = new HashMap<>();
			}
			
			if(adjs[n1].get(n2) == null) {
				adjs[n1].put(n2, new ArrayList<>());
			}
			if(adjs[n2].get(n1) == null) {
				adjs[n2].put(n1, new ArrayList<>());
			}
			
			adjs[n1].get(n2).add(i);
			adjs[n2].get(n1).add(i);
		}// input done
		
		dijkstra();
		
		System.out.println(distance[n]);
	}
	
	private static void dijkstra() {
		//init
		distance = new long[n+1];
		Arrays.fill(distance, INF);
		
		PriorityQueue<long[]> pq = new PriorityQueue<>((a1,a2)-> { //{노드번호, 현재 노드까지오는데 걸린 시간}을 넣는다.
			return Long.compare(a1[1], a2[1]);
		});
		
		distance[1] = 0;
		pq.add(new long[] {1, 0});
		
		while(!pq.isEmpty()) {
			long[] cur = pq.poll();
			int curNode = (int)cur[0];
			long curDist = cur[1];
			if(curNode == n) break; //n노드까지의 최소시간 구했으면 탈출
			
			if(curDist > distance[curNode]) continue; //이미 갱신된 값임.
			
			for(Integer nextNode : adjs[curNode].keySet()) {
				//현재시간부터, nextNode까지 가는 길이 열리는 가장 짧은 시간 구하기
				long minTime = Long.MAX_VALUE;
				for(Integer time : adjs[curNode].get(nextNode)) {
					long k = (curDist - time) % m <= 0? (curDist -time)/m : (curDist - time)/m + 1;
					if(minTime > k * m + time) minTime = k * m + time;
				}
				
				//새로 구한 시간이 현재까지 구한값보다 작으면 갱신후 pq에 넣기.
				if(minTime + 1 < distance[nextNode]) {
					distance[nextNode] = minTime+1;
					pq.add(new long[] {nextNode, minTime +1});
				}
			}
		}
	}
}
