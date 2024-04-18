import java.io.*;
import java.util.*;
/*
 * 정렬 : 30만*log30만 * 2= 대충 600만정도
 * 하나씩 이진 탐색 : 30만 * log30만 = 600만정도
 * 불가능한 값이 껴있는 배열을 이진탐색 + 최솟값을 찾기위해, union-find를 응용
 */
public class Main {
	public static Jewel[] jewels;
	public static int[] bags;
	public static int[] parents;
	public static boolean[] isSelected;
	public static Map<Integer, Long> map;
	public static int n, k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		jewels = new Jewel[n];	
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			jewels[i] = new Jewel(m,v);
		}
		
		bags = new int[k];
		for(int i = 0; i < k; i++) {
			bags[i] = Integer.parseInt(br.readLine().trim());
		}
		

		
		//정렬
		Arrays.sort(jewels);
		Arrays.sort(bags);
		
		parents= new int[k];
		for(int i = 0; i < k; i++) {
			parents[i] = i;
		}
		map = new HashMap<>(); 
		//보석 하나씩 보면서 이진탐색
		for(int i = 0; i < n; i++) {
			Jewel curJ = jewels[i];
			long m = curJ.weight;
			
			//이진탐색 + 유니온파인드.. ㅋㅋ
			int a = 0;
			int b = k-1;
			int minIdx = -1;
			while(a <= b) {
				int idx = find((a+b)/2);
				//idx가 음수라는 뜻은, (a+b)/2부터 0까지 전부 찼다는 뜻이다.
				if(idx < 0) {
					a = (a+b)/2 + 1;
				}
				//m이 더 크면 a늘리기
				else if(m > bags[idx]) {
					a = (a+b)/2 + 1;
				}
				//m이 더 작으면,
				else {
					minIdx = idx;
					//b줄이기
					b = idx - 1;
				}
					
			}
			
			//찾았으면
			if(minIdx != -1) {
				//넣고, parents 갱신
				map.put(minIdx, curJ.value);
				union(minIdx);
			}
			
		}//탐색끝
		
		//답구하기
		long answer = 0;
		for(int key : map.keySet()) {
			answer += map.get(key);
		}
		
		System.out.println(answer);
	}
	
	
	public static int find(int a) {
		//자기자신이거나, -1이면 리턴
		if(parents[a] == -1 || parents[a] == a) return parents[a];
			
		//아니면 타고올라가면서 갱신.
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a) {
		if(parents[a] == -1) return false;
		if(a == 0) {
			parents[a] = -1;
			return true;
		}
		
		int aParent = find(a);
		int bParent = find(a-1);
		
		if(aParent == bParent) return false;
		
		parents[aParent] = bParent;
		
		return true;
	}
	
	
	public static class Jewel implements Comparable{
		long weight;
		long value;
		
		public Jewel(long weight, long value) {
			this.weight = weight;
			this.value = value;
		}
		
		//값어치 높은순으로 정렬
		@Override
		public int compareTo(Object j1) {
			Jewel j = (Jewel)j1;
			if(this.value == j.value) {
				return Long.compare(this.weight, j.weight);
			}
			return Long.compare(this.value, j.value) * -1;
		}
	}
}
