/*
 * 1은뒤에서, 0은 앞에서 세고, 살릴 인덱스만 true 처리
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		
		
		int zeroCnt = 0;
		int oneCnt = 0;
		
		String input = br.readLine().trim();
		//0개수, 1개수 세기
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '0') zeroCnt++;
			else oneCnt++;
		}
		
		//0 반만큼 인덱스 세기. 앞에서 센다.
		boolean[] isAlive = new boolean[input.length()];
		int cnt = zeroCnt;
		for(int i = 0; i< input.length(); i++) {
			if(input.charAt(i) == '0') {
				isAlive[i] = true;
				cnt--;
			}
			
			if(cnt == zeroCnt/2) break;
		}
		
		//1 반만큼 인덱스 세기. 뒤에서 센다.
		cnt = oneCnt;
		for(int i = input.length()-1; i >= 0; i--) {
			if(input.charAt(i) == '1') {
				isAlive[i] = true;
				cnt--;
			}
			if(cnt == oneCnt/2) break;
		}
		
		//새로운 input 만들기
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < input.length(); i++) {
			if(isAlive[i]) {
				sb.append(input.charAt(i));
			}
		}
		

		
		//출력
		System.out.println(sb);
	}
}
