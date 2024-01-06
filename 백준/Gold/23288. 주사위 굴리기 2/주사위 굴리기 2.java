import java.io.*;
import java.util.*;

public class Main {
	public static Dice dice;
	public static int n,m,k,score, curDir;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[] dy = {0,1,0,-1}; //우하좌상
	public static int[] dx = {1,0,-1,0}; //우하좌상
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//input done
		
		//init
		dice = new Dice();
		score = 0;
		curDir = 0;
		visited = new boolean[n][m];
		
		for(int i = 0; i < k; i++) {
			//주사위 굴리기
			rollDice();
			
			//점수계산
			calScore();
			
			//방향설정
			setDir();
			
		}
		
		System.out.println(score);
	}
	
	private static void rollDice() {
		//주사위 지도에서의 위치변경
		dice.y = dice.y + dy[curDir];
		dice.x = dice.x + dx[curDir];
		//주사위 전개도 변경
		int tmp = dice.f;
		switch(curDir) {
		case 0: //우
			dice.f = dice.l;
			dice.l = dice.b;
			dice.b = dice.r;
			dice.r = tmp;
			break;
		case 1: //하
			dice.f = dice.u;
			dice.u = dice.b;
			dice.b = dice.d;
			dice.d = tmp;
			break;
		case 2: //좌
			dice.f = dice.r;
			dice.r = dice.b;
			dice.b = dice.l;
			dice.l = tmp;
			break;
			
		case 3: //상
			dice.f = dice.d;
			dice.d = dice.b;
			dice.b = dice.u;
			dice.u = tmp;
			break;
		}
	}
	
	private static void calScore() {
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited[i], false);
		}
		int curNum = map[dice.y][dice.x];

		Queue<int[]> q = new ArrayDeque<int[]>();
		visited[dice.y][dice.x] = true;
		score += curNum;
		q.add(new int[]{dice.y,dice.x});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx>= m || visited[ny][nx] || map[ny][nx] != curNum) continue;
				visited[ny][nx] = true;
				score += curNum;
				q.add(new int[] {ny,nx});
			}
		}
	}
	
	private static void setDir() {
		//방향체크
		int curNum = map[dice.y][dice.x];
		if( dice.b > curNum) curDir = (curDir+1)%4;
		if( dice.b < curNum) curDir = (curDir+3)%4;
		
		//막혀있으면 반대방향
		int ty = dice.y + dy[curDir];
		int tx = dice.x + dx[curDir];
		
		if(ty < 0 || tx < 0 || ty >=n || tx >= m) {
			curDir = (curDir+2)%4;
		}
	}
	
	public static class Dice{
		int f,b,u,d,l,r; //앞뒤상하좌우
		int y, x; // 현재 주사위위치
		public Dice(){
			this.f = 1;
			this.b = 6;
			this.u = 2;
			this.d = 5;
			this.l = 4;
			this.r = 3;
			this.y = 0;
			this.x = 0;
		}
	}
}
