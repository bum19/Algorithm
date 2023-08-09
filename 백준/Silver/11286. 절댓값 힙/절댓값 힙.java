//메모리: 
//실행시간 :
import java.util.*;
import java.io.*;
public class Main {
	static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		PriorityQueue<Integer> pq;
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		//우선순위큐의 우선순위 기준 구현. 절댓값이 작은수. 절댓값이 같은경우 둘중 값이 작은것
		pq = new PriorityQueue<Integer>((a,b) -> {
			
			if(Math.abs(a) == Math.abs(b))
				return a - b;
			
			return Math.abs(a) - Math.abs(b);
		});
		
		for(int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(br.readLine());
			
			//0입력받은경우
			if(tmp == 0) {
				if(pq.isEmpty()) sb.append(0).append("\n");
				else			 sb.append(pq.poll()).append("\n");
			}
			//그외
			else {
				pq.add(tmp);
			}
		}
		
		System.out.println(sb);
	}

}
