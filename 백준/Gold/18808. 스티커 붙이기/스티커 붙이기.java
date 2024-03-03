import java.io.*;
import java.util.*;
public class Main {
	public static int n,m,k, answer;
	public static int[][] board;
	public static List<int[][]> stickers;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		stickers = new ArrayList<>();
		
		for(int i = 0; i < k ; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken());
			int sx = Integer.parseInt(st.nextToken());
			int[][] sticker = new int[sy][sx];
			for(int y = 0; y < sy; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < sx; x++) {
					sticker[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			stickers.add(sticker);
		}
		
//		// 입력확인
//		for(int[][] sticker : stickers) {
//			for(int y = 0; y <  sticker.length ; y++) {
//				System.out.println();
//				for(int x = 0; x < sticker[0].length ; x++) {
//					System.out.print(sticker[y][x]+ " ");
//				}
//			}
//		}
		
		//모든 스티커에대해 붙이기
		int tmp = 1;
		for(int[][] sticker : stickers) {
			//회전시켜 총 4회 반복
			for(int rotate = 0; rotate < 4; rotate++) {
				//왼쪽 위부터 시작.
				for(int i = 0; i <= n - sticker.length; i++) {
					for(int j = 0; j <= m - sticker[0].length ; j++) {
						if(check(i,j,sticker)) { //붙이는데 성공하면 탈출
//							System.out.println("rotate : "+rotate+"번 회전 했고, "+tmp+"번 스티커 붙이기 가능.");
							attach(i,j,sticker);
//							System.out.println("붙인뒤 상태");
//							for(int y = 0; y < n ; y++) {
//								System.out.println();
//								for(int x = 0; x < m ; x++) {
//									System.out.print(board[y][x]+ " ");
//								}
//							}
//							System.out.println("----");
							rotate = 3;
							i = n;
							j = m;
						}
					}
				}
				
				//회전시키기
				if(rotate != 3) {
//					System.out.println(tmp+"번 스티커회전");
					sticker = rotateSticker(sticker);
//					for(int y = 0; y <  sticker.length ; y++) {
//						System.out.println();
//						for(int x = 0; x < sticker[0].length ; x++) {
//							System.out.print(sticker[y][x]+ " ");
//						}
//					}
//					
//					System.out.println("--");
				}
			}
			tmp++;
		}
		
		//세기
		answer = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m;j++) {
				if(board[i][j] != 0) answer++;
			}
		}
		
		System.out.println(answer);
		
	}
	
	private static boolean check(int baseY, int baseX, int[][] sticker) {
		int sy = sticker.length;
		int sx = sticker[0].length;
		for(int y = 0; y < sy; y++) {
			for(int x = 0; x < sx; x++) {
				if(board[baseY + y][baseX + x] == 1 && sticker[y][x] == 1)
					return false;
			}
		}
		
		return true;
	}
	
	private static void attach(int baseY, int baseX, int[][] sticker) {
		int sy = sticker.length;
		int sx = sticker[0].length;
		for(int y = 0; y < sy; y++) {
			for(int x = 0; x < sx; x++) {
				if(sticker[y][x] != 0)
					board[baseY + y][baseX + x] = sticker[y][x];
			}
		}
	}
	
	private static int[][] rotateSticker(int[][] sticker){
		int[][] tmp = new int[sticker[0].length][sticker.length]; //y, x 크기 바꿔야함.
		
		for(int y = 0; y < sticker.length; y++) {
			for(int x = 0; x < sticker[0].length; x++) {
				tmp[x][sticker.length-1-y] = sticker[y][x];
			}
		}
		return tmp;
	}
}
