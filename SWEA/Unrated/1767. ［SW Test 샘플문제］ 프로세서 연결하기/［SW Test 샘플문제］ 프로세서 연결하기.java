//
import java.util.*;
import java.io.*;

public class Solution {
	public static int t, n, maxCore, minLength;
	public static int[][] arr;
	public static List<int[]> cores;
	public static int[] dy = { 0, -1, 1, 0, 0 }; // 연결x, 상하좌우 전선연결
	public static int[] dx = { 0, 0, 0, -1, 1 }; // 연결x, 상하좌우 전선연결

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {

			n = Integer.parseInt(br.readLine().trim());
			arr = new int[n][n];
			cores = new ArrayList<int[]>();

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1 && i > 0 && i < n - 1 && j > 0 && j < n - 1)
						cores.add(new int[] { i, j }); // 벽에안붙어있는 코어만 추가.
				}
			}
			maxCore = 0;
			minLength = Integer.MAX_VALUE;

			dfs(0, 0, 0);
			
			// 출력저장.
			sb.append("#").append(test_case).append(" ").append(minLength).append("\n");
		}
		System.out.println(sb);
	}

	// 5^(코어개수)만큼탐색. 단 길이가 길어지면 탐색중단. 전선이 겹치면 탐색중단.
	private static void dfs(int depth, int curCore, int curLength) {
//		System.out.println("dfscall. depth :"+depth+", curCore : "+curCore+", curLength : "+curLength);
		//현재 코어 개수가 같은데, 현재 길이가 minLength보다 길어도, 코어개수가 늘어날수있잖아
		
		//코어개수가 maxCore를 넘을 가능성조차 없을때,
		if (curCore + cores.size()-depth < maxCore) {
			return;
		}
		
		//코어개수가 maxCore와 같아질 가능성은 있는데, 이미 길이가 더 길때
		else if(curCore + cores.size()- depth == maxCore && curLength >= minLength) {
			return; // 현재코어개수가 적거나같은데 길이가 더 길어지면 탐색 중단.
		}

		if (depth == cores.size()) { // 코어 한번씩 탐색했다면

			if (curCore == maxCore) { // 코어개수가 같다면
				minLength = minLength>curLength?curLength:minLength;

			} else if (curCore > maxCore) { // 현재 코어가 더 많다면
				maxCore = curCore;
				minLength = curLength;
			}
			return;
		}

		for (int dir = 0; dir < 5; dir++) {

			if (dir == 0)
				dfs(depth + 1, curCore, curLength);
			else {
				int ny = cores.get(depth)[0] + dy[dir];
				int nx = cores.get(depth)[1] + dx[dir];
				boolean isAvailable = true;
				int length = 0;
				// 선연결. 연결중에 프로세서나 다른 간선 만나면 실패. 복구.
				while (ny >= 0 && nx >= 0 && ny < n && nx < n) {
					if (++arr[ny][nx] >= 2)
						isAvailable = false;
					length++;
					ny += dy[dir];
					nx += dx[dir];
				}
				// 선연결 성공하면 탐색 이어가기.
				if (isAvailable)
					dfs(depth + 1, curCore + 1, curLength + length);
				// 선연결 성공하던 실패하던, 복구
				ny = cores.get(depth)[0] + dy[dir];
				nx = cores.get(depth)[1] + dx[dir];
				while (ny >= 0 && nx >= 0 && ny < n && nx < n) {
					arr[ny][nx]--;
					ny += dy[dir];
					nx += dx[dir];
				}
			}
		}

	}
}
