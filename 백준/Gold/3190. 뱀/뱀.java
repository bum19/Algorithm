import java.io.*;
import java.util.*;

public class Main {
	public static int n,k,l;
	public static List<Integer> dirs;
	public static int[][] map; //0 :empty, 1 : apple, 2:visited
	public static int[] dy = {0,1,0,-1};
	public static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) -1;
			int x = Integer.parseInt(st.nextToken()) -1;
			map[y][x] = 1;
		}
		
		l = Integer.parseInt(br.readLine());
		dirs= new ArrayList<>(); 
		int dir = 0;
		int cnt = 0;
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			for(; cnt < t; cnt++) {
				dirs.add(dir);	
			}
			if(c == 'D') dir = (dir+1)%4;
			else dir = (dir+3)%4;
		}
		
		//play
		System.out.println(play(dir));
	}
	
	private static int play(int lastDir) {
		Deque<int[]> snake = new ArrayDeque<>();
		snake.addLast(new int[] {0,0});
		map[0][0] = 2; //visited
		int time = 0;
		while(true) {
			int dir; 
			if(time < dirs.size()) {
				dir = dirs.get(time);
			}
			else {
				dir = lastDir;
			}
			
			//머리 꺼내기
			int[] head = snake.peekFirst();
			
			//움직이기
			int ny = head[0] + dy[dir];
			int nx = head[1] + dx[dir];
			if(ny < 0 || nx < 0 || ny >=n || nx >=n || map[ny][nx] == 2) {
				return time + 1;
			}
			
			if(map[ny][nx] == 0) {
				int[] tail = snake.pollLast();
				map[tail[0]][tail[1]] = 0;	
			}

			map[ny][nx] = 2;
			snake.addFirst(new int[] {ny,nx});
			time++;
		}
	}
}