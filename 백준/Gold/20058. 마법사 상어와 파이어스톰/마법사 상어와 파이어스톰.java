import java.io.*;
import java.util.*;

public class Main{
	public static int n,q,size,maxIce,sum;
	public static int[][] board;
	public static boolean[][] visited;
	public static int[] levels;
	public static int[] dy = {-1,0,1,0}; //상우하좌
	public static int[] dx = {0,1,0,-1}; //상우하좌
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		size = 1 << n;
		board = new int[size][size];
		visited= new boolean[size][size];
		levels = new int[q];
		
		for(int i = 0 ; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < q; i++) {
			levels[i] = Integer.parseInt(st.nextToken());
		}
		
		maxIce = 0;
		sum = 0;
		
		for(int i = 0; i < q; i++) {
			//돌리기
			spin(levels[i]);
			//얼음 녹이기
			melt();
			
//			System.out.println("cur state");
//			for(int a =0 ; a < size; a++) {
//				for(int b =0; b < size; b++) {
//					System.out.print(board[a][b]+" ");
//				}
//				System.out.println();
//			}
			
		}
		
		//남은 얼음개수
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(board[i][j] > 0) sum += board[i][j];
			}
		}
		
		//가장큰 얼음덩어리
		for(int i = 0; i < size; i++) {
			for(int j = 0 ; j < size; j++) {
				if(!visited[i][j] && board[i][j] > 0)
					checkMaxIce(i,j);
			}
		}
		
		System.out.println(sum+"\n"+maxIce);
		
	}
	
	public static void spin(int l) {
		int[][] tmp = new int[size][size];
		for(int i = 0 ; i < size; i+= 1<<l) {
			for(int j = 0; j < size; j += 1<<l) {
				for(int y = 0 ; y < 1<<l; y++) {
					for(int x = 0; x < 1<<l; x++) {
						tmp[i + x][j + (1<<l)-1-y] = board[i + y][j + x];
					}
				}
			}
		}
		board = tmp;		

	}
	
	//녹일거 다 정하고 동시에 갱신해야함.탐색중 녹인걸로 근처얼음 판단하면 안됨.
	public static void melt() {
		int[][] tmp = new int[size][size]; //이게아니면 그냥 좌표 저장해놧다가 board에다 바로 뺴는방식 ㅇ
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				int adjIce = 0;
				for(int dir = 0; dir <4; dir++) {
					int ni = i + dy[dir];
					int nj = j + dx[dir];
					if(ni >= 0 && nj >= 0 && ni < size && nj < size && board[ni][nj] > 0) {
						adjIce++;
					}
				}
				if(adjIce < 3) {
					tmp[i][j] = board[i][j]-1;
				}
				else{
					tmp[i][j] = board[i][j];
				}
			}
		}
		board = tmp;
	}
	
	public static void checkMaxIce(int y, int x) {
		Queue<int[]> q = new ArrayDeque<int[]>(); 
		//bfs로탐색
		int iceSize = 1;
		visited[y][x] = true;
		
		q.add(new int[] {y,x});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				
				if(ny < 0 || nx < 0 || ny >= size || nx >= size || visited[ny][nx] || board[ny][nx] <= 0 ) continue;
				
				iceSize++;
				visited[ny][nx] = true;
				q.add(new int[] {ny,nx});
			}
		}
		
		if(iceSize > maxIce) maxIce = iceSize;
	}
}
