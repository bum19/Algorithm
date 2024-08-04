/*
 * 모든칸 탐색, visited처리, ArrayList에 삽입
 * 이동
 * 이동 없을때까지 반복
 * O(2500*?)
 */
import java.io.*;
import java.util.*;
public class Main {	
	public static int n,l,r;
	public static int[][] countries;
	public static boolean[][] visited;
	public static List<int[]> list;
	
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		countries = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				countries[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int days = 0;
		boolean isMoved = true;
		list = new ArrayList<>();
		while(isMoved) {
			isMoved = false;
			
			visited = new boolean[n][n];
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(visited[i][j]) continue;
					//열고 이동하
					open(i,j,list);
					if(list.size() > 1) {
						isMoved = true; //연합이 결성된적있으면 이동한적있음 처리
					}
					list.clear();
				}
			}
			
			if(isMoved)days++;
		}
		System.out.println(days);
	}
	
	private static void open(int y,int x, List<int[]> list) {
		int total = 0;
		Queue<int[]> q = new ArrayDeque<>();
		visited[y][x] = true;
		total += countries[y][x];
		list.add(new int[] {y,x});
		q.add(new int[] {y,x});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			for(int dir = 0; dir <4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				if(ny < 0 || nx < 0 || ny >= n || nx >= n || visited[ny][nx] ||
						Math.abs(countries[ny][nx] - countries[cur[0]][cur[1]]) < l ||
						Math.abs(countries[ny][nx] - countries[cur[0]][cur[1]]) > r) continue;
				
				visited[ny][nx] = true;
				total += countries[ny][nx];
				list.add(new int[] {ny,nx});
				q.add(new int[] {ny,nx});
			}
		}
		if(list.size() >1)
			move(total, list);
	}
	
	private static void move(int total, List<int[]> list) {
		int avg = total/list.size();
		
		for(int[] loc : list) {
			countries[loc[0]][loc[1]] = avg;
		}
	}
		
}
