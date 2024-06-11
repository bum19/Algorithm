import java.io.*;
import java.util.*;
/*
 * 힙 직접구현
 * 
 * 배열로 양의 정수 최소힙 구현
 */
public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		

//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		MyMinHeap pq = new MyMinHeap(n);
		
		for(int i = 0 ; i < n; i++) {
			int input = Integer.parseInt(br.readLine());
			if(input == 0) {
				if(pq.isEmpty()) {
					sb.append('0');
				}
				else {
					sb.append(pq.poll());
				}
				sb.append('\n');
			}
			else {
				pq.add(input);
			}
		}
		
		System.out.println(sb);
	}
	
	public static class MyMinHeap{
		int size;
		int[] arr;
		public MyMinHeap(int n){
			this.size = 0;
			this.arr = new int[n+1]; //root인덱스 1로하기.
			Arrays.fill(arr, -1);
		}
		
		//힙 삽입
		public void add(int num) {
			//일단 넣는다.
			arr[++size] = num;
			
			//거슬러 올라가기
			int cur = size;
			while(cur > 1) {
				//현재 나와 부모 비교. 내가 더작으면 부모와 교환. 부모의 다른 자식은 부모보다 작다는것이 자명하므로 비교 x.
				if(arr[cur] < arr[cur/2]) {
					swap(arr ,cur, cur/2);
					cur = cur/2;
				}
				else {
					break;
				}
			}
		}
		
		
		//힙 조회 후 제거
		public int poll() {
			int ret = arr[1];
			arr[1] = arr[size--];
			
			//아래로 내려가기
			int cur = 1;
			while(cur <= size/2) { //마지막 부모노드까지 탐색
				//가장 작은 노드를 부모로 올리기
				int smallest = cur;
				if(cur*2 <= size && arr[smallest] > arr[cur*2]) {
					smallest = cur*2;
				}
				if(cur*2 + 1 <= size && arr[smallest] > arr[cur*2 + 1]) {
					smallest = cur*2+1;
				}
				if(smallest == cur) break;
				swap(arr, cur, smallest);
				cur = smallest;
			}
			
			return ret;
		}
		
		public boolean isEmpty() {
			if(size == 0) return true;
			return false;
		}
		
		
		private void swap(int[] arr,int idx1, int idx2) {
			int tmp = arr[idx1];
			arr[idx1] = arr[idx2];
			arr[idx2] = tmp;
		}
		
	}
}
