/*
 * 승객 좌표 값 : (승객idx + 1) * 10 으로 설정. 범위 : 10 ~ (m * 10)
 * 목적지 좌표 값 : (승객idx + m + 1) * 10 으로 설정. 범위 : (m+1)*10 ~ (2*m*10)
 * -한 손님의 출발지가 다른 손님의 목적지일 수 있음.
 * -자잘한 디버깅 완료.
 * -반례해보고 dist 가 -1인경우 예외처리가 안됨을 확인
 * -행우선,열우선이 안됨을 확인 (<좌-하> 가 <우-우>보다 우선순위가 높았음)
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static int n,m,fuel, taxiY,taxiX;
	public static int[][] map;
	public static boolean[][] visited;
	
	public static int[][] goals;
	public static int[] dy = {-1,0,0,1}; //상,좌,우,하   행우선, 열우선
	public static int[] dx = {0,-1,1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		 
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		
		map = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//taxi input
		st = new StringTokenizer(br.readLine());
		taxiY = Integer.parseInt(st.nextToken())-1; 
		taxiX = Integer.parseInt(st.nextToken())-1;
		
		//customer input
		goals = new int[m][2];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int ty = Integer.parseInt(st.nextToken())-1;
			int tx = Integer.parseInt(st.nextToken())-1;

			map[y][x] = (i+1)*10;
			goals[i][0] = ty;
			goals[i][1] = tx;
		}
		
		
		visited = new boolean[n][n];
		for(int i = 0; i < m; i++) {
			//고객 찾기
			int[] customer = findCustomer();
			if(customer == null) { //고객을 못찾은 경우
				System.out.println(-1);
				return;
			}

			//고객에게 이동
			if(moveTaxi(customer) < 0) { //고객한테 도달 실패한 경우
				System.out.println(-1);
				return;
			}
			
			//이때 연료 0이면 -1 출력
			if(fuel <= 0) {
				System.out.println(-1);
				return; 
			}
			
			//목적지 찾기
			int[] goal = findGoal(customer);
			//택시 위치 이동
			if(!moveToGoal(goal)) {
				System.out.println(-1);
				return;
			}
			//목적지 데려다 준 고객 맵에서 지우기
			map[customer[0]][customer[1]] = 0;
		}
		
		System.out.println(fuel);
		
	}
	
	private static int[] findCustomer() {
		initVisit();
		int minDist = Integer.MAX_VALUE;
		int[] ret = new int[] {Integer.MAX_VALUE, Integer.MAX_VALUE};
		
		Queue<int[]> q = new ArrayDeque<>();
		if(map[taxiY][taxiX] >= 10 && map[taxiY][taxiX] <= m*10) {
			return new int[] {taxiY,taxiX};
		}
		visited[taxiY][taxiX] = true;
		q.add(new int[] {taxiY,taxiX,0});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			if(cur[2] + 1 > minDist) continue;
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >=n || nx >= n || map[ny][nx] == 1 || visited[ny][nx]) continue;
				if(map[ny][nx] >= 10 && map[ny][nx] <= m*10) {
					//작거나, 같으면서 행,열 우선일경우
					if(cur[2] + 1 < minDist) {
						minDist = cur[2] +1;
						ret[0] = ny;
						ret[1] = nx;
					}
					else if(cur[2]+1 == minDist) {
						if(ny < ret[0]) {
							ret[0] = ny;
							ret[1] = nx;
						}
						else if(ny == ret[0]){
							ret[1] = Math.min(ret[1], nx);
						}
					}
					continue;
				}
				visited[ny][nx] = true;
				q.add(new int[] {ny,nx,cur[2]+1});
			}
		}
		
		
		if(minDist == Integer.MAX_VALUE)
			return null;
		
		return ret;
	}
	
	private static int moveTaxi(int[] target) {
		initVisit();
		Queue<int[]> q = new ArrayDeque<>();
		if(taxiY == target[0] && taxiX == target[1]) {
			return 0;
		}
		visited[taxiY][taxiX] = true;
		q.add(new int[] {taxiY,taxiX, 0});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || map[ny][nx] == 1 || visited[ny][nx]) continue;
				if(ny == target[0] && nx == target[1]) {
					taxiY = ny;
					taxiX = nx;
					fuel -= cur[2] + 1;
					if(fuel >= 0) return cur[2]+1;
					break;
				}
				visited[ny][nx] = true;
				q.add(new int[] {ny,nx, cur[2]+1});
			}
		}
		
		return -1;
	}
	
	private static int[] findGoal(int[] customer) {
		int idx = map[customer[0]][customer[1]]/10 - 1;
		return goals[idx];
	}
	
	private static boolean moveToGoal(int[] goal) {
		
		int dist = moveTaxi(goal);
		if(dist < 0) return false;
		if(fuel < 0) return false;
		
		fuel += dist * 2;
		return true;
	}
	
	
	private static void initVisit() {
		for(int i = 0; i < n; i++) {
			Arrays.fill(visited[i],false);
		}
	}
}