import java.io.*;
import java.util.*;
//이분탐색힌트봄.
public class Main {
	public static List<Integer> san;
	public static List<Integer> yum;
	public static int answer1, answer2, closestNum, n;
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine().trim());
		
		san = new ArrayList<Integer>(); 
		yum = new ArrayList<Integer>();
		
		//입력 오름차순으로 주어짐. 음수는 내림차순, 양수는 오름차순으로 입력받기
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(st.nextToken());
			if(tmp < 0) {
				san.add(tmp);
			}
			else {
				yum.add(tmp);
			}
		}
		
		closestNum = Integer.MAX_VALUE;
		
		for(int i = 0 ; i < san.size(); i++) {
			int cur = san.get(i);
			int s = 0;
			int e = yum.size()-1;
			
			while(s <= e) {
				int tmp = cur + yum.get((s+e)/2);
//				System.out.println(cur+", "+yum.get((s+e)/2));
				if(closestNum > Math.abs(tmp)) {
					answer1= cur;
					answer2= yum.get((s+e)/2);
					closestNum = Math.abs(tmp);
				}
				if(tmp < 0) s = (s+e)/2 + 1;
				else if(tmp > 0) e = (s+e)/2 - 1;
				else break;
			}
			
		}
		
		//산성만 2개 더하기
		if(san.size() >= 2) {
			int tmp1 = san.get(san.size()-2);
			int tmp2 = san.get(san.size()-1);
			
			if(closestNum > Math.abs(tmp1+tmp2)) {
				closestNum = Math.abs(tmp1+tmp2);
				answer1 = tmp1;
				answer2 = tmp2;
			}
			
		}
		
		//알칼리성만 2개 더하기
		if(yum.size() >= 2) {
			int tmp1 = yum.get(0);
			int tmp2 = yum.get(1);
			
			if(closestNum > Math.abs(tmp1+tmp2)) {
				closestNum = Math.abs(tmp1+tmp2);
				answer1 = tmp1;
				answer2 = tmp2;
			}
			
		}
		
		System.out.println(answer1+" "+answer2);
		
	}
}
