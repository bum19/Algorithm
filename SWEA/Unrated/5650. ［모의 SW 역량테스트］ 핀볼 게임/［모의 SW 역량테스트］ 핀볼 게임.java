import java.io.*;
import java.util.*;
//선명이코드
public class Solution {
    static int N, map[][], start[], dir, row, col, score = 0, max;
    static boolean game;
    static List<Integer> wormHole[];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int test = 1; test <= T; test++) {
            max = 0;
            N = Integer.parseInt(br.readLine().trim());	//map의 크기
            map = new int[N][N];
            wormHole = new ArrayList[11];	// 같은 숫자의 두 웜홀의 {row1, col1, row2, col2}로 값을 가지는 List 

            for(int i = 0; i < N; i++) {	// map에 입력 값을 저장
                String str = br.readLine();
                StringTokenizer stk = new StringTokenizer(str);
                for(int j = 0; j < N; j++) {
                    int num = Integer.parseInt(stk.nextToken());
                    map[i][j] = num;
                    if(num > 5) {	// 웜홀(5~10)일때
                        if(wormHole[num] == null) wormHole[num] = new ArrayList<Integer>(); //해당번호의 웜홀이 선언되지 않았다면 선언하고
                        wormHole[num].add(i);	// num번 웜홀의 row값
                        wormHole[num].add(j);	// num번 웜홀의 col값 저장
                    }
                }
            }

            for(int i = 0; i < N; i ++) {	// map의 전체위치 탐색
                for(int j = 0; j < N; j++) {
                    if(map[i][j] != 0) continue;	// map의 빈칸(0)이 아니라면 continue
                    start = new int[] {i, j};	// 시작위치 저장
 
                    for(int d = 0; d < 4; d++) {	// 상하좌우 방향으로 실행
                                           row = i; col = j;	// 공의 시작위치 지정
                        score = 0;	//시작점수 초기화
                        dir = d;	//시작 방향 설정
                        game = true;	//게임 시작 flag
                        while(game) {
                            move();	//공을 움직이고
                            check();	//유효한 곳인지 체크를 반복
                        }
                        if(score > max) max = score;	//한위치의 한방향에서 게임이 끝났을 때 최고점수 갱신
                    }
                }
            }
            System.out.println("#"+test+" "+max);
        }

    }

    public static void move() {	//공을 움직이는 메서드
        switch (dir) {
        case 0:    // 공이 위로 갈때
            if(row > 0) row--;	//위로 갈 수 있으면 위로 감
            else {	//위로 갈 수 없다면(벽에 부딪힌다면)
                score++;	//점수+1
                dir = 1;	//아래로 방향 전환
            }
            break;
        case 1: // 공이 아래로 갈때
            if(row < N-1) row++;	//아래로 갈 수 있으면 아래로 감
            else {	//아래로 갈 수 없다면
                score++;	//점수+1
                dir = 0;	//위로 방향 전환
            }
            break;
        case 2:    // 공이 왼쪽으로 갈때
            if(col > 0) col--;	//왼쪽으로 갈수 있으면 왼쪽으로 감
            else {	//왼쪽으로 갈 수 없다면
                score++;	//점수+1
                dir = 3;	//오른쪽으로 방향 전환
            }
            break;
        case 3: // 공이 오른쪽으로 갈때 갈때
            if(col < N-1) col++;	//오른쪽으로 갈 수 있다면 오른쪽으로 감
            else {	//오른쪽으로 갈 수 없다면
                score++;	//점수+1
                dir = 2;	//왼쪽으로 방향전환
            }
            break;
        default:
            System.out.println("유효하지 않은 방향");
            break;
        }
    }

    public static void jump(int num) {    // 웜홀을 통해 순간이동, 5 <= num <= 10
        int r0 = wormHole[num].get(0);	//num번 첫번째 웜홀의 row
        int c0 = wormHole[num].get(1);	//num번 첫번째 웜홀의 col
        int r1 = wormHole[num].get(2);	//num번 두번째 웜홀의 row
        int c1 = wormHole[num].get(3);	//num번 두번째 웜홀의 col

        if(row == r0 && col == c0) {	//현재위치가 첫번쨰 웜홀이라면
            row = r1; col = c1;	// 두번쨰 웜홀로 순간이동
        }
        else if(row == r1 && col == c1) {	//현재위치가 두번쨰 웜홀이라면
            row = r0; col = c0;	//첫번째 웜홀로 순간이동
        }
        else System.out.println("유효하지 않은 index");
    }

    public static void check() {	//해당위치가 어떤곳인지 판단하고 그에 알맞은 실행을 하는 메서드
        switch(map[row][col]) {	//현재위치의 숫자를 key로 하는 switch문
        case -1:    // 블랙홀일 때 게임 종료
            game = false;
            return;
        case 0:        // 빈칸일 때 시작지점이었다면 종료
            if(row == start[0] && col == start[1]) {
                game = false;
                return;
            }
            break;
        case 1:	// 1번 블록
            if(dir == 0) dir = 1;            // 아래에서 왔다면
            else if (dir == 1) dir = 3;        // 위에서 왔다면
            else if (dir == 2) dir = 0;        // 오른쪽에서 왔다면
            else if (dir == 3) dir = 2;        // 왼쪽에서 왔다면
            score++;	// 블록에 부딪혔으므로 점수+1
            break;
        case 2:	// 2번 블록
            if(dir == 0) dir = 3;            // 아래에서 왔다면
            else if (dir == 1) dir = 0;        // 위에서 왔다면
            else if (dir == 2) dir = 1;        // 오른쪽에서 왔다면
            else if (dir == 3) dir = 2;        // 왼쪽에서 왔다면
            score++;	// 블록에 부딪혔으므로 점수+1
            break;
        case 3:	// 3번 블록
            if(dir == 0) dir = 2;            // 아래에서 왔다면
            else if (dir == 1) dir = 0;        // 위에서 왔다면
            else if (dir == 2) dir = 3;        // 오른쪽에서 왔다면
            else if (dir == 3) dir = 1;        // 왼쪽에서 왔다면
            score++;	// 블록에 부딪혔으므로 점수+1
            break;
        case 4:	// 4번 블록
            if(dir == 0) dir = 1;            // 아래에서 왔다면
            else if (dir == 1) dir = 2;        // 위에서 왔다면
            else if (dir == 2) dir = 3;        // 오른쪽에서 왔다면
            else if (dir == 3) dir = 0;        // 왼쪽에서 왔다면
            score++;	// 블록에 부딪혔으므로 점수+1
            break;
        case 5:	// 5번 블록
            if(dir == 0) dir = 1;            // 아래에서 왔다면
            else if (dir == 1) dir = 0;        // 위에서 왔다면
            else if (dir == 2) dir = 3;        // 오른쪽에서 왔다면
            else if (dir == 3) dir = 2;        // 왼쪽에서 왔다면
            score++;	// 블록에 부딪혔으므로 점수+1
            break;
        case 6:    // 웜홀일때
        case 7:
        case 8:
        case 9:
        case 10:
            jump(map[row][col]);
            break;
        default:
            System.out.println("유효하지 않은 index");
            break;
        }
    }
}