import java.util.Scanner;

public class Main {
	public static int n,m;
	public static int[] data;
	public static boolean[] visited;
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		data = new int[m];
		visited = new boolean[n];
		permutation(0);
		System.out.println(sb);
	}
	
	public static void permutation(int depth) {
		if(depth == m) {
			for(int i = 0 ; i < m; i++)
				sb.append(data[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				data[depth] = i+1;
				permutation(depth+1);
				visited[i] = false;
			}
		}
	}
}
