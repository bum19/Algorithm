//답 봄
//1. 부분합 배열을 하나씩탐색하면서,이전에 sum[i] - k 를 만족하는 값이 존재했다면, 해당 값만큼 answer에 더함.
//2. 현재재 sum[i]의 값의 개수를 map 에 더함
//시간복잡도 n+1
//*뻬야하는 값을 탐색하는시간이 최대 n에서 전부 1로 줄어듦. (1번작업으로 인해)
//long타입 체크
import java.io.*;
import java.util.*;
public class Main {
	public static int n,k;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] sum = new int[n];
		st = new StringTokenizer(br.readLine());
		
		sum[0] = Integer.parseInt(st.nextToken());
		for(int i = 1 ; i < n; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		long answer = 0;
		
		Map<Integer, Integer> map = new HashMap<>(); // (값, 해당값을 가진  sum 배열의 개수)
		for(int i = 0; i < n; i++) {
			//값이 있는지 확인하고 answer에 더하기 2 0 2 0
			if(map.get(sum[i] - k) != null) {
				answer += map.get(sum[i]-k);
			}
			
			//현재 sum값 map에 갱신
			if(map.get(sum[i]) == null) {
				map.put(sum[i], 1);
			}
			else {
				map.put(sum[i], map.get(sum[i]) + 1);
			}
			
			//현재 값 체크
			if(sum[i] == k) answer++;
					
		}
		
		System.out.println(answer);
	}
}