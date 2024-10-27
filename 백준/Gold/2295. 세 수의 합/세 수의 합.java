/*
 * 1000^3 / 6
 * 최대 1.6억 시간복잡도 가능.
 * 메모리초과로인한 for문으로 comb 전환
 * 변수선언을 for문 밖으로 빼봄
 * 답보고품.
 */
import java.io.*;
import java.util.*;

public class Main {
	public static int n, max;
	public static int[] input;
	public static Set<Integer> set;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());

		input= new int[n];
		set = new HashSet<>();
		for(int i = 0; i< n ; i++) {
			int num = Integer.parseInt(br.readLine().trim());
			input[i] = num;
			set.add(num);
		}
		
		max = Integer.MIN_VALUE;
					
//		sol1();
		
		sol2();
		
		System.out.println(max);
	}
	
	private static void sol2() {
		Set<Integer> twoSum = new HashSet<>();
		for(int i = 0; i < n; i++) {
			for(int j = i; j < n; j++) {
				twoSum.add(input[i] + input[j]);
			}
		}
		
		for(int i = 0; i < n ;i++) {
			for(int j = i+1; j < n; j++) {
				int realTwoSum = Math.abs(input[i] - input[j]);
				int total = input[i]> input[j]?input[i] : input[j];
				if(twoSum.contains(realTwoSum) && max < total){
					max = total;
				}
			}
		}
		
	}

	private static void sol1() {
		int sum = 0;
		int i = 0, j = 0, k = 0;
		for(i = 0 ; i < n; i++) {
			for(j  = i ; j < n; j++) {
				for(k = j; k < n; k++) {
					sum = input[i] + input[j] + input[k];
					if(set.contains(sum) && max < sum ) {
						max = sum;
					}
				}
			}
		}
	}
}
