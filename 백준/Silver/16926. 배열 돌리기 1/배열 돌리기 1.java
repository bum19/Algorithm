import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static int n, m, r;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int[][] arr;
	static int[][] visited;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int x = 0, y = 0;
		n = sc.nextInt();
		m = sc.nextInt();
		r = sc.nextInt();
		arr = new int[n][m];
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <m; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		
		for(int i = 0; i < r; i++) {
			rotate(0);
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <m; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	static void rotate(int depth) {
		//변 탐색 순서에 유의! 왼쪽 - 상단 - 오른쪽 - 하단
		if (depth >= Math.min(m, n)/2)
			return;

		int tmp = arr[n-1-depth][depth];
		// 왼쪽 변
		for (int y = n-1-depth; y > depth; y--) {
			arr[y][depth] = arr[y-1][depth];
		}

		// 상단
		for (int x = depth; x < m - depth - 1; x++) {
			arr[depth][x] = arr[depth][x + 1];
		}
		
		// 오른쪽 변
				for (int y = depth; y < n - depth - 1; y++) {
					arr[y][m - depth - 1] = arr[y + 1][m - depth - 1];
				}
		
		// 하단
		for (int x = m - depth - 1; x > depth; x--) {
			arr[n - depth - 1][x] = arr[n - depth - 1][x - 1];
		}
		

		arr[n-1-depth][depth + 1] = tmp;
		rotate(depth + 1);
	}
}