import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	//점화식을 이용한 풀이.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int[][] input;
		int n, m, x1, x2, y1, y2, sum = 0, result;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//점화식의 일관성을 위해 배열크기를 가로세로 1줄씩 늘림.
		input = new int[n+1][n+1];
		
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				input[i][j] = input[i][j-1]+ input[i-1][j] - input[i-1][j-1] + Integer.parseInt(st.nextToken());  
			}
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			
			result = input[x2][y2] - input[x1-1][y2] - input[x2][y1-1] + input[x1-1][y1-1];
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
//	// 한줄씩 누적합을 구해서 풀이
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		StringBuilder sb = new StringBuilder();
//		int[][] input;
//		int n, m, x1, x2, y1, y2, sum = 0;
//
//		n = sc.nextInt();
//		m = sc.nextInt();
//		input = new int[n][n];
//
//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < n; j++) {
//				sum += sc.nextInt();
//				input[i][j] = sum;
//			}
//			sum = 0;
//		}
//
//		for (int i = 0; i < m; i++) {
//			x1 = sc.nextInt() - 1;
//			y1 = sc.nextInt() - 1;
//			x2 = sc.nextInt() - 1;
//			y2 = sc.nextInt() - 1;
//			sum = 0;
//			for (int x = x1; x <= x2; x++) {
//				sum += y1 == 0 ? input[x][y2] : input[x][y2] - input[x][y1 - 1];
//			}
//			sb.append(sum).append("\n");
//		}
//
//		System.out.println(sb);
//	}

}