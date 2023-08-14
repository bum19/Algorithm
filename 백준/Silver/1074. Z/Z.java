import java.io.*;
import java.util.*;

public class Main {
	public static int n, r, c, cnt,goCnt;
	public static boolean isFound;
	public static int[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		
		go(0, 0, 1 << n);
//		System.out.println("goCnt : "+goCnt);
		System.out.println(cnt);
	}
	private static void go(int sr, int sc, int size) {

		//현재 블록에 존재하면
		if(size == 2) {
			int y = sr, x = sc;
			if(r == y && c == x) {
				return;
			}
			else if(r == y && c== x+1) {
				cnt++;
				return;
			}
			else if(r == y+1 && c == x) {
				cnt += 2;
				return;
			}
			else {
				cnt += 3;
				return;
			}
		}
		
//		System.out.println("현재블록이 사이즈가 2는 아님");
		//4분할로 나눈뒤 1면일때,2면일때,3면일때,4면일때 생각.
		int half = size/2;
		//왼쪽위
		if(r >= sr && r < sr+half && c >= sc && c < sc+half) {
			go(sr, sc, half);
		}
		//오른쪽위
		else if(r >= sr && r < sr+half && c >= sc+half && c < sc+half+half) {
			cnt += half*half;
			go(sr, sc + half, half);
		}
		//왼쪽아래
		else if(r >= sr+half && r < sr+half+half && c >= sc && c < sc+half) {
			cnt += half*half * 2;
			go(sr+half, sc, half);
		}
		//오른쪽아래
		else if(r >= sr+half && r < sr+half+half && c >= sc+half && c < sc+half+half) {
			cnt += half*half*3;
			go(sr+half, sc+half, half);
		}
//		System.out.println("go 종료");
	}
}

