import java.io.*;
import java.util.*;

public class Main {
	
	public static PriorityQueue<Long> pq = new PriorityQueue<>();
	public static long result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 1; i <=n; i++) {
			pq.add(sc.nextLong());
		}
		
		for(int i = 1; i <= n ; i++) {
			result += Math.abs(pq.poll()-i); 
		}
		
		
		System.out.println(result);
	}

}
