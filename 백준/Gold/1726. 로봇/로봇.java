/*
 * 반례좀 얻어서 품
 * 1. 방향 매핑문제로 인한 문제 + 다른칸으로 이동할 때, 탈출 조건을 중간에 1이있거나 바운더리 벗어나는 경우와 더 작은 값이 있는경우를 구분함.   
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int m,n,min;
	public static int[][] map;
	public static int[][][] visited;
	
	public static int[] dy = {1,0,-1,0}; // 하우상좌
	public static int[] dx = {0,1,0,-1};
	public static int[] mapDir = {-1,1,3,0,2};
	
	public static int sy,sx,sDir, ty,tx, tDir;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		map = new int[m][n];
		
		for(int i = 0; i< m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		sy = Integer.parseInt(st.nextToken()) -1;
		sx = Integer.parseInt(st.nextToken()) -1;
		sDir = mapDir[Integer.parseInt(st.nextToken())];

		st = new StringTokenizer(br.readLine());
		ty = Integer.parseInt(st.nextToken()) -1;
		tx = Integer.parseInt(st.nextToken()) -1;
		tDir = mapDir[Integer.parseInt(st.nextToken())];
		
		start();
		System.out.println(visited[ty][tx][tDir]);
	}
	
	private static void start() {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited = new int[m][n][4];
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				Arrays.fill(visited[i][j], Integer.MAX_VALUE);
			}
		}
		visited[sy][sx][sDir] = 0;
		q.add(new int[] {sy,sx,sDir,0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cy = cur[0];
			int cx = cur[1];
			int cDir = cur[2];
			int cCnt = cur[3];
			
//			System.out.println("("+ cy+","+cx +")" + " dir : "+cDir+", dist : "+ cCnt);
			
			//회전 후 넣기
			if(cCnt + 1 < visited[cy][cx][(cDir +1) % 4]) {
				visited[cy][cx][(cDir +1) % 4] = cCnt+1;
//				System.out.println("input rotate val 1");
				q.add(new int[] {cy,cx,(cDir+1) % 4, cCnt+1});
			}
			if(cCnt + 2 < visited[cy][cx][(cDir +2) % 4]) {
				visited[cy][cx][(cDir +2) % 4] = cCnt+2;
//				System.out.println("input rotate val 2");
				q.add(new int[] {cy,cx,(cDir+2) % 4, cCnt + 2});
			}
			if(cCnt + 1 < visited[cy][cx][(cDir +3) % 4]) {
				visited[cy][cx][(cDir +3) % 4] = cCnt+1;
//				System.out.println("input rotate val 3");
				q.add(new int[] {cy,cx,(cDir+3) % 4, cCnt + 1});
			}
			
			//이동
			for(int i = 1; i <= 3 ; i++) {
				int ny = cy + dy[cDir] * i;
				int nx = cx + dx[cDir] * i;
				if(ny < 0 || nx < 0 || ny >=m || nx >= n || map[ny][nx] == 1) break;
				if(visited[ny][nx][cDir] <= cCnt + 1) continue; //사이에 있는값이 더 먼저 와 있을 수 있다.
				visited[ny][nx][cDir] = cCnt + 1;
				q.add(new int[] {ny, nx, cDir, cCnt + 1});
//				System.out.print("move to ");
//				System.out.println("("+ ny+","+nx +")" + " dir : "+cDir+", dist : "+ (cCnt+1));
			}
		}
	}
}
