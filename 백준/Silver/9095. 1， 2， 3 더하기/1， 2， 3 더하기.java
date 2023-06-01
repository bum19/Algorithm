import java.util.Scanner;

public class Main {
    public static int cnt;
    public static int T,n;
    public static int[] arr= new int[4];
    public static void sum(int num){
        if(num >= n){
            if(num == n){
                cnt++;
            }
            return;
        }

        for(int i = 1;i<=3;i++){
            sum(num+i);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            n = sc.nextInt();
            cnt = 0;
            sum(0);
            sb.append(cnt);
            if (i != T - 1)
                sb.append("\n");
        }

        System.out.println(sb);
    }
}
