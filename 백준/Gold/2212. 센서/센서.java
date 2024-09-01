//1. 정렬을한다.
//2. k= 1이면 그냥 가장 먼 두 센서간의 거리
//	k= 2면, 가장 긴 두점 사이의 거리를 제거한 값.
//	k = 3이면, 그 다음 거리를 제거한 값.
//해당 구현을 위해 필요한것은, 좌표를 오름차순 정렬후, 좌표간 거리를 pq로 저장하는것이 필요하다.
import java.io.*;
import java.util.*;

public class Main{
	public static int n,k;
	public static List<Integer> sensors;
	public static PriorityQueue<Integer> edges;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		k = Integer.parseInt(br.readLine().trim());
		if(n <= k) {
			System.out.println(0);
			return;
		}
		
		sensors = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i< n ; i++) {
			int num = Integer.parseInt(st.nextToken());
			sensors.add(num);
		}
		
		if(sensors.size() <= k) {
			System.out.println(0);
			return;
		}
		
		Collections.sort(sensors);
		
		edges = new PriorityQueue<>(Collections.reverseOrder());
		int pre = 0;
		for(int i = 1; i < sensors.size(); i++) {
			edges.add(sensors.get(i) - sensors.get(i-1));
		}
		
		//k == 1일때 기본값
		int answer = sensors.get(sensors.size()-1) - sensors.get(0);
		
		for(int i = 1; i < k; i++) {
			answer -= edges.poll();
		}
		
		System.out.println(answer);
	}
}