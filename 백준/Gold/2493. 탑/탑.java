//메모리 : 
//실행시간 : 
import java.util.*;
import java.io.*;

public class Main {
	
	public static int n;
	public static int[] input;
	public static int[] output;
	public static PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a1,a2) -> a1[0] - a2[0]);
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		input = new int[n];
		output = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i<n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//입력 뒤에서부터 받는다.
		for(int i = n-1; i >=0 ; i--) {
			int[] tmp = {input[i], i};
			//pq에서 현재 탑에 송신되는 녀석 있나 보기
			while(true) {
				if(pq.isEmpty()) {
					pq.offer(tmp);
					break;
				}
//				System.out.println("pq.size() :"+pq.size()+", i: "+i+",pq.peek() :"+pq.peek()[0]);
				//큐 안의 제일 작은탑이 걸리는경우
				if(input[i] >= pq.peek()[0]) {
					//해당 탑인덱스위치에 기록하기 + 큐에서빼내기
					output[pq.poll()[1]] = i+1;
				}
				else {
				//안걸리는경우
					//while탈출
					break;
				}
			}
			//현재 위치 넣기
			pq.offer(tmp);
			
		}
		//output출력
		for(int i = 0; i < n; i++) {
			sb.append(output[i]).append(" ");
		}
		
		System.out.println(sb);
		
		
		
	}

}
