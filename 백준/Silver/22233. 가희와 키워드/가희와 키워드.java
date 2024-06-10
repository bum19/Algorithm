import java.io.*;
import java.util.*;
public class Main {
	public static int n,m, cnt;
	public static Map<String, Boolean> map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new HashMap<String, Boolean>();
		
		for(int i = 0; i < n; i++) {
			String tmp = br.readLine().trim();
			map.put(tmp, false);
		}
		
		cnt = n;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), ",");
			
			while(st.hasMoreTokens()) {
				String tmp = st.nextToken();
				if(map.get(tmp) != null && !map.get(tmp)) {
					map.put(tmp, true);
					cnt--;
				}
			}
			sb.append(cnt).append('\n');
		}
		
		System.out.println(sb);
	}
}
