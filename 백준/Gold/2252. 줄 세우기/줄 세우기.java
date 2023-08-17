import java.io.*;
import java.util.*;
//ssafy전은수교수님코드 위상정렬
public class Main {

	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		String[] oneLine = br.readLine().split(" ");
		
		int N = Integer.parseInt(oneLine[0]);	//학생수
		int M = Integer.parseInt(oneLine[1]);	//키를 비교한 횟수
		
		//인접리스트
		ArrayList<Integer>[] g = new ArrayList[N+1];
		
		for (int i = 1; i <= N; i++) {
			g[i] = new ArrayList<Integer>();
		}
		
		//노드별 진입차수 저장 배열
		int[] indegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			String[] M_input = br.readLine().split(" ");
			int front = Integer.parseInt(M_input[0]);
			int back = Integer.parseInt(M_input[1]);
			g[front].add(back); //g[back].add(front)는 무방일경우만 하자구
			indegree[back]++;
		}
		
		//처리. 더하거나 빼거나 그러기
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; ++i) {
			if(indegree[i] == 0) { //진입차수가 0인 노드(시작점)를 큐에 삽입.
				q.offer(i);
			}
		}
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			sb.append(cur).append(" ");
			
			for(int i = 0; i< g[cur].size(); i++) {
				int next = g[cur].get(i);
				
				if(--indegree[next] == 0) {
					q.offer(next);
				}
			}

		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();		
	}

	
}
