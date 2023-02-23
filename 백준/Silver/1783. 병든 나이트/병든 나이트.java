import java.util.Scanner;

//6가지로나눔
// 세로 1
// 세로 2 , 가로<=7 / 가로 >7
// 세로 3이상, 가로 <=4 / 4< 가로 < 7,  7<=가로
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N,M;
        N = sc.nextInt();
        M = sc.nextInt();
        int answer;


        if(N == 1){
            answer = 1;
        }
        else if(N == 2){
            if(M <= 7) answer = (M+1)/2;
            else       answer = 4;
        }
        else{
            if(M <=4) answer = M;
            else if( M < 7) answer = 4;
            else answer = 5+(M-7);
        }

        System.out.println(answer);
    }
}
