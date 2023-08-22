//
import java.io.*;
import java.util.*;

public class Main {
	// 최대 15개숫자중 3~15개 뽑는 조합문제. 순서가 정해져있으므로 뽑기만 하면 된다.
	public static int l, c;
	public static char[] input, output;
	public static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		input = new char[c];
		output = new char[l];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			input[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(input);
		// 모음들어가있는지체 크
		// 사전순 정렬은 알아서

		dfs(0, 0, 0, 0);
		System.out.println(sb);
	}

	private static void dfs(int start, int cnt, int vowelCnt, int consonantCnt) {
		if (cnt == l) {
			if (vowelCnt >= 1 && consonantCnt >= 2) {
				for (int i = 0; i < l; i++) {
					sb.append(output[i]);
				}
				sb.append("\n");
			}
			return;
		}
		
		for(int i = start; i < c; i++) {
			output[cnt] = input[i]; 
			if(input[i] == 'a' || input[i] == 'e' || input[i] == 'i' || input[i] == 'o'|| input[i] == 'u') {
				dfs(i+1, cnt+1, vowelCnt+1, consonantCnt);
			}
			else {
				dfs(i+1, cnt+1, vowelCnt, consonantCnt+1);
			}
		}
		
	}
}
