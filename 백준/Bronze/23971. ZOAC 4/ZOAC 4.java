import java.io.*;
import java.util.*;
public class Main {
	public static int count, h, w, n, m;
	public static int sx = 1, sy =1;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		count = 0;
		while(sy <= h) {
			while(sx <= w) {	
				count++;
				sx += m+1;
			}
			sx = 1;
			sy += n+1;
		}
		
		System.out.println(count);
	}
}
