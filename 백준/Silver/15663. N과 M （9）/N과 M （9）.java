import java.io.*;
import java.util.*;
public class Main {
	public static int n, m;
	public static int[] input;
	public static int[] output;
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		input = new int[n];
		visited = new boolean[n];
		output = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		perm(0);
		System.out.println(sb);
		
	}
	public static void perm(int depth) {
		if(depth == m) {
			for(int i = 0; i < m; i++)
				sb.append(output[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		int pre = -1;
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				if(pre == input[i]) continue; // 이전에 같은숫자로 탐색을 진행했으면 또 고르지않는다.
				visited[i] = true;
				output[depth] = input[i];
				perm(depth+1);
				visited[i] = false;
				pre = input[i];
			}
		}
		
	}
}
