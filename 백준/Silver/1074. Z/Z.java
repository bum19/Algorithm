import java.io.*;
import java.util.*;

public class Main {
	public static int n, r, c, cnt; //크기, 행좌표, 열좌표, 방문카운트
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력스트림
		StringTokenizer st; //입력받아서 토큰화
		
		st = new StringTokenizer(br.readLine()); //한 줄 입력
		
		n = Integer.parseInt(st.nextToken());	//n 입력
		r = Integer.parseInt(st.nextToken());	//행 좌표 입력
		c = Integer.parseInt(st.nextToken());	//열 좌표 입력
		
		
		go(0, 0, 1 << n);	//탐색시작
		System.out.println(cnt);	//출력
	}
	
	//탐색시작. 공간 sr ~ sr+size, sc ~ sc+size만큼의 공간을 탐색한다.
	//핵심은 go는 무조건 r과 c를 포함하는 공간만 탐색한다.
	private static void go(int sr, int sc, int size) { 

		//현재 블록사이즈가 2라면
		if(size == 2) {
			int y = sr, x = sc;
			if(r == y && c == x) { //z시작위치면 이미 cnt다 있으므로 그냥 리턴
				return;
			}
			else if(r == y && c== x+1) { //z의 2번째 위치면 cnt 1증가
				cnt++;
				return;
			}
			else if(r == y+1 && c == x) {//z의 3번째 위치면 cnt 2증가
				cnt += 2;
				return;
			}
			else {			//z의 4번째 위치면 cnt 3증가
				cnt += 3;
				return;
			}
		}
		
		
		//4분할로 나눈뒤, 존재하는 블록에 한하여 탐색을 시작.
		int half = size/2;
		//왼쪽위에 존재할경우 바로 탐색.
		if(r >= sr && r < sr+half && c >= sc && c < sc+half) {
			go(sr, sc, half);
		}
		//오른쪽위에 존재할경우 왼쪽위크기만큼 더하고 탐색.
		else if(r >= sr && r < sr+half && c >= sc+half && c < sc+half+half) {
			cnt += half*half;
			go(sr, sc + half, half);
		}
		//왼쪽아래에 존재할경우 왼쪽위,오른쪽위 크기만큼 더하고 탐색 
		else if(r >= sr+half && r < sr+half+half && c >= sc && c < sc+half) {
			cnt += half*half * 2;
			go(sr+half, sc, half);
		}
		//오른쪽아래에 존재할경우, 왼쪽위, 오른쪽위, 왼쪽아래 크기만큼 더하고 탐색
		else if(r >= sr+half && r < sr+half+half && c >= sc+half && c < sc+half+half) {
			cnt += half*half*3;
			go(sr+half, sc+half, half);
		}
	}
}

