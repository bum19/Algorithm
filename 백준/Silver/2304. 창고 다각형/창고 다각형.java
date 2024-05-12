/*
 * 가장 높은 기둥을 찾는다.
 * 해당 기둥부터 왼쪽/오른쪽으로 가장 높은기둥 찾아가면서 더하기.
 */
import java.io.*;
import java.util.*;
public class Main{
	public static int n;
	public static int[][] sticks;
	public static int minSpace;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		sticks = new int[n][2];
		int maxHeight = -1;
		int maxHeightIdx = -1;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sticks[i][0] = Integer.parseInt(st.nextToken()); //위치
			sticks[i][1] = Integer.parseInt(st.nextToken()); //거리
		}
		
		Arrays.sort(sticks, (a1,a2) -> {
			return Integer.compare(a1[0],a2[0]); //위치 작은순으로 정렬
		});
		
		for(int i = 0; i < n; i++) {
			if(maxHeight < sticks[i][1]) {
				maxHeight = sticks[i][1];
				maxHeightIdx = i;
			}
		}
		
		recur(maxHeightIdx, 'l');
		recur(maxHeightIdx, 'r');
		minSpace -= maxHeight; //두번 호출로 인해 두번 더했으므로 한번 빼기.
		System.out.println(minSpace);
	}
	
	//현재 매개변수로 입력받은 위치의 막대길이를 더하고, 왼쪽 막대기와 현재 막대기 사이의 거리를 구하고, 현재막대기와 오른쪽 막대기 사이의 거리를 구함.
	private static void recur(int idx, char dir) {
		
		//현재 막대너비 더하기
		minSpace += sticks[idx][1];
		
		int nextIdx = -1;
		int nextMaxHeight = -1;
		
		if(dir == 'l') {
			//왼쪽 최장막대기 구하기
			for(int i = idx-1; i >= 0; i--) {
				if(nextMaxHeight < sticks[i][1]) {
					nextMaxHeight = sticks[i][1];
					nextIdx = i;
				}
			}
			//두 막대 사이의 면적 구하기.
			if(nextIdx >= 0) {//왼쪽막대가 존재할경우에만
				minSpace += (sticks[idx][0] - sticks[nextIdx][0] -1 ) * sticks[nextIdx][1];
				//왼쪽 최장막대기 호출
				recur(nextIdx, dir);
			}
		
		}
		else {
			//오른쪽 최장막대기 구하기
			for(int i = idx+1; i < n; i++) {
				if(nextMaxHeight < sticks[i][1]) {
					nextMaxHeight = sticks[i][1];
					nextIdx = i;
				}
			}
			//두 막대사이의 면적구하기
			if(nextIdx >= 0) {
				minSpace += (sticks[nextIdx][0] - sticks[idx][0] -1) * sticks[nextIdx][1];
				//오른쪽 최장막대기 호출
				recur(nextIdx, dir);
			}
		}

	}
}
