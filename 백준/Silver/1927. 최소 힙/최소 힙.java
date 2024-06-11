import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		
		for(int i = 0 ; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(pq.isEmpty()) {
					sb.append('0');
				}
				else {
					sb.append(pq.poll());
				}
				sb.append('\n');
			}
			else {
				pq.add(input);
			}
		}
		
		System.out.println(sb);
	}
}
