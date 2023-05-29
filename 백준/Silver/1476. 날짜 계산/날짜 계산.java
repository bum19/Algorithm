import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] input = new int[3];
        int E=1,S=1,M=1;
        int year = 1;

        for(int i =0; i<3; i++){
            input[i] = sc.nextInt();
        }

        while(true){
            if(E>15) E = 1;
            if(S>28) S = 1;
            if(M>19) M = 1;

            if(E == input[0] && S == input[1] && M == input[2]) break;

            E++;S++;M++;year++;
        }
        System.out.println(year);
    }
}
