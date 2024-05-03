import java.util.*;
import java.io.*;
//그냥 1씩 더하자 브론즈 문제인데
public class Main {

	public static int n;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int answer = 0;
		for(int i = 0; i <n ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			StringTokenizer st2= new StringTokenizer(st.nextToken(), ":");
			int startTime = 60 * Integer.parseInt(st2.nextToken()) + Integer.parseInt(st2.nextToken()); 
			int endTime = startTime + Integer.parseInt(st.nextToken());
			for(int j = startTime; j < endTime; j++) {
				if( 420 <= j && j < 1140)
					answer += 10;
				else
					answer += 5;
			}
		}
		
		System.out.println(answer);
	}
}

