import java.io.*;
import java.util.*;
//하드코딩에대해 이쁘게할 필요가 있다.
public class Main {
	public static int n, m, minSagak, count;
	
	public static int[][] map;
	public static List<CCTV> cctvs; //타입, 좌표를 담는다.
	
	public static int[] dy = {-1,0,1,0}; //상우하좌
	public static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		cctvs = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] != 0 && map[i][j] != 6) {
					cctvs.add(new CCTV(map[i][j], i, j));
				}
			}
		}
		//input done
		
		//init
		minSagak = Integer.MAX_VALUE;
		
		//재귀적으로 4^8가지의 경우의 수 탐색
		go(0);
		
		System.out.println(minSagak);
	}
	
	private static void go(int depth) {
		if(depth == cctvs.size()) {

			//사각지대 세기
			int sagak = 0;
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m ; j++) {
					if(map[i][j] == 0) sagak++; 
				}
			}
			if(minSagak > sagak) minSagak = sagak; 
			return;
		}
		
		//cctv
		CCTV cctv = cctvs.get(depth);
		for(int dir = 0; dir < 4; dir++) {
			if(cctv.type == 1) {
				draw(cctv.y, cctv.x, dir, '-');
			}
			else if(cctv.type == 2) {
				draw(cctv.y, cctv.x, dir, '-');
				draw(cctv.y, cctv.x, (dir+2)%4, '-');
			}
			else if(cctv.type == 3) {
				draw(cctv.y,cctv.x, dir, '-');
				draw(cctv.y,cctv.x, (dir+1)%4, '-');
			}
			else if(cctv.type == 4) {
				draw(cctv.y,cctv.x, dir, '-');
				draw(cctv.y,cctv.x, (dir+1)%4, '-');
				draw(cctv.y,cctv.x, (dir+2)%4, '-');
			}
			else if(cctv.type == 5) {
				draw(cctv.y,cctv.x, dir, '-');
				draw(cctv.y,cctv.x, (dir+1)%4, '-');
				draw(cctv.y,cctv.x, (dir+2)%4, '-');
				draw(cctv.y,cctv.x, (dir+3)%4, '-');
			}
			else{
				System.out.println("잘못된 입력");
			}
			go(depth+1);
			if(cctv.type == 1) {
				draw(cctv.y, cctv.x, dir, '+');
			}
			else if(cctv.type == 2) {
				draw(cctv.y,cctv.x, dir, '+');
				draw(cctv.y,cctv.x, (dir+2)%4, '+');
			}
			else if(cctv.type == 3) {
				draw(cctv.y,cctv.x, dir, '+');
				draw(cctv.y,cctv.x, (dir+1)%4, '+');
			}
			else if(cctv.type == 4) {
				draw(cctv.y,cctv.x, dir, '+');
				draw(cctv.y,cctv.x, (dir+1)%4, '+');
				draw(cctv.y,cctv.x, (dir+2)%4, '+');
			}
			else if(cctv.type == 5) {
				draw(cctv.y,cctv.x, dir, '+');
				draw(cctv.y,cctv.x, (dir+1)%4, '+');
				draw(cctv.y,cctv.x, (dir+2)%4, '+');
				draw(cctv.y,cctv.x, (dir+3)%4, '+');
			}
			else{
				System.out.println("잘못된 입력");
			}
			
			if(cctv.type == 2) dir += 2;
			if(cctv.type == 5) break;
		}
	}
	
	public static void draw(int y, int x, int dir, char c) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		while(ny >= 0 && ny < n && nx >= 0 && nx < m) {
			if(map[ny][nx] == 6) break;
			
			if(c == '-') {
				if(map[ny][nx] <= 0) map[ny][nx]--;
			}
			else if(c == '+') {
				if(map[ny][nx] <= 0) map[ny][nx]++;
			}
			ny = ny + dy[dir];
			nx = nx + dx[dir];
		}

	}
	
	
	public static class CCTV{
		int type, y,x;
		CCTV(int type, int y, int x){
			this.type = type;
			this.y = y;
			this.x = x;
		}
	}
}