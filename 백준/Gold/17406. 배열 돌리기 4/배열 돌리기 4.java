import java.util.*;
import java.io.*;

public class Main {
	public static int n, m, k, minValue; // 세로, 가로, 최솟값
	public static int[][] temp; // 회전용 원본 복사 배열
	public static int[][] arr; // 원본배열
	public static int[][] rotateInfos; // 회전정보
	public static int[] orders; // 회전순서
	public static boolean[] isSelected; // 회전순서 dfs를 위한 boolean배열

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		temp = new int[n][m];
		rotateInfos = new int[k][3];
		orders = new int[k];
		isSelected = new boolean[k];
		minValue = Integer.MAX_VALUE;

		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			rotateInfos[i][0] = Integer.parseInt(st.nextToken()); // r
			rotateInfos[i][1] = Integer.parseInt(st.nextToken()); // c
			rotateInfos[i][2] = Integer.parseInt(st.nextToken()); // s
		}
		// 입력끝

		// 회전순서정하고 회전시키기
		dfs(0);

		// 출력
		System.out.println(minValue);
	}

	//회전순서정하고, 순서대로 회전시킨뒤 최솟값 구하기
	static void dfs(int depth) {
		if (depth == k) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j <m; j++) {
					temp[i][j] = arr[i][j];
				}
			}
//			temp = arr.clone();

			// 회전순서 정해졌으면, 순서대로 회전하기.
			for (int i = 0; i < k; i++) {
				int startR = rotateInfos[orders[i]][0] - rotateInfos[orders[i]][2] - 1; // r - s - 1 (인덱스가 0부터시작하므로)
				int startC = rotateInfos[orders[i]][1] - rotateInfos[orders[i]][2] - 1; // c - s - 1
				int length = rotateInfos[orders[i]][2] * 2 + 1;							// 회전하는 배열의 크기는 length*length이다.
				rotate(startR, startC, length);	//회전

				// 회전잘했는지 확인용 디버깅 코드 시작
//				for (int y = 0; y < n; y++) {
//					for (int x = 0; x < m; x++) {
//						System.out.print(temp[y][x] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println("----------------");
				// 회전잘했는지 확인용 디버깅 코드 끝
			}

			
			calMin(); //최솟값 갱신
			return;
		}

		for (int i = 0; i < k; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				orders[depth] = i;
				dfs(depth + 1);
				isSelected[i] = false;
			}
		}
	}

	// 당기는식으로 구현
	static void rotate(int startR, int startC, int length) {

		//length값이 바뀌기때문에 이렇게 하면 안됨.
//		if (cnt > length / 2) {
//			return;
//		}
		
		//tmp 넣는 로직때문에 필요함
		if(length == 1 ) {
			return;
		}
		
		int tmp = temp[startR][startC];

		// 왼쪽 이동
		for (int y = 0; y < length - 1; y++) {
			temp[startR + y][startC] = temp[startR + y + 1][startC];
		}
		// 아래 이동
		for (int x = 0; x < length - 1; x++) {
			temp[startR + length - 1][startC + x] = temp[startR + length - 1][startC + x + 1];
		}
		// 오른쪽 이동
		for (int y = 0; y < length - 1; y++) {
			temp[startR + length - 1 - y][startC + length - 1] = temp[startR + length - 1 - y - 1][startC + length - 1];
		}
		// 위 이동
		for (int x = 0; x < length - 1; x++) {
			temp[startR][startC + length - 1 - x] = temp[startR][startC + length - 1 - x - 1];
		}

		temp[startR][startC + 1] = tmp;

		rotate(startR + 1, startC + 1, length - 2);
	}

	static void calMin() {
		for (int i = 0; i < n; i++) {
			int currentVal = 0;
			for (int j = 0; j < m; j++) {
				currentVal += temp[i][j];
			}
			minValue = Math.min(currentVal, minValue);
		}
	}
}
