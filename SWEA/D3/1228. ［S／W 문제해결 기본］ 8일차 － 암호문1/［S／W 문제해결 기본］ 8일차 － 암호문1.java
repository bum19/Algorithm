import java.io.*;
import java.util.*;

public class Solution {
	public static int n, m, idx, k;
	public static List<Integer> cyphers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int x, y;

		for (int test_case = 1; test_case <= 10; test_case++) {
			// 입력
			n = Integer.parseInt(br.readLine().trim()); // 첫줄에 암호문 길이 입력
			cyphers = new ArrayList<Integer>();

			st = new StringTokenizer(br.readLine()); // 둘째줄에 암호문들 입력
			for (int i = 0; i < n; i++) {
				cyphers.add(Integer.parseInt(st.nextToken()));
			}

			m = Integer.parseInt(br.readLine()); // 셋째줄에 명령어 개수 입력

			st = new StringTokenizer(br.readLine()); // 명령어들 입력.
			for (int i = 0; i < m; i++) {

				st.nextToken(); // l
				idx = Integer.parseInt(st.nextToken()); // x
				k = Integer.parseInt(st.nextToken()); // y

				for (int j = idx; j < idx + k; j++) { // s
					// 이거처리해줘야..
					cyphers.add(j, Integer.parseInt(st.nextToken()));
						
				}
			}

			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < 10; i++)
				sb.append(cyphers.get(i)).append(" ");
			sb.append("\n");
		}

		bw.append(sb);
		bw.flush();
		bw.close();
		br.close();

	}
}
