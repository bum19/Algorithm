import java.io.*;
import java.util.*;

public class Main {
    public static int n, b, c;
    public static long sum;
    public static int[] a;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine().trim());
        a = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i< n; i++){
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        sum += n;
        for(int i = 0; i < n; i ++){
            //메인감독관수만큼 빼고 남은만틈 더하기
            a[i] = a[i]-b;
            if(a[i] < 0) a[i] = 0;

            //부감독관 개수 구하기
            sum += a[i]/c  +  (a[i] % c != 0 ? 1:0);
        }
        System.out.println(sum);
    }
}