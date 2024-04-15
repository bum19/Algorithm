import java.io.*;
import java.util.*;
public class Main {
	public static int n,m;
	public static Map<Integer, Integer> map;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		map = new HashMap<Integer, Integer>();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < n ;i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.get(num) != null) {
				map.put(num, map.get(num) + 1);
			}
			else {
				map.put(num, 1);
			}
		}
//		System.out.println("map size" + map.size());
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(map.get(num) == null) {
				sb.append(0).append(" ");
			}
			else{
				sb.append(map.get(num)).append(" ");
			}
		}
		
		System.out.println(sb);
	}
}
