//
import java.io.*;
import java.util.*;

public class Main {
	public static int n, m, zeroCnt, minZeroCnt;
	public static int[][] room;
	public static int[][] tmp;
	public static int[] dy = { -1, 0, 1, 0 }; //상하좌우 -> 상우하좌
	public static int[] dx = { 0, 1, 0, -1 }; //상우하좌
	public static List<int[]> cctvs;
	public static List<Integer> dirs;
//		public static List<List<int[]>>[] cctv; 
//		cctv = new List[6];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		room = new int[n][m];
		tmp = new int[n][m];
		cctvs = new ArrayList<int[]>();
		dirs = new ArrayList<Integer>();
		minZeroCnt = Integer.MAX_VALUE;
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				room[i][j] = tmp[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] >=1 && room[i][j] <= 5) cctvs.add(new int[] {i, j}); 
				if(room[i][j] == 0) zeroCnt++;
			}
		}
//		System.out.println("zeroCnt : "+zeroCnt);
		getDirections(0);
		System.out.println(minZeroCnt);
		
	}
	
	//4가지 상태 방향.
	private static void getDirections(int depth) {
		if(depth == cctvs.size()) {
			bfs();
			//출력
//			for(int i = 0; i < n; i++) {
//				for(int j = 0; j <m; j++) {
//					System.out.print(tmp[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("------------");
//			
			//출력끝
			//탐색 끝내고 방 복구
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < m; j++) {
					tmp[i][j] = room[i][j];
				}
			}

			return;
		}

		
		for(int i = 0; i < 4; i++) {
			dirs.add(i);
			getDirections(depth+1);
			dirs.remove(depth);
		}
	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		int tmpZeroCnt = zeroCnt;
//		System.out.println("before tmpZeroCnt : "+tmpZeroCnt);
		//cctv정점들 방향정해서 넣어주기.
		for(int i = 0; i< cctvs.size(); i++) {
			if(tmp[cctvs.get(i)[0]][cctvs.get(i)[1]] == 1) {
				//1방향
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (1 + dirs.get(i))%4});
			}
			else if(tmp[cctvs.get(i)[0]][cctvs.get(i)[1]] == 2) {
				//2방향
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (1 + dirs.get(i))%4});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (3 + dirs.get(i))%4});
			}
			else if(tmp[cctvs.get(i)[0]][cctvs.get(i)[1]] == 3) {
				//2방향
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (0 + dirs.get(i))%4});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (1 + dirs.get(i))%4});
			}
			else if(tmp[cctvs.get(i)[0]][cctvs.get(i)[1]] == 4) {
				//3방향
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (0 + dirs.get(i))%4});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (1 + dirs.get(i))%4});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], (3 + dirs.get(i))%4});
			}
			else if(tmp[cctvs.get(i)[0]][cctvs.get(i)[1]] == 5) {
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], 0});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], 1});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], 2});
				q.add(new int[] {cctvs.get(i)[0], cctvs.get(i)[1], 3});
			}
		}
		
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			int ny = cur[0] + dy[cur[2]];
			int nx = cur[1] + dx[cur[2]];
			if(ny < 0 || nx < 0 || ny >= n || nx >= m || tmp[ny][nx] == 6) continue;
			if(tmp[ny][nx] == 0) {
				tmp[ny][nx] = -1;
				tmpZeroCnt--;
			}
			q.add(new int[] {ny,nx, cur[2]});
		}
//		System.out.println("after tmpZeroCnt : "+tmpZeroCnt);
		if(minZeroCnt > tmpZeroCnt) minZeroCnt = tmpZeroCnt;
	}

}
