/*
 * 숫자 타고타고가다가, 이미 탐색한 숫자만났을때,
 * 시작한 숫자면 정답 set에 넣기
 */
import java.io.*;
import java.util.*;
public class Main {
	public static int[][] chart;
	public static int n;
	public static Set<Integer> answerSet;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine().trim());
		
		chart = new int[2][n];
		for(int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(br.readLine().trim());
			chart[0][i] = i+1;
			chart[1][i] = tmp;
		}//input done
		
		
		
		answerSet = new HashSet<>();
		for(int i = 0; i < n; i++) {
			int start = i+1;
			Map<Integer,Boolean> map = new HashMap<>();
			
			int idx = i;
			map.put(start, true);
			
			while(true) {
		
				int next = chart[1][idx];
				
				if(map.get(next) == null) {
					map.put(next, true);
					idx = next-1;
				}
				else {
					if(start == next) {
						for(Integer num : map.keySet()) {
							answerSet.add(num);
						}
					}
					break;
				}
			}
		}
		
		
		//정렬 및 정답 생성
		sb.append(answerSet.size()).append("\n");
		int[] answerArr = new int[answerSet.size()];
		
		int idx = 0;
		for(int num : answerSet) {
			answerArr[idx++] = num;
		}
		
		Arrays.sort(answerArr);
		
		for(int i = 0; i < answerArr.length; i++) {
			sb.append(answerArr[i]).append("\n");
		}
	
		System.out.println(sb);
	}
}
