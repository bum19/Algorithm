import java.io.*;
import java.util.*;

//플레이어별로 bfs. si칸만큼 확장
public class Main {
	public static int n,m,p;
	public static boolean isGamePossible;
	public static List<int[]>[] players; //중방 탐후 삭제
	public static int[] playersDist;
	public static int[] castleCnt;
	public static char[][] map;
	public static int[] dy = {-1,1,0,0}; //udlr
	public static int[] dx = {0,0,-1,1}; //udlr
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		castleCnt = new int[p+1];
		
		players = new List[p+1]; //0번인덱스 사용 x
		playersDist = new int[p+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= p; i++) {
			playersDist[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= p; i++) {
			players[i] = new ArrayList<int[]>();
		}
		
		for(int i = 0; i < n;i++) {
			String tmp = br.readLine().trim();
			for(int j = 0; j < m; j++){
				map[i][j] = tmp.charAt(j);
				if(map[i][j] >= '1' && map[i][j] <= '9') {
					players[map[i][j]-'0'].add(new int[] {i,j});
				}
			}
		}//input done
		
	
		//expand
		isGamePossible = true;
		while(isGamePossible) {
			isGamePossible = false;
			for(int i = 1 ; i <= p; i++) {
				expand(i);
			}
		}
		
		//count
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] >= '1' && map[i][j] <= '9') {
					castleCnt[map[i][j]-'0']++;
				}
			}
		}
		
		
		//print
		for(int i = 1; i <= p; i++) {
			if(i != p)
				sb.append(castleCnt[i]).append(" ");
			else
				sb.append(castleCnt[i]);
		}
		System.out.println(sb);
	}
	
	private static void expand(int idx) {
		Queue<int[]> q = new ArrayDeque<int[]>(); //yLoc, xLoc, dist
		List<int[]> player = players[idx];
		int size = player.size()-1; 
		
		for(int i = size; i >= 0; i--) {
			int[] cur = player.get(i);
			q.add(new int[] {cur[0],cur[1],0});
			player.remove(i);
		}
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] >= playersDist[idx]) continue;
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny <0 || nx <0 || ny>= n || nx >= m || map[ny][nx] != '.') continue;
				//map 변경
				map[ny][nx] = (char)(idx + '0');
				//플레이어성에 추가
				player.add(new int[] {ny,nx});
				//add queue
				q.add(new int[] {ny,nx, cur[2]+1});
				isGamePossible = true;
				
			}
		}
	}
}
