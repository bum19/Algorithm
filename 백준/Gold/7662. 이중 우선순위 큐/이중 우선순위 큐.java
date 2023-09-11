import java.io.*;
import java.util.*;
public class Main {
	public static int  t, k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		TreeMap<Integer, Integer> tm = new TreeMap<>(); //key는 입력값, value는 입력 값 개수
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			k = Integer.parseInt(br.readLine().trim());
			tm.clear();
			
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				
				if(cmd == 'I') {
					if(tm.containsKey(num))tm.put(num, tm.get(num)+1); //이미 있으면 개수증가
					else				   tm.put(num, 1); //없으면 개수 1로 놓고 새로 넣기
				}
				
				else if(!tm.isEmpty()) {
					int key;
					if(num == 1) key = tm.lastKey();
					else		 key = tm.firstKey();
					
					if(tm.get(key) == 1) tm.remove(key); //다지우는 연산임.
					else 				 tm.put(key, tm.get(key)-1);
				}
				
			}
			
			if(tm.isEmpty()) sb.append("EMPTY\n");
			else sb.append(tm.lastKey()).append(" ").append(tm.firstKey()).append("\n");
		}
		
		System.out.println(sb);
	}
}
