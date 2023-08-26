//
import java.io.*;
import java.util.*;
public class Solution {
	public static int t, k, answer;
	public static List<Integer>[] magnets;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		t = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= t; test_case++) {
			k = Integer.parseInt(br.readLine().trim()); //회전 횟수 입력
			
			answer = 0; //답 초기화.
			magnets = new List[5]; //자석 리스트 초기화. 자석인덱스 1~4만 쓸거임.
			for(int i = 1; i <= 4; i++) { //자석 리스트 생성
				magnets[i] = new LinkedList<Integer>();
			}
			
			
			for(int i = 1; i <= 4; i++) { //자석 정보 입력.
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < 8; j++) {
					magnets[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			for(int i = 0; i < k; i++) { //회전 정보 입력.
				st = new StringTokenizer(br.readLine());
				int magnetIdx = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());
				rotate(magnetIdx, rotateDir); //입력과 동시에 자석 회전시키기.
			}
			
			for(int i = 1; i <= 4; i++) {
				if(magnets[i].get(0) == 1) answer |= (1 << i-1);
			}
			
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void rotate(int idx, int dir) {
		int[] dirs = new int[5];
		dirs[idx]= dir;
		
		//회전정보를 바탕으로 회전 정보세팅. 4개 전부세팅될때까지.
		//현재 자석 왼쪽애들 세팅.
		for(int i = idx; i > 1; i--) {
			if(magnets[i-1].get(2) != magnets[i].get(6)) { //현재 자석 6번 자성정보와 왼쪽 자석 2번 자성정보가 다르면
				dirs[i-1] = dirs[i] * -1 ; //반대로 회전.
			}
		}
		for(int i = idx; i < 4; i++) {
			if(magnets[i].get(2) != magnets[i+1].get(6)) { //현재 자석 6번 자성정보와 왼쪽 자석 2번 자성정보가 다르면
				dirs[i+1] = dirs[i] * -1 ; //반대로 회전.
			}
		}
		
		//회전
		for(int i = 1; i <= 4; i++) {
			if(dirs[i] == 1) { //1이면 시계방향 회전.
				//맨뒤꺼를 뽑아서 맨 앞에 넣는다.
				int tmp = magnets[i].remove(7);
				magnets[i].add(0, tmp);
			}
			else if(dirs[i] == -1) { // -1이면 반시계방향 회전.
				//맨앞에꺼를 뽑아서 맨뒤에 넣는다.
				int tmp = magnets[i].remove(0);
				magnets[i].add(tmp);
			}
		}
	}

}
