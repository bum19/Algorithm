import java.io.*;
import java.util.*;
public class Main {
	public static int n, m, minCount;
	public static String minSequence;
	public static int[] red, blue, escape; //빨간구슬,파란구슬,탈출구멍 좌표
	public static char[][] board;
	public static List<Character> sequence;
	public static char[] dirChar = {'U','D','L','R'};
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new char[n][m];
		red = new int[2];
		blue = new int[2];
		escape= new int[2];
		sequence = new ArrayList<Character>();
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < m; j++) {
				board[i][j] = tmp.charAt(j);
				if(board[i][j] == 'R') {
					red[0] = i;
					red[1] = j;
					board[i][j] = '.';
				}
				if(board[i][j] == 'B') {
					blue[0] = i;
					blue[1] = j;
					board[i][j] = '.';
				}
				if(board[i][j] == 'O') {
					escape[0] = i;
					escape[1] = j;
				}
			}
		}
		//입력끝
		
		//init
		minCount = Integer.MAX_VALUE;
		
		//방향정하기. 4^10의 경우의수. 100만번 고정.
		dfs(0);
		
		if(minCount == Integer.MAX_VALUE) System.out.println(-1);
		else {
			System.out.println(minCount+1); // 카운트 0부터 9까지함. 1~10으로 바꾸기위해 1더해주기.
			System.out.println(minSequence);
		}
		
	}
	
	private static void dfs(int count) {
		if(count >= 10 || count >= minCount) { //기울이기 0~9번쨰함.
			return;
		}
		
		for(int dir = 0; dir < 4; dir++) {
			//기울이기 전 구슬위치 기억
			int ry,rx,by,bx;
			ry = red[0];
			rx = red[1];
			by = blue[0];
			bx = blue[1];
			
			//방향 정하고, 기울이기
			sequence.add(dirChar[dir]);
			tilt(dir);
			
			//파란공이빠져서 실패했거나, 성공했다면 true 반환. 아니라면 false 반환
			if(!checkGameEnd(count)) {
				dfs(count + 1);
			}
			
			//탐색 끝난후 원상복구
			sequence.remove(sequence.size() - 1);
			red[0] = ry;
			red[1] = rx;
			blue[0] = by;
			blue[1] = bx;
			
		}
	}
	
	private static void tilt(int dir) {
		//x축 같고, u,d일경우
		if((red[1] == blue[1]) && (dirChar[dir] == 'U' || dirChar[dir] == 'D')){
			if(dirChar[dir] == 'U') {
				if(red[0] < blue[0]) {
					move(red, dir, 'r');
					move(blue,dir,'b');
				}
				else {
					move(blue,dir,'b');
					move(red,dir,'r');
				}
			}
			if(dirChar[dir] == 'D') {
				if(red[0] < blue[0]) {
					move(blue, dir, 'b');
					move(red,dir,'r');
				}
				else {
					move(red,dir,'r');
					move(blue,dir,'b');
				}
			}
		}
		
		//y축 같고, l, r일경우
		else if((red[0] == blue[0]) && (dirChar[dir] == 'L' || dirChar[dir] == 'R')) {
			if(dirChar[dir] == 'L') {
				if(red[1] < blue[1]) {
					move(red,dir,'r');
					move(blue,dir,'b');
				}
				else {
					move(blue,dir,'b');
					move(red,dir,'r');
				}
			}
			if(dirChar[dir] == 'R') {
				if(blue[1] < red[1]) {
					move(red,dir,'r');
					move(blue,dir,'b');
				}
				else {
					move(blue,dir,'b');
					move(red,dir,'r');
				}
			}
		}
		
		//그외 평범한 경우 순서상관없음
		else {
			move(blue,dir,'b');
			move(red,dir,'r');
		}
			
	}
	
	private static void move(int[] vessel, int dir, char color) {
		int[] otherVessel = color == 'r'? blue:red;
		//범위를 벗어나거나, 구멍이거나, 벽이나 다른 색 만나기전까지 계속.
		while(true) {
			int ny = vessel[0] + dy[dir];
			int nx = vessel[1] + dx[dir];
			
			if(ny < 0 || nx < 0 || ny >= n || nx >= m) break;
			
			if(board[ny][nx] == 'O') {
				vessel[0] = ny;
				vessel[1] = nx;
				break;
			}
			
			if( board[ny][nx] == '#' || (otherVessel[0] == ny && otherVessel[1] == nx)) {
				break;
			}
		
			vessel[0] = ny;
			vessel[1] = nx;
			
		}
		
	}
	
	//성공/실패시 true, 진행중일시 false 여부 판단. 성공시 minCount, minSequence갱신
	private static boolean checkGameEnd(int count) {
		//파란색 들어갔는지판단.
		if(blue[0] == escape[0] && blue[1] == escape[1]) {
			return true;
		}
		//빨간색 들어갔는지판단. 들어갔다면 성공 갱신.
		if(red[0] == escape[0] && red[1] == escape[1]) {
			//minCount갱신
			if(minCount > count) {
				minCount = count;
				StringBuilder sb = new StringBuilder();
				for(char c : sequence) {
					sb.append(c);
				}
				minSequence = sb.toString();
			}
		}
		return false;
	}
}