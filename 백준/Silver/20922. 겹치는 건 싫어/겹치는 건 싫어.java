/*
 * 그냥 map 으로 k개이상나오나 검사하기. k개 이상 나오면 map 초기화하고 다시 진행.
 * -> 하려했으나, 반례 발견. 10 2/ 1 2 3 4 4 4 5 6 7 8 이런 경우 6 나오게 하려면,투포인터전략으로가야한다.
 * -> lIdx 증가 잘해줘야한다.
 * 
 */
import java.io.*;
import java.util.*;
public class Main {

	public static int n,k;
	public static int[] input;
	public static Map<Integer, Integer> cntMap;
	public static int[] cntArr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		input = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n;i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int maxLength = Integer.MIN_VALUE;
		int length = 0;
		cntMap = new HashMap<Integer, Integer>();
		cntArr = new int[100001];
		int lIdx = -1;
		int rIdx = 0;
		
		for(; rIdx < n; rIdx++) {
			int cur = input[rIdx];
			//만약cur이 이미 k개 포함되어있다면 length 및 lIdx 갱신.
			if(cntArr[cur] == k) {
				while(input[++lIdx] != cur) {
					cntArr[input[lIdx]]--;
				}
				maxLength = Math.max(maxLength, length);
				length = rIdx - lIdx;
				
				continue;
			}
			
			//만약 cur이 아직 k개 포함 안되어있으면, cur 개수 갱신하고 계속 진행
			cntArr[cur]++;
			length++;
		}
		
		System.out.println(Math.max(length, maxLength));
	}
}
