import java.io.*;
import java.util.*;
//시간이랑 경로 출력. bfs로 저장.
//마지막 연산저장 + sb.insert로 앞에서 채우기할때 걸린시간 2696ms
//마지막 숫자 저장 +sb.append로 채우기 할때걸린시간
public class Main{
	public static int n,k;
	public static int[][] dist; //dist[0][0] : 0까지 걸린 시간, [0][1] : 0에도달하기 전 위치
	public static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dist = new int[100001][2];
		visited = new boolean[100001];
//		Arrays.fill(dist,Integer.MAX_VALUE);
		
		Queue<int[]> q= new ArrayDeque<>();
		visited[n] = true;
		q.add(new int[] {n, 0}); // curIdx, dist
		while(!q.isEmpty()){
			int[] cur = q.poll(); //idx, dist
			int curIdx = cur[0];
			int curDist = cur[1];
			if(curIdx  == k) break;
			for(int i = 0; i < 3; i++) {
				int nextIdx = calIdx(curIdx, i);
				if(nextIdx < 0 || nextIdx > 100000 || visited[nextIdx]) continue;
				visited[nextIdx] = true;
				dist[nextIdx][0] = curDist + 1;
				dist[nextIdx][1] = curIdx;
				q.add(new int[] {nextIdx, curDist+1});
			}
		}
		
		sb.append(dist[k][0]).append("\n");

		recurAdd(k, sb);
		
		System.out.println(sb.toString().trim()); //맨끝 공백 제거
		
	}
	
	public static int calIdx(int idx, int num) {
		if(num == 0)
			return idx +1;
		else if(num == 1)
			return idx -1;
		else if(num == 2)
			return idx *2;
		else
			return -1;
	}
	
	public static void recurAdd(int curIdx, StringBuilder sb) {
		if(curIdx == n) {
			sb.append(curIdx).append(" ");
			return;
		}
		
		recurAdd(dist[curIdx][1], sb);
		
		sb.append(curIdx).append(" ");
	}
}