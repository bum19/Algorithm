/*
 * 위상정렬 응용한다.
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n,m;
	public static int[] degree;
	public static List<Integer>[] adjs; // 해당 인덱스 다음에 오는 노드 번호 리스트
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		degree = new int[n+1];
		adjs = new List[n+1];
		for(int i = 1; i<= n; i++) {
			adjs[i] = new ArrayList<>();
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int pre = -1;
			for(int j = 0; j < num; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(pre != -1) {
					adjs[pre].add(cur);
					degree[cur]++;
				}
				pre = cur;
			}
		}
		
		if(!topologySort()) {
			System.out.println(0);
			return;
		};
		
		System.out.println(sb);
	}
	
	private static boolean topologySort() {
		Queue<Integer> q = new ArrayDeque<>();
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) q.add(i);
		}
		
		for(int i = 0; i < n; i++) {
			if(q.isEmpty()) {
				return false;
			}
			int cur = q.poll();
			sb.append(cur).append('\n');
			for(int singer: adjs[cur]) {
				if(--degree[singer] == 0) {
					q.add(singer);
				}
			}
		}
		return true;
	}
}
