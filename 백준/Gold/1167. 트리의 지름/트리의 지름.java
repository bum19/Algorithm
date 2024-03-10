/*
 * 
 * 시간복잡도 O(E)
 * 트리의 최대 간선 개수는 V-1개이다.
 * 알고리즘
 * 0)루트를 정한다.
 * 1)루트부터 재귀적으로 바닥부터 현재 노드까지의 최대 거리를 구하고 저장한다.
 * 2)현재 노드가 루트일때, 현재 노드를 지나치는 리프노드간 최대거리를 확인하고, treeRadius에 갱신시킨다.
 * 2)를 하는이유 : 1)만으로 실제 루트에서의 거리차이를 구하면, 다음과 같은 경우 최대거리를 놓칠수 있다.
 * 		 	  n
 * 			1/ \1
 *  		n 	n
 *  	100/ \100 
 *  
 */

import java.io.*;
import java.util.*;
public class Main {
	public static int V, treeRadius;
	public static List<NodeDist>[] adjs;
	public static int[] maxDist; //maxDist[i] = 트리 바닥에서 i노드까지의 최대 거리.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		V = Integer.parseInt(br.readLine().trim());
		maxDist = new int[V + 1]; // 1번노드부터 사용한다.
		Arrays.fill(maxDist, -1);
		adjs = new List[V+1];
		
		//input
		for(int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int curNode = Integer.parseInt(st.nextToken());
			adjs[curNode] = new ArrayList<>();
			
			while(true) {
				int node = Integer.parseInt(st.nextToken());
				//-1이면 다음줄 탐색
				if(node == -1) break;
				int dist = Integer.parseInt(st.nextToken());
				adjs[curNode].add(new NodeDist(node, dist));
			}
		}
		//input done
		
		//제일 연결된 간선이 많은 노드를 루트로한다.
		int root = -1;
		int edgeNum = -1;
		for(int i = 1; i <= V; i++) {
			if(adjs[i].size() > edgeNum) {
				root = i;
				edgeNum = adjs[i].size();
			}
		}
		
		//재귀로 treeRadius 구하기.
		recur(root, -1);
		System.out.println(treeRadius);
	}
	
//	재귀적으로 1)바닥부터 해당 노드까지 올수있는 최대거리를 찾고,  2)트리 지름을 갱신한다.
	private static int recur(int cur, int parent){
		if(maxDist[cur] != -1) return maxDist[cur];
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		for(int i = 0 ; i < adjs[cur].size(); i++) {
			
			int nextNode = adjs[cur].get(i).node;
			int dist = adjs[cur].get(i).dist;
			
			if(nextNode == parent) continue;
			pq.add(recur(nextNode, cur) + dist);
		}
		
		//현재 노드가 리프노드인 경우.
		if(pq.isEmpty()) {
			maxDist[cur] = 0;
			return maxDist[cur];
		}
		
		//현재까지의 최대 거리 저장.
		maxDist[cur] = pq.poll();
		
		//현재 노드 자식노드가 한개인경우
		
		
		//treeRadius갱신 tmp는 현재 노드를 지나치는 리프노드간 최대거리.
		int tmp = maxDist[cur]; //현재노드가 자식노드를 하나만 가질경우
		if(!pq.isEmpty()) tmp += pq.poll(); //현재노드가 자식노드를 하나 이상 가질경우.
		
		if(treeRadius < tmp) treeRadius = tmp;
		
		return maxDist[cur];
	}
	
	private static class NodeDist{
		int node, dist;
		public NodeDist(int node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
}
