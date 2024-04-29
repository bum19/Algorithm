import java.io.*;
import java.util.*;
/*
 * 기존 나의 방식으로는 왜 안풀리는지는 모름. 반례를 찾는데 실패함.
 * 정석으로 답보고 중위순회해보자.
 */
public class Main {
	public static int curCol;
	public static List<List<Integer>> levels; // level 별 curCol값.
	public static int[] parents; //root찾기위한값.
	
	public static int n;
	public static Node[] nodes;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine().trim());
		
		parents = new int[n+1];
		for(int i = 1; i <= n; i++) {
			parents[i] = i;
		}
		nodes = new Node[n+1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int cur = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			nodes[cur] = new Node(left,right);
			if(left != -1)
				parents[left] = cur;
			if(right != -1)
				parents[right] = cur;
		}
		
		
		//get root
		int root = -1;
		for(int i = 1; i <= n ; i++) {
			if(parents[i] == i) {
				root = i;
				break;
			}
		}
		
		//preOrder
		curCol = 1;
		levels = new ArrayList<>();
		levels.add(new ArrayList<>());
		preOrder(root, 1);
		
		//level별로 최대최소 찾기.
		int answerLevel = -1,answerDiff= Integer.MIN_VALUE;  
		for(int level = 1; level < levels.size(); level++) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < levels.get(level).size(); i++) {
				if(min > levels.get(level).get(i)) {
					min = levels.get(level).get(i);
				}
				if(max < levels.get(level).get(i)) {
					max = levels.get(level).get(i);
				}
			}
			
			if( (max - min + 1) > answerDiff) {
				answerDiff = max-min + 1;
				answerLevel = level;
			}
		}
		
		System.out.println(answerLevel + " " + answerDiff);
	}
	
	
	public static void preOrder(int node, int level) {
		if(levels.size() <= level) {
			levels.add(new ArrayList<>());
		}
		
		
		
		if(nodes[node].left != -1) {
			preOrder(nodes[node].left, level+1);
		}
		
		levels.get(level).add(curCol++);
		
		if(nodes[node].right != -1) {
			preOrder(nodes[node].right, level+1);
		}
		
	}
	
	public static class Node{
		int left, right;
		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}
}
