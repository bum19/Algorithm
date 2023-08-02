import java.util.Scanner;

public class Main {
	
		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			StringBuilder sb = new StringBuilder();
			int[][] input;
			int n, m, x1, x2, y1, y2, sum = 0;
			
			n = sc.nextInt();
			m = sc.nextInt();
			input = new int[n][n];
			
			for(int i = 0 ; i < n; i++) {
				for(int j = 0; j <n; j++) {
					sum += sc.nextInt();
					input[i][j] = sum;
				}
				sum = 0;
			}
			
			for(int i = 0; i < m; i++) {
				x1 = sc.nextInt() - 1 ;
				y1 = sc.nextInt() - 1;
				x2 = sc.nextInt() - 1;
				y2 = sc.nextInt() - 1;
				sum = 0;
				for(int x = x1; x <= x2; x++) {
					sum += y1==0?input[x][y2]:input[x][y2]-input[x][y1-1];
				}
				sb.append(sum).append("\n");
			}
			
			System.out.println(sb);
	}
 
}