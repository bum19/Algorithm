import java.util.*;
import java.io.*;


public class Main
{
    public static int n,m;
    public static int[] output;
    public static StringBuffer sb = new StringBuffer();
    public static void dfs(int start, int depth){
        if(depth == m){
            for(int out : output){
                sb.append(out).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i< n; i++){
            output[depth] = i+1;
            dfs(i+1,depth+1);
        }
    }
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        output = new int[m];
        dfs(0, 0);
        System.out.println(sb.toString());
    }
}