/*
 * 밑에서부터 한줄씩보면서 블록 사이값만큼 빗물값 더해가기.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int h,w;
	public static int[] heights;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		heights = new int[w];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < w; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
		
		int answer = 0;
		for(int y = 1; y <= h; y++) {
			int boardIdx = -1;
			for(int x = 0; x < w; x++) {
				if(heights[x] >= y) {
					if(boardIdx != -1) {
						answer += x - boardIdx - 1;
					}
					boardIdx = x; 
				}
			}
		}
		
		System.out.println(answer);
	}
}
