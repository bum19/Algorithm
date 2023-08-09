//메모리 : 
//실행시간 : 
import java.util.*;
import java.io.*;

public class Solution {
	//상하좌우 방향 정하기
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	public static int t, n, currentStart, cnt, maxStart, maxCnt;
	public static int[][] arr; // [i][0] : i값이 들어간 y좌표, [i][1] : j값이 들어간 x좌표

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 입력
		t = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine());
			arr = new int[n * n + 1][2]; // 0~n*n개의 숫자의 좌표를 저장하는 배열
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					arr[tmp][0] = i;
					arr[tmp][1] = j;
				}
			} // 입력끝
			
			//구현
			find();
			//출력저장
			sb.append("#").append(test_case).append(" ").append(maxStart).append(" ").append(maxCnt).append("\n");
		}
		//출력
		System.out.println(sb);
	}

	// 숫자 1부터 n^2까지 연속된 인덱스인지 확인. 연결이 끊긴다면 cnt갱신
	static void find() {
		cnt = 1 ;
		maxCnt = Integer.MIN_VALUE;
		currentStart = 1;
		int py = arr[currentStart][0];
		int px = arr[currentStart][1];
		
		//py, px = 이전 y, x인덱스
		for(int i = 2; i <= n*n; i++) {
			int x, y, j;
			//이전 좌표와 연결된 좌표인지 확인.
			for(j = 0; j < 4; j++) {
				y = py + dy[j];
				x = px + dx[j];
				if(y == arr[i][0] && x == arr[i][1]) {
					//연결된 좌표면 cnt 증가후 for문탈출
					cnt++; break;
				}
			}
			//만약 연속되지않았다면 maxCnt 확인후 갱신, cnt,currentStart갱신
			if(j ==4) {
				if(maxCnt < cnt) {
					maxCnt = cnt;
					maxStart = currentStart;
				}
				cnt = 1;
				currentStart = i;
			}
			//탐색 계속함
			py = arr[i][0];
			px = arr[i][1];
		}
		//for문 끝나고 현재쌓인거 확인 후 갱신
		if(maxCnt < cnt) {
			maxCnt = cnt;
			maxStart = currentStart;
		}
	}

}
