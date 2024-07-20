/*
 * 왼쪽부터 입력에따라 건물 더하기 처리
 * 기존에 존재했던 높이인지 파악을 위해 pq에 넣어놓고 하나씩 뽑기
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
			
		int answer = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			int cur = Integer.parseInt(st.nextToken());
			if(cur == 0) {
				pq.clear();
				continue;
			}
			
			while(!pq.isEmpty() && pq.peek() > cur) {
				pq.poll();
			}
			
			if(pq.isEmpty()) {
				answer++;
				pq.add(cur);
			}
			else if(pq.peek() == cur) {
				continue;
			}
			else {
				answer++;
				pq.add(cur);
			}
		}
		
		System.out.println(answer);
	}
}
