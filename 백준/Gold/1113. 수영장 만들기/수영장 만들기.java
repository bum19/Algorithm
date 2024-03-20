import java.io.*;
import java.util.*;
/*
 * 1) 높이가 1~9인 좌표 각각 저장
 * 2) 높이가1인 좌표부터 bfs 탐색 시작. 이 때, 서로 높이가 1인애들이 벽으로 분리되어 있을 수 있으므로 신경써서 처리
 * 2-1) 자기보다 높은 수 나오면 탐색종료.
 * 2-2) 자기보다 낮은 수 나오면 탐색종료.
 * 2-3) 낮은수가 나온적있으면, 아무것도 안함.
 * 2-4) 높은수 나온것중 가장 작은 숫자로 visited부분 갱신.
 * 3) 높이9까지 2번과정 반복
 * 
 * 시간복잡도 : 9 * (BFS(NM) + 갱신(NM))  = 450000 
 */
public class Main {
	public static int n,m, water;
	public static int[][] pool;
	public static List<Location>[] locs;
	public static boolean[][] visited;
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		pool = new int[n+2][m+2]; //맨 바깥을 0으로 채워준다.
		locs = new List[10]; //첫 0부위는 쓰지않는다. 1~9만사용한다.
		visited = new boolean[n+2][m+2];
		
		for(int i = 0 ; i <= 9; i++) {
			locs[i] = new ArrayList<Location>();
		}
		
		for(int i = 1; i <= n; i++) {
			String tmp = br.readLine().trim();
			for(int j = 1; j <= m; j++) {
				pool[i][j] = tmp.charAt(j-1)-'0';
				
				locs[pool[i][j]].add(new Location(i,j));
			}
		}
		//input done

		for(int height = 1; height <=9; height++) {
			bfs(height);
		}
		
		System.out.println(water);
		
	}
	
	public static void bfs(int height) {
		
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> group = new ArrayList<>();
		for(Location loc : locs[height]) {
			if(visited[loc.y][loc.x]) continue;
			boolean isPossible = true; //수영장 만들수 있는지 여부
			int minWall = 10;
			
			visited[loc.y][loc.x]= true; 
			q.add(new int[] {loc.y,loc.x});
			group.add(new int[] {loc.y, loc.x});
			
			while(!q.isEmpty()) {
				int[] cur = q.poll();
				for(int dir =0 ; dir < 4; dir++) {
					int ny = cur[0] + dy[dir];
					int nx = cur[1] + dx[dir];
					
					if(visited[ny][nx]) continue;
					if(pool[ny][nx] < height) {
					
						isPossible = false;
					}
					
					else if(pool[ny][nx] > height) {
						
						minWall = pool[ny][nx] < minWall? pool[ny][nx]: minWall;
					}
					else {
						visited[ny][nx] = true;
						q.add(new int[] {ny,nx});
						group.add(new int[] {ny,nx});
					}
				}
			}

			//물채우기
			if(isPossible) {
				for(int[] groupLoc: group) {
					water += (minWall-height);
					pool[groupLoc[0]][groupLoc[1]] = minWall;
					locs[minWall].add(new Location(groupLoc[0], groupLoc[1]));
				}
			}
			
			q.clear();
			group.clear();

		}
		
		//visited 원상복구
		for(Location loc: locs[height]) {
			visited[loc.y][loc.x] = false;
		}

	}
	
	
	public static class Location{
		int y, x;
		Location(int y, int x){
			this.y = y;
			this.x = x;
		}
	}
}






