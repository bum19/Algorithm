import java.io.*;
import java.util.*;
/*
 * 1) BFS로 모든 사람간 거리 구하기.
 * 2) 최대 n^2.
 * 3) BFS로 모든 사람간 거리구하면서 케빈베이컨 값 구하기.
 * 
 */
public class Main {
	public static int n,m, minKb, answer;
	public static Set<Integer>[] adjs;
	public static int[][] dist;
	public static int[] kb;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		adjs = new Set[n+1]; //0번 인덱스는 버린다. 중복이 가능하므로 set으로 정의한다.
		dist = new int[n+1][n+1]; //dist[i][j] = dist[i][j]까지의 최소거리.
		kb = new int[n+1];
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(adjs[s] == null) {
				adjs[s] = new HashSet<>();
			}
			if(adjs[e] == null) {
				adjs[e] = new HashSet<>();
			}
			adjs[s].add(e);
			adjs[e].add(s);
		}
		
		//bfs로 모든 사람 거리 구하기
		for(int i = 1; i <= n; i++) {
			bfs(i);
		}
		
		minKb = Integer.MAX_VALUE;
		answer = -1;
		for(int i = 1; i <= n; i++) {
			if(minKb > kb[i]) {
				minKb = kb[i];
				answer = i;		
			}
		}
		
		System.out.println(answer);
		
		
	}
	private static void bfs(int node) {
		boolean[] visited = new boolean[n+1];
		Queue<int[]> q = new ArrayDeque<>();
		visited[node] = true;
		q.offer(new int[] {node,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curNode = cur[0];
			int curDist = cur[1];
			//방문한적있으면 넘어가고, 방문한적 없으면 케빈베이컨에 더하고 집어넣기
			for(Integer nextNode : adjs[curNode]) {
				if(!visited[nextNode]) {
					visited[nextNode] = true;
					if(dist[node][nextNode] == 0) {
						//케빈베이컨에 더하고
						kb[node] += curDist + 1;
						kb[nextNode] += curDist + 1;
						//둘사이의 거리 갱신하고
						dist[node][nextNode] = curDist + 1;
						dist[nextNode][node] = curDist + 1;
					}
					//큐에 넣기
					q.offer(new int[] {nextNode, curDist+1});
				}
			}
		}
	}
}
