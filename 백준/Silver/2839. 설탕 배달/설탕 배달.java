
//메모리	:
//실행시간	:
import java.util.*;
import java.io.*;
public class Main {
	public static int n, minBags = Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int startKg5 = n/5;
		
		for(int kg5 = startKg5; kg5 >=0; kg5--) {
			//kg3의개수는 (n - kg5개수*5)/3이다.
			int kg3 = (n - (kg5 * 5)) /3;
			if((kg3 * 3 + kg5 * 5) == n) minBags = Math.min(kg3+kg5, minBags);
		}
		if(minBags == Integer.MAX_VALUE) minBags = -1;
		System.out.println(minBags);
		
	}

}
