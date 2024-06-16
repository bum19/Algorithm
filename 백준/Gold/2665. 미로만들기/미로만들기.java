import java.io.*;
import java.util.*;
/*
 * 각 칸이 노드 하나
 * 흰색-> 흰색 은 0, 흰/검->검은색은 1이라고 하자.
 * 다익스트라.
 */
public class Main {
	public static int n;
	public static int[] distances;
	public static char[][] map;
	
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	public static void main(String [] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		map = new char[n][n];
		
		for(int i = 0 ; i < n; i++) {
			String tmp = br.readLine().trim();
			for(int j  =0; j < n; j++) {
				map[i][j] = tmp.charAt(j);
			}
		}
		
		
		dijkstra();
		System.out.println(distances[n*n-1]);
		
	}
	
	private static void dijkstra() {
		// 0,0 을 0번노드, n-1,n-1을 n^2-1번 노드라 한다.
		distances = new int[n*n];
		Arrays.fill(distances, 100);
		distances[0] = 0;
		boolean[] visited = new boolean[n*n];
		PriorityQueue<int[]> pq = new PriorityQueue<>((a1,a2)->(Integer.compare(a1[1],a2[1]))); //갱신한 노드들과, 갱신당시 그 노드들까지의  거리
		//visited 없을때, 들어온 dist 값이 distance[]배열에 있는것보다 크면, 이미 갱신된 애로 작업이 끝났으므로, 넘어간다. 근데, 같으면, 걔가 바로 작업해야할 순간인거다. 근데,가중치 0이 가능하면, 작업이끝났는지 안끝났는지를 모른다. 그러므로 visited를 사용한다.
		//는 사실 또 들어올 일이 없다. 비교할떄 > 로 비교하기때문이다. 같은값이 들어올 일은 없다는 것이다.
		pq.add(new int[] {0,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int cNode = cur[0];
			int dist = cur[1];
			
			if(distances[cNode] < dist) continue; //이미 더 작은값으로 pq에서 나왔다는 뜻 == 이미 해당 노드까지의 최솟값은 진짜 최솟값으로 정해졌다는뜻 == 이미 해당값을 최소로해서 계산 마쳤다는뜻
			if(cNode == n*n -1) return;
			
			//cNode와 인접한 친구들 찾기
			for(int dir = 0; dir < 4; dir ++) {
				int ny = cNode/n + dy[dir];
				int nx = cNode%n + dx[dir];
				int nNode = ny * n + nx;
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
				
				int weight = map[ny][nx] == '1'?0:1;
				if(distances[nNode] > distances[cNode] + weight) {
					distances[nNode] = distances[cNode] + weight;
					pq.add(new int[] {nNode, distances[nNode]});
				}
			}
			
		}
		
	}
}
