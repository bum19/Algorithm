//
import java.util.*;
import java.io.*;
public class Main {
	static int n, l;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n; i++) {
			pq.offer(Integer.parseInt(st.nextToken()));
		}
		
		
		while(!pq.isEmpty()) {
			int h = pq.poll();
			if(l < h) break;
			l += 1;
		}
		System.out.println(l);

	}

}
