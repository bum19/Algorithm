import java.io.*;
import java.util.*;
/* 
 * 진용이풀이
 * dp[k] = 위치 k까지 도달할때 걸리는 가장 짧은 거리
 * dp[k] = k까지 도달하는데 걸리는 최소길이
 * 지름길을 끝나는 위치로 오름차순 정렬
 * 끝나는 위치가 짧은 지름길부터 하나씩 적용후, 그 이후 dp값들 갱신
 * 모든 지름길 다 탐색할때까지 반복
 */

public class Main {
	public static PriorityQueue<FastWay> fastWays; //지름길들 저장.
	public static int dp[];
	public static int n, d;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		dp = new int[10001];
		fastWays = new PriorityQueue<FastWay>( (fw1, fw2) -> {
			return Integer.compare(fw1.end, fw2.end);
		});
		
		
		for(int i =0 ;i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			fastWays.offer(new FastWay(start,end,dist));
		}
		//input done
		
		//init
		for(int i = 0; i < 10001; i++) {
			dp[i] = i;
		}
		
		while(!fastWays.isEmpty()) {
			FastWay fw = fastWays.poll();
			
			dp[fw.end] = Math.min(dp[fw.end], dp[fw.start] + fw.dist);
			
			for(int i = fw.end; i <= d; i++) {
				dp[i] = Math.min(dp[i], dp[fw.end] + (i-fw.end));
			}
		}
		
		System.out.println(dp[d]);
	}
	
	public static class FastWay{
		int start;
		int end;
		int dist;
		public FastWay(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
	}
	
}
