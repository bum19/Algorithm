//
import java.io.*;
import java.util.*;

public class Main {
	public static int n, d, k, c;
	public static int[] table;
	public static int[] selectCnt; //초밥종류별 선택된 횟수
	public static int maxCnt, cnt; //최대 초밥 종류 수, 현재 선택된 초밥 종류 수
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		table = new int[n];
		selectCnt = new int[d+1]; //초밥번호는 1번부터시작하므로 d+1을 선언.
		maxCnt = Integer.MIN_VALUE;
		cnt = 0;
		
		for(int i = 0; i < n; i++) {
			table[i] = Integer.parseInt(br.readLine().trim());
		}
		
		//초기세팅 0~k-1까지 k개 선택. 일단 쿠폰 고려하지 않고 카운트
		for(int i = 0; i < k; i++) {
			if(++selectCnt[table[i]] == 1) cnt++ ; //처음 해당종류 초밥 만났으면(1이면) cnt 증가. 쿠폰만나도 일단 cnt 증가.  
		}
		if(selectCnt[c] == 0) cnt++; //쿠폰이 초기세팅에 안껴있다면 cnt증가.
		
		//cnt는 항상 쿠폰종류를 포함한 상태.
		
		
		for(int i = 1; i < n; i++) {
//			System.out.println(i+"~"+(i+k-1%n));
			selectCnt[table[i-1]]--; //맨앞칸에 있던 애 제거
			if(selectCnt[table[i-1]] == 0) { //제거한 초밥종류가 현재 0개 포함되있다면
				cnt--;	//초밥개수 감소.
				if(table[i-1] == c) cnt++; //근데 걔가 하필 쿠폰이면 cnt개수 하나증가
			}
			
			selectCnt[table[(i+k-1)%n]]++; //맨뒷칸에 있는 애 추가
			if(selectCnt[table[(i+k-1)%n]] == 1) { //맨뒷칸에 있는애가 처음 추가 되었다면
				cnt++; //초밥개수 증가.
				if(table[(i+k-1)%n] == c) cnt--; //근데 하필 걔가 쿠폰이면 이미 쿠폰은 포함되있으므로 증가했던 cnt 하나 감소 
			}
		
			//현재 cnt 개수 갱신
			if(maxCnt < cnt) maxCnt = cnt;
		}
		
		System.out.println(maxCnt);
	}

}
