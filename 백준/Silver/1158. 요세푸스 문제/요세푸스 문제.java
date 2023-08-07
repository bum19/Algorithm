import java.util.*;

public class Main {
	static int n,k;
	static List<Integer> cq = new LinkedList<Integer>();
	static int[] output;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder("<");
		n = sc.nextInt();
		k = sc.nextInt();
		output = new int[n];
		
		for(int i = 1; i <= n; i++) cq.add(i);
		int start = k-1;
		int idx = 0;
		for(int i = 0; i < n; i++) {
			output[i] = cq.remove(start);
			if(!cq.isEmpty())
				start = (start-1+k)%cq.size();
		}
		
		for(int i = 0; i < n; i++) {
			sb.append(output[i]);
			if(i != n-1) sb.append(", ");
		}
		sb.append(">");
		
		System.out.println(sb);
		
	}
}

