//
import java.io.*;
import java.util.*;
//문제점1: 상어가 이동할때 거기에있는 상어를 없애버림.
//해결방법: 상어 이동시키고, 그다음에 갱신.
//문제점 : 그럼 기존에있던 값을 어떻게하지?
//해결방법: 기존 위치만 제거하고, 새로 추가하는일을 나중에하자~ 낚시왕이 잡은애는 사이즈를 0으로 만들어버려서 체크하자.
//새로추가할때 인덱스 조심하자
//이동할때 한번 이동할때마다 while문 돌도록 잘 설정하자. 
public class Main {
	public static int r,c,m; //ocean크기 행,열, 상어개수
	public static int answer; //잡은 상어크기의 총 합.
	public static List<Shark> sharks; //상어 객체 저장. 필요없을수도있는데 상어하나씩 이동시킬때 ocean전부 탐색안하려고  향상을 위해 넣어놓음.
	public static int[][][] ocean; //값으로 상어에 대한 속력,방향,크기를 가짐.
	public static int[] dy = {0, -1, 1, 0, 0}; //입력에 맞게 설정. -상하우좌
	public static int[] dx = {0, 0, 0, 1, -1}; //입력에 맞게 설정. -상하우좌
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		ocean = new int[r+1][c+1][3]; //[r][c][0~2]: r,c칸에 상어의 속도,방향,크기
		sharks = new ArrayList<>();
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int tmpR = Integer.parseInt(st.nextToken());
			int tmpC = Integer.parseInt(st.nextToken());
			int tmpS = Integer.parseInt(st.nextToken());
			int tmpD = Integer.parseInt(st.nextToken());
			int tmpZ = Integer.parseInt(st.nextToken());
			
			ocean[tmpR][tmpC][0] = tmpS;
			ocean[tmpR][tmpC][1] = tmpD;
			ocean[tmpR][tmpC][2] = tmpZ;
			
			sharks.add(new Shark(tmpR,tmpC,tmpS,tmpD,tmpZ));
		}
		//입력 끝

		for(int i = 1; i <= c; i++) {
			//출력
//			System.out.println(i+"열에 낚시왕 납시오~!");
//			for(int a= 1; a <= r; a++) {
//				for(int b = 1; b <= c; b++) {
//					System.out.print(ocean[a][b][2] +" ");
//				}
//				System.out.println();
//			}
			//출력끝
			getShark(i);
			moveShark();
			updateSharkList();
		}
		
		//출력
		System.out.println(answer);
	}
	
	public static void getShark(int col) { //현재 낚시왕의 열 위치를 매개변수로 받는다.
		for(int i = 1; i <= r; i++) {
			if(ocean[i][col][2] != 0) { //해당칸에 상어의 크기가 존재하면 상어가 있다는 것.
				answer += ocean[i][col][2];
//				System.out.println("잡았다요놈 : ("+i+", "+col+")"+"size : "+ocean[i][col][2]);
				ocean[i][col][0] = 0;	//상어있던 위치 비우기
				ocean[i][col][1] = 0; //상어있던 위치 비우기
				ocean[i][col][2] = 0; //상어있던 위치 비우기
				break;	//탈출
			}
		}
	}
	
	public static void moveShark(){//상어이동.
		
		for(Shark shark : sharks) {
			int tmpR = shark.r;
			int tmpC = shark.c;
			int speed = shark.s;
			int dir  = shark.d;
			int size = shark.z;
			if(ocean[tmpR][tmpC][2] == 0) {//낚시왕이 잡은 상어라면 그냥 진행
				shark.z = 0; //추후 삭제할때 같이 삭제할수 있도록 사이즈 0으로 만듦.
				continue; //잡힌 상어는 이동안하므로
			}
			
//			System.out.println("상어 이동할게~ 위치 제거 shark z :"+shark.z);
			ocean[tmpR][tmpC][0] = 0; //이동전 상어 위치 초기화
			ocean[tmpR][tmpC][1] = 0; //이동전 상어 위치 초기화
			ocean[tmpR][tmpC][2] = 0; //이동전 상어 위치 초기화
			
			tmpR = tmpR + dy[dir]*speed;
			tmpC = tmpC + dx[dir]*speed;
//			System.out.println("상어 이동시작. ("+tmpR+", "+tmpC+"), dir: "+dir);
			while(tmpR <= 0 || tmpC <= 0 || tmpR > r || tmpC > c) {
				if(dir == 1) 	  dir = 2;
				else if(dir == 2) dir = 1;
				else if(dir == 3) dir = 4;
				else if(dir == 4) dir = 3;
				
				if(tmpR > r) {
					tmpR = r - (tmpR - r); 
				}
				else if(tmpC > c) {
					tmpC = c - (tmpC - c);
				}
				else if(tmpR <= 0) {
					tmpR = -1 * tmpR + 2; //0인덱스가없어서 1을더하는게아니라 2를 더해줘야함.
				}
				else if(tmpC <= 0) {
					tmpC = -1 * tmpC + 2; //0인덱스가없어서 1을더하는게아니라 2를 더해줘야함.
				}
//				System.out.println("while도는중.. ("+tmpR+", "+tmpC+"), dir"+dir);
			}
			
			shark.r = tmpR;
			shark.c = tmpC;
			shark.d = dir;
//			System.out.println("이동끝 상어 : ("+tmpR+", "+tmpC+"), dir :"+dir);
//			if(shark.z > ocean[tmpR][tmpC][2]) { //이동한 위치가 비었거나 더 작은 상어가 있다면 갱신.
//				ocean[tmpR][tmpC][0] = shark.s;
//				ocean[tmpR][tmpC][1] = dir;
//				ocean[tmpR][tmpC][2] = shark.z; 
//			}
			
		}
	}
	
	public static void updateSharkList() { //상어 위치 갱신 및 없어진 상어처리.
		
		for(Shark shark : sharks) {
			if(shark.z != 0 && shark.z > ocean[shark.r][shark.c][2]) { //ocean에 기록할 위치에 비었거나 더 작은 상어가 있나 확인.
//				System.out.println("상어기록할게~shark z :" +shark.z);
				ocean[shark.r][shark.c][0] = shark.s; //ocean에 현재 상어 기록
				ocean[shark.r][shark.c][1] = shark.d; 
				ocean[shark.r][shark.c][2] = shark.z; 
			}
		}
		
		//상어 새롭게 갱신
		sharks.clear();
		for(int i = 1; i <= r; i++) {
			for(int j = 1; j <= c; j++) {
				if(ocean[i][j][2] != 0) {
//					System.out.println("상어리스트 새로 추가할게~ shark.z :"+ocean[i][j][2]);
					sharks.add(new Shark(i,j,ocean[i][j][0],ocean[i][j][1],ocean[i][j][2]));
				}
			}
		}
	}
	
	public static class Shark{
		int r,c,s,d,z; //행, 열, 속도, 방향, 크기
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
		
	}

}
