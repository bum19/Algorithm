import java.io.*;
import java.util.*;
//플로이드 워샬 알고리즘 사용하는 문제
//d[i,j] = min(d[i,j], d[i,k] + d[k,j]) // i에서 j까지의 거리는 i에서 k까지 가는 거리의 최소값에다가 k에서 j까지 가는 거리의 최솟값의 합(즉, k를 경유해서 가는 거리) k는 1~n(단 i!=k, j!=k이다)  
//음수사이클이 존재하는 경우 최단거리가 음의무한으로 수렴할수있으므로 성립 x
//
public class Solution {
	public static final int INF = 1001;
	public static int t,n;
	public static int[][] adj; //인접행렬로 시작해 최단경로 행렬로 바뀔것임.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			adj = new int[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					adj[i][j] = Integer.parseInt(st.nextToken());
					if(adj[i][j] == 0) adj[i][j] = INF; //
				}
			}
			
			
			//플로이드워샬로 모든 최단경로 갱신.
			for(int k = 0 ; k < n; k++) {
				for(int i = 0; i < n; i++) {
					for(int j = 0; j <n; j++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
			
			//음의 사이클있나 체크
			for(int i = 0; i < n; i++) {
				if(adj[i][i] < 0) {
					System.out.println("음의 사이클 존재함.");
				}
			}
			
			//값 노드별 최단값 더하기
			int minVal = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++) {
				int tmp = 0;
				for(int j = 0; j < n; j++) {
					if(i==j) continue;
					tmp += adj[i][j];
				}
				if(minVal > tmp) minVal = tmp;
			}
			
			sb.append("#").append(test_case).append(" ").append(minVal).append("\n");
		}
		System.out.println(sb);
	}

}
