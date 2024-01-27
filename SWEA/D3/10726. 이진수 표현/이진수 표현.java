import java.util.Scanner;
import java.io.FileInputStream;


class Solution
{
	public static void main(String args[]) throws Exception
	{
		
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		StringBuilder sb = new StringBuilder();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int n = sc.nextInt();
            int m = sc.nextInt();
            if( (m & (1<<n)-1) == (1<<n)-1 ) sb.append("#").append(test_case).append(" ON\n");
            else sb.append("#").append(test_case).append(" OFF\n");
		}
        System.out.println(sb);
	}
}