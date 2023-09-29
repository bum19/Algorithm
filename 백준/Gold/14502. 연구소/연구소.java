import java.io.*;
import java.util.*;

public class Main {
	public static int n,m, maxSafeSpace,safeSpace;
	public static int[][] room, tmp; //복사해서 셀거임.
	public static int[][] wall3;
	public static List<int[]> viruses; //바이러스 좌표 저장.
	public static int[] dy = {-1,0,1,0};//상우하좌
	public static int[] dx = {0,1,0,-1};//상우하좌
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		maxSafeSpace = 0;
		room = new int[n][m];
		tmp = new int[n][m];
		wall3 = new int[3][2]; //벽3 개의 좌표
		viruses = new ArrayList<int[]>();
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == 2) viruses.add(new int[] {i,j});
			}
		} //입력끝
		
		//벽위치 3개 정하기
		for(int a = 0; a < n * m; a++) {
			//a 위치가 0이 아니면 continue;
			if(room[a/m][a%m] != 0) continue;
			for(int b = a+1; b< n * m; b++) {
				//b 위치가 0이 아니면 continue;
				if(room[b/m][b%m] != 0) continue;
				for(int c= b+1; c< n * m; c++) {
					//c 위치가 0이 아니면 continue;
					if(room[c/m][c%m] != 0) continue;
					wall3[0][0] =  a/m; wall3[0][1] = a%m;
					wall3[1][0] =  b/m; wall3[1][1] = b%m;
					wall3[2][0] =  c/m; wall3[2][1] = c%m;
					go(); //벽세우고 바이러스 퍼뜨리는 시뮬시작
				}
			}
		}
		
		//출력
		System.out.println(maxSafeSpace);
	}
	
	public static void go() {
		//테스트할 룸설정
		safeSpace = 0;
		for(int i = 0;  i < n; i++) {
			for(int j = 0; j < m; j++) {
				tmp[i][j] = room[i][j];
				if(tmp[i][j] == 0) safeSpace++;//안전구역 개수 세기
			}
		}
		//벽세우기
		for(int i = 0; i < 3; i++) {
			int y = wall3[i][0];
			int x = wall3[i][1];
			tmp[y][x] = 1;
			safeSpace--; //벽 하나세우면 안전영역하나 사라지므로 1감소
		}
		//바이러스퍼뜨리기
		spread();
		//안전구역 세기
		if(maxSafeSpace < safeSpace) maxSafeSpace = safeSpace;
	}
	
	public static void spread() {
		Queue<int[]> q = new ArrayDeque<>();
		for(int[] virus : viruses) {
			q.add(virus);
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || tmp[ny][nx] != 0) continue;
				tmp[ny][nx] = 2;
				safeSpace--;
				q.add(new int[] {ny,nx});
			}
		}
	}
}
