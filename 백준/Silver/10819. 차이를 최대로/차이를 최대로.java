import java.util.Scanner;

public class Main {
    public static int[] input;
    public static int[] arr;
    public static boolean[] visited;
    public static int n, maxValue;

    public static void dfs(int depth){
        if(depth == n){
            int tmp = 0;
            for(int i =1; i< n; i++){
                tmp += Math.abs(arr[i] - arr[i-1]);
            }
            maxValue = Math.max(maxValue,tmp);
            return;
        }

        for(int i =0 ; i< n; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[depth] = input[i];
                dfs(depth+1);
                visited[i] =  false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        input = new int[n];
        arr = new int[n];
        visited = new boolean[n];
        for(int i =0 ;i<n;i++){
            input[i] = sc.nextInt();
        }

        dfs(0);

        System.out.println(maxValue);
    }
}
