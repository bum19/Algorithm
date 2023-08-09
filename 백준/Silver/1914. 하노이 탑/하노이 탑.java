import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BigInteger bi = BigInteger.valueOf(2);
        bi =  bi.pow(n);
        bi = bi.subtract(BigInteger.ONE);
        sb.append(bi).append("\n");

        if(n <= 20){
            hanoi(n,1,2,3);
        }

        System.out.println(sb);
    }

    public static void hanoi(int n, int from, int temp, int to){
        if(n==0)
            return;

        hanoi(n-1,from,to,temp);
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(n-1,temp,from,to);
    }

}
