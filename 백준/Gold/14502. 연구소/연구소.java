import java.io.IOException;
import java.util.*;

public class Main{
	public static int n,m,maxCnt = -1;
	public static int board[][];
	public static boolean virVisited[][];
	public static ArrayList<Pair> zeroPoint = new ArrayList<>();
	public static ArrayList<Pair> vir = new ArrayList<>();
	public static Pair[] newWall = new Pair[3];
	public static int[] dy = {0,0,-1,1};
	public static int[] dx = {1,-1,0,0};
	public static void set3walls(int depth, int start) {
		if(depth ==3) {
			//벽3개세우기
			for(Pair wall : newWall) {
				board[wall.first][wall.second] = 1;
			}
			//바이러스퍼트리기
			virVisited = new boolean[n][m];
			for(Pair virPoint : vir) {
				virVisited[virPoint.first][virPoint.second] =true;
				virusSpread(virPoint);
			}
			//안전지역 세기
			int cnt = 0;
			for(int i =0; i<n; i++) {
				for(int j = 0; j<m;j++) {
					if(board[i][j] == 0 && virVisited[i][j] == false) cnt++;
				}
			}
			if(cnt > maxCnt) maxCnt = cnt;
			//벽 초기화
			for(Pair wall : newWall) {
				board[wall.first][wall.second] = 0;
			}
			//바이러스초기화는 virVisited를 새로 선언하는것으로 대체
			return;
		}
		
		for(int i = start ; i< zeroPoint.size(); i++) {
			newWall[depth] = zeroPoint.get(i);
			set3walls(depth+1, i+1);
		}
	}
	
	public static void virusSpread(Pair virPoint) {
		int y = virPoint.first;
		int x = virPoint.second;
		for(int i =0 ; i<4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
			if(board[ny][nx] == 0 && virVisited[ny][nx]== false) {
				virVisited[ny][nx] = true;
				virusSpread(new Pair(ny,nx));
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		board = new int[n][m];
		for(int i=0; i< n; i++) {
			for(int j = 0; j<m; j++) {
				board[i][j] = sc.nextInt();
				if(board[i][j]==0) zeroPoint.add(new Pair(i,j));
				if(board[i][j]==2) vir.add(new Pair(i,j));
			}
		}
		set3walls(0, 0);
		System.out.println(maxCnt);
	}
}

class Pair{
	int first;
	int second;
	Pair(int first, int second){
		this.first = first;
		this.second = second;
	}
}