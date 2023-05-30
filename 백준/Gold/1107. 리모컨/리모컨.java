//타겟에서 모든버튼누를수있는 수까지 -- or ++
import java.util.*;
public class Main {
    public static boolean[] isNumBanned = new boolean[10];
    public static int numOfDigit(int num){
        int cnt = 0;
        if(num == 0) return 1;
        else {
            while (num != 0) {
                num /= 10;
                cnt++;
            }
            return cnt;
        }
    }
    public static boolean pass(int num){
        if(num == 0){
            if(isNumBanned[0]== true) return false;
            else return true;
        }
        while(num>0){
            int tmp = num % 10;
            if(isNumBanned[tmp] == true) return false;
            num /= 10;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N;
        int M;
        int result;
        N = sc.nextInt();
        M = sc.nextInt();
        for(int i =0 ; i<M; i++){
            int tmp = sc.nextInt();
            isNumBanned[tmp] = true;
        }

        //모든 넘버가 버튼으로 누를수있는 숫자에 도달
        if(M==10){
            result = Math.abs(N-100);
        }
        else {
            int plus_case;
            int minus_case;
            int push_num;
            int made_num;
            //더해서 모든넘버가 버튼으로 누를수있는 숫자(made_num)로 도달
            made_num = N;
            push_num = 0;
            while (true) {
                //버튼 0만살아있을경우 도달불가
                if (M == 9 && isNumBanned[0] == false) {
                    push_num = 500001;
                    break;
                }
                if (pass(made_num)) break;
                push_num++;
                made_num++;
            }
            plus_case = numOfDigit(made_num) + push_num;

            //빼서 모든넘버가 버튼으로 누를수있는 숫자(made_num)로 도달
            made_num = N;
            push_num = 0;
            while (true) {
                if (made_num < 0) {
                    push_num = 500001;
                    break;
                }
                if (pass(made_num)) break;
                push_num++;
                made_num--;
            }
            minus_case = numOfDigit(made_num) + push_num;
            result = (plus_case < minus_case) ? plus_case : minus_case;
        }
            //100에서 순수 더하기빼기한것과 비교
            if(result > Math.abs(N-100))
                result = Math.abs(N-100);

            System.out.println(result);
    }
}
