
/*
 * 오랜만의 다익스트라네 굿굿
 */
import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 50000000;
	public static int n, m;
	public static int[] distance;
	public static boolean[] visited;
	public static List<int[]>[] adjs; // adjs.get(i)는 i번째 노드와 인접한 노드번호 및 거리

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		adjs = new List[n + 1];// 0번째 idx는 사용안함.
		for (int i = 1; i <= n; i++) {
			adjs[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			adjs[n1].add(new int[] { n2, dist });
			adjs[n2].add(new int[] { n1, dist });
		}

		dijkstra();

		System.out.println(distance[n]);
	}

	public static void dijkstra() {
		// init
		distance = new int[n + 1];
		visited = new boolean[n + 1];
		Arrays.fill(distance, INF);
		distance[1] = 0;

		// pq원소값 : 현재 노드, 현재 노드까지 가는 밝혀진 최소거리.
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1, a2) -> {
			return Integer.compare(a1[1], a2[1]);
		});

		pq.add(new int[] { 1, 0 });

		while (!pq.isEmpty()) {
			int cur[] = pq.poll();
			int curNode = cur[0];
			int curDist = cur[1];

			// 간선 길이가 0일 수 있어서 visited를 추가. 원래같으면 visited없이, 최솟값으로 갱신된것이면 넘어가게 할 수 있음.
			if (visited[curNode])
				continue;

			visited[curNode] = true;
			if (curNode == n)
				return; // n까지의 최소거리 구했다면 종료

			for (int[] adj : adjs[curNode]) {
				int nextNode = adj[0];
				int edge = adj[1];
				if (distance[nextNode] > distance[curNode] + edge) {
					distance[nextNode] = distance[curNode] + edge;
					pq.add(new int[] { nextNode, distance[nextNode] });
				}
			}

		}

	}
}
