/*
 * 미세먼지안녕
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int r,c,t;
	public static int[] cleanU, cleanD;
	public static List<Dust> dusts;
	public static int[][] map;
	
	public static int[] dy = {-1,0,1,0};
	public static int[] dx = {0,1,0,-1};
	
	public static class Dust{
		int r, c, val;
		public Dust(int r, int c, int val) {
			this.r = r;
			this.c = c;
			this.val = val;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		dusts = new ArrayList<Dust>();

		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if(cur == -1) {
					if(cleanU == null) {
						cleanU = new int[2];
						cleanU[0] = i;
						cleanU[1] = j;
						map[i][j] = -1;
					}
					else {
						cleanD = new int[2];
						cleanD[0] = i;
						cleanD[1] = j;
						map[i][j] = -1;
					}
				}
				else if(cur != 0) {
					dusts.add(new Dust(i,j,cur));
				}
			}
		}//input done
		
//		System.out.println("input done");
		
		for(int i = 0; i < t; i++) {
			spread();
			move('u');
			move('d');
			update();
		}
		
		int answer = 0;
		for(Dust d : dusts) {
			answer += d.val;
		}
		System.out.println(answer);
		
	}
	
	private static void spread() {
		for(Dust d : dusts) {
			int val = (d.val)/5;
			for(int dir = 0; dir < 4 ; dir++) {
				int nr = d.r + dy[dir];
				int nc = d.c + dx[dir];
				if(nr < 0 || nc <0 || nr >= r || nc >= c || map[nr][nc] == -1) {
					continue;
				}
				map[nr][nc] += val;
				d.val -= val;
			}
			map[d.r][d.c] += d.val;
		}	
	}
	
	private static void move(char type) {
		if(type == 'u') {
			int pr = cleanU[0];
			int pc = cleanU[1];
			int dir = 0;
			while(!(pr == cleanU[0] && pc == cleanU[1]+1)) {
				int cr = pr + dy[dir];
				int cc = pc + dx[dir];
				if(cr < 0 || cc < 0 || cr > cleanU[0] || cc >= c) {
					dir = (dir+1)%4;
					continue;
				}
				if(pr != cleanU[0] || pc != cleanU[1]) {
					map[pr][pc] = map[cr][cc];
				}

				map[cr][cc] = 0;
				pr = cr;
				pc = cc;
			}
		}
		else if(type == 'd') {
			int pr = cleanD[0];
			int pc = cleanD[1];
			int dir = 3;
			while(!(pr == cleanD[0] && pc == cleanD[1] + 1)) {
				int cr = pr + dy[dir];
				int cc = pc + dx[dir];
				if(cr < cleanD[0] || cc < 0 || cr >= r || cc >= c){
					dir = (dir + 3) % 4;
					continue;
				}
				if(pr != cleanD[0] || pc != cleanD[1]) {
					map[pr][pc] = map[cr][cc];
				}
				map[cr][cc] = 0;
				pr = cr;
				pc = cc;
			}
		}
		else {
			System.out.println("Wrong Input");
			return;
		}
	}
	
	private static void update() {
		
		//update dusts
		dusts.clear();
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] > 0) {
					dusts.add(new Dust(i,j,map[i][j]));
				}
			}
		}
		
		//init map
		for(int i = 0; i < r; i++) {
			Arrays.fill(map[i], 0);
		}
		map[cleanU[0]][cleanU[1]] = -1;
		map[cleanD[0]][cleanD[1]] = -1;
		
	}
	
	private static void show() {
		for(int i = 0; i <r; i++) {
			for(int j = 0; j < c; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
}
