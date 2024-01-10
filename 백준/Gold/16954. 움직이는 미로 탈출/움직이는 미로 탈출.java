import java.io.*;
import java.util.*;

public class Main {
	public static char[][] board;
	public static boolean[][] visited;
	public static List<int[]> walls;
	public static int[] dy = {-1,-1,0,1,1,1,0,-1}; //상 우상 우 우하 하 하좌 좌 좌상
	public static int[] dx = {0,1,1,1,0,-1,-1,-1}; //
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		board = new char[8][8];
		visited = new boolean[8][8];
		walls = new ArrayList<int[]>();
		
		for(int i = 0; i < 8; i++) {
			String str = br.readLine().trim();
			for(int j = 0; j < 8; j++) {
				board[i][j] = str.charAt(j);
				if(board[i][j] == '#') walls.add(new int[] {i,j});
			}
		}
		//input done
		
		Queue<Data> q = new ArrayDeque<>();
		visited[7][0] = true;
		q.add(new Data('u',7,0));
		//@@@@@@@@
		for(int i = walls.size()-1; i >= 0; i--) { //밑에서부터 넣기 @@ 이런거 조심해서 생각해야함.
			q.add(new Data('w',walls.get(i)[0], walls.get(i)[1]));
		}
		
		while(!q.isEmpty()) {
			Data cur = q.poll();
			//욱제 이동
			if(cur.type == 'u') {
				//욱제 오른쪽 끝 도달했으면 탈출
				if(cur.y == 0 && cur.x == 7) {
					System.out.println(1);
					return;
				}
				//현재이동한곳에 벽온적있으면, 여기 존재하던 욱제는더이상 이동 불가능
				if(!visited[cur.y][cur.x]) continue;
				//제자리이동
				visited[cur.y][cur.x] =  true; //이건 없어도 될지도
				q.add(cur);
				
				for(int dir = 0; dir < 8; dir++) {
					int ny = cur.y + dy[dir];
					int nx = cur.x + dx[dir];
					if(ny < 0 || nx < 0 || ny >= 8 || nx >= 8 || visited[ny][nx] || board[ny][nx] == '#') continue;
					visited[ny][nx] = true;
					q.add(new Data('u',ny,nx));
				}
			}
			//벽 이동
			else{
				board[cur.y][cur.x] = '.';
				int ny = cur.y + dy[4];
				int nx = cur.x + dx[4];
				if(ny < 8) {
					board[ny][nx] = '#';
					if(visited[ny][nx]) visited[ny][nx] = false;
					q.add(new Data('w',ny,nx));
				}
			}
		}
		
		System.out.println(0);
		
	}
	
	public static class Data{
		char type;
		int y,x;
		public Data(char type, int y, int x) {
			this.type = type;
			this.y = y;
			this.x = x;
		}
		public String toString() {
			return type +": "+ y+ ", "+ x;
		}
	}
}
