//메모리	: 
//실행시간 : 

import java.util.*;
import java.io.*;
//부분집합 길이 정해서 재귀
//하려햇으나 연속된 부분 문자열을 추출하므로 for문으로 슬라이딩예정
//문자열에 있는 개수세기. 누적합으로 구현가능.일단은 그냥 해보고 시간초과났으므로 누적합 저장 메모리 구현
//for문탐색하면서 그때그때 갱신.
public class Main {
	static int s, p, cnt;
	static String dna;
	static int[] limit = new int[4];
	static int[] currentNum = new int[4];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//입력
		s = sc.nextInt();
		p = sc.nextInt();
		dna = sc.next();
		for(int i = 0; i < 4; i++) {
			limit[i] = sc.nextInt();
		}

		//입력 종료
		
		

		//초기세팅
		for(int i = 0; i < p; i++) {
			if(dna.charAt(i) == 'A') currentNum[0]++;
			else if(dna.charAt(i) == 'C') currentNum[1]++;
			else if(dna.charAt(i) == 'G') currentNum[2]++;
			else if(dna.charAt(i) == 'T') currentNum[3]++;
		}
		//슬라이드하면서 탐색
		for(int i = 0 ; i <= s - p; i++) {
			if(i != 0) {
			if(dna.charAt(i-1) == 'A') currentNum[0]--;
			else if(dna.charAt(i-1) == 'C') currentNum[1]--;
			else if(dna.charAt(i-1) == 'G') currentNum[2]--;
			else if(dna.charAt(i-1) == 'T') currentNum[3]--;
			
			if(dna.charAt(i+p-1) == 'A') currentNum[0]++;
			else if(dna.charAt(i+p-1) == 'C') currentNum[1]++;
			else if(dna.charAt(i+p-1) == 'G') currentNum[2]++;
			else if(dna.charAt(i+p-1) == 'T') currentNum[3]++;
			}
			//개수명확한지 검증
//			System.out.println("i : "+i+", currentNum: "+Arrays.toString(currentNum));
			for(int j = 0; j < 4; j++) {
				
				if(currentNum[j] < limit[j]) {
					cnt--;
					break;
				}
			}
			cnt++;
//			System.out.println("i : "+i+", cnt : "+cnt);
		}
		
		System.out.println(cnt);
	}
}
