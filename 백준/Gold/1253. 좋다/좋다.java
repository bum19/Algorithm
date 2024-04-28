import java.io.*;
import java.util.*;
/*
 * NC2로 숫자 2개 고르기
 * 2개 더한값 Map에 넣기
 * 2000개 순회하면서 map에있으면 +하기
 * 문제에서 다른수에 대한 설명이 부족한듯
 * 자기 자신을 제외한 2개의 수로 만들어져야함. << 이 설명이 빠짐.
 */
public class Main {
	public static int n, goodCnt;
	public static int zeroCnt;
	public static int[] num;
	public static Map<Integer, Integer> map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		
		num = new int[n];
		zeroCnt = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			if(num[i] == 0)
				zeroCnt++;
		}
		
		map = new HashMap<>();
		//2가지 경우 뽑아서 Map에넣기
		for(int i = 0; i < n; i++) {
			for(int j = i + 1; j < n; j++) {
				int tmp = num[i] + num[j];
				if(map.get(tmp) != null) {
					map.put(tmp, map.get(tmp) + 1);
				}
				else
					map.put(tmp,1);
			}
		}
		
		
		//확인
		//0이있고, map에 해당 값 들어간 횟수가 0의 개수라면 자기자신으로 만든 경우므로 세지않는다.
		//현재 조회하는게 0이고, 0이 2개인데 0을 만든 경우가 1개라면, 자기자신으로 만든경우이므로 세지 않는다.
		for(int i = 0; i < n; i++) {
			if(map.get(num[i]) != null) {
				if(num[i] == 0) {
					if(zeroCnt == 2 && map.get(0) == 1) continue;
				}
				else {
					if(zeroCnt > 0 && map.get(num[i]) == zeroCnt) continue;
				}
				goodCnt++;
				
			}
		}
		
		System.out.println(goodCnt);
	}

}
