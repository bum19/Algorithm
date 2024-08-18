/*
 * bfs로 탐색.
 * 돈 더 많이 들고 방에 왔을 경우 재탐색.
 * 시간이 엄청 더 늘진 않을것으로 고려됨
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static Room[] maze;
	public static int[] curMon; //방에들어왔을때 소지한 금액
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		while(true) {
			n = Integer.parseInt(br.readLine());
			if(n == 0) {
				System.out.println(sb);
				return;
			}
			maze = new Room[n+1]; //0번인덱스는 버릴것.
			for(int i = 1; i<= n; i++) {
				st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int cost = Integer.parseInt(st.nextToken());
				List<Integer> doors = new ArrayList<>();
				while(true) {
					int tmp = Integer.parseInt(st.nextToken());
					if(tmp == 0) break;
					doors.add(tmp);
				}
				maze[i] = new Room(i,cost,type,doors);
			}//input done
			
			if(check()) {;
				sb.append("Yes\n");
			}
			else {
				sb.append("No\n");
			}
		}
	}
	
	private static boolean check() {
		curMon = new int[n+1]; //visited역할을 수행한다.
		Arrays.fill(curMon, -1);
		curMon[1] = 0;
		
		if(maze[1].type == 'T' && maze[1].cost > 0) {
			return false;
		}
		
		if(maze[1].type == 'L') {
			curMon[1] = maze[1].cost;
		}
		
		Queue<int[]> q = new ArrayDeque<>(); //현재방과 소지금
		q.add(new int[] {1,curMon[1]});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int curIdx = cur[0];
			int curCost = cur[1];
			
			for(int door : maze[curIdx].doors) {
				if(maze[door].type == 'E') { //다음 방이 빈방인 경우
					if(door == n) return true;
					if(curCost > curMon[door]) {
						curMon[door] = curCost;
						q.add(new int[] {door,curCost});
					}
				}
				else if(maze[door].type == 'L') { //다음 방이 레프리콘인 경우
					if(door == n) return true;
					if(curCost > curMon[door]) {
						curMon[door] = curCost;
						q.add(new int[] {door, Math.max(curCost, maze[door].cost)});
					}
				}
				else if(maze[door].type =='T') { //다음 방이 트롤인 경우
					//갈수있는지 체크
					if(curCost > curMon[door] && curCost >= maze[door].cost) {
						if(door == n) return true;
						curMon[door] = curCost;
						q.add(new int[] {door,curCost - maze[door].cost});
					}
				}
			}
			
		}
		
		
		return false;
	}
	
	public static class Room{
		int num, cost;
		char type;
		List<Integer> doors;
		public Room(int num, int cost, char type, List doors) {
			this.num = num;
			this.cost = cost;
			this.type = type;
			this.doors = doors;
		}
	}
}
