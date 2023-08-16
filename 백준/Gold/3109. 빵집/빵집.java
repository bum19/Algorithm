
//참고 : 조현우님 코드
//메모리 :			
//실행시간:		
import java.util.*;
import java.io.*;

public class Main {
	public static int r, c, cnt;
	public static boolean isFound;
	public static char[][] board;
	public static int[] dy = { -1, 0, 1 };
	public static int[] dx = { 1, 1, 1 };

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		board = new char[r][c];
		
		for(int i = 0; i < r; i++) {
			String str = br.readLine().trim();
			for(int j = 0; j < c; j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		
		for(int i = 0; i < r; i++) {
			isFound = false;
			back(i, 0);
		}
		
		System.out.println(cnt);
		
	}
	
	public static void back(int y, int x) {
		board[y][x] = 'x';
//		System.out.println("("+y+", "+x+")");
		if(x == c-1) {
//			System.out.println("found");
			isFound = true;
			cnt++;
			return;
		}
		
		for(int dir = 0; dir < 3; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if(ny < 0 || nx < 0 || ny >= r || nx >= c || board[ny][nx] == 'x') continue;

//			board[ny][nx] = 'x';
			if(!isFound) back(ny, nx);
			else return;
		}
	}

}
