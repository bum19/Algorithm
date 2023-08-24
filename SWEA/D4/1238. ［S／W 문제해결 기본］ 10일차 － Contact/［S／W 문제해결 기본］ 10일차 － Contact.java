//
import java.io.*;
import java.util.*;
//bfs 활용.
public class Solution {
	public static int inputLength, start, nodeNum, depth;
	public static List<Integer>[] adjs;
	public static boolean[] visited;
	public static PriorityQueue<int[]> endNodes; //끝 노드의 노드번호, 연락을받게된 시간. 0번인덱스 : 노드번호, 1번인덱스 : 깊이
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			st = new StringTokenizer(br.readLine());
			inputLength = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			adjs = new List[101]; //최대 노드개수 100개
			for(int i = 0; i < 101; i++) {
				adjs[i] = new ArrayList<Integer>();
			}
			visited = new boolean[101];
			endNodes = new PriorityQueue<int[]>((a1,a2)->{
				if(a1[1] < a2[1]) 		return 1;
				else if(a1[1] == a2[1]) return Integer.compare(a1[0], a2[0]) * -1;
				else			  		return -1;
			});
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < inputLength/2; i++) {
				int idx1 = Integer.parseInt(st.nextToken());
				int idx2 = Integer.parseInt(st.nextToken());
				if(adjs[idx1] == null) adjs[idx1] = new ArrayList<Integer>();
				adjs[idx1].add(idx2);
			}
//			System.out.println(Arrays.deepToString(adjs));
			
			bfs(start);
			sb.append("#").append(test_case).append(" ").append(endNodes.poll()[0]).append("\n");
		}
		System.out.print(sb);
	}

	private static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		
		visited[start] = true;
		q.offer(new int[] {start, 0}); //시작노드와 전화받는 시간을 입력.
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curNode = cur[0];
			int curTime = cur[1];
//			System.out.println("curNOde : "+curNode+", curTime :"+curTime);
			boolean isLastNode = true; //현재 노드가 마지막 노드인지 확인.
			for(int nextNode : adjs[curNode]) { //현재 노드에 인접한 노드들에 대해 탐색.
				if(!visited[nextNode]) {
//					System.out.println("다음노드는 얘를 넣을거에요"+nextNode);
					isLastNode = false; //갈곳이 있으므로 마지막노드가 아님 표시
					visited[nextNode] = true; //다음 갈곳 방문처리
					q.offer(new int[] {nextNode, curTime+1});
				}
			}
			
			if(isLastNode) {
//				System.out.println("어 마지막 노드다");
			endNodes.add(new int[] {curNode,curTime}); //현재노드가 마지막 위치의 노드면 노드번호와 전화받는시간 입력.
			}
		}
	}
}
