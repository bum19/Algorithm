import java.io.*;
import java.util.*;

public class Main {
	
	public static int m, s;
	public static Data[][] map;
	public static List<Fish> fishClones;
	public static int[] shark;
	public static int[][] sharkMove;
	public static int[][] maxSharkMove;
	public static int maxKill;
	public static int[] dy = {-1,-1,0,1,1,1,0,-1};// 상, 상좌, 좌, 좌하, 하, 하우, 우, 우상.
	public static int[] dx = {0,-1,-1,-1,0,1,1,1};// 상, 상좌, 좌, 좌하, 하, 하우, 우, 우상.
	public static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		map = new Data[5][5]; //i,j 가 0인건 버림
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <=4; j++) {
				map[i][j] = new Data();
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			dir = (11-dir)%8; // 방향내가 설정한대로 저장.
			map[y][x].dirs.add(dir);
		}
		
		shark = new int[2];
		st = new StringTokenizer(br.readLine());
		shark[0] = Integer.parseInt(st.nextToken());
		shark[1] = Integer.parseInt(st.nextToken());
		//input done
		
		//init
		maxSharkMove = new int[3][2];
		sharkMove = new int[3][2];
		fishClones= new ArrayList<Fish>();
		
		
		for(int i = 0; i < s; i++) { //iterate s times
			
			//1. fish clone
			//격자초기화,
			fishClones.clear();
			for(int y = 1; y <= 4; y++) {
				for(int x = 1; x <=4; x++) {
					for(int dir : map[y][x].dirs) {
						fishClones.add(new Fish(y,x,dir)); //격자별 저장된 물고기 정보담기
					}
					map[y][x].dirs.clear(); //격자 초기화
				}
			}

			
			//2. fish move
			//격자에 움직인 물고기들 입력
			for(Fish fish : fishClones) {
				boolean isMoved = false;
				int ny = fish.y;
				int nx = fish.x;
				int curDir = fish.dir;
				for(int j = 0; j < 8; j++) {
					ny = fish.y + dy[(fish.dir+j) % 8];
					nx = fish.x + dx[(fish.dir+j) % 8];
					if(ny <= 0 || nx <= 0 || ny > 4 || nx > 4) continue;
					if(map[ny][nx].smell > 0 ) continue;
					if(ny == shark[0] && nx == shark[1]) continue;
					isMoved = true;
					map[ny][nx].dirs.add((fish.dir+j)%8);
					break;
				}
				if(!isMoved) {
					map[fish.y][fish.x].dirs.add(fish.dir);
				}
			}

			//3. shark move.
			//상어 64가지 경우중, 사전순 (상->좌->하->우) 탐색.
			// 이거 때매 엄청오래고민. 상어는 중복방문이 ㄷ가능하다 /////////*******************
			maxKill = Integer.MIN_VALUE;
			visited = new boolean[5][5]; 
			maxSharkMove = new int[3][2]; //최종 상어움직임입력담을곳
			sharkMove = new int[3][2]; //탐색중 상어움직임 담을곳.
			dfs(shark[0], shark[1], 0, 0); //shark y,x, depth, kill
			
			//maxSharkMove에 저장된대로 움직이면서 kill
			for(int j = 0; j < 3; j++) {
				int y = maxSharkMove[j][0];
				int x = maxSharkMove[j][1];
				if(map[y][x].dirs.size() != 0) { //물고기가 존재한다면 냄새를 남긴다.
					map[y][x].dirs.clear();
					map[y][x].smell = 3;
				}
			}
			
			//상어 위치 갱신
			shark[0] = maxSharkMove[2][0];
			shark[1] = maxSharkMove[2][1];

			
			//4. 격자에 냄새 제거
			for(int y = 1; y <= 4; y++) {
				for(int x = 1; x <=4; x++) {
					if(map[y][x].smell > 0) map[y][x].smell--;
				}
			}
			
			//5. fish clone input
			for(Fish fish : fishClones) {
				map[fish.y][fish.x].dirs.add(fish.dir);
			}
		
		}
		
		//6. count fishnum
		int answer = 0;
		for(int i = 1; i <= 4; i++) {
			for(int j = 1; j <= 4; j++) {
				answer += map[i][j].dirs.size();
			}
		}
		
		System.out.println(answer);
	}
	
	
	public static void dfs(int y, int x, int depth, int kill) {
		if(depth == 3) {
			if(maxKill < kill) {
				maxKill = kill;
				for(int i = 0; i < 3; i++) {
					maxSharkMove[i][0] = sharkMove[i][0];
					maxSharkMove[i][1] = sharkMove[i][1];
				}
			}
			return;
		}
		
		for(int dir = 0; dir < 8; dir += 2) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(ny <= 0 || nx <= 0 || ny > 4 || nx > 4) continue;
			sharkMove[depth][0] = ny;
			sharkMove[depth][1] = nx;
			if(!visited[ny][nx]) {
				visited[ny][nx] = true;
				dfs(ny,nx,depth+1, kill + map[ny][nx].dirs.size());
				visited[ny][nx] = false;
			}
			else {
				dfs(ny,nx,depth+1, kill);
			}
		}
	}
	
	public static class Data{
		List<Integer> dirs;
		int smell;
		public Data() {
			this.dirs = new ArrayList<Integer>();
			this.smell = 0;
		}
	}
	
	public static class Fish{
		int y, x, dir;
		public Fish(int y, int x, int dir){
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
}
