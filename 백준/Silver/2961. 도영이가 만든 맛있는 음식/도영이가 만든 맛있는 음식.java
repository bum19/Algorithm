import java.util.*;
import java.io.*;

//메모리	: 
//실행시간 : 
public class Main {
//부분집합으로 재료를 1~n개 고르고, 차이를 구해서 최소인경우 출력
	
	public static int n;
	public static long[][] ing;
	public static boolean[] isSelected;
	public static long min = Long.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		ing = new long[n][2];
		isSelected = new boolean[n];
		
		for(int i = 0 ; i < n; i++) {
			ing[i][0] = sc.nextLong();
			ing[i][1] = sc.nextLong();
		}
		
		recur(0);
		
		System.out.println(min);
	}
	
	
	private static void recur(int cnt) {
		
		if(cnt == n) {
			int mSum = 1, sSum = 0, ingNum = 0;
			for(int i = 0; i < n; i++) {
				if(isSelected[i]) {
					 ingNum++;
					 mSum *= ing[i][0];
					 sSum += ing[i][1];
				}
			}
			
			//재료 선택 아예안했을경우 제외
			if(ingNum != 0) {
				min = Math.min(min, Math.abs(sSum-mSum));
			}
			return;
			
		}
		
		isSelected[cnt] = false;
		recur(cnt+1);
		isSelected[cnt] = true;
		recur(cnt+1);
		
	}

}
