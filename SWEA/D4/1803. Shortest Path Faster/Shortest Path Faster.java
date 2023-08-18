//실행시간	:
//메모리	:
import java.io.*;
import java.util.*;

public class Solution {
	public static final long INF = Long.MAX_VALUE;
	public static int t, n, m, start, end;
	public static long[] dist;
//	public static boolean[] selected;
	public static List<DistIdx>[] w; //

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			// 입력
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			// 초기화
			dist = new long[n + 1]; // 노드번호와 인덱스를 맞추기위해 1개 더 크게 설정.
			w = new List[n + 1]; // 컬렉션 배열 선언시 타입은 정하지않네요.
			for (int i = 1; i <= n; i++)
				w[i] = new ArrayList<DistIdx>(); // 인접리스트 노드별 빈 리스트 생성.

//			selected = new boolean[n + 1]; // 해당 정점까지의 거리 탐색 완료했는지 검색.
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int idx1, idx2;
				long length;

				idx1 = Integer.parseInt(st.nextToken());// 시작점
				idx2 = Integer.parseInt(st.nextToken());// 끝점
				length = Long.parseLong(st.nextToken());// 간선길이

				if (w[idx1] == null) {
					w[idx1] = new ArrayList<DistIdx>();
				}
				if (w[idx2] == null) {
					w[idx2] = new ArrayList<DistIdx>();
				}
				w[idx1].add(new DistIdx(length, idx2));
				w[idx2].add(new DistIdx(length, idx1));
			}
			// 입력끝
			
			dijk_pq(start);
			sb.append("#").append(test_case).append(" ").append(dist[end]).append("\n");
		}
		System.out.println(sb);
	}

	// 풀고나서 pq우선순위 조건 안주면 어떻게 되는지 한번 알아보자.
	public static void dijk_pq(int start) {
		DistIdx current;
		int curNode = -1;
		long curDist = -1;
		PriorityQueue<DistIdx> pq = new PriorityQueue<DistIdx>((a1, a2) ->{
			return (int)(a1.distance - a2.distance);
		});
		
		Arrays.fill(dist, INF); // distance arrays.fill로 초기화
		dist[start] = 0; // 시작점 0으로.
		pq.add(new DistIdx(dist[start], start)); //pq에 (0, 시작점) 추가
		
		while (!pq.isEmpty()) {
			current = pq.poll();
			curDist = current.distance;
			curNode = current.index;
			
			if(dist[curNode] != curDist) continue;
			
			for(DistIdx nxt :  w[curNode]) {
				if(dist[nxt.index] <= dist[curNode] + nxt.distance) continue;
				
				dist[nxt.index] = dist[curNode] + nxt.distance;
				pq.add(new DistIdx(dist[nxt.index], nxt.index));
			}
		}
		
		
	}
	static class DistIdx{
		long distance;
		int index;
		
		public DistIdx(long distance, int index) {
			super();
			this.distance = distance;
			this.index = index;
		}
		
	}
}

