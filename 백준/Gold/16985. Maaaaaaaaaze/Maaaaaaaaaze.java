import java.io.*;
import java.util.*;

public class Main {
	
	public static int minMove;
	
	public static int[] seq; //순서
	public static boolean[] isSelected; //5개 순열을 위한 배열
	public static int[][][] plates;
	public static int[][][] maze;
	public static boolean[][][] isVisited; //bfs탐색을위한 배열
	public static int[] dy = {1,-1,0,0,0,0}; //앞뒤왼오위아래
	public static int[] dx = {0,0,-1,1,0,0}; //앞뒤왼오위아래
	public static int[] dz = {0,0,0,0,-1,1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		plates = new int[5][5][5];
		maze = new int[5][5][5];
		isSelected = new boolean[5];
		isVisited = new boolean[5][5][5];
		
		//input
		for(int i = 0 ; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k < 5; k++) {
					plates[i][j][k] = Integer.parseInt(st.nextToken());
				}
			}
		}	
		//init
		minMove = Integer.MAX_VALUE;
		
		//순서정하기
		seq = new int[5];
		seqPerm(0);
		
		if(minMove == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(minMove);
	}
	
	private static void seqPerm(int depth) {
		if(depth == 5) {
			//순서정했으면, 돌려야지.
			spinRecur(0);
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				seq[depth] = i;
				seqPerm(depth + 1);
				isSelected[i] = false;
			}
		}
		
	}
	public static int cnt;
	private static void spinRecur(int depth) {
		if(depth == 5) {
			cnt++;
			//다 돌렸으면, 탐색. 출발가능한곳 4군데
			bfs(0,0,0);
			bfs(0,4,0);
			bfs(0,0,4);
			bfs(0,4,4);
			
			return;
		}
		
		for(int spinNum = 0; spinNum < 4; spinNum++) {
			spinOne(depth, spinNum);
			spinRecur(depth + 1);
		}
	}
	
	private static void bfs(int sz, int sy, int sx) {

		//maze 탐색
		//도착치는 4군데 가능.

		int ez = 4;
		int ey = sy == 0 ? 4 : 0;
		int ex = sx == 0 ? 4 : 0;
		isVisited = new boolean[5][5][5];
		
		if(maze[sz][sy][sx] == 0) return;
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		q.add(new int[] {sz,sy,sx, 0}); //좌표 및 이동횟수
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			if(cur[0] == ez && cur[1] == ey && cur[2] == ex) {
				//비교하고, 리턴
				if(minMove > cur[3]) minMove = cur[3];
				return;
				
			}
			for(int dir = 0; dir < 6; dir++) {
				int nz = cur[0] + dz[dir];
				int ny = cur[1] + dy[dir];
				int nx = cur[2] + dx[dir];
				if(nz < 0 || ny < 0 || nx < 0 || nz >=5 || ny >=5 || nx >=5 || maze[nz][ny][nx] == 0 || isVisited[nz][ny][nx]) continue;
				isVisited[nz][ny][nx] = true;
				q.add(new int[] {nz,ny,nx,cur[3]+1});
			}
		}
	}
	
	private static void spinOne(int depth, int spinNum) {
		int[][] curPlate = plates[seq[depth]];
		int[][] tmp = new int[5][5];//회전시키기 전 배열
		int[][] target = maze[depth]; //실제 maze값
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				target[i][j] = curPlate[i][j];
				tmp[i][j] = curPlate[i][j];
			}
		}


		
		for(int i = 0; i < spinNum; i++) {
			//90도회전
			for(int y = 0 ; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					target[x][5-1-y] = tmp[y][x];
				}
			}
			
			//다음 회전을 위해 tmp배열 업데이트
			for(int y = 0; y < 5; y++) {
				for(int x = 0; x < 5; x++) {
					tmp[y][x] = target[y][x];
				}
			}
		}
	}
}
