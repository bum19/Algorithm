import java.io.*;
import java.util.*;

public class Main {
	public static String[] yoil = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
	public static int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int mon = Integer.parseInt(st.nextToken());
		int day = Integer.parseInt(st.nextToken());
		
		int totaldays = 0;
		for(int i = 1; i <= mon-1; i++) {
			totaldays += days[i];
		}
		totaldays += day-1; //1월 1일부터시작하므로
		
		System.out.println(yoil[totaldays%7]);
		
	}
}
