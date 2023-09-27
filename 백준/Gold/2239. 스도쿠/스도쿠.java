import java.io.*;
import java.util.*;

public class Main {
    private static List<int[]> zeroList; //0으로되어있는 곳 좌표들 리스트
    private static int[][] room; //스도쿠 판
    private static boolean isFound; //스도쿠 다 채웠는지 여부
    private static boolean[] isNumExist = new boolean[10]; //스도쿠 검사로직에 필요한 숫자 중복체크용 리스트
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        room = new int[9][9];
        zeroList = new ArrayList<int[]>();
        for(int i = 0; i < 9; i++) {
            String line = br.readLine().trim();
            for (int j = 0; j < 9; j++) {
                room[i][j] = line.charAt(j) - '0';
                if (room[i][j] == 0) zeroList.add(new int[]{i, j});
            }
        }

        make(0, ""); // 스도쿠 채우는 함수;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                sb.append(room[i][j]);
            }
            sb.append("\n");
        }
//        System.out.println("결국 이거 호출하니? "+ isFound);
        System.out.println(sb);
    }

    private static void make(int idx, String str){
//        System.out.println("make "+idx+"시작");
        if(idx == zeroList.size()){
            isFound = true;
            return;
        }

        //현재 위치에 1~9 넣고, 진행하기.
        int cur[] = zeroList.get(idx);
        int y = cur[0];
        int x = cur[1];
        for(int i = 1 ; i <= 9; i++){
            if(isFound) return;// 스도쿠 완성했을경우 탐색 멈춤
            room[y][x] = i;
            if(check(y, x)){ //가능하면 넣고 다음거 탐색진행
//            	System.out.println(i+"삽입");
                make(idx+1, str+i);
            }
            else {
//            	System.out.println(idx+"번째에 "+i+"넣을 때 실패 : ("+y+", "+x+") "+str + "\""+i+"\"");
//            	if(str.equals("4628757368") && i== 9) {
//            		for(int a = 0; a < 9; a++) {
//            			for(int b = 0; b < 9; b++) {
//            				System.out.print(room[a][b]);
//            			}
//            			System.out.println();
//            		}
//            	}
            	
            }
            if(!isFound) room[y][x] = 0;
        }
        
//        System.out.println("make "+idx+"끝");
    }

    private static boolean check(int y, int x){
        //가로검사
        Arrays.fill(isNumExist, false); //0 인덱스 안쓰고, 1~9있으면 true
        for(int i = 0; i < 9; i++){
            if(room[y][i] != 0 && isNumExist[room[y][i]]){ //현재 검사중인 칸이 0이 아니고 이미 존재하는 숫자면 거짓 리턴
//                System.out.println("가로검사실패"); //0이 아니면서 존재해야 실패인데
                return false;
            }
            else{
                isNumExist[room[y][i]] = true;
            }
        }

        //세로검사
        Arrays.fill(isNumExist, false); //0 인덱스 안쓰고, 1~9있으면 true
        for(int i = 0; i < 9; i++){
            if(room[i][x] != 0 && isNumExist[ room[i][x] ] ){ //현재 검사중인 칸이 0이 아니고 이미 존재하는 숫자면 거짓 리턴
//                System.out.println("세로검사실패");
                return false;
            }
            else{
                isNumExist[ room[i][x] ]= true;
            }
        }

        //사각형 내에 있는지 검사.
        Arrays.fill(isNumExist, false); //0 인덱스 안쓰고, 1~9있으면 true
        y = y/3 * 3;
        x = x/3 * 3;
        for(int i = y; i < y+3; i++){
            for(int j = x; j < x+3; j++){
                if(room[i][j] != 0 && isNumExist[room[i][j]]){
//                    System.out.println("fail mini rectangle");
                    return false;
                }
                else{
                    isNumExist[room[i][j]] = true;
                }
            }
        }

        return true;
    }
}