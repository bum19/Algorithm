import java.util.*;
//인접한 방을 위해 각 칸마다 인접한 칸에 대해 다른 방이면 덧셈실시.
public class Main {
	public static int n, m;
	public static int maxRoomArea = Integer.MIN_VALUE;
	public static int maxBreakRoomArea = Integer.MIN_VALUE;
	public static int currentRoomArea = 0;
	public static int roomCount = 0;
	public static int[][] arr;
	public static int[][][] pointInfo;
	public static boolean[][] visited;
	public static int[] dx = { -1, 0, 1, 0 };
	public static int[] dy = { 0, -1, 0, 1 };
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		m = sc.nextInt();
		n = sc.nextInt();
		arr = new int[n][m];
		//각 칸별로 속한 방과 방의 크기를 기록하기위한 pointInfo. pointInfo[y][x][0]  == 방식별자, pointInfo[y][x] == 방크기
		pointInfo = new int[n][m][2];
		visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++)
				arr[i][j] = sc.nextInt();
		}
		getRoom();
		System.out.println(roomCount);
		System.out.println(maxRoomArea);
		
		maxRoomArea = Integer.MIN_VALUE;
		//벽없애고 getRoom();
		for(int i =0 ; i < n; i++) {
			for(int j = 0; j < m; j++) {
				for(int k = 0 ; k < 4; k++) {
					int ny = i + dy[k];
					int nx = j + dx[k];
					
					//현재 좌표 주위 좌표확인. 벽이 있을경우 서로 다른 방이면 더해본다.
					if(ny >= 0 && ny < n && nx >=0 && nx < m && (arr[i][j] & (1 << k)) == (1 << k)) {
						if(pointInfo[i][j][0] != pointInfo[ny][nx][0])
							maxBreakRoomArea = Math.max(maxBreakRoomArea, pointInfo[i][j][1] + pointInfo[ny][nx][1]);
					}

					}
				}
			}
		System.out.println(maxBreakRoomArea);
	}
	
	//bfs 탐색을 통한 방의개수 및 최대방의 넓이 구하기.
	public static void getRoom() {
		Queue<Pair> q = new LinkedList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!visited[i][j]) {
					List<Pair> tempList = new ArrayList<>();
					currentRoomArea = 0;
					visited[i][j] = true;
					q.offer(new Pair(i,j));
					//현재 탐색중인 방의 좌표들을 입력
					tempList.add(new Pair(i,j));
					
					while(!q.isEmpty()) {
						int x = q.peek().x;
						int y = q.peek().y;
						q.poll();
						currentRoomArea++;
						
						for(int k = 0; k < 4; k++) {
							int nx = x + dx[k];
							int ny = y + dy[k];
							if( (arr[y][x] & (1 << k)) == 0 && nx >=0 && nx < m && ny >=0 && ny < n && !visited[ny][nx]) {
								visited[ny][nx] = true;
								q.offer(new Pair(ny,nx));
								tempList.add(new Pair(ny,nx));
							}
						}
						
					}
					
					for(Pair p : tempList ) {
						pointInfo[p.y][p.x][0] = roomCount;
						pointInfo[p.y][p.x][1] = currentRoomArea;
					}
					roomCount++;
					maxRoomArea = Math.max(currentRoomArea, maxRoomArea);
				}
			}
		}
	}
}
class Pair{
	int x;
	int y;
	Pair(int y, int x){
		this.y = y;
		this.x = x;
	}
}
