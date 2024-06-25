/*
 * 0~A까지의 1의 개수를 구하는것은, A를 이진수로 나타냈을때, 1이 있는 자릿수들에 대해 (자릿수*2^(자릿수-1) + 1)*(현재 자릿수보다 앞에있는 1의 개수)를 전부 더한값
 * A~B사이 1의 개수 = 0~B까지의 1의개수에서 0~(A-1)의 1의 개수를 뺀 값.
 * 
 * 예를들어, 0부터 101100의 값을 가진 A까지의 1의 개수를 구해보자
 * A의 1의개수는 총 3개이므로, 각 자릿수는 3의자리,4의자리,6의자리이다.
 * 숫자 101000 ~ 101100까지 1의 개수는, 2(앞에 있는 1의개수) * (2^2+1)(000~100까지의 가능한 0과 1의 순열) +  2*2^(2-1)+1(000~100까지의 1의 개수, 자릿수 * (2^자릿수-1)+1)이다.
 * 숫자 100000 ~ 100111까지 1의 개수는, 1(앞에 있는 1의 개수) * (2^3)(000~111까지의 가능한 0과 1의 순열) +  3*2^(3-1)(000~111까지의 1의 개수)이다.
 * 숫자 000000 ~ 011111까지 1의 개수는, 0(앞에 있는 1의 개수) * (2^5)(000~11111까지의 가능한 0과 1의 순열) + 5*2^(5-1)(000~11111까지의 1의개수) 이다.
 * 이를 나타낸것이 countOneFromZero() 함수.
 * double형을 long형으로 해보자.
 * 나누거나 곱하는 과정을 비트연산으로해보니까 된다. /2나 %2, 혹은 power을 직접구현하거나 Math.pow를 안쓰니 됨.
 */
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		System.out.println(countOneFromZero(b) - countOneFromZero(a-1));

	}
	
	private static long countOneFromZero(long num) {
		//1개수 구하기
		int oneCnt = 0;
		long tmp = num;
		while(tmp > 0) {
			if((tmp & 1) == 1) oneCnt++;
			tmp = tmp >> 1;
		}
		
		int position = 0;
		long cnt = 0;
		tmp = num;
		while(tmp > 0) {
			if((tmp & 1) == 1) {
				if(cnt == 0) { //최초에만 1더한값들로 진행.
					cnt += position * ( 1L<< (position-1))+1;
					cnt += ((1L << position) + 1)* --oneCnt;
				}
				else{
					cnt += position * ( 1L<< (position-1));
					cnt += (1L << position)* --oneCnt;
				}

			}
			
			position++;
			tmp = tmp >> 1;
		}
		return cnt;
	}
}
