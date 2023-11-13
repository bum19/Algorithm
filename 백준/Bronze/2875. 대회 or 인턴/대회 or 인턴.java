import java.io.*;
import java.util.*;

public class Main {
	public static int n,m,k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		int total = n + m;
		int team;
		if(n/2 > m) team = m;
		else team = n/2;
		
		if(team > (total-k)/3) team = (total-k)/3;
		
		System.out.println(team);
		
	}
}
