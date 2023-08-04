import java.util.*;
import java.io.*;

public class Solution {

	public static int n;
	public static Queue<Integer> q = new LinkedList<>();
	public static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int t = 1; t <= 10; t++) {
			//입력시작
			n = sc.nextInt();
			for(int i = 0 ; i < 8; i++) {
				q.offer(sc.nextInt());

			}
//			System.out.println(Arrays.toString(q.toArray()));
			//입력끝
			
			//8자리 숫자값 만들기
			int dec = 0;
			while(true) {
				int temp = q.poll();
				temp -= (dec+1);
				if(temp <= 0) {
					temp = 0;
					q.offer(temp);
					break;
				}
				else {
					q.offer(temp);
				}
//				System.out.println(Arrays.toString(q.toArray()));
				dec++;
				dec %= 5;
			}
			
			sb.append("#").append(t).append(" ");
			for(int i = 0 ; i < 8; i++) {
				sb.append(q.poll()).append(" ");
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb);
		
	}
}
