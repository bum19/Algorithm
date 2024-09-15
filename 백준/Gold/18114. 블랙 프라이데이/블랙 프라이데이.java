/*
 * 블랙프라이데이
 * 완탐+ 이분탐
 * 투포인터 + 이분탐
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,c;
	public static int[] products;
	public static boolean isPossible;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		products = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			products[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(products);
		
		//1개 일때 이분탐색
		if(find(-1,-1,c)) {
			System.out.println(1);
			return;
		}
		
		//2~3개일 때 투포인터 + 이분탐색
		System.out.println(twoPointer()?1:0);
		
	}
	
	private static boolean twoPointer() {
		int left = 0, right = n-1;
		while(left < right) {
			int target = c;
			if(target <  products[left]+ products[right]) {
				right--;
			}
			else if(target > products[left] + products[right]) {
				target -= products[left]+products[right];
				if(find(left,right, target))
					return true;
				left++;
			}
			else {
				return true;
			}
		}
		return false;
	}
	
	private static boolean find(int a, int b, int target) {

		int s = 0, e = n-1;
		while(s<=e) {
			int mid = (s+e)/2;
			if(products[mid] < target) {
				s = mid+1;
			}
			else if(products[mid] > target){
				e = mid-1;
			}
			else {
				if(mid == a || mid == b) return false;
				return true;
			}
		}
		return false;
	}
}
