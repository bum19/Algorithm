import java.io.*;
import java.util.*;

public class Solution {
	public static int t, n, target, curDay;
	public static List<Integer> trees = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			n = Integer.parseInt(br.readLine().trim());
			target = 0;
			curDay = 0;
			trees.clear();
			int maxValue = -1;
			st = new StringTokenizer(br.readLine());
			// 입력받으면서 최대크기 나무 찾기
			for (int i = 0; i < n; i++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp > maxValue) { // 최댓값 찾기
					maxValue = tmp;
				}
				trees.add(tmp);
			}

			// 최대나무크기에서 차이만큼 만들기
			int size = trees.size();
			for (int i = trees.size() - 1; i >= 0; i--) {
				int tmp = maxValue - trees.get(i);
				if (tmp == 0)
					trees.remove(i); // 0이된친구는 물줄필요 없으므로 제거
				else
					trees.set(i, tmp); // 차이만큼 새롭게 값 갱신
			}

			// 물주기.
			giveWater();

			sb.append("#").append(test_case).append(" ").append(curDay).append("\n");
		}
		System.out.println(sb);
	}

	private static void giveWater() {
		while (trees.size() != 0) { // 물줄 나무가 남아있을때까지 반복
//			System.out.println("curday : "+curDay+", "+trees);
			curDay++;
			int minEven = 121, minEvenIdx = -1, minOdd = 121, minOddIdx = -1;

			if (trees.size() == 1) { // 하나 남았을 때
				if (curDay % 2 == 0 && trees.get(0) == 2) { // 2이면서 짝수날일때
					break;
				} else if (curDay % 2 == 1 && trees.get(0) == 2) { // 2이면서 홀수날일때
					curDay++;
					break;
				} else if (curDay % 2 == 0 && trees.get(0) == 1) { // 1이면서 짝수날일때
					curDay++;
					break;
				} else if (curDay % 2 == 1 && trees.get(0) == 1) { // 1이면서 홀수날일때
					break;
				} else { // 그 외 일때
					trees.set(0, trees.get(0) - 1 - (curDay + 1) % 2); // 짝수일때 1더빼기.
				}
			}
			else if (curDay % 2 == 0) { // 여러개 남았고, 짝수날일때

				// 제일작은 짝수, 제일작은 홀수 찾기.
				for (int i = 0; i < trees.size(); i++) {
					if (trees.get(i) % 2 == 0 && minEven > trees.get(i)) { // 현재값 짝수면서 최솟값일때
						minEven = trees.get(i);
						minEvenIdx = i;
					}
					if (trees.get(i) % 2 == 1 && minOdd > trees.get(i) && trees.get(i) > 1) { // 현재값 홀수면서 1보다 크고 최솟값일때
						minOdd = trees.get(i);
						minOddIdx = i;
					}
				}
				
				//제일작은짝수 있다면 해당 값 감소, 없다면 1보다큰 홀수 값 감소. 둘다 없다면 아무것도안함.
				if(minEvenIdx != -1) {
					minEven -= 2;
					if(minEven == 0) trees.remove(minEvenIdx);
					else			 trees.set(minEvenIdx, minEven);
				}
				else if(minOddIdx != -1) {
					minOdd -= 2;
					trees.set(minOddIdx, minOdd);
				}
				else continue;
			}
			else { // 여러개 남았고, 홀수날일때
				// 제일작은 짝수, 제일작은 홀수 찾기.
				for (int i = 0; i < trees.size(); i++) {
					if (trees.get(i) % 2 == 0 && minEven > trees.get(i)) { // 현재값 짝수면서 최솟값일때
						minEven = trees.get(i);
						minEvenIdx = i;
					}
					if (trees.get(i) % 2 == 1 && minOdd > trees.get(i)) { // 현재값 홀수면서 최솟값일때
						minOdd = trees.get(i);
						minOddIdx = i;
					}
				}
				
				//제일작은홀수 있다면 해당 값 감소, 없다면 짝수 값 감소. 둘다 없다면 아무것도안함.
				if(minOddIdx != -1) {
					minOdd -= 1;
					if(minOdd == 0) trees.remove(minOddIdx);
					else			 trees.set(minOddIdx, minOdd);
				}
				else if(minEvenIdx != -1) {
					minEven -= 1;
					trees.set(minEvenIdx, minEven);
				}
				else continue;
			}
		}
	}

}
