import java.io.*;
import java.util.*;
//김진용식 풀이
public class Main {
	public static List<Integer>[] children;
	public static int n, eraseNum, answer;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		children = new List[n];
		for(int i = 0; i < n; i++) {
			children[i] = new ArrayList<Integer>();
		}
		
		st = new StringTokenizer(br.readLine());
		
		int root = -99;
		for(int i = 0 ; i <n ; i++) {
			int child = i;
			int parent = Integer.parseInt(st.nextToken());
			if(parent == -1) root = i;
			else			children[parent].add(child);
		}
		
		eraseNum = Integer.parseInt(br.readLine());
		erase(root, eraseNum);
		children[eraseNum].clear();
		
		//count leaf
		count(root);
		
		System.out.println(answer);
	}
	//eraseNum 자식들 제거
	public static void erase(int cur, int target) {
		for(int i = 0; i < children[cur].size(); i++) {
			if(children[cur].get(i)  == target) {
				children[cur].remove(i);
				break;
			}
			else {
				erase(children[cur].get(i), target);
			}
		}	
		return;	
	}
	
	//count
	public static void count(int num) {
		if(num != eraseNum && children[num].size() == 0) {
			answer++;
			return;
		}
		
		for(int i = 0; i < children[num].size(); i++) {
			count(children[num].get(i));
		}
	}
}
