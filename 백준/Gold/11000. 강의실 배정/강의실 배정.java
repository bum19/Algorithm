import java.io.*;
import java.util.*;

public class Main {
	public static int n;
	public static PriorityQueue<int[]> lectures; //시작순 정렬
	public static PriorityQueue<Integer> rooms; //끝나는 시간 정렬
	public static void main(String[] args ) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//시작 시간 순으로
		lectures = new PriorityQueue<int[]>(new Comparator<int[]>(){
			public int compare(int[] a1, int[] a2) {
				return Integer.compare(a1[0], a2[0]);
			}
		});
		
		//방은 끝나는시간 넣기. 빠른 순
		rooms = new PriorityQueue<Integer>();
		
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		
		
		for(int i = 0 ; i < n ; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			lectures.add(new int[] {s,e});
		}
		
		while(!lectures.isEmpty()) {
			int[] lecture = lectures.poll();
			//방이 있는경우
			if(!rooms.isEmpty()) {
				int endTime = rooms.peek();
				//방에들어갈수있으면 
				if(endTime <= lecture[0]) {
					rooms.poll();
					rooms.add(lecture[1]);
				}
				//없으면 추가
				else {
					rooms.add(lecture[1]);
				}
			}
			//방이 없는경우
			else {
				rooms.add(lecture[1]);
			}
		}
		
		System.out.println(rooms.size());
		
	}

}
