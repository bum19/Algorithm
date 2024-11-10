/*
 * 1차시도 : 완탐백트래킹
 * 2차시도 : 답봄. 5차원 dp. 
 * 	dp[a][b][c][ppre][pre]를통한 메모이제이션을 가미한? 완탐
 */
import java.io.*;
import java.util.*;
public class Main {
	public static boolean isDone;
	public static boolean[][][][][] visited;
	public static int a, b, c;
	public static char[] arr;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine().trim();
		arr = new char[input.length()];
		for(int i = 0; i < arr.length; i++) {
			if(input.charAt(i) == 'A') a++;
			if(input.charAt(i) == 'B') b++;
			if(input.charAt(i) == 'C') c++;
		}
		
		isDone = false;
		visited = new boolean[a+1][b+1][c+1][3][3];
		perm(0, arr.length, 'A','A');
		if(isDone)
			System.out.println(sb);
		else {
			System.out.println(-1);
		}
	}
	
	private static void perm(int depth, int length, char pre, char prePre) {
		
		//정답 찾았으면 탐색정지
		if(isDone) return;
		
		//이미 봤던 건 방문하지 않는다.
		if(visited[a][b][c][prePre-'A'][pre-'A']) return;
		
		//현재 경로 방문처리
		visited[a][b][c][prePre-'A'][pre-'A'] = true;
		
		if(depth == length) {
			sb.append(String.valueOf(arr));
			isDone = true;
			return;
		}
		
		if(a > 0) {
			a--;
			arr[depth] = 'A';
			perm(depth+1, length, 'A', pre);
			a++;
		}
		if(b > 0 && pre != 'B') {
			b--;
			arr[depth] = 'B';
			perm(depth+1, length, 'B', pre);
			b++;
		}
		if(c > 0 && pre != 'C' && prePre != 'C') {
			c--;
			arr[depth] = 'C';
			perm(depth+1, length, 'C', pre);
			c++;
		}
	}
}
