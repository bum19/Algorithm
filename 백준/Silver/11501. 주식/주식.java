/*
 * 현재일수보다 미래인 일수에, 주식가격이 더커질일이 없다면,매도타이밍
 * 그렇지않다면, 매수타이밍.
 * greedy
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int t,n;
	public static long maxProfit;
	public static long[] costs;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int t  = Integer.parseInt(br.readLine());
		for(int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			costs = new long[n];
			
			for(int i = 0; i < n; i++) {
				costs[i] = Long.parseLong(st.nextToken());
			}
			//input done
			
			//들고있던값 전부 파는날 정하기. 미래에 현재보다 큰 코스트를 가진 날이 없다면, 파는날.
			boolean[] sellDay = new boolean[n];
			long biggestCost = -1;
			int biggestCostIdx = -1;
			for(int i = n-1; i>=0; i--) {
				if(biggestCost < costs[i]){
					sellDay[i] = true;
					biggestCost = costs[i];
					biggestCostIdx = i;
				}
			}
			
			//팔기
			int stockCnt = 0;
			long profit = 0;
			for(int i = 0; i < n; i++) {
				//파는 날이면 다 팔기
				if(sellDay[i]) {
					profit += costs[i] * stockCnt;
					stockCnt = 0;
				}
				//안파는 날이면 사기
				else {
					profit -= costs[i];
					stockCnt++;
				}
			}
			
			sb.append(profit).append('\n');
		}
		System.out.println(sb);
	}
}
