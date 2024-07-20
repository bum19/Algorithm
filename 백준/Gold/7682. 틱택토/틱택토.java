import java.io.*;
import java.util.*;

public class Main {
	public static char[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		map = new char[3][3];
		while(true) {
			String input = br.readLine().trim();
			if("end".equals(input)) break;
			int dotCnt = 0;
			int xCnt = 0;
			int oCnt = 0;
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					map[i][j] = input.charAt(i*3 + j);
					if(map[i][j] == '.') dotCnt++;
					if(map[i][j] == 'X') xCnt++;
					if(map[i][j] == 'O') oCnt++;
				}
			}
			//input done
			
			if(dotCnt == 0) { //꽉 차있을 경우
				if(xCnt == 5 && oCnt == 4 && !check('O')) {
					sb.append("valid\n");
					continue;
				}
			}
			else { // 꽉 안차있을 경우
				if(xCnt - oCnt == 1 && check('X') && !check('O')) {
					sb.append("valid\n");
					continue;
				}
				if(xCnt == oCnt && !check('X') && check('O')) {
					sb.append("valid\n");
					continue;
				}
			}
			
			sb.append("invalid\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean check(char c) {
		//8가지 경우
		
		for(int i = 0; i < 3; i++) {
			//가로
			if(map[i][0] == map[i][1] && map[i][1] == map[i][2] && map[i][2] == c) {
				return true;
			}
			//세로
			if(map[0][i] == map[1][i] && map[1][i] == map[2][i] && map[2][i] == c) {
				return true;
			}
		}
		
		//대각
		if(map[0][0] == map[1][1] && map[1][1] == map[2][2] && map[2][2] == c) {
			return true;
		}
		
		if(map[0][2] == map[1][1] && map[1][1] == map[2][0] && map[2][0] == c) {
			return true;
		}
		
		return false;
	}
}
