/*
자기보다 큰 남성, 자기보다 작은 여성 : a
자기보다 작은 남성, 자기보다 큰 여성 : b
라고 할때, 
1.  a형 그룹, b형 그룹으로 분류.
2. 각 유형에 대해 여자, 남자 키 작은순 정렬 후 투포인터로 쌍만들
 */
import java.io.*;
import java.util.*;
public class Main {
	public static List<Integer> aGirls, aBoys, bGirls, bBoys;
	public static int n;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		aBoys = new ArrayList<>();
		bBoys = new ArrayList<>();
		for(int i = 0; i< n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num < 0) { // 키가 작은 여자와 춤추고싶은 남자
				aBoys.add(num * -1);
			}
			else {
				bBoys.add(num);
			}
		}
		st = new StringTokenizer(br.readLine());
		aGirls = new ArrayList<>();
		bGirls = new ArrayList<>();
		for(int i = 0; i< n; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(num < 0) { // 키가 작은 남자와 춤추고 싶은 여자
				bGirls.add(num * -1);
			}
			else {
				aGirls.add(num);
			}
		}
		//input  done
		
		Collections.sort(aGirls);
		Collections.sort(aBoys);
		Collections.sort(bGirls);
		Collections.sort(bBoys);
		
		int cnt = 0;
		int gIdx = 0, bIdx = 0;
		//g < b 만족하는 경우 쌍만들기
		while(gIdx < aGirls.size() && bIdx < aBoys.size()) {
			if(aGirls.get(gIdx) < aBoys.get(bIdx)) {
				cnt++;
				gIdx++;
				bIdx++;
			}
			else {
				bIdx++;
			}
		}
		
		gIdx = 0; bIdx = 0;
		//b < g 만족하는 경우 쌍만들기
		while(gIdx < bGirls.size() && bIdx < bBoys.size()) {
			if(bBoys.get(bIdx) < bGirls.get(gIdx)) {
				cnt++;
				gIdx++;
				bIdx++;
			}
			else {
				gIdx++;
			}
		}
		
		System.out.println(cnt);
	}
}
