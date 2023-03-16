import java.util.Scanner;

public class Main {
    public static StringBuffer sb = new StringBuffer();
    public static void move(char dir, int length){
        for(int i =0; i< length ; i++){
            sb.append(dir);
        }
        return;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean downFlag = true;
        boolean rightFlag = true;

        int r,c;
        int minValue = 1000;
        int minR = -1;
        int minC = -1;

        r = sc.nextInt();
        c = sc.nextInt();
        int[][] board = new int[r][c];
        //입력받으면서 검정칸 최솟값찾기(홀수인덱스,홀수인덱스)
        for(int i =0 ; i< r; i++ ){
            for(int j = 0; j<c; j++){
                board[i][j] = sc.nextInt();
                if((i+j)%2 == 1) {
                    if (board[i][j] < minValue) {
                        minValue = board[i][j];
                        minR = i;
                        minC = j;
                    }
                }
            }
        }

        //세로(r) 홀, 가로(c) 홀/짝 인 경우 : 스네이크
        if(r%2 == 1){
            for(int y = 0 ; y < r; y++){
                if(y%2 == 0) move('R',c-1);
                else move('L',c-1);

                if(y != r-1) sb.append("D");
            }
        }
        //세로(r) 짝, 가로(c) 홀   인 경우 : 가로 한번쭉 쓸고 (dr ur rd) d , ld rd 반복하다 도착하면 d빼기
        else if(r%2 == 0 && c%2 == 1){
            for(int x = 0; x<c; x++){
                if(x %2 == 0) move( 'D',r-1);
                else move('U',r-1);

                if(x != c-1) move('R',1);
            }
        }
        //짝 짝
        else{
            for(int y = 0; y< r;y+=2){
                if(minR == y || minR == y+1){
                    downFlag = true;
                    rightFlag = false;
                    for(int x =0; x<c; x++){
                        if(minC != x){
                            if(downFlag) move('D',1);
                            else move('U',1);
                            downFlag = !downFlag;
                        }
                        if(x != c-1) move('R',1);
                    }
                }
                else{
                    if(rightFlag){
                        move('R',c-1);
                        move('D',1);
                        move('L',c-1);
                    }
                    else{
                        move('L',c-1);
                        move('D',1);
                        move('R',c-1);
                    }
                }
                if(y != r-2) sb.append("D");
            }
        }
        System.out.println(sb.toString());

    }
}
