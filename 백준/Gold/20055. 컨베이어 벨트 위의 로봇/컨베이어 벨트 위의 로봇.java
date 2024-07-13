import java.io.*;
import java.util.*;
/*
 * 시키는대로 구현. 시간복잡도 O(nk*1000)
 * 벨트움직일때 내구도도 움직임.
 * 시작위치와 내리는 위치만 움직이게 수정할..필요까진 없으륻
 * 일단 로봇이 4000개 생기는거 때문에 continue 시에  시간초과나는듯. 뭔가 그냥 loop횟수를세나보다.
 * 로봇 순서대로 세는거 그냥 n에가까운거부터 보기.
 */

public class Main {
	
	public static Belt[] belts;
	public static int n,k, zeroCnt, robotIdx;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		belts = new Belt[n*2];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n*2 ;i++) {
			belts[i] = new Belt(false, Integer.parseInt(st.nextToken()));
		}
		
		zeroCnt = 0;
		int cnt = 0;

		while(zeroCnt < k) {
			cnt++;
			moveBelt();
			moveRobot();
			putRobot();
			if(zeroCnt == k) break;
		}
		System.out.println(cnt);
	}
	
	private static void moveBelt() {
		Belt tmp = belts[n*2 -1];
		for(int i = n*2 - 1; i >= 0; i--) {
			int idx = i % (2*n);
			if(i == 0)	belts[idx] = tmp;
			else		belts[idx] = belts[idx-1];
			
			if(belts[idx].robot && idx == n-1) { //현재 벨트 위에 로봇이 있고 내리는 칸이면 	
					belts[idx].robot = false;
			}
		}
	}
	
	private static void moveRobot() {
		for(int i = n-2; i >= 0; i--) {

			int cur = i;
			int next = cur + 1;
			
			//현재 칸 로봇 없으면 넘어가
			if(!belts[cur].robot) continue;
			
			//다음 칸 내구도 체크
			if(belts[next].durability <= 0) continue;
			
			//다음 칸 로봇 유무 체크
			if(belts[next].robot) continue;
			
			//move robot
			if(next != n-1) {
				belts[next].robot = belts[cur].robot;
			}
			
			belts[cur].robot = false;
			
			//내구도 갱신
			if(belts[next].durability > 0) {
				belts[next].durability--;
				if(belts[next].durability == 0) {
					zeroCnt++;
				}
			}
			
		}
	}
	
	private static void putRobot() {
		if(belts[0].durability > 0) {
			belts[0].robot = true;
		}
		
		//내구도 갱신
		if(belts[0].durability > 0 ) {
			belts[0].durability--;
			if(belts[0].durability == 0) {
				zeroCnt++;
			}
		}

	}
	
	public static class Belt{
		boolean robot;
		int durability;
		
		public Belt (boolean robot, int durability) {
			this.robot = robot;
			this.durability = durability;
		}
		
		public String toString() {
			return durability+"/"+robot+" ";
		}
	}
}
