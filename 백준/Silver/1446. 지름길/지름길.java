import java.io.*;
import java.util.*;
/*
 * 지름길을 타거나, 안타거나 모든 경우 탐색
 * 지름길 최대 12개, 2^12승의 경우의 수 가능.
 * 풀이 방법은 생각났는데, 구현이 좀 더러움.
 * 
 */
public class Main {
	public static List<FastWay>[] fastWays;// 지름길, 인덱스는 지름길의 시작위치.
	public static int n,d, minDist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		fastWays = new List[10001];
		for(int i =0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			if(fastWays[start] == null) {
				fastWays[start] = new ArrayList<>();
			}
			fastWays[start].add(new FastWay(start,end,dist));
		}
		//input done
		
		minDist = Integer.MAX_VALUE;
		recur(0,0);
		
		System.out.println(minDist);
		
	}
	
	private static void recur(int curLoc, int curDist) {
		if(curLoc >= d) {
			if(curLoc == d && minDist > curDist) {
				minDist = curDist;
			}
			return;
		}
		
		for(;curLoc < d; curLoc++) {
			//지름길타기
			if(fastWays[curLoc] != null) {
				for(FastWay fw : fastWays[curLoc]) {
					recur(fw.end, curDist + fw.dist);
				}
			}
			
			//지름길 안타고 그냥 가기
			curDist++;
		}
		
		if(minDist > curDist) {
			minDist = curDist;
		}
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
