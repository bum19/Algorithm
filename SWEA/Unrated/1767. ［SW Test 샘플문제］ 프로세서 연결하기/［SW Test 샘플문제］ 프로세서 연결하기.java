//
import java.io.*;
import java.util.*;

public class Solution {
	public static int t, n, maxCore, minDist;
	public static int[][] board;
	public static List<int[]> cores;
	public static int[] dy = { -1, 1, 0, 0 }; // 상하좌우
	public static int[] dx = { 0, 0, -1, 1 }; // 상하좌우

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			// 입력
			n = Integer.parseInt(br.readLine().trim());
			board = new int[n + 1][n + 1]; // 격자판이 1부터시작.
			cores = new ArrayList<int[]>();
			maxCore = 0;
			minDist = Integer.MAX_VALUE;

			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= n; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if (board[i][j] == 1 && i >= 1 && j >= 1 && j <= n && j <= n) {
						cores.add(new int[] { i, j });
					}
				}
			}
			// 입력끝

			dfs(0, 0, 0);

			sb.append("#").append(test_case).append(" ").append(minDist).append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int cnt, int curCore, int dist) {

		// 가지치기. 현재코어개수 + cores.size() - cnt가 maxCore보다 작으면 탈출.
		if(curCore + (cores.size() - cnt) < maxCore) return;

		// cnt가 코어개수가 되면 탈출. 코어개수가 더 적으면 그냥종료.
		if (cnt == cores.size()) {
			if (maxCore > curCore) //현재 코어개수가 더 적다면
				return;
			else if(maxCore < curCore) { //현재 코어개수가 더 많다면
					maxCore = curCore;
					minDist = dist;
					return;
				}
			else { //현재 코어개수와 maxCore값이 같다면
				if (minDist > dist)
					minDist = dist;
				return;
			}
		}

		// 코어에 전선 연결하기
		// 아예 연결안하는경우
		dfs(cnt + 1, curCore, dist);
		// 4방연결하는경우
		for (int dir = 0; dir < 4; dir++) {
			// 방향에따라 연결.
			boolean isCross = false; // 전선 교차되는지 확인.
			int tempDist = 0;
			
			int ny = cores.get(cnt)[0] + dy[dir];
			int nx = cores.get(cnt)[1] + dx[dir];
			
			while (true) { // 전선이 끝에닿을때까지 연결.
				if (ny <= 0 || nx <= 0 || ny > n || nx > n)	break; // 끝까지 갔으면 반복 종료.
				
				board[ny][nx] += 2; //방문처리를 2더한걸로 진행.
				tempDist++; //전선길이 1 증가.
				
				if (board[ny][nx] != 2) isCross = true; //2더했는데 2가아니면 이미 코어가있거나 전선이 깔린것임.
				
				ny += dy[dir];
				nx += dx[dir];
			}

			if (!isCross) { // 잘 안겹치고 연결된다면 게속탐색
				dfs(cnt + 1, curCore + 1, dist + tempDist);
			}

			// 탐색후 전선 복구
			ny = cores.get(cnt)[0] + dy[dir];
			nx = cores.get(cnt)[1] + dx[dir];
			while (true) { // 전선이 끝에닿을때까지 연결.
				if (ny <= 0 || nx <= 0 || ny > n || nx > n)	break; // 끝까지 갔으면 반복 종료.
				
				board[ny][nx] -= 2; //2 다시 빼면서 복구 진행.
				
				ny += dy[dir];
				nx += dx[dir];
			}
		} // 방향에따른 호출 종료.
	}

}
