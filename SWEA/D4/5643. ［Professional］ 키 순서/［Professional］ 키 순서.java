import java.io.*;
import java.util.*;
//플로이드 워샬로 각경로가 존재하는지 여부 파악후, 어떤정점에 대해 모든 정점에 대한 경로가 존재한다면 해당정점은 자기 순위를 알수있다.

public class Solution {
	public static final int INF = 500;
	public static int t, n, m, answer;
	public static int[][] adj;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine().trim());
			m = Integer.parseInt(br.readLine().trim());
			adj = new int[n][n];
			answer = n; //answer 전체 정점 개수
			
			//adj 값 INF로 초기화
			for(int i = 0 ; i < n; i++) {
				for(int j = 0; j < n ; j++) {
					adj[i][j] = INF;
				}
			}
			
			//adj 입력
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int v1 = Integer.parseInt(st.nextToken())-1;
				int v2 = Integer.parseInt(st.nextToken())-1;
				adj[v1][v2] = 1;
			}
			
			
			//플로이드 워샬 알고리즘
			for(int k =0 ; k < n; k++) {
				for(int i = 0; i< n; i ++) {
					for(int j = 0; j < n; j++) {
						adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
					}
				}
			}
			
			//개수 세기. 자기자신의 순위모르는 사람 만날때마다 answer 값 하나씩 감소
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(i == j) continue;
					if(adj[i][j] == INF && adj[j][i] == INF) { //i정점에서 갈수 없는 j정점이 있다면
						answer--;
						break;
					}
				}
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
			
		}
		System.out.println(sb);

	}

}
