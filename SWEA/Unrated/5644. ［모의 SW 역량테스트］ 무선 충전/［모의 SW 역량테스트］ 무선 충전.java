//
import java.io.*;
import java.util.*;

public class Solution {
	public static int t, m, a;
	public static int[] aMove;
	public static int[] bMove;
	public static int[][] bc; // [0][0]~[0][3] 각각 x좌표, y좌표, 충전범위, 성능
	public static boolean[] isBcExistOnA; // 해당 충전기가 존재하는지
	public static boolean[] isBcExistOnB; // 해당 충전기가 존재하는지
	public static int[] dy = { 0, -1, 0, 1, 0 }; // 정지 상우하좌
	public static int[] dx = { 0, 0, 1, 0, -1 }; // 정지 상우하좌

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		t = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= t; test_case++) {
			// 입력시작
			st = new StringTokenizer(br.readLine()); //
			m = Integer.parseInt(st.nextToken()); //
			a = Integer.parseInt(st.nextToken());
			aMove = new int[m+1]; //0~m까지 탐색
			bMove = new int[m+1]; //0~m까지 탐색
			bc = new int[a][4];
			isBcExistOnA = new boolean[a];
			isBcExistOnB = new boolean[a];

			//a이동 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { //0초엔 가만히있기
				aMove[i] = Integer.parseInt(st.nextToken());
			}
			
			//b이동 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= m; i++) { //0초엔 가만히있기
				bMove[i] = Integer.parseInt(st.nextToken());
			}

			//충전기 입력
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				bc[i][0] = Integer.parseInt(st.nextToken());
				bc[i][1] = Integer.parseInt(st.nextToken());
				bc[i][2] = Integer.parseInt(st.nextToken());
				bc[i][3] = Integer.parseInt(st.nextToken());
			}

			// bc 성능 큰 순으로 정렬.
			Arrays.sort(bc, (a1, a2) -> {
				return (a1[3] - a2[3]) * -1;
			});
//			System.out.println(Arrays.deepToString(bc));
			// 입력끝
			
			//구현. 각 칸마다 충전값 구하기
			int ax = 1, ay = 1, bx = 10, by = 10, sum = 0 ;
			for (int i = 0; i <= m; i++) { //처음상태부터 총 m+1 탐색
				//이동
				ax = ax + dx[aMove[i]];
				ay = ay + dy[aMove[i]];
				bx = bx + dx[bMove[i]];
				by = by + dy[bMove[i]];
//				System.out.println("(ax, ay): ("+ax+", "+ay+"), (bx, by): ("+bx+", "+by+")");
				//충전기 있는지 확인
				boolean isChargableA = false;
				boolean isChargableB = false;
				for(int j = 0; j < a; j++) {
					if( Math.abs(ax- bc[j][0]) + Math.abs(ay - bc[j][1]) <= bc[j][2]) {
						isChargableA = true;
						isBcExistOnA[j] = true; 
					}
					if( Math.abs(bx- bc[j][0]) + Math.abs(by - bc[j][1]) <= bc[j][2]) {
						isChargableB = true;
						isBcExistOnB[j] = true;
					}
				}
				//a혼자 충전기 2개먹을때.
				//충전기 상위 2개까지 더함.
				//충전기 1개일경우 그냥 그것만더함
				//충전개 2개일경우 2개까지더함.
				//a가 2개, b가 1개일경우. 가장큰거겹치면 하나더하고, 그다음큰거가진사람꺼 더하기.
				int currentChargenum = 0;
				for(int j = 0; j < a; j++) {
					if(currentChargenum ==2) break;
					//둘이 충전기 겹치면 일단 하나만 더함.
					if(isBcExistOnA[j] && isBcExistOnB[j]) {
						sum += bc[j][3];
						currentChargenum++;
					}
					else if (isBcExistOnA[j] && isChargableA) {
						sum += bc[j][3];
						isChargableA = false;
						currentChargenum++;
					}
					else if (isBcExistOnB[j] && isChargableB) {
						sum += bc[j][3];
						isChargableB = false;
						currentChargenum++;
					}
				}
//				System.out.println(i+" : "+sum+", "+Arrays.toString(isBcExist));
				//충전기 존재여부 초기화
				Arrays.fill(isBcExistOnA, false);
				Arrays.fill(isBcExistOnB, false);
			}
			//출력저장.
			sb.append("#").append(test_case).append(" ").append(sum).append("\n");
		}
		
		System.out.println(sb);
	}

}
