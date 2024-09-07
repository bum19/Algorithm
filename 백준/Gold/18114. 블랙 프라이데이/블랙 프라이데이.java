/*
 * 블랙프라이데이
 * 완탐+ 이분탐
 * 
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n,c;
	public static int[] products;
	public static int[] arr;
	public static boolean[] isSelected;
	public static boolean isPossible;
	public static Map<Integer, Integer> map;
	public static Set<Integer> set;
	
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
		
		//1개 일때 이분탐
		isSelected = new boolean[n];
		arr = new int[3];
		comb(0,0,0);
		System.out.println(isPossible?1:0);
	}
	
	private static void comb(int start, int depth, int curNum) {
		if(isPossible)return;
		
		if(depth == 2) {
			int target = c;
			for(int i = 0; i < depth; i++) {
				target -= arr[i];
			}
			if(find(target)) {
				isPossible = true;
			}
			return;
		}
		
		for(int i = start; i < n; i++) {
			if(products[i] + curNum < c) {
				isSelected[i] = true;
				arr[depth] = products[i]; 
				comb(i+1, depth+1, products[i]+curNum);
				isSelected[i] = false;
			}
			else if(products[i] + curNum == c) {
				isPossible = true;
				return;
			}
		}
	}
	
	private static boolean find(int target) {

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
				if(isSelected[mid]) return false;
				return true;
			}
		}
		return false;
	}
}
