import java.io.*;
import java.util.*;
/*
 * n번째로 들어온친구가 빠져나올때,  n-1개의 앞에 차들이 빠져나왔는지확인하기.
 * 시간복잡도 O(n^2)
 */
public class Main {
	public static int n, count;
	public static boolean[] isPassed; 
	public static Map<String, Integer> map; //차이름 -> 들어간순서로 바꿔서 저장.
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		isPassed = new boolean[n];
		map = new HashMap<String, Integer>();
		
		for(int i = 0; i < n; i++) {
			String carNum = br.readLine().trim();
			map.put(carNum, i);
		}
		
		for(int i = 0 ; i < n ; i++) {
			String carNum = br.readLine().trim();
			int num = map.get(carNum);
			isPassed[num] = true;
			for(int j = 0; j < num; j++) {
				if(!isPassed[j]) {
					count++;
					break;
				}
			}
		}
		
		System.out.println(count);
	}
	
}
