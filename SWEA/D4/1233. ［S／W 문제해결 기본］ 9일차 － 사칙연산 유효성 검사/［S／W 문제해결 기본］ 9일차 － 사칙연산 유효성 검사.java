import java.util.*;
import java.io.*;

public class Solution {
	static int n;
	static int[] arr;
	static int isValid;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st ;
		StringBuilder sb = new StringBuilder();
		String tmp;
		//dfs 재귀적 탐색으로 왼쪽 노드와 오른쪽 노드가 둘다 숫자이고, 자신이 연산자인지 확인한다. 
		
		for(int test_case = 1; test_case <= 10; test_case++) {
			//입력
			n = Integer.parseInt(br.readLine());
			arr = new int[n+1];
			for(int i = 1 ; i<= n; i++) {
				//연산자면 0을, 숫자면 1을 저장한다.
				st = new StringTokenizer(br.readLine());
				//index 날리기
				st.nextToken();
				tmp = st.nextToken();
				if(tmp.charAt(0) >= '0' && tmp.charAt(0) <= '9') {
					arr[i] = 1;
				}
				else arr[i] = 0;
				
			}
//			for(int i = 1 ; i <=n; i++)
//			System.out.println(arr[i]);
			//구현
			isValid = isValid(1)?1:0;
			//출력
			sb.append("#").append(test_case).append(" ").append(isValid).append("\n");
		}
		System.out.println(sb);
	}
	
	//idx 노드의 왼쪽과 오른쪽이 숫자인지 확인. 숫자면 참. 연산자면 오류.
	static boolean isValid(int idx) {
		
		if(arr[idx] == 1) {
			//현재인덱스가 숫자이고, 자식이 있으면 false 반환.
			if(idx*2 <= n) return false;
			//현재인덱스가 숫자이고, 자식이 없으면 true 반환.
			else return true;
		}
		else {
			//현재인덱스가 연산자일경우, 자식이 1명 이하면 false 반환.
			if(idx*2 >= n) return false;
			//현재인덱스가 연산자일경우, 자식이 2명이면 두자식이 숫자인지 확인후 반환.
			else return isValid(idx*2) && isValid(idx*2 +1); 
		}
		
	}
}
