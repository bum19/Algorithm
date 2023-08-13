import java.io.*;
import java.util.*;
public class Main {
	public static int n, x, k, idx;	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		x = sc.nextInt();
		k = sc.nextInt();
		
		idx = x;
		for( int i = 0; i < k; i++) {
			int tmp = sc.nextInt();
			int tmp2 = sc.nextInt();
			if(idx == tmp) idx = tmp2;
			else if( idx == tmp2) idx = tmp;
		}
		
		System.out.println(idx);
	}
}
