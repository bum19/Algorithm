import java.io.*;
import java.util.*;
/*
 * union-find를 통해 연결되어있는지확인하자.
 * 
 */
public class Main {
	public static int n, m;
	public static int[] parents;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		m = Integer.parseInt(br.readLine().trim());
		
		//입력받으면서 동시에 union, find하자
		parents = new int[n+1]; //1번인덱스부터사용한다.
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		
		for(int i = 1; i <= n ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <=n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i,j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int pre = Integer.parseInt(st.nextToken());
		for(int i = 1; i< m; i++) {
			if(union(pre, Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}
	
	private static int find(int node) {
		if(parents[node] == node) return node;
		
		return parents[node] = find(parents[node]);
	}
	
	private static boolean union(int aNode, int bNode) {
		int aRoot = find(aNode);
		int bRoot = find(bNode);
		if(aRoot == bRoot) return false;
		
		parents[aRoot] = bRoot;
		return true;
	}
}
