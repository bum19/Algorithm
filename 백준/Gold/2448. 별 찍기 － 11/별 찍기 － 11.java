import java.io.*;
import java.util.*;
/*
 * 격자판만들고찍자. n 은 3000, 격자칸 크기 최대 900만.
 */
public class Main {
	public static char[][] board;
	public static int n;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		board= new char[n][2*n-1];
		//초기화
		for(int i = 0; i < n; i++) {
			Arrays.fill(board[i], ' ');
		}
		draw(n, 0,0);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < board.length;i++) {
			for(int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	private static void draw(int num, int y, int x) {
		if(num == 3) {
			//최소단위그리기
			for(int i = 0; i < 5; i++) {
				if(i == 2) {
					board[y][x+i] = '*';
				}
				
				if(i%2 != 0) {
					board[y+1][x+i] = '*';
				}

				board[y+2][x+i] = '*';
			}
			return;
		}
		
		draw(num/2, y, x + num/2);
		draw(num/2, y + num/2, x);
		draw(num/2, y + num/2, x + num);
	}
}
