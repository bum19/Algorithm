/*
 * 인덱스를 들고, 0인 인덱스와 1인 인덱스를 원소로하는 리스트 두개를 병합하는 방식으로 코딩
 */
import java.io.*;
import java.util.*;
public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		List<Integer> zeroIndice = new ArrayList<>();
		List<Integer> oneIndice = new ArrayList<>();
		
		int zeroCnt = 0;
		int oneCnt = 0;
		
		String input = br.readLine().trim();
		
		//0개수, 1개수 세기
		for(int i = 0; i < input.length(); i++) {
			if(input.charAt(i) == '0') zeroCnt++;
			else oneCnt++;
		}
		
		//0 반만큼 인덱스 세기. 앞에서 센다.
		int cnt = zeroCnt;
		for(int i = 0; i< input.length(); i++) {
			if(input.charAt(i) == '0') {
				zeroIndice.add(i);
				cnt--;
			}
			
			if(cnt == zeroCnt/2) break;
		}
		
		//1 반만큼 인덱스 세기. 뒤에서 센다.
		cnt = oneCnt;
		for(int i = input.length()-1; i >= 0; i--) {
			if(input.charAt(i) == '1') {
				oneIndice.add(i);
				cnt--;
			}
			if(cnt == oneCnt/2) break;
		}
		//1 리스트 거꾸로해주기
		oneIndice.sort(Collections.reverseOrder());
		
		//새로운 input 만들기
		StringBuilder sb = new StringBuilder();
		int zeroIdx = 0;
		int oneIdx = 0;
		while(true) {
			if(zeroIdx >= zeroIndice.size() || oneIdx >= oneIndice.size()) {
				break;
			}
			if(zeroIndice.get(zeroIdx) < oneIndice.get(oneIdx)) {
				sb.append(0);
				zeroIdx++;
			}
			else {
				sb.append(1);
				oneIdx++;
			}
		}
		
		//둘중 남은것 채워주기
		for(; zeroIdx < zeroIndice.size(); zeroIdx++) {
			sb.append(0);
		}
		for(; oneIdx < oneIndice.size(); oneIdx++) {
			sb.append(1);
		}
		
		//출력
		System.out.println(sb);
	}
}
