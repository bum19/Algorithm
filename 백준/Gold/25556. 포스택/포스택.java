/*
* greedy 접근. 스택에서 가장 작으면서, 현재값보다 큰 곳 있나확인
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int n;
	public static int[] input;
	public static int[] stacksPeek; //맨마지막값만확인하면되므로 굳이스택자료형일 필요는 없음
	public static void main(String[] args) throws IOException{
		BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		stacksPeek = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if(!check(cur)) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
	}
	
	public static boolean check(int cur) {
		int idx = -1;
		int val = -1;
		for(int i = 0; i < 4; i++) {
			//현재스택값이 cur보다 작고, 이전에 찾은 val보다 크면 교체
			if(stacksPeek[i] < cur && stacksPeek[i] > val) {
				idx = i;
				val = stacksPeek[i];
			}
		}
		
		//스택에 넣을자리 못찾았으면 false return
		if(idx == -1) {
			return false;
		}
		
		stacksPeek[idx] = cur;
		return true;
	}
}
