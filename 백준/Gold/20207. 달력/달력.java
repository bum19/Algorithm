/*
 * 달력일자별로 일정을넣는다.
 * 현재일자에 일정이 있다면, 일정없을때까지탐색한다.
 * 	일정이 가장 많은 날 * 연속된 일정일을 곱한다. 
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static int[] calendar;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		calendar = new int[366];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			for(int j = s; j <= e; j++) {
				calendar[j]++;
			}
		}
		
		int area = 0;
		int width = 0;
		int height = 0;
		for(int i = 1; i <= 365; i++) {
			if(calendar[i] == 0) {
				area += width * height;
				width = 0;
				height = 0;
				continue;
			}
			
			width++;
			height = Math.max(height, calendar[i]);
		}
		
		area += width * height;
		
		System.out.println(area);
	}
}
