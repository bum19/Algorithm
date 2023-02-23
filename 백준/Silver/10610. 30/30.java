import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
//남의코드 복붙으로 출력초과 에러 해결되나 확인
// 30
// 문자열 문제
// 시간제한 : 1초
public class Main {
	public static void main(String args[]) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String nStr = br.readLine();
		int sum = 0;

		// 해당 숫자 재배치 시작
		char[] charArr = nStr.toCharArray();
		Arrays.sort(charArr);	// 오름차순 정렬
		int len = charArr.length;

		StringBuilder sb = new StringBuilder();
		// 오름차순 정렬이므로, 맨 끝 원소부터 반대로 탐색
		for(int i = len - 1; i >= 0; i--) {
			int num = charArr[i] - '0';
			sum += num;
			sb.append(num);
			
		}
				
		// 30의 배수인지 판단하기 위한 조건 생성
		if(charArr[0] != '0' || sum % 3 != 0) {	// 만약 3의 배수가 아니라면
			System.out.println(-1);
			return;
		}
		
		System.out.println(sb.toString());
	}
}