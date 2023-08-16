//실행시간	:	
//메모리	:
import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static int[][] frame;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tmp;
		
		n = Integer.parseInt(br.readLine().trim());
		
		frame = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			tmp = br.readLine().trim();
			for(int j = 0; j < n; j++) {
				frame[i][j] = tmp.charAt(j) - '0';
			}
		}
		press(0, 0, n);
		
		System.out.println(sb);
	}
	
	private static void press(int sr, int sc, int size) {
		
		//쪼갤지말지 확인
		int oneCount = 0, zeroCount = 0;
		for(int i = sr; i < sr + size; i++) {
			for(int j = sc; j < sc + size; j++) {
				if(frame[i][j] == 0) zeroCount++;
				else if(frame[i][j] == 1) oneCount++;
			}
		}
		
		if(zeroCount == size*size) {
			sb.append(0);
			return;
		}
		else if(oneCount == size*size) {
			sb.append(1);
			return;
		}
		
		//쪼기야된다면 괄호로 감싸기
		sb.append("(");
		//왼쪽위부터 시계방향으로 탐색
		int half = size/2;
		press(sr, sc, half);
		press(sr, sc + half, half);
		press(sr + half, sc, half);
		press(sr + half, sc + half, half);
		sb.append(")");
	}
}
