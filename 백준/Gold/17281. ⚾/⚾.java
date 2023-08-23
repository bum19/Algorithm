//
import java.io.*;
import java.util.*;

public class Main {
	public static int n, maxScore; // 이닝수, 최대 점수
	public static int[][] ining; // 각 이닝별 0~8번타자의 기록
	public static int[] seq; // 타순.
	public static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		n = Integer.parseInt(br.readLine().trim());
		ining = new int[n][9]; // 입력 기록
		seq = new int[9]; // 현재 타순.
		isSelected = new boolean[9];
		maxScore = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				ining[i][j] = Integer.parseInt(st.nextToken()); // i이닝때 j타자의 결과.
			}
		 }
		
		seq[3] = 0;
		
		go(0);
		
		System.out.println(maxScore);
	}

	private static void go(int depth) {
		if(depth == 9) {
			playGame();
			return;
		}
		
		for(int i = 1; i < 9; i++) {
			if(!isSelected[i]) {
				isSelected[i] = true;
				seq[depth] = i;
				if(depth == 2) go(depth+2); //depth == 3일때는 정해져있으므로 탐색하지 않는다.
				else		   go(depth+1);
				isSelected[i] = false;
			}
		}
		
	}

	private static void playGame() {
		int score = 0, outCnt = 0, iningNum = 0, ground = 0, groundCnt = 0, tmpGround = 0, tmpCnt = 0;
		// 순서대로 타자들 넣기.
		int idx = 0;
		while (true) { // outCnt가 27이되면, 게임 끝.
			if (outCnt == 3) { // outCnt이 3의배수가될때마다 이닝 변경.
				outCnt = 0;
				ground = 0;
				iningNum++;
			}
			if (iningNum == n)
				break;

			int cur = seq[idx];
//			System.out.println("이닝 : "+iningNum+", 현재타자 : "+cur +" 예상타율: "+ining[iningNum][cur]+", 아웃:"+outCnt+", score :"+score +", ground : "+ground);	
			// 0이면 아웃카운트 증가하고 그라운드에 아무것도안함.
			if (ining[iningNum][cur] == 0) {
				outCnt++; // 0이면 그라운드에 추가 안하고 아웃카운트만 증가.
			} else {// 0이 아니면
				tmpGround = (ground << ining[iningNum][cur]) & 7; // 전칸 타자들 현재 타수가 친 만큼 이동.
//				System.out.println("그라운드 변동. 움직인그라운드(아직 방금 친 타자는 안들옴):"+tmpGround);

				// 홈에 도착한사람 계산
				tmpCnt = groundCnt = 0; // tmpCnt는 tmp(2)의 1개수, groundCnt는 ground(2)의 1개수.
				for (int i = 0; i < 3; i++) {
					if ((ground & (1 << i)) == (1 << i))
						groundCnt++;
					if ((tmpGround & (1 << i)) == (1 << i))
						tmpCnt++;
				}
//				System.out.println("groundCnt : "+groundCnt+", tmpCnt : "+tmpCnt);
//				if(seq[0] == 3 && seq[1] == 2 && seq[2] == 1) {
//					System.out.println("ground : "+ground+", tmpGround : "+tmpGround+", groundCnt : "+groundCnt+", tmpCnt : "+ tmpCnt);
//				System.out.println("차이만큼 더할게요 score "+(groundCnt-tmpCnt)+"만큼증가");
//				}
				score += groundCnt - tmpCnt; // 사라진 1개수만큼 더하기.
				ground = tmpGround;

				if (ining[iningNum][cur] == 4) {
//					if(seq[0] == 3 && seq[1] == 2 && seq[2] == 1)
//					System.out.println("홈런. 스코어 1더할게요");
					score++;
				}
				else
					ground = ground | 1 << ining[iningNum][cur] - 1;
			}
			idx = (idx+1)%9;
		}

		if (maxScore < score)
			maxScore = score;
//		System.out.println("score : "+score+","+Arrays.toString(seq));
	}
}
