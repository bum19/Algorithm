import java.io.*;
import java.util.*;

//음의 사이클이 존재한다면, 참. -> 웜홀나오는곳에서 들어오는곳으로 경로 있나 확인, 경로길이가 음수면 yes
//-> 벨만포드로 음의사이클 확인.
//아니면, 거짓
//같은방향에 대해서 도로정보는 최솟값만입력받아도됨.
//지점 중복방문 가능.
public class Main {

	public static final int INF = Integer.MAX_VALUE;
	public static int[] dist;
	public static int[][] adjs;
	public static List<Edge> edges;
	public static List<Integer> warmHoles;
	public static int t,n,m,w;
	public static boolean isPossible;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		//input
		t = Integer.parseInt(br.readLine().trim());
		for(int testCase = 1; testCase <= t; testCase++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			dist = new int[n+1];
			Arrays.fill(dist, INF);
			adjs = new int[n+1][n+1];
			for(int i = 1; i<=n; i++) {
				Arrays.fill(adjs[i], INF);
			}
			edges = new ArrayList<Edge>();
			warmHoles = new ArrayList<Integer>();
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				
				if(adjs[start][end] > time) adjs[start][end] = time;
				if(adjs[end][start] > time) adjs[end][start] = time;
			}
			
			for(int i = 0; i < w; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int time = Integer.parseInt(st.nextToken());
				if(adjs[start][end] > time * -1) {
					adjs[start][end] = time * -1;
					warmHoles.add(end);
				}
			}
			
			//edge input
			for(int i = 1; i <= n; i++) {
				for(int j =1 ; j<=n; j++) {
					if(adjs[i][j] != INF) {
						edges.add(new Edge(i,j,adjs[i][j]));
					}
				}
			}
			//input done

			
			//1. 웜홀 나오는 부분에 대해 bf실행. 음수사이클 생길수 있 모든 곳 확인가능 : 시간복잡도V*E*W = 2억5천
			//2. 연결안된 노드가 있을수 있음.union-find로 체크한뒤, parent 값에 한해서 체크
			
			//1번방법
			for(int warmHole : warmHoles) {
				if(isPossible = bf(warmHole)) break;
			}
			
			if(isPossible) sb.append("YES\n");
			else sb.append("NO\n");
		}
		System.out.println(sb);
	}
	
	private static boolean bf(int start) {
		dist[start] = 0;
		
		for(int i = 1; i <=n; i++) { //
			for(int j = 0; j < edges.size(); j++) {
				Edge edge = edges.get(j);
				if(dist[edge.n1] != INF && dist[edge.n2] > dist[edge.n1]+ edge.dist) {
					dist[edge.n2] = dist[edge.n1] + edge.dist;
					if(i == n) return true; //음수사이클 존재하면 가능
				}
			}
		}
		return false;
	}
	

	
	static class Edge{
		int n1,n2,dist;
		Edge(int n1, int n2, int dist){
			this.n1 = n1;
			this.n2 = n2;
			this.dist = dist;
		}
		public String toString() {
			return n1+","+n2+" : "+ dist;
		}
	}
}
