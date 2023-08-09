
//메모리 :	
//실행시간 : 
import java.util.*;
import java.io.*;

public class Main {

	static int n, area;
	static boolean[][] visited = new boolean[100][100];
	static int[][] point;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 입력
		n = Integer.parseInt(br.readLine());
		point = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			point[i][0] = Integer.parseInt(st.nextToken());
			point[i][1] = Integer.parseInt(st.nextToken());
		}
		// 입력끝

		// 한칸씩 탐색하면서 넓이에 추가.
		for (int i = 0; i < n; i++) { // 색종이 개수만큼 탐색
			for (int x = point[i][0]; x < point[i][0] + 10; x++) { // 색종이 x좌표 10개
				for (int y = point[i][1]; y < point[i][1] + 10; y++) { // 색종이 y좌표 10개
					if (!visited[y][x]) { // visited를 사용해서 이미 방문한곳일경우 넓이를 구하지않는다.
						area++;
						visited[y][x] = true;
					}
				}
			}
		}
		
		//출력
		System.out.println(area);

	}

}
