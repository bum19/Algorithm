import java.io.*;
import java.util.*;
public class Solution {
	static int t;
	static Queue<Integer> q = new ArrayDeque<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb= new StringBuilder();
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			//입력
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			//0되는거 생길때까지 돌리기
			int i = 0;
			while(true) {
				int num = q.poll() - (i+1);
				if(num < 0 ) num = 0;
				q.offer(num);
				
				if(num == 0) break;
				
				i = ++i % 5;
			}
			
			sb.append("#").append(test_case).append(" ");
			while(!q.isEmpty()) sb.append(q.poll()).append(" ");
			sb.append("\n");
			
		}
		
		bw.append(sb);
		bw.flush();
		
		bw.close();
		br.close();
	}
}
