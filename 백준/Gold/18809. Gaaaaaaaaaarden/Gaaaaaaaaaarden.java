import java.io.*;
import java.util.*;
//조합으로 시작 좌표 구하고,
//bfs를 통해서 꽃개수구하기
//초록, 빨강순으로 뿌리기. 호수에는 불가능. (색,도착 시간) 저장.
//0이아닌 같은 시간 도달시, (색,도착 시간 저장), 꽃증가. 큐에 담지는 않는다. 탐색중지

public class Main {
	
	public static int n,m,g,r, maxFlower;
	public static int[][] gArr; //[k][0], [k][1] : k의 y좌표, k의 x좌표
	public static int[][] rArr; //[k][0], [k][1] : k의 y좌표, k의 x좌표
	public static List<int[]> possibleLand;
	public static boolean[] isSelected;
	
	
	public static int[][] gArrivedTime; //g 배양액 도착시간
	public static int[][] rArrivedTime; //r 배양액 도착시간
	public static boolean[][] isFlower; 
	public static int[][] map;
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		gArr = new int[g][2];
		rArr = new int[r][2];
		gArrivedTime = new int[n][m];
		rArrivedTime = new int[n][m];
		isFlower = new boolean[n][m];
		possibleLand = new ArrayList<int[]>();
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2)possibleLand.add(new int[] {i,j});
			}
		}
		isSelected = new boolean[possibleLand.size()];
		
		maxFlower = 0;
		combG(0,0);		
		
		System.out.println(maxFlower);
	}
	
	private static void combG(int idx, int depth) {
		if(depth == g) {
			combR(0,0);
			return;
		}
		
		for(int i = idx; i < possibleLand.size(); i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				gArr[depth] = possibleLand.get(i);
				combG(i+1, depth+1);
				isSelected[i] = false;
			}
		}
	}
	
	private static void combR(int idx, int depth) {
		if(depth == r) {
			//조합끝.
			//초기화
			gArrivedTime = new int[n][m];
			rArrivedTime = new int[n][m];
			isFlower = new boolean[n][m];
			//배양
			expand();
			return;
		}
		
		for(int i = idx; i< possibleLand.size(); i++) {
			if(!isSelected[i]) {
				rArr[depth] = possibleLand.get(i);
				combR(i+1,depth+1);
			}
		}
	}
	
	private static void expand() {
		int flowerCnt = 0;
		//현재 g 위치 담기. 현재r 위치담기. 위치 각각 -1로 초기화, 거리 0.
		Queue<int[]> q = new ArrayDeque<int[]> ();
		for(int i = 0; i < g; i++) {
//			사실 bfs시 거리는 안들고 다녀도 됨. 미방문구역을 -1로 초기화하는식으로 구현하면 됨. 미방문구역을 0으로 해서 들고다님.
			q.add( new int[] {'g', gArr[i][0], gArr[i][1], 0}); //배양액색, 좌표, 거리
			gArrivedTime[gArr[i][0]][gArr[i][1]] = -1; //시작점 -1처리
		}
		for(int i = 0; i < r; i++) {
			q.add(new int[] {'r', rArr[i][0], rArr[i][1], 0}); //배양액색, 좌표, 거리
			rArrivedTime[rArr[i][0]][rArr[i][1]] = -1; //시작점 -1처
		}
		
		while(!q.isEmpty()) {
			//2나 0이면서  방문한적없는곳만 방문가능 
			int[] cur = q.poll();
			//꽃이면 continue
			if(isFlower[cur[1]][cur[2]]) continue;
			
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[1] + dy[dir];
				int nx = cur[2] + dx[dir];
				if(ny <0 || nx < 0 || ny>=n || nx >= m || map[ny][nx] == 0)continue; //인덱스벗어나거나 호수면 continue
				if(cur[0] == 'g' && gArrivedTime[ny][nx] != 0) continue; //방문한적있으면 continue
				if(cur[0] == 'r' && rArrivedTime[ny][nx] != 0) continue; //방문한적있으면 continue
				
				//g일때
				if(cur[0] == 'g') {
					//g는 항상 r보다 먼저 도달. 먼저 큐에 넣어씀.
					gArrivedTime[ny][nx] = cur[3] + 1;
					q.add(new int[] {'g',ny,nx,cur[3]+1});
				}
				
				
				//r일때
				if(cur[0] == 'r') {
					rArrivedTime[ny][nx] = cur[3] + 1;
					//가려는곳에 g가동시에 왔다면, 꽃피우고 탐색중단.
					if(gArrivedTime[ny][nx] == rArrivedTime[ny][nx]) {
						isFlower[ny][nx] = true;
						flowerCnt++;
					}
					//그게아니라면, add
					else {
						q.add(new int[] {'r',ny,nx,cur[3]+1});
					}
				}
			}
		}

		if(maxFlower < flowerCnt) maxFlower = flowerCnt;
		
	}
}
