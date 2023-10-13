import java.io.*;
import java.util.*;
public class Main {
//내부에있는 거리가 1보다작은경우 가능하다고 출력이됨. 고려해보자. 사이에 0만있는지 검사라던지.	
	public static int n,m, minLen, islandVisitCnt;
	public static int[][] map;
	public static boolean[][] mapVisited;
	public static List<List<int[]>> islands;
	
	public static List<int[]>[] adjs; // 각 섬에 인접하는 섬과의 거리. int[0] = 섬, int[1] = 거리.
	public static int[] dy = {-1,0,1,0}; //상우하좌
	public static int[] dx = {0,1,0,-1}; //상우하좌
	
	//prim 알고리즘
	public static boolean[] islandVisited;
	public static int[] minEdges;
	public static PriorityQueue<int[]> pq; //원소는 정점, 해당정점까지의 최소값.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		mapVisited = new boolean[n][m];
		islands = new ArrayList<>();
		
		//입력
		for(int i  =0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
//		System.out.println(Arrays.deepToString(map));
		
		//섬파악
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] != 0 && !mapVisited[i][j]) {
//					System.out.println("섬 만들기시작. 좌표 : "+i+","+j);
					makeIsland(i,j); //i,j는 행열좌표
				}
			}
		}
		
//		System.out.println("섬개수 : "+islands.size());
		
		adjs = new List[islands.size()];
		for(int i = 0 ; i < islands.size(); i++) {
			adjs[i] = new ArrayList<int[]>();
		}
		
		//간선파악
		for(int i = 0; i < islands.size()-1; i++) {
			for(int j = i+1; j < islands.size(); j++) {
				getDist(i,j); //i, j는 섬인덱스
				
			}
		}
		////////////출력부문
//		for(int i = 0 ; i < islands.size(); i++) { //섬별
//			for(int j = 0 ; j < islands.get(i).size(); j++) { //섬내 좌표들
//					System.out.println(islands.get(i).get(j)[0] + " " + islands.get(i).get(j)[1]);
//				}
//			System.out.println("---------------");
//		}
//		
//		for(int i = 0 ; i < adjs.length; i++) {
//			for(int j = 0 ; j < adjs[i].size(); j++) {
//				System.out.println(i+"섬의 인접정점 과 거리 :"+ adjs[i].get(j)[0] +", "+adjs[i].get(j)[1]);
//			}
//		}
//		
		//////////
		//prim
		
		minEdges = new int[islands.size()];
		pq = new PriorityQueue<>((a1,a2) ->  {
			return Integer.compare(a1[1], a2[1]);
		});
		Arrays.fill(minEdges, Integer.MAX_VALUE);
		islandVisited = new boolean[islands.size()];
		minEdges[0] = 0;
		pq.offer(new int[] {0,0});
		while(!pq.isEmpty()) { 
			//현재 pq에서 뽑은애는 방문정점과 인접한 정점중 가장 짧은 간선으로 연결된 정점임.
			int[] cur = pq.poll();
			int curIsland = cur[0];
			int curEdgeLen = cur[1];
			if(islandVisited[curIsland]) continue; //갱신되기 전 값이 들어있을수 있음.
			
			//방문처리
			islandVisited[curIsland] = true;
			islandVisitCnt++;
			minLen += curEdgeLen;
			
			for(int[] adj : adjs[curIsland]) {//현재 추가한 정점과 인접한 정점간 거리가 갱신할곳있으면 갱신
				if(!islandVisited[adj[0]] && minEdges[adj[0]] > adj[1]) { //여기서 방문검사한번하면 엣지에 들어가는건 비방문애들만 있을거같긴함.
					minEdges[adj[0]] = adj[1];
					pq.offer(adj);
				}
			}
			
		}
		
		//출력
		if(islandVisitCnt == islands.size())
			System.out.println(minLen);
		else
			System.out.println(-1);
	}
	
	private static void makeIsland(int y, int x) { //섬bfs로파악해서, 하나의 섬 만들기
		
		//bfs로 섬파악
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> island = new ArrayList<>();
		mapVisited[y][x] = true;
		island.add(new int[] {y,x});
		q.offer(new int[] {y,x});
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= m || mapVisited[ny][nx] || map[ny][nx] == 0) continue; 
				mapVisited[ny][nx] = true; //방문처리
				island.add(new int[] {ny,nx}); //현재 섬정보에 좌표 추가
				q.offer(new int[] {ny,nx}); //탐색을 이어감.
			}
		}
		
		//탐색끝나면 섬 집어넣기
		islands.add(island);
	}
	
	private static void getDist(int n1, int n2) { //섬과 섬 사이의 거리구하기
		int edge = Integer.MAX_VALUE;
		//거리가 1이상이고, 같은 행/열이어야함.
		for(int[] n1Loc : islands.get(n1)) {
			for(int[] n2Loc : islands.get(n2)) {
				
				int tmp = Integer.MAX_VALUE, start, end; //두 점사이의 거리, 다리시작점, 다리끝점 
				boolean isAvailable = true; // 다리가능여부 
				
				if(n1Loc[0] != n2Loc[0] && n1Loc[1] != n2Loc[1]) continue; //섬을 이루는 두 좌표의 거리를 구할때, 행과 열 둘 다 다르면 둘사이의 거리는 못구함
				
				 //y좌표가 평행일때	
				if(n1Loc[0] == n2Loc[0]) {
					if(Math.abs(n1Loc[1] - n2Loc[1])-1 < 2) continue; //둘 사이의 다리 놓을때 다리 길이가 2 이상이어야함
					//두 점사이에 0이 없는지 확인
					if(n1Loc[1] > n2Loc[1]){
						start = n2Loc[1];
						end = n1Loc[1];
					} 
					else{
						start = n1Loc[1];
						end = n2Loc[1];
					}
					
					for(int i = start+1; i < end; i++) { //사이에 1이 있으면 불가능. 
						if(map[n1Loc[0]][i] == 1) {
							isAvailable = false;
							break;
						}
					}
					
					if(isAvailable) //두점사이에 1이없고, 2이상이면 가능.
						tmp = Math.abs(n1Loc[1] - n2Loc[1])-1;
					
				}
				// x좌표가 평행일때
				else { 
					if(Math.abs(n1Loc[0] - n2Loc[0])-1 < 2) continue; //둘 사이의 다리 놓을때 다리 길이가 2 이상이어야함
					//두 점사이에 0이 없는지 확인
					if(n1Loc[0] > n2Loc[0]){
						start = n2Loc[0];
						end = n1Loc[0];
					} 
					else{
						start = n1Loc[0];
						end = n2Loc[0];
					}
					
					for(int i = start+1; i < end; i++) { //사이에 1이 있으면 불가능. 
						if(map[i][n1Loc[1]] == 1) {
							isAvailable = false;
							break;
						}
					}
					
					if(isAvailable) //두점사이에 1이없고, 2이상이면 가능.
						tmp = Math.abs(n1Loc[0] - n2Loc[0])-1;
					
				}
				
				if(edge > tmp) edge = tmp;
			}
		}
		
		//거리를 구했으면 거리추가, 못구했으면 넘어가기.
		if(edge != Integer.MAX_VALUE) {
			adjs[n1].add(new int[] {n2, edge});
			adjs[n2].add(new int[] {n1, edge});
		}
	}

}
