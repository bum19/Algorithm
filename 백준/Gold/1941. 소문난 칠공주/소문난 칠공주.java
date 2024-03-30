import java.io.*;
import java.util.*;
/*
 * 모든 S 위치 찾고, S위치별로 dfs
 * 한번 뎁스탈때마다, 현재까지포함시킨 집합에 인접한 애들 모두 탐색.
 * 현재 집합 방문한적있는지 비트마스킹 visited Map으로 표시. 즉, { 1,2, 3}  탐색과 {1,3, 2} 탐색 중복을 막는다.
 * 현재 집합에 Y가 4가되는 경우는 탐색 x;
 * S visited처리
 * 모든 S위치에대해 반복.
 * 최대경우의수 4 * 6 * 8 * 10 * 12 * 14 * 16 *25(s가 25개일경우)
 */
public class Main {
	
	//비트마스킹시 입력할 위치를 숫자로 기록해놓은 배열
	public static final int[][] locNum = { {0,1,2,3,4}, {5,6,7,8,9}, {10,11,12,13,14}, {15,16,17,18,19}, {20,21,22,23,24} };
	public static int answer; //답
	public static char[][] map;	//입력받을 map
	public static List<int[]> Ss; //S위치 집합.
	
	//dfs 탐색시 쓰이는 방문자료구조와 방향자료구조
	public static Map<Integer, Boolean> setVisited; //방문한 집합
	public static boolean[][] isVisited; //탐색시 방문한 칸
	public static int[] dy = {-1,1,0,0};
	public static int[] dx = {0,0,-1,1};
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[5][5];
		Ss = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			String input = br.readLine().trim();
			for(int j = 0; j < 5; j++) {
				map[i][j] = input.charAt(j);
				if(map[i][j] == 'S') Ss.add(new int[] {i, j});
			}
		}
		
		isVisited = new boolean[5][5];
		setVisited = new HashMap<>();
		for(int[] S : Ss) {
			//탐색시 들고다닐 집합 선언
			List<int[]> curSet = new ArrayList<>();
			
			//칸, 집합 방문처리
			isVisited[S[0]][S[1]] = true;
			setVisited.put(1 << locNum[S[0]][S[1]], true);
			curSet.add(S);
			//탐색시작.
			dfs(curSet, 1 << locNum[S[0]][S[1]], 1, 0);
			//현재 S위치를 포함하는 칠공주는 모두 탐색했으므로 현 S에 대한 isVisited는 해제안한다.
		}
		
		System.out.println(answer);
	}
	
	public static void dfs(List<int[]> curSet,int curBitMask, int memberCount, int YCount) {
		if(memberCount == 7) {
			answer++;
			return;
		}
		
		int length = curSet.size();
		
//		for(int[] cur : curSet) {
		for(int i = 0; i < length; i++) {
			int[] cur = curSet.get(i);
			//현재 집합을가지고 인접한 친구들 모두 탐색
			for(int dir = 0; dir < 4; dir++) {
				int ny = cur[0] + dy[dir];
				int nx = cur[1] + dx[dir];
				//해당 칸 범위 확인, 방문한적있는지 확인
				if(ny < 0 || nx < 0 || ny >=5 || nx >=5 || isVisited[ny][nx]) continue;
				
				//해당 칸을 집합에포함시켰을때에 대해 탐색한적 있는지 확인
				if(setVisited.get(curBitMask | (1 << locNum[ny][nx])) != null) continue;
				
				//Y인지 확인
				int isY = 0;
				if(map[ny][nx] == 'Y') isY = 1;
				
				//Y가 4칸 이상인지 확인
				if(isY + YCount >= 4) continue;
				
				isVisited[ny][nx] = true;
				setVisited.put(curBitMask | (1 << locNum[ny][nx]), true);
				
				curSet.add(new int[] {ny,nx});
				dfs(curSet, curBitMask | (1 << locNum[ny][nx]), memberCount +1, YCount + isY);
				
				isVisited[ny][nx] = false;
				curSet.remove(curSet.size()-1);
			}
			
		}
	}
	
}
