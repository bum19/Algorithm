import java.util.*;
import java.io.*;


public class Solution{
	static int n, m, idx, k;
	static List<Integer> cipherList = new ArrayList<Integer>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n ; i++){
				cipherList.add(Integer.parseInt(st.nextToken()));
			}
			m = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				st.nextToken();
				idx = Integer.parseInt(st.nextToken());
				k = Integer.parseInt(st.nextToken());
				for(int j = idx; j < idx + k; j++) {
					cipherList.add(j,Integer.parseInt(st.nextToken()));
				}
				
			}
		
			sb.append("#").append(test_case).append(" ");
			for(int i = 0; i < 10; i++)
				sb.append(cipherList.get(i)).append(" ");
			sb.append("\n");
			
			cipherList.clear();
			
		}
		
		System.out.println(sb);
	}

}
