

//
import java.io.*;
import java.util.*;

public class Main {
	public static final int INF = 4000001;
	public static int k, w, h;
	public static int[][] map;
	public static int[][][] minMove; // minMove[i][j][k] i,j까지 도달하는데 k번의 점프를 통해서 도착한 최소개수. bfs로 입력. 점화식 x
	public static int[] dy = { -1, 1, 0, 0, -2, -2, -1, -1, 2, 2, 1, 1 }; // 상하좌우 + 8가지 움직임
	public static int[] dx = { 0, 0, -1, 1, -1, 1, -2, 2, -1, 1, -2, 2 }; // 상하좌우 + 8가지 움직임

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		k = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());

		map = new int[h][w];
		minMove = new int[h][w][k + 1];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				Arrays.fill(minMove[i][j], INF); // 최소도달 개수 INF로 초기화.
			}
		}

		for (int i = 0; i < h; i++) { // map 입력
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < w; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		bfs();

		int answer = INF;
		for (int i = 0; i <= k; i++) {
			answer = Math.min(answer, minMove[h - 1][w - 1][i]);
		}

		if (answer == INF)
			answer = -1;

		System.out.println(answer);


	}

	// 풀이1. bfs. 도달했을때 이미 최솟값이면 탐색종료.
	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>(); // y, x좌표랑 도달하는데 걸리는 거리를 저장.
		minMove[0][0][0] = 0;
		q.offer(new int[] { 0, 0, 0 }); // y, x, 점프가능횟수.

		while (!q.isEmpty()) {
			int[] cur = q.poll();
//			System.out.println(cur[2]);
			for (int dir = 0; dir < 12; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];

				if (ny < 0 || nx < 0 || ny >= h || nx >= w || map[ny][nx] == 1)
					continue; // 인덱스를 벗어나거나 해당칸 map값이 1이거나 현재 저장된 최솟값 1더한것보다 작으면 가만히.

				if (dir >= 4 && cur[2] < k && minMove[ny][nx][cur[2] + 1] > minMove[cur[0]][cur[1]][cur[2]] + 1) {
					minMove[ny][nx][cur[2] + 1] = minMove[cur[0]][cur[1]][cur[2]] + 1;
					q.offer(new int[] { ny, nx, cur[2] + 1 });
				}
				if (dir < 4 && minMove[ny][nx][cur[2]] > minMove[cur[0]][cur[1]][cur[2]] + 1) {
					minMove[ny][nx][cur[2]] = minMove[cur[0]][cur[1]][cur[2]] + 1;
					q.offer(new int[] { ny, nx, cur[2] });
				}
			}
		}
	}

}
