/*
 * 1) 진실을 아는 사람이 있을때 제대로 얘기하기. 이때 isTrueParty처리.
 * 2) 제대로 얘기 했을 때 들은 사람들 진실을 아는사람 맵에 추가.
 * 3) 더이상 사람 추가 안될때까지, 1~2 반복.
 * 4)  1-2는최대 50번반복.  파티 50이므로 반복마다 50개의 파티 확인. 최대 2500반복
 * isTrueParty 안된사람 세기.
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static int n, m;
	public static List<Integer>[] party;
	public static boolean[] isTrueParty;
	public static Map<Integer, Boolean> trueMans;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		party = new List[m];
		for(int i = 0; i < m; i++) {
			party[i] = new ArrayList<>();
		}
		
		//trueMan init
		trueMans = new HashMap<>();
		st = new StringTokenizer(br.readLine());
		st.nextToken();
		while(st.hasMoreTokens()) {
			trueMans.put(Integer.parseInt(st.nextToken()), true);
		}
		
		//party init
		for(int i = 0; i< m; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				party[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		boolean trueManUpdate = true; //루프별 진실말하는 사람 업데이트 되었는지 여부. 업데이트 안됐을 때 루프 탈출
		isTrueParty = new boolean[m];
		int falsePartyCount = m;
		while(trueManUpdate) {
			trueManUpdate = false;
			for(int i= 0; i< m; i++) { //파티돌면서 탐색
				if(isTrueParty[i]) continue; // 현재파티가 이미 진실말한 파티면, 넘어가기
				for(int man : party[i]) { //파티내에 진실말한사람 있나 확인
					if(trueMans.containsKey(man)) { //현재파티에 참가한 사람 모두 진실아는 사람 추가.
						for(int trueMan : party[i]) {
							if(trueMans.containsKey(trueMan)) continue;
							else {
								trueMans.put(trueMan, true);
								trueManUpdate = true;
							}
						}
						isTrueParty[i] = true;
						falsePartyCount--;
						break;
					}
				}
			}
		}
		
		System.out.println(falsePartyCount);
		
	}
}
