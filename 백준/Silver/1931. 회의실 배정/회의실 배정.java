import java.io.*;
import java.util.*
;
public class Main {
	public static int n,count;
	public static PriorityQueue<int[]> pq;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		pq = new PriorityQueue<int[]>((a1,a2)->{
			if(a1[1] == a2[1])
				return Integer.compare(a1[0],a2[0]);
			else
				return Integer.compare(a1[1], a2[1]);
		});
		n = Integer.parseInt(st.nextToken());
		
		count = 0;
		
		for(int i = 0 ; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			pq.add(new int[] {s,e});
		}
		
		int curEndTime = 0;
		for(int i = 0; i < n ; i++) {
			int[] lecture = pq.poll();
			//하나씩뽑으면서 시작시간이 현재 끝시간보다 크면 넣기
			if(curEndTime <= lecture[0]) {
//				System.out.println(curEndTime+" : "+lecture[0]+", "+lecture[1]);
				count++;
				curEndTime = lecture[1];
			}
		}
		System.out.println(count);
	}
}
