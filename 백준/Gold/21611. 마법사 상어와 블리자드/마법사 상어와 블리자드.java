import java.io.*;
import java.util.*;

public class Main {
	public static int n, m;
	public static int[][] board;
	public static int[] boomCnt;
	public static int[][] commands;
	public static int[] dy = { 0, 1, 0, -1 };// 좌하우상
	public static int[] dx = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new int[n][n];
		commands = new int[m][2];
		boomCnt = new int[4];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			if (dir == 1)
				dir = 3;
			else if (dir == 2)
				dir = 1;
			else if (dir == 3)
				dir = 0;
			else if (dir == 4)
				dir = 2;
			commands[i][0] = dir;
			commands[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < m; i++) {
			// blizzard
			blizzard(commands[i][0], commands[i][1]);
			// move ball
			moveBall();
			// explode. return false if no explosion needed
			while (explode()) {
				moveBall();
			}
			// changeball
			changeBall();
		}

		System.out.println(boomCnt[1] + (2 * boomCnt[2]) + (3 * boomCnt[3]));
	}

	public static void blizzard(int dir, int dist) {
		int sy = n / 2, sx = n / 2;
		for (int i = 1; i <= dist; i++) {
			board[sy + dy[dir] * i][sx + dx[dir] * i] = 0;
		}
	}

	public static void moveBall() {
		int[][] tmp = new int[n][n];
		List<Integer> values = new ArrayList<Integer>();
		int cy = n / 2, cx = n / 2, dir = 0;
		for (int i = 0; i < n * n; i++) {
			// i당 방향 2개에 대해 탐색함
			for (int j = 0; j < 2; j++) {
				// 한 방향에 i번 이동
				for (int k = 0; k <= i; k++) {
					cy = cy + dy[dir];
					cx = cx + dx[dir];
					if (cy >= 0 && cx >= 0 && cy < n && cx < n) {
						if (board[cy][cx] != 0) {
							values.add(board[cy][cx]);
						}
					}
				}
				dir = (dir + 1) % 4;
			}

			// 무의미한 순환종료. 사소한 코드한줄 추가
			if (cy < 0 || cx < 0)
				break;
		}
		
		//tmp에 옮기기		
		cy = n/2;
		cx = n/2;
		dir = 0;
		for (int i = 0; i < n * n; i++) {
		// i당 방향 2개에 대해 탐색함
		for (int j = 0; j < 2; j++) {
			// 한 방향에 i번 이동
			for (int k = 0; k <= i; k++) {
				cy = cy + dy[dir];
				cx = cx + dx[dir];
				if (cy >= 0 && cx >= 0 && cy < n && cx < n) {
					if(values.isEmpty()) continue;
					tmp[cy][cx] = values.get(0);
					values.remove(0);
				}
			}
			dir = (dir + 1) % 4;
		}

		// 무의미한 순환종료. 사소한 코드한줄 추가
		if (cy < 0 || cx < 0)
			break;
	}
		board = tmp;
	}

	public static boolean explode() {
		// 순환하면서 4개이상이면 폭발. 
		boolean isExplode = false;
		List<int[]> pre = new ArrayList<>();
		int py = n / 2, px = n / 2, dir = 0;
		int cy = 0, cx = 0; // init
		for (int i = 0; i < n * n; i++) {
			// i당 방향 2개에 대해 탐색함
			for (int j = 0; j < 2; j++) {
				// 한 방향에 i번 이동
				for (int k = 0; k <= i; k++) {
					cy = py + dy[dir];
					cx = px + dx[dir];
					if (cy >= 0 && cx >= 0 && cy < n && cx < n) {
						// 전 값이랑 비교후, 같으면넣기.
						if(!(py == n/2 && px == n/2) && board[py][px] == board[cy][cx] && board[cy][cx] != 0) {
							pre.add(new int[] {cy, cx});
						}
						if(board[py][px] != board[cy][cx]) {
							if(pre.isEmpty() && board[cy][cx] != 0) 
								pre.add(new int[] {cy, cx});
							//다르면 다빼고 넣는데, 4개이상이면 폭발.
							if(pre.size() >=4) {
								//폭발.
								boomCnt[board[py][px]] += pre.size();
								for(int l = 0; l < pre.size(); l++) {
									board[pre.get(l)[0]][pre.get(l)[1]] = 0;
								}
								isExplode = true;
								pre.clear();
								//폭발시키고 넣어야지
								if(board[cy][cx] != 0)
									pre.add(new int[] {cy,cx});
							}
							//4개 이하면 그냥 초기화
							else {
								pre.clear();
								if(board[cy][cx] != 0)
									pre.add(new int[] {cy,cx});
							}
							
						}
					}
					py = cy;
					px = cx;
				}
				dir = (dir + 1) % 4;
			}
			// 무의미한 순환종료. 사소한 코드한줄 추가
			if (cy < 0 || cx < 0)
				break;
		}
		
		//탐색끝나고 리스트에 값 남아있으면폭발
		if(pre.size() >=4) {
			boomCnt[board[pre.get(0)[0]][pre.get(0)[1]]] += pre.size();
			//폭발.
			for(int l = 0; l < pre.size(); l++) {
				board[pre.get(l)[0]][pre.get(l)[1]] = 0;
			}
			
			isExplode = true;
			pre.clear();
		}
		return isExplode;
	}

	public static void changeBall() {
		List<Integer> values = new ArrayList<>(); //board에 새로 담을 값이 담긴 list
		List<int[]> pre = new ArrayList<>(); //연속되는 구슬 세기 위한 list
		int py = n / 2, px = n / 2, dir = 0;
		int cy = 0, cx = 0; // init
		for (int i = 0; i < n * n; i++) {
			// i당 방향 2개에 대해 탐색함
			for (int j = 0; j < 2; j++) {
				// 한 방향에 i번 이동
				for (int k = 0; k <= i; k++) {
					cy = py + dy[dir];
					cx = px + dx[dir];
					if (cy >= 0 && cx >= 0 && cy < n && cx < n) {
						// 전 값이랑 비교후, 같으면넣,
						if( !(py == n/2 && px == n/2) && board[py][px] == board[cy][cx]) {
							pre.add(new int[] {cy, cx});
						}
						if(board[py][px] != board[cy][cx]) {
							if(pre.isEmpty()) {
								pre.add(new int[] {cy,cx});
							}
							else {
								values.add(pre.size());
								values.add(board[py][px]);
								pre.clear();
								pre.add(new int[] {cy,cx});
							}
							
						}
					}
					py = cy;
					px = cx;
				}
				dir = (dir + 1) % 4;
			}
			// 무의미한 순환종료. 사소한 코드한줄 추가
			if (cy < 0 || cx < 0)
				break;
		}
		
		//바꾼값으로 넣기
		int[][] tmp = new int[n][n];
		cy = n/2;
		cx = n/2;
		dir = 0;
		for (int i = 0; i < n * n; i++) {
			// i당 방향 2개에 대해 탐색함
			for (int j = 0; j < 2; j++) {
				// 한 방향에 i번 이동
				for (int k = 0; k <= i; k++) {
					cy = cy + dy[dir];
					cx = cx + dx[dir];
					if (cy >= 0 && cx >= 0 && cy < n && cx < n) {
						if(values.isEmpty()) continue;
						tmp[cy][cx] = values.get(0);
						values.remove(0);
					}
				}
				dir = (dir + 1) % 4;
			}
			if(values.isEmpty() || cy < 0 || cx < 0) break;
		}
		//transform
		board = tmp;
	}
	
}