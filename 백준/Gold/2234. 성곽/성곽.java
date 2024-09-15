/*
 * 1. bfs를 이용해 방 개수, 방 최대 넓이 계산하기
 * 2. 모든칸 탐색하면서 이웃한 칸이 다른 방일경우 합쳐보기
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,m,maxRoom,maxMergeRoom;
	public static int[][] rooms;
	public static int[][] visited;
	public static List<Integer> roomSize;
	
	public static int[] dy = {0,-1,0,1}; //좌상우하 - 비트 연산에 맞게 설정
	public static int[] dx = {-1,0,1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		rooms = new int[m][n];

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				rooms[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//bfs
		int roomIdx = 0;
		roomSize = new ArrayList<>();
		visited = new int[m][n];
		maxRoom = Integer.MIN_VALUE;
		for(int i  = 0; i < m; i++) {
			Arrays.fill(visited[i],-1);
		}
		
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(visited[i][j] == -1) {
					bfs(i,j, roomIdx++);
				}
			}
		}
		
		//tryMerge
		maxMergeRoom = Integer.MIN_VALUE;
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				tryMerge(i,j);
			}
		}
		
		System.out.println(roomIdx+"\n"+maxRoom+"\n"+maxMergeRoom);
	}
	
	private static void bfs(int y, int x, int roomIdx) {
		int size = 1;
		Queue<int[]> q = new ArrayDeque<>();
		visited[y][x] = roomIdx;
		
		q.add(new int[] {y,x});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if( (rooms[cur[0]][cur[1]]&(1<<dir)) != 0 || ny < 0 || nx < 0 || ny >= m || nx >= n || visited[ny][nx] != -1) {
					continue;
				}
				
				size++;
				visited[ny][nx] = roomIdx;
				q.add(new int[] {ny,nx});
			}
		}
		
		//최대 방 크기 갱신
		maxRoom = Math.max(maxRoom, size);
		
		//방크기 저장
		roomSize.add(size);
	}
	
	private static void tryMerge(int y, int x) {
		for(int dir = 0; dir < 4; dir++) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if(ny < 0 || nx < 0 || ny >= m || nx >= n || visited[ny][nx] == visited[y][x]) {
				continue;
			}
			maxMergeRoom = Math.max(maxMergeRoom, roomSize.get(visited[ny][nx]) + roomSize.get(visited[y][x]));
		}
	}
}
