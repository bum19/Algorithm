import java.util.Scanner;

public class Solution {
	public static int[][] arr;
	public static boolean[][] visited;
	public static int[] dx = { 1, 0, -1, 0 };
	public static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t, n, x = 0, y = 0 , cnt = 1, dir = 0;
		t = sc.nextInt();
		
		
		for(int test_case = 1; test_case <= t; test_case++) {
			n = sc.nextInt();
			
			//테스트케이스마다 값 초기화
			arr = new int[n][n];
			visited = new boolean[n][n];
			cnt = 1;
			dir = 0;
			x = 0;
			y = 0;
			arr[y][x] = cnt;
			visited[y][x] = true;
		
			//한칸씩 탐색. 인덱스를 벗어나거나 
			while(cnt <= n*n) {
			
				arr[y][x] = cnt;
				visited[y][x] = true;
			
				int nx = x + dx[dir];
				int ny = y + dy[dir];
			
				//nx,ny가 인덱스를 벗어나거나 visited[ny][nx]가 방문한곳이면 방향과 nx,ny값 다시 설정.
				if(nx < 0 || ny < 0 || nx >= n || ny >= n || visited[ny][nx]) {
					dir = (dir+1)%4;
					nx = x + dx[dir];
					ny = y + dy[dir];
				}
				x = nx;
				y = ny;
				cnt++;
			}
			
			sb.append("#").append(test_case).append("\n");
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					sb.append(arr[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}

}
