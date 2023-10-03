import java.io.*;
import java.util.*;
public class Main {
	
	public static int t, n, sy, sx, ey, ex; //테스트케이스 개수, 편의점개수, 시작위치, 도착위치.
	public static List<int[]> convs; //편의점 위치.
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		for(int test_case = 1;  test_case <= t; test_case++) {
			convs = new ArrayList<>(); //편의점 위치 담을 곳 초기화.
			n = Integer.parseInt(br.readLine().trim()); //편의점 개수 입력
			st = new StringTokenizer(br.readLine()); //시작위치입력
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			for(int i = 0 ; i <  n; i++) {
				st = new StringTokenizer(br.readLine());
				convs.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			st = new StringTokenizer(br.readLine()); //도착위치 입력
			ex = Integer.parseInt(st.nextToken());
			ey = Integer.parseInt(st.nextToken());
			//입력끝
			
			String str = happyOrSad();
			sb.append(str).append("\n");
		}
		System.out.println(sb);
	}
	
	
	private static String happyOrSad() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {sx,sy});
		while(!q.isEmpty()) {
			int[] cur = q.poll();
//			System.out.println("현재위치 : "+cur[0]+", "+cur[1]);
			//현재위치에서 도착지까지 1000맨해튼 안쪽이면 happy리턴
//			System.out.println("현재위치에서 목적지까지거리 : "+(Math.abs(cur[0]-ex) + Math.abs(cur[1]-ey)));
			if(Math.abs(cur[0]-ex) + Math.abs(cur[1]-ey) <= 1000) return "happy";
			
			//현재위치에서 편의점까지 1000맨해튼 안쪽이면 큐에 집어넣고, 해당편의점은 제거
			int size = convs.size();
			for(int i = size-1; i>=0; i--) {
				int conX = convs.get(i)[0];
				int conY = convs.get(i)[1];
//				System.out.println("현재위치에서 편의점까지 거리 : "+(Math.abs(conX-cur[0]) + Math.abs(conY-cur[1])));
				if(Math.abs(conX-cur[0]) + Math.abs(conY-cur[1]) <= 1000) {
					q.add(new int[] {conX, conY});
					convs.remove(i);
				}
			}
		}
		return "sad";
	}
}
