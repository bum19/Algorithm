/*
 * 차분배열? 개념 사용. 
 * 일별로 3군데에 체크.(차분배열방식적용)
 * 시간복잡도 O(3N + M^2)
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static int m,n;
	public static int[] arr; //맨 왼쪽열과 맨 위쪽행 값들을 저장할 차분배열
	public static int[][] larvas;
	public static int[] dy = {0,-1,-1}; //왼, 왼위, 위
	public static int[] dx = {-1,-1,0};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		arr = new int[2* m - 1];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			arr[zero] += 1;
//			arr[zero+one] -= 1;
//			arr[zero+one] += 2;
			if(zero + one < 2 * m -1)
				arr[zero+one] += 1;
		}
		
		//init
		larvas = new int[m][m];
		for(int i = 0; i< m; i++) {
			Arrays.fill(larvas[i],1);
		}
		
		int y = m-1, x = 0;
		int idx = 0; // 차분 배열 탐색을 위한 인덱스
		int plus = 0; // 차분 배열을 통해서 더하기
		while(x < m) {
			plus += arr[idx++];
			
			larvas[y][x] += plus;
			
			if(y > 0) {
				y--;
			}
			else {
				x++;
			}
		}
		
		fill();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(larvas[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	private static void fill() {
		for(int i = 1; i < m; i++) {
			for(int j = i; j < m; j++) {
				//fill down
				larvas[j][i] = Math.max(Math.max(larvas[j][i-1], larvas[j-1][i-1]), larvas[j-1][i]);
				//fill right
				larvas[i][j] = Math.max(Math.max(larvas[i][j-1], larvas[i-1][j-1]), larvas[i-1][j]);
			}
		}
	}
}
