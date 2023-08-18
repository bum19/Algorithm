import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, d, maxKill, currentKill;
	public static int[][] board;
	public static int[][] tmp; //탐색때 쓸 보드
	public static int[] archers; //궁수3명 열 좌표저장
	public static Queue<int[]> q = new ArrayDeque<int[]>(); //궁수 활범위를 bfs 탐색하기 위한 큐 [0] : y좌표, [1] : x 좌표
	public static boolean[][] visited;
	public static int[] dy = {0,-1,0};	 //궁수 활범위를 bfs 탐색하기 위한 방향변수, 좌,상,우
	public static int[] dx = {-1,0,1};	 //궁수 활범위를 bfs 탐색하기 위한 방향변수, 좌,상,우
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		tmp = new int[n][m];
		visited = new boolean[n][m];
		archers = new int[3]; 
		maxKill = Integer.MIN_VALUE;
		
		//보드 입력.
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j =0 ; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//궁수배치
		for(int i = 0; i < m; i++) {
			for(int j = i+1; j < m; j++) {
				for(int k = j+1; k < m; k++) {
					//궁수자리 3곳 정하기
					archers[0] = i;	//0번궁수 열좌표
					archers[1] = j;	//1번궁수 열좌표
					archers[2] = k;	//2번궁수 열좌표
//					if(i == 1 && j == 3 && k == 5)
//						System.out.println("20번죽이는경우의수----------------------------");
					//초기화
					for(int y = 0; y < n; y++) {
						for(int x = 0 ; x< m; x++) {
							tmp[y][x] = board[y][x];
						}
					}
					currentKill = 0;
					
//					System.out.println("궁수자리세팅완료. 죽이기시작.");
					//화살범위에 있는 애들 죽이기. 궁수위치 행이 n부터 1까지 총 n번 탐색. 
					kill();
//					if(currentKill == 20) System.out.println("i,j,k "+i+" "+j+" "+ k);
					if(currentKill > maxKill) maxKill = currentKill;
				}
			}
		}
		System.out.println(maxKill);
	}
	
	private static void kill() { 
		////화살범위에 있는 애들 죽이기. 궁수위치 행이 n부터 1까지 총 n번 움직이며탐색.
		for(int r = n-1; r >= 0; r--) { //r은 현재 성벽 위치 바로 위
//			System.out.println(r+"성벽부터 탐색");

			boolean[][] gonnaDie = new boolean[n][m]; //죽을사람 위치 저장하는 리스트
			for(int i = 0; i < 3; i++) { //왼쪽에있는 궁수부터 탐색.
				q.clear();
//				System.out.println(archers[i]+"번 궁수 시작");
				for(int j = 0; j < n; j++) { //visited 초기화.
					Arrays.fill(visited[j], false);
				}
				
				q.offer(new int[] {r,archers[i],1}); //y좌표,x좌표, 현재좌표까지의 거리 입력.
				
				int ny, nx;
				while(!q.isEmpty()) {
					int[] cur = q.poll();
//					System.out.println("("+cur[0]+", "+cur[1]+")");
					if(tmp[cur[0]][cur[1]] == 1) { //현재 탐색한곳에 적이있으면
//						System.out.println("적발견. 사살.");
						gonnaDie[cur[0]][cur[1]] = true;
						break;
					}
					for(int dir = 0; dir < 3; dir++) { //3방탐색
						ny = cur[0] + dy[dir];
						nx = cur[1] + dx[dir];
						if(ny < 0 || nx < 0 ||ny >= n || nx >= m || cur[2] >= d || visited[ny][nx]) continue;
						visited[ny][nx] = true; //방문처리
						q.offer(new int[] {ny, nx, cur[2]+1});
					}
				}
				
			}
			//죽인적 갱신
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < m; x++) {
					if(gonnaDie[y][x] == true) {
						tmp[y][x] = 0;
						currentKill++;
					}
				}
			}
			
		}
	}


}
