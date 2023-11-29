import java.io.*;
import java.util.*;


public class Main {
	public static int n,m, answer; //n은 세로, m은 가로.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		//세로 1일때 1칸
		if(n == 1) {
			answer = 1;
		}
		//세로 2일때 최대 4칸. 가로 3칸 -> 2칸.가로 5칸 ->3칸, 가로 7칸 이상: 4칸
		else if(n == 2) {
			if( m < 7) {
				answer = (m-1)/2 + 1;
			}
			else {
				answer = 4;
			}
			
		}
		//세로3일때 최대 칸.가로 2 : 2칸, 가로3 : 3칸, 가로 4~6 : 4칸, 가로7+x : 5+x
		else {
			if(m < 4) {
				answer = m;
			}
			else if( m < 7) {
				answer = 4;
			}
			else {
				answer = 5 + (m-7);
			}
		}
		
		System.out.println(answer);
	}

}
