import java.io.*;
import java.util.*;

//플로이드 워샬로 dp[1][1], dp[2][2] ,... dp[v][v] 중 최솟값 찾기
//INF 값 40만 -> 400만으로 바꿈 -> 1000만으로 바꿈
public class Main{
	public static final int INF = 10000000;
	public static int v,e,minCycle;
	public static int[][] dist;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		
		dist = new int[v+1][v+1];
		for(int i = 1; i <= v; i++) {
			Arrays.fill(dist[i],INF);
		}
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			dist[n1][n2] = d;
		}
		//input done
		
		//floyd-warshall
		for(int k = 1; k <= v; k++) {
			for(int i =1; i<= v; i++) {
				for(int j = 1; j <= v; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		
		//get Answer
		minCycle = INF;
		for(int i = 1; i<=v; i++) {
			if(minCycle > dist[i][i]) minCycle = dist[i][i];
		}
		if(minCycle == INF) System.out.println(-1);
		else System.out.println(minCycle);
	}
}