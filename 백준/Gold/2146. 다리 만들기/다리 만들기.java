//섬을 쪼개기
//섬마다 가장자리 bfs로 탐색. 최솟값 발견시 탈출.
//이미 구한 최솟값보다 길어지면, 중단!
import java.io.*;
import java.util.*;

public class Main {
	
	public static int continentCnt, n, minBridge;
	public static int[][] map;
	public static boolean[][] visited; //방문
	public static List<List<int[]>> islands;
	public static int[] dy = {-1,1,0,0};//udlr
	public static int[] dx = {0,0,-1,1};//udlr
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//input
		n = Integer.parseInt(br.readLine().trim());
		map = new int[n][n];
		
		for(int i = 0; i <n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//input done
		
		//divide island
		//map island get their islandnum
		islands = new ArrayList<>();
		continentCnt = 0;
		visited = new boolean[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				makeIsland(++continentCnt, i, j);
			}
		}

		//getMinBridge
		minBridge = Integer.MAX_VALUE;
		for(int i = 0; i < islands.size(); i++) {
			for(int j = 0; j < n; j++) {
				Arrays.fill(visited[j], false);
			}
			getMinBridge(i+1, islands.get(i));
		}
		
		System.out.println(minBridge);
	}
	
	//makeIsland. change mapValue, make IslandList
	private static void makeIsland(int curIslandNum, int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> island = new ArrayList<int[]>();
		
		visited[i][j] = true;
		map[i][j] = curIslandNum;
		island.add(new int[] {i,j});
		q.add(new int[] {i,j});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] || map[ny][nx] == 0) continue;
				
				visited[ny][nx] = true;
				map[ny][nx] = curIslandNum;
				island.add(new int[] {ny,nx});
				q.add(new int[] {ny,nx});
			}
		}
		islands.add(island);
	}
	
	private static void getMinBridge(int curIslandNum, List<int[]> island) {
		Queue<int[]> q = new ArrayDeque<>();
		for(int[] point : island) {
			visited[point[0]][point[1]] = true;
			q.add(new int[] {point[0],point[1],0}); //curLoc, curDist
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			//다른 섬 도달하면 탈출.
			if(map[cur[0]][cur[1]] != curIslandNum && map[cur[0]][cur[1]] != 0) {
				if(minBridge > cur[2] - 1) minBridge = cur[2] - 1;
				break;
			}
			//길이가 더 길어지면 탈출
			if(cur[2] > minBridge) break;
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] || map[ny][nx] == curIslandNum) continue;
				visited[ny][nx] = true;
				q.add(new int[] {ny,nx,cur[2]+1});
			}
		}
	}
	
}
