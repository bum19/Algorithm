//메모리	: 
//실행시간 : 
import java.io.*;
import java.util.*;
//n자리소수 알아볼때, n-1자리신기소수에 0~9붙여서 소수검사
public class Main {
	static int n;
	static List<Integer> primeNums;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		n = sc.nextInt();
		
		
		primeNums = findPrime(n);
		
		for(int num : primeNums) {
			sb.append(num).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	private static List<Integer> findPrime(int n) {
		if(n == 1) {
			List<Integer> tmpList = new ArrayList<Integer>();
			boolean isPrime;
			for(int i = 2; i <=9; i++) {
				isPrime = true;
				for(int j = 2; j <= Math.sqrt(i); j++) {
					if(i%j == 0) isPrime = false;
				}
				if(isPrime) tmpList.add(i);
			}
			
			return tmpList;
		}
		
		List<Integer> preList = findPrime(n-1);
		List<Integer> currentList = new ArrayList<Integer>();
		int temp;
		for(int num : preList) {
			//n-1에 1~9를 붙인다.
			//붙인수를 지금까지 구한 1자리,2자리,..n-1자리로 나눠본다.
			temp = num*10+1;
//			System.out.println(temp);
			for(int i = temp ; i <= temp + 8; i++) {
//				System.out.println(i);
				boolean isPrime = true;
				for(int j = 2; j <= Math.sqrt(i); j++) {
					if(i%j == 0) {
						isPrime = false;
						break;
					}
				}
				if(isPrime) currentList.add(i);
			}
		}
		
		return currentList;
				
	}

	
}
