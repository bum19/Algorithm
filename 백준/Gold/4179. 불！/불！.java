import java.io.*;
import java.util.*;

public class Main {
	public static int r,c, escapeTime;
	public static char[][] map;
	public static int[] jStart;
	public static List<int[]>  fStart;
	public static boolean[][] isJVisited;
	public static boolean[][] isFVisited;
	public static int[] dy = {-1,1,0,0}; //상하좌우
	public static int[] dx = {0,0,-1,1}; //상하좌우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String[] input;
		
		input = br.readLine().trim().split(" ");
		r = Integer.parseInt(input[0]);
		c = Integer.parseInt(input[1]);
		
		map = new char[r][c];
		isJVisited = new boolean[r][c];
		isFVisited = new boolean[r][c];
		fStart = new ArrayList<int[]>();
		
		for(int i = 0 ; i < r; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < c; j++) {
				map[i][j] = tmp.charAt(j);
				if(map[i][j] == 'J') {
					jStart = new int[] {i,j};
				}
				else if(map[i][j] == 'F') {
					fStart.add(new int[] {i,j});
				}
			}
		}
		
		//input done
		escapeTime = -1; //init
		Queue<int[]> q = new ArrayDeque<>();
		isJVisited[jStart[0]][jStart[1]] = true;
		q.offer(new int[] {'j', jStart[0],jStart[1], 0});
		for( int[] f : fStart) {
			isFVisited[f[0]][f[1]] = true;
			q.offer(new int[] {'f', f[0],f[1]});
		}
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			//j일경우, 현재위치가 불 안 붙어있을 경우만 진행
			if(cur[0] == 'j' && !isFVisited[cur[1]][cur[2]]) {
				//현재 가장자리라면 게임끝
				if(cur[1] == 0 || cur[1] == r-1 || cur[2] == 0 || cur[2] == c-1) {
					escapeTime = cur[3] + 1;
					break;
				}
				for(int dir = 0; dir < 4; dir++) {
					int ny = cur[1] + dy[dir];
					int nx = cur[2] + dx[dir];
					//가장자리체크할필요는 없다
					if(map[ny][nx] == '#' || isFVisited[ny][nx] || isJVisited[ny][nx]) continue;
					isJVisited[ny][nx] = true;
					q.add(new int[] {'j',ny,nx, cur[3]+1});
				}
			}
			//f일 경우.
			else if(cur[0] == 'f'){
				for(int dir = 0; dir < 4; dir++) {
					int ny = cur[1] + dy[dir];
					int nx = cur[2] + dx[dir];
					if(ny < 0 || nx < 0 || ny >= r || nx >= c || map[ny][nx] == '#' || isFVisited[ny][nx]) continue;
					isFVisited[ny][nx] = true;
					q.add(new int[] {'f',ny,nx});
				}
			}
		}//end while
		
		if(escapeTime == -1) System.out.println("IMPOSSIBLE");
		else				 System.out.println(escapeTime);
	}
}
