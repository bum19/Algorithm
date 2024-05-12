/*
 * 상어 이동 모든 경우의수 dfs 탐색
 * 복구로직이 넘 ㅜ힘들었음
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[][] map = new int[4][4]; //각 칸에는 물고기번호를저장. -1이면 빈칸.
	public static int maxNum, curNum;
	public static Fish[] fishes = new Fish[17]; //상어0번, 1~16번 물고기
	
	public static int[] dy = {-1,-1,0,1,1,1,0,-1}; //반시계  상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	public static int[] dx = {0,-1,-1,-1,0,1,1,1}; //반시계  상, 좌상, 좌, 좌하, 하, 우하, 우, 우상
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine()); 
			for(int j = 0; j < 4; j++) {
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken())-1;
				
				fishes[idx] = new Fish(i,j,dir,true); 
				map[i][j] = idx;
			}
		}
		
		//초기상태 설정
		//상어가 0,0에 있는것 먹고시작.
		fishes[0] = new Fish(0,0,fishes[map[0][0]].dir,true);
		fishes[map[0][0]].isAlive = false;
		
		curNum += map[0][0];
		map[0][0] = 0;
		
		fishMove(1, 0);
		
		System.out.println(maxNum);
	}
	
	private static void fishMove(int idx, int depth) {
		if(idx == 17) {
			idx = 0;
		}
		
		if(!fishes[idx].isAlive) {
			fishMove(idx+1, depth);
			return;
		}
		
		int y = fishes[idx].y;
		int x = fishes[idx].x;
		int dir = fishes[idx].dir;
		//물고기이동로직 작성
		if(idx != 0) {
			//이동
			for(int i = 0; i < 8; i++) {
				int ny = y + dy[(dir + i) % 8];
				int nx = x + dx[(dir + i) % 8];
				if(ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || map[ny][nx] == 0) continue;
				
				//이동할수 있으면 이동하기.
				int sIdx = map[ny][nx];
				if(sIdx != -1) {
					//물고기 배열 변경

					int sy = fishes[sIdx].y;
					int sx = fishes[sIdx].x;
					
					fishes[idx].y = ny;
					fishes[idx].x = nx;
					fishes[idx].dir = (dir+i) % 8;
					
					fishes[sIdx].y = y;
					fishes[sIdx].x = x;
					
					//map 변경
					map[y][x] = sIdx;
					map[ny][nx] = idx;
					
					
					//다음 이동 호출
					fishMove(idx+1,depth);
					
					//물고기 배열 복구
					fishes[idx].y = y;
					fishes[idx].x = x;
					fishes[idx].dir = dir;
					
					fishes[sIdx].y = ny;
					fishes[sIdx].x = nx;
					
					//map 복구
					map[y][x] = idx;
					map[ny][nx] = sIdx;
				}
				else {
					//물고기 배열 변경
					fishes[idx].y = ny;
					fishes[idx].x = nx;
					fishes[idx].dir = (dir+i) % 8;

					//map 변경
					map[y][x] = sIdx;
					map[ny][nx] = idx;
					
					//다음 이동 호출
					fishMove(idx+1,depth);
					
					
					//물고기 배열 복구
					fishes[idx].y = y;
					fishes[idx].x = x;
					fishes[idx].dir = dir;
					
					//map 복구
					map[y][x] = idx;
					map[ny][nx] = sIdx;
				}
				break; 
			}
		}
		
		//상어로직 작성
		else if(idx == 0) {
			boolean isMove = false;
			for(int i = 1; i <= 3; i++) {
				int ny = y + dy[dir] * i;
				int nx = x + dx[dir] * i;
				
				if(ny < 0 || nx < 0 || ny >= 4 || nx >= 4 || map[ny][nx] == -1) continue;
				
				//이동할 수 있으면 이동
				isMove = true;

				int vIdx = map[ny][nx];
				//물고기 배열 변경

				int vy = fishes[vIdx].y;
				int vx = fishes[vIdx].x;
				int vDir = fishes[vIdx].dir;
				fishes[vIdx].isAlive = false;
				
				fishes[idx].y = vy;
				fishes[idx].x = vx;
				fishes[idx].dir = vDir;
				
				//map 변경
				map[y][x] = -1;
				map[ny][nx] = idx;
				
				//curNum 변경
				curNum += vIdx;
				
				//다음 이동 호출
				fishMove(idx+1,depth+1);
				
				//물고기 배열 복구
				fishes[idx].y = y;
				fishes[idx].x = x;
				fishes[idx].dir = dir;
				
				fishes[vIdx].isAlive = true;
				
				//map 복구
				map[y][x] = idx;
				map[ny][nx] = vIdx;
				
				//curNum 복구
				curNum -= vIdx;
			}
			if(!isMove) { // 움직인적 없으면 갱신
				if(maxNum < curNum) {
					maxNum = curNum;
				}
			}

		}
	}
	private static class Fish{
		int y,x,dir;
		boolean isAlive;
		public Fish(int y, int x, int dir,boolean isAlive) {
			this.y = y;
			this.x = x;
			this.dir = dir;
			this.isAlive= isAlive;
		}
	}
}
