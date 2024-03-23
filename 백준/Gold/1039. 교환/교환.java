import java.io.*;
import java.util.*;
/* 답봄.
 * BFS탐색.
 * 처음에는 자릿수가 n 이고 반복횟수 k일때
 * 
 * (nC2) ^ k라고생각했으나,
 * 매 i(i는 0~k-1)번쨰 마다 중복처리로 최대 n번탐색한다.
 * 그러므로 시간복잡도는 O(nk)이고, 이는 1000만밖에안됨.
 * 
 *  
 */
public class Main {
	public static boolean[][] visited; // visited[i][num] i번 반복했을떄, 숫자 num 방문여부
	public static int number;
	public static int numLength;
	public static int k;
	public static int maxNum;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		number = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		//input done
		
		//예외처리. 1자리수거나, 2자리면서 0이있으면 불가능.
		if(number < 10 || (number < 100 & number%10 == 0)) {
			System.out.println(-1);
			return;
		}
		
		//numLength계산
		int tmp = number;
		while(tmp > 0) {
			tmp /= 10;
			numLength++;
		}
		
		bfs();
		System.out.println(maxNum);
	}
	
	private static void bfs() {
		Queue<int[]> q= new ArrayDeque<>();
		visited = new boolean[k+1][1000001];
		
		visited[0][number] = true;
		q.add(new int[] {number, 0});
		//큐가 빌떄까지 반복. k번 진행할떄까지간다.
		while(!q.isEmpty()) {
			int cur[] = q.poll();
			//k번 반복한 수라면 최대숫자 갱신만 진행
			if(cur[1] == k) {
				if(maxNum < cur[0]) {
					maxNum = cur[0];
				}
				continue;
			}
			
			//현재숫자에서 가능한 모든 조합 큐에 추가. 단 이미 방문한 숫자는 넣지 않기
			for(int i = 0; i < numLength; i++) {
				for(int j = i+1; j < numLength; j++) {
					int swapNum = swap(cur[0], i,j); //0이 맨앞자리인경우 -1 리턴
					if(swapNum != -1 && !visited[cur[1]+1][swapNum]) {
						visited[cur[1]+1][swapNum] = true;
						q.add(new int[] {swapNum, cur[1] + 1});
					}
				}
			}
		}
	}
	

	
	private static int swap(int n, int idx1, int idx2) {
		int length = 0; //사실 다 같아서 다시계산안해도됨.
		int tmp = n;
		while(tmp >0) {
			tmp /=10;
			length++;
		}
		
		//배열로만들기
		int[] arr = new int[length];
		tmp = n;
		for(int i = length-1; i >= 0; i--) {
			arr[i] = tmp%10;
			tmp /= 10;
		}
		
		//바꾸기
		tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
		
		if(arr[0] == 0) return -1;
		
		//다시 숫자로 만들기.
		StringBuilder sb = new StringBuilder();
		for(int num : arr) {
			sb.append(num);
		}
		
		return Integer.parseInt(sb.toString());
	}
}
