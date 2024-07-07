/* 모든 점에대해 4방체크.
 * 좌하,하, 우하, 우 체크. 5개 되면, 6목체크
 * 시간복잡도 19*19*5
 * > 육목체크 주의
 * >>가장 왼쪽에 있는걸 해야함 주의 <<<. 이를 위해, 열 우선 탐색
 * >> dir도 좌하,하,우하,우가 아니라, 하,우하,우,우상으로 체크해야함. (좌하의 경우, 왼쪽이 아닌ㄴ 오른쪽 위 출ㄹ력) <<
 * 출력조건을 잘생각하자.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int[][] map;
	public static int[] dy = {1,1,0,-1}; //하, 우하, 우, 우상
	public static int[] dx = {0,1,1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		map = new int[19][19];
		
		for(int i = 0; i < 19 ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//모든 점에 대해 탐색. 열 우선 탐색.
		for(int i = 0; i < 19; i++) {
			for(int j = 0; j < 19; j++) {
				if(map[j][i] == 0) continue;
				for(int dir = 0; dir < 4; dir++) {
					if(check5(map[j][i], 1,j,i,dir) && check6(map[j][i],j,i,dir)) {
						System.out.println(map[j][i]);
						System.out.println((j+1) + " "+ (i+1));
						return;
					}
				}
			}
		}
		
		System.out.println(0);
	}
	
	public static boolean check5(int val, int depth, int y, int x, int dir) {
		int ny = y + dy[dir];
		int nx = x + dx[dir];
		
		if(depth == 5) {
			//5개 연속하고, 6번째가 다른 돌이면 true 처리.
			if(ny < 0 || nx <0 || ny >= 19 || nx >= 19 || map[ny][nx] != val)
				return true;
			else
				return false;
		}
		
		//5개가 안됐는데, 다른돌이오면 false 처리.
		if(ny < 0 || nx <0 || ny >= 19 || nx >= 19 || map[ny][nx] != val)
			return false;
		
		return check5(val, depth + 1, ny, nx, dir);
	}
	
	public static boolean check6(int val,int y,int x,int dir) {
		int ny = y - dy[dir];
		int nx = x - dx[dir];
		
		if(ny < 0 || nx <0 || ny >= 19 || nx >= 19 || map[ny][nx] != val)
			return true;
		else
			return false;
	}
}
