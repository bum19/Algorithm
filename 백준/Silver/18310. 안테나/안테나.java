import java.io.*;
import java.util.*;
/*
 * 1. 오름차순 정렬
 * 2. arr[i] : i에 안테나를 설치했을때, 거리의 총합.
 * 먼저 arr[0]을 다 더해서 구한뒤, 그다음 arr[i]부터 이전 집개수 * arr[i]-arr[i-1]만큼 더해주고, (총집개수 - 이전집개수) * arr[i] - arr[i-1]만큼 빼준다.
 */
public class Main {
	public static int n;
	public static long[] input;
	public static long[] arr;
	
	public static void main(String[] args) throws IOException{
		BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		input = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(input);
		
		//init
		arr = new long[n];
		for(int i = 1; i < n; i++) {
			arr[0] += input[i] - input[0];
		}

		//탐색하면서 구하기
		int left = 1; //왼쪽에 있는 집 개수. idx0인 집을 왼쪽에 놓고시작
		int right = n-1; //오른쪽에 있는 집개수.(자기 포함)
		long min = arr[0];
		int idx = 0;
		for(int i = 1; i < n; i++) {
			arr[i] = arr[i-1] + left * (input[i] - input[i-1]) - right * (input[i] - input[i-1]);
			left++;
			right--;
			if(min > arr[i]) {
				min= arr[i];
				idx = i;
			}
		}
		
		System.out.println(input[idx]);
		
	}
}
