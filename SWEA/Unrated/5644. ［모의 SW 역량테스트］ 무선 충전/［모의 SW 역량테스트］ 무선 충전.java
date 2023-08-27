
import java.io.*;
import java.util.*;

public class Solution {
	public static int t, m, a, totalCharge;
	public static int[] aMove; // a움직임 기록.
	public static int[] bMove; // b움직임 기록
	public static int[][] bc; // 배터리정보. x좌표,y좌표,충전범위,성능
	public static boolean[] isBCOnA; // bc가 a위에있는지 체크
	public static boolean[] isBCOnB; // bc가 b위에있는지 체크
	public static int[] dy = { 0, -1, 0, 1, 0 }; // 정지, 상우하좌
	public static int[] dx = { 0, 0, 1, 0, -1 }; // 정지, 상우하좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());

			aMove = new int[m + 1];
			bMove = new int[m + 1];
			bc = new int[a][4];
			totalCharge = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { // a움직임 정보 입력
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { // b움직임 정보 입력
				bMove[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < a; i++) {// 충전기정보입력. x,y좌표,길이,성능
				st = new StringTokenizer(br.readLine());
				bc[i][0] = Integer.parseInt(st.nextToken());
				bc[i][1] = Integer.parseInt(st.nextToken());
				bc[i][2] = Integer.parseInt(st.nextToken());
				bc[i][3] = Integer.parseInt(st.nextToken());
			}

			// bc 성능 큰순으로 정렬
			Arrays.sort(bc, (bc1, bc2) -> {
				return Integer.compare(bc1[3], bc2[3]) * -1;
			});

			int[] a = { 1, 1 };
			int[] b = { 10, 10 };
			for (int i = 0; i <= m; i++) {
				a[0] += dx[aMove[i]];
				a[1] += dy[aMove[i]];

				b[0] += dx[bMove[i]];
				b[1] += dy[bMove[i]];
				charge(a[0], a[1], b[0], b[1]);
			}

			sb.append("#").append(test_case).append(" ").append(totalCharge).append("\n");
		}
		System.out.print(sb);
	}

	private static void charge(int ax, int ay, int bx, int by) {
		boolean isAChargable = false, isBChargable = false; //충전기위에 있는지 체크.
		isBCOnA = new boolean[a];
		isBCOnB = new boolean[a];

		// 충전기 위에있는지 확인
		for (int i = 0; i < a; i++) {
			if (Math.abs(bc[i][0] - ax) + Math.abs(bc[i][1] - ay) <= bc[i][2]) {
				isBCOnA[i] = true;
				isAChargable = true;
			}
			if (Math.abs(bc[i][0] - bx) + Math.abs(bc[i][1] - by) <= bc[i][2]) {
				isBCOnB[i] = true;
				isBChargable = true;
			}
		}
		// 충전. 둘이 겹치면 a에 넣기. 2개 모이면 중간에끝내기
		int chargeCnt = 1;
		if(isAChargable && isBChargable) chargeCnt = 2; //둘다 충전기위에있으면 최대 2개까지 충전가능
		else 							 chargeCnt = 1; //그렇지않다면 최대 1개까지 충전가능
		for (int i = 0; i < a; i++) {
			if(chargeCnt == 0) break;
			if(isBCOnA[i] && isBCOnB[i] && isAChargable && isBChargable) { //큰거부터 둘이 같이 위에있으면 무조건 더함.
				totalCharge += bc[i][3];
				chargeCnt--;
			}
			else if(isBCOnA[i] && isAChargable) { //a위에있고 a충전가능하면 더함. 위에꺼는 b꺼라 치면됨.
				totalCharge += bc[i][3];
				isAChargable = false; 	//확실하게 a충전했으므로 이제 충전 못함.
				chargeCnt--;
			}
			else if(isBCOnB[i] && isBChargable) { //b위에 있으면 더함. 위에더한건 a꺼라 치면됨.
				totalCharge += bc[i][3];
				isBChargable = false; //확실하게 b충전했으므로 이제 b 충전 못함
				chargeCnt--;
			}
			
		}
	}
}
