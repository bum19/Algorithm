//
import java.io.*;
import java.util.*;

public class Solution {
	public static int t, n, m;
	public static int[] parents;
	public static List<int[]> edges;
	public static Set<Integer> roots;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			edges = new ArrayList<int[]>();
			parents = new int[n+1];
			roots = new HashSet<Integer>();
			for(int i = 1; i <= n; i++) {
				parents[i] = i;
			}
			
			for(int i = 0; i < m ; i++) {
				st = new StringTokenizer(br.readLine());
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				edges.add(new int[] {idx1,idx2});
			}
			
			
			for(int i = 0; i < m; i++) {
				union(edges.get(i)[0], edges.get(i)[1]);	//간선들을 보며 유니온 진행
			}
			
			//전부 루트가 아닐거므로(나중에 크게 트리가 합쳐지는것 생각)
			for(int i = 1; i <= n; i++) {	//정점기준으로 전부 find실행(루트로 합친다.)
				find(i);
			}
			
			for(int i = 1; i <= n; i++) {
				roots.add(parents[i]); 	//parents에 적힌 root 넣기
			}
			
			sb.append("#").append(test_case).append(" ").append(roots.size()).append("\n");
		}
		System.out.print(sb);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	public static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}

}
