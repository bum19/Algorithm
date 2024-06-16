import java.io.*;
import java.util.*;
/*
 * 각 칸이 노드 하나
 * 흰색-> 흰색 은 0, 흰/검->검은색은 1이라고 하자.
 * 0-1 BFS.
 * 다익스트라랑 거의 똑같은데, 정렬안하고 앞뒤로 넣는 Deque쓰는것만 다르다.
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
		Deque<int[]> dq = new ArrayDeque<>(); //갱신한 노드들과, 갱신당시 그 노드들까지의  거리
		dq.addFirst(new int[] {0,0});
		while(!dq.isEmpty()) {
			int[] cur = dq.poll();
			int cNode = cur[0];
			int dist = cur[1];
			
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
					if(weight == 0) {
						dq.addFirst(new int[] {nNode,distances[nNode]});
					}
					else {
						dq.addLast(new int[] {nNode, distances[nNode]+ weight});
					}
				}
			}
			
		}
		
	}
}
