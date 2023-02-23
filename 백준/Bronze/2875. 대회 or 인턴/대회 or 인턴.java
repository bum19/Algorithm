import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N, M, K;
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        int output = Math.min(Math.min((N + M - K) / 3, N / 2), M);
        System.out.println(output);
    }
}