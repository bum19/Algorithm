/*
 * BFS를 통해 모든 좌표에 가장가까운 편의점 기록
 *
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n,m,r,c,minScore;
	public static List<int[]> convs;
	public static List<Home> homes;
	
	public static int[] dy = {-1,0,1,0}; //urdl
	public static int[] dx = {0,1,0,-1};
	public static int[][] dist; //해당좌표에서 편의점까지의 거리
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		homes = new ArrayList<>();
		for(int i =0; i< r; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			homes.add(new Home(y,x,cost));
		}
		
		convs = new ArrayList<>();
		for(int i = 0; i < c; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			convs.add(new int[] {y,x});
		}
		
		minScore = Integer.MAX_VALUE;
		dist = new int[n+1][m+1];
		bfs();
		for(Home home : homes) {
			minScore = Math.min(minScore, dist[home.y][home.x] * home.cost);
		}
		
		System.out.println(minScore);
	}
	
	private static void bfs() {
		
		boolean[][] visited = new boolean[n+1][m+1];
		Queue<int[]> q = new ArrayDeque<>(); //큐는 좌표와 편의점에서 해당좌표까지의 거리를 들고다닌다.
		for(int[] conv : convs) {
			visited[conv[0]][conv[1]] = true;
			dist[conv[0]][conv[1]] = 0;
			q.add(new int[] {conv[0],conv[1],0});
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir <4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 1 || nx < 1 || ny > n || nx > m || visited[ny][nx]) continue;
				
				visited[ny][nx] = true;
				dist[ny][nx] = cur[2]+1;
				
				q.add(new int[] {ny,nx,cur[2]+1});
			}
			
		}
	}
	
	public static class Home{
		int y, x, cost;
		public Home(int y, int x, int cost) {
			this.y = y;
			this.x = x;
			this.cost = cost;
		}
	}
}
