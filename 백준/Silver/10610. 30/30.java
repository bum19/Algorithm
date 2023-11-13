import java.io.*;
import java.util.*;
public class Main {
	public static int[] numCount;
	public static int sum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine().trim();
		sum = 0;
		numCount = new int[10];
		for(int i = 0; i < str.length(); i++) {
			numCount[str.charAt(i)-'0']++;
			sum += str.charAt(i) - '0';
		}
		
		if(sum%3 == 0 && numCount[0] != 0) {
			for(int i = 9; i >= 0; i--) {
				for(int j = 0; j < numCount[i]; j++) {
					sb.append(i);
				}
			}
		}else {
			sb.append(-1);
		}
		
		System.out.println(sb);
	}
}
