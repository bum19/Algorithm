import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void changeSwitch(int[] switches, int idx) {
		if (switches[idx] == 0)
			switches[idx] = 1;
		else
			switches[idx] = 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int[] switches;
		int switchNum;
		int peopleNum;
		int gender;
		int switchIdx;
		switchNum = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		switches = new int[switchNum];
		for (int i = 0; i < switchNum; i++) {
			switches[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		peopleNum = Integer.parseInt(st.nextToken());

		for (int peopleIdx = 0; peopleIdx < peopleNum; peopleIdx++) {
			st = new StringTokenizer(br.readLine());
			gender = Integer.parseInt(st.nextToken());
			switchIdx = Integer.parseInt(st.nextToken());

			// 남자일경우
			if (gender == 1) {
				for (int i = switchIdx-1; i < switchNum; i += switchIdx) {
					changeSwitch(switches, i);
				}
			}
			// 여자일경우
			else {
				changeSwitch(switches, switchIdx-1);

				int i = 0;
				while (true) {
					if ((switchIdx-1 - i) < 0 || (switchIdx-1 + i) >= switchNum)
						break;
					if (switches[switchIdx-1 + i] == switches[switchIdx-1 - i]) {
						changeSwitch(switches,switchIdx-1 + i);
						changeSwitch(switches,switchIdx-1 - i);
					} else
						break;
					i++;
				}
			}
		}
		for(int i = 0 ; i < switchNum; i++) {
			if( i != 0 && i%20 == 0) sb.append("\n");
			sb.append(switches[i]).append(" ");
//			if((i != (switchNum-1)) && (i%20 != 0))
//				sb.append(" ");
		}
		
		System.out.println(sb);
		
	}

}
