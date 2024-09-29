/*
 * 전체 맵을 90도회전시키고, 뒤집어서 테스트하기
 */
import java.io.*;
import java.util.*;
public class Main {
	public static List<int[][]> shapes;
	public static int n,m,max;
	public static int[][] map1,map2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map1 = new int[n][m];
		map2 = new int[m][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map1[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		init();
		int[][] cur = map1;
		for(int rev = 0; rev < 2; rev++) {
			for(int rot = 0; rot < 4; rot++) {
				check(cur);
				cur = rotate(cur);
			}
			cur = symmetric(cur);
		}
		
		System.out.println(max);
		
	}
	
	private static void init() {
		shapes = new ArrayList<>();
		
		shapes.add(new int[][]{{0,0},{0,1},{0,2},{0,3}});
		shapes.add(new int[][]{{0,0},{0,1},{1,0},{1,1}});
		shapes.add(new int[][]{{0,0},{1,0},{2,0},{2,1}});
		shapes.add(new int[][]{{0,0},{1,0},{1,1},{2,1}});
		shapes.add(new int[][]{{0,0},{0,1},{0,2},{1,1}});
		
	}
	
	private static void check(int[][] cur) {
		for(int i = 0; i < cur.length; i++) {
			for(int j = 0; j < cur[0].length; j++){
				for(int[][] shape : shapes) {
					compare(shape,i,j,cur);
				}
			}
		}
	}
	
	private static void compare(int[][] shape, int y, int x, int[][] cur) {
		int val = 0;
		for(int[] loc: shape) {
			int ry = loc[0] + y;
			int rx = loc[1] + x;
			if(ry < 0 || rx <0 || ry >= cur.length || rx >= cur[0].length) return;
			val += cur[ry][rx];
		}
		max = Math.max(max,val);
	}
	
	
	private static int[][] rotate(int[][] arr) {
		int ylen = arr.length;
		int xlen = arr[0].length;
		int[][] ret;
		if(arr == map1) {
			ret = map2;
		}
		else {
			ret = map1;
		}
		
		for(int y = 0; y < ylen; y++) {
			for(int x = 0; x < xlen; x++) {
				ret[x][ylen-1-y] = arr[y][x];
			}
		}
		
		return ret;
	}
	
	public static int[][] symmetric(int[][] arr) {
		int ylen = arr.length;
		int xlen = arr[0].length;
		
		for(int y = 0; y < ylen; y++) {
			for(int x = 0; x < xlen/2; x++) {
				int tmp = arr[y][x];
				arr[y][x] = arr[y][xlen-1-x];
				arr[y][xlen-1-x] = tmp;
			}
		}
		
		return arr;
	}
	
	private static void show(int[][] cur) {
		for(int i = 0; i < cur.length; i++) {
			for(int j = 0; j < cur[0].length; j++) {
				System.out.print(cur[i][j] +" ");
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}
}
