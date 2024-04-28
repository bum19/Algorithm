/*
 * 홀수길이를 가진 사이클이 존재하지 않으면 이분그래프
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int k,v,e;
	public static List<Integer>[] adjs;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		k = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= k; test_case++) {
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			adjs = new List[v+1];
			for(int i = 1; i <= v ;i++) {
				adjs[i] = new ArrayList<Integer>();
			}
			for(int i = 0 ; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				
				int v1 = Integer.parseInt(st.nextToken());
				int v2 = Integer.parseInt(st.nextToken());
				
				adjs[v1].add(v2);
				adjs[v2].add(v1);
			}
			
			
			//v가 1이 아니고, 홀수사이클이 없을경우 이분그래프
			if(v != 1 && !bfs()) {
				sb.append("YES\n");
			}
			else {
				sb.append("NO\n");
			}
			
		}
		System.out.println(sb);
	}
	public static boolean bfs() {
	    int[] distance = new int[v+1];
	    Arrays.fill(distance, -1); // 방문하지 않은 정점 표시

	    for (int start = 1; start <= v; start++) {
	        if (distance[start] == -1) { // 아직 방문하지 않은 정점에서 시작
	            Queue<Integer> q = new ArrayDeque<>();
	            distance[start] = 0;
	            q.add(start);

	            while (!q.isEmpty()) {
	                int cur = q.poll();
	                for (int next : adjs[cur]) {
	                    if (distance[next] == -1) { // 방문하지 않은 정점
	                        distance[next] = distance[cur] + 1;
	                        q.add(next);
	                    } else if ((distance[cur] + distance[next]) % 2 == 0) { // 홀수 사이클 판별
	                        return true; // 홀수 사이클 존재
	                    }
	                }
	            }
	        }
	    }
	    return false; // 홀수 사이클 없음
	}
	
//	public static boolean bfs() {
//		int[] distance = new int[v+1]; //1번정점에서부터 최단거리
//		Arrays.fill(distance, -1);
//		
//		Queue<int[]> q = new ArrayDeque<>(); //현재정점, 이전정점, 거리 들고 감.
//		distance[1] = 0;
//		q.add(new int[] {1,0,0});
//		while(!q.isEmpty()) {
//			int[] cur = q.poll();
//			int curV = cur[0];
//			int preV = cur[1];
//			int dist = cur[2];
//			
//			//다음 정점들 탐색
//			for(int next : adjs[curV]) {
//				System.out.println("curV : "+curV);
//				System.out.println("next : "+next);
//				if(next == preV) continue;
//				//다음정점 방문한적 없으면 가기
//				if(distance[next] == -1) {
//					System.out.println(next+"노드에 새로운값 입력. : "+(dist+1));
//					distance[next] = dist + 1;
//					q.add(new int[] {next,curV,dist+1});
//					continue;
//				}
//				//방문한적 있으면, 홀수되나 확인. 
//				else {
//					//홀수면 홀수사이클. 
//					System.out.println(next + " 방문한적있음. dist["+next+"] : "+distance[next]+", 거기까지 갈 거리 :"+(dist+1));
//					if( (distance[next] + (dist+1))%2 == 1 ) {
//						return true;
//					}
//				}
//			}
//		}
//		return false;
//	}
}
