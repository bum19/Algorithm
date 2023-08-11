
//메모리:
//실행시간
import java.io.*;
import java.util.*;
public class Main {
	//9개의 수중 합이 총합-100만큼 되는 2개의 수찾기.
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] arr = new int[9];
		boolean[] isNot7nanjeng = new boolean[9];
		int num = 0;
		for(int i = 0; i< 9; i++) {
			arr[i] = sc.nextInt();
			num += arr[i];
		}
		num -= 100;
		
		for(int i = 0; i < 9; i++) {
			for(int j = i+1; j < 9; j++) {
				if(arr[i] + arr[j] == num) {
					isNot7nanjeng[i] = true;
					isNot7nanjeng[j] = true;
				}
			}
		}
		
		for(int i = 0 ; i < 9; i++) {
			if(!isNot7nanjeng[i])
				System.out.println(arr[i]);
		}
		
	}


}
