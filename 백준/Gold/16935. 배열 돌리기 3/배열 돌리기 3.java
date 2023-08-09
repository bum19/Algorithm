//메모리 :
//실행시간 :
import java.util.*;
import java.io.*;
public class Main{
	
	static int n,m,r; //배열크기nxm, 연산횟수 r
	static int[][] arr; //입력 배열
	static int[] rs; //연산 저장 배열
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		//입력
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		arr = new int[n][m];
		rs = new int[r];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < r; i++) {
			rs[i] = Integer.parseInt(st.nextToken());
		}
		//입력 끝
		
		//돌리기 연산 r회 실행
		for(int i = 0; i < r; i++) {
			change(rs[i]);
		}
		
		//출력
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		//출력 끝
	}

	//연산종류1~6을 인자로 받아 배열을 바꾸는 연산 실행.
	static void change(int kind) {
		int tmp;
		
		//1번연산. 상하반전
		if(kind == 1) {
			//x는 같고, y와 n-1-y 인덱스의 값을 바꾼다.
			for(int y = 0; y < n/2; y++) {
				for(int x = 0; x < m; x++) {
					tmp = arr[y][x];
					arr[y][x] = arr[n -1 -y][x];
					arr[n - 1 - y][x] = tmp;
				}
			}
			return;
		}
		
		//2번연산. 좌우반전
		if(kind == 2) {
			//y는 같고, x와 m-1-x 인덱스의 값을 바꾼다
			for(int x = 0; x < m/2; x++) {
				for(int y = 0; y < n; y++) {
					tmp = arr[y][x];
					arr[y][x] = arr[y][m-1-x];
					arr[y][m-1-x] = tmp;
				}
			}
			
			return;
		}
		
		//3번연산. 90도 오른쪽 회전
		if(kind == 3) {
			//삼각함수 회전 행렬을 사용해서 변환한다.
			//x` = cosΘ*x - sinΘ*y
			//y` = sinΘ*x + cosΘ*y;
			//변환 방식 : x` = n-1-y, y` = x
			//회전한 배열을 저장하기 위해 새롭게 배열선언
			int[][] tmpArr = new int[m][n]; 
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < m; x++) {
					tmpArr[x][n-1-y] = arr[y][x];
				}
			}
			arr = tmpArr;
			//헷갈리지 않게 n과 m의 값을 바꿔준다.
			tmp = n;
			n = m;
			m = tmp;
			return;
		}

		//4번연산. 90도 왼쪽회전
		//3번과 동일한 로직 사용.
		if(kind == 4) {
			//x` = y, y` = m - 1 - x
			//회전한 배열을 저장하기 위해 새롭게 배열선언
			int[][] tmpArr = new int[m][n]; 
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < m; x++) {
					tmpArr[m-1-x][y] = arr[y][x];
				}
			}
			arr = tmpArr;
			//헷갈리지 않게 n과 m의 값을 바꿔준다.
			tmp = n;
			n = m;
			m = tmp;
			
			return;
		}
		
		//5번연산
		if(kind == 5) {
			//로직구현
			int[][] tmpArr = new int[n/2][m/2];
			//1번그룹 기억
			for(int i = 0 ; i < n/2; i++) {
				for(int j = 0; j < m/2; j++) {
					tmpArr[i][j] = arr[i][j];
				}
			}
			//4->1 이동
			for(int i = n/2; i < n; i++) {
				for(int j = 0; j < m/2; j++) {
					arr[i-n/2][j] = arr[i][j];
				}
			}
			//3->4 이동
			for(int i = n/2; i < n; i++) {
				for(int j = m/2; j < m; j++) {
					arr[i][j-m/2] = arr[i][j];
				}
			}
			//2->3 이동
			for(int i = 0; i < n/2; i++) {
				for(int j = m/2; j < m; j++) {
					arr[i+n/2][j] = arr[i][j];
				}
			}
			//1->2 이동
			for(int i = 0; i < n/2; i++) {
				for(int j = m/2; j < m; j++) {
					arr[i][j] = tmpArr[i][j-m/2]; 
				}
			}
			return;
		}
		
		//6번연산. 5번연산과 동일
		if(kind == 6) {
			//로직구현
			int[][] tmpArr = new int[n/2][m/2];
			//1번그룹 기억
			for(int i = 0 ; i < n/2; i++) {
				for(int j = 0; j < m/2; j++) {
					tmpArr[i][j] = arr[i][j];
				}
			}
			//2->1 이동
			for(int i = 0; i < n/2; i++) {
				for(int j = m/2; j < m; j++) {
					arr[i][j-m/2] = arr[i][j];
				}
			}
			//3->2 이동
			for(int i = n/2; i < n; i++) {
				for(int j = m/2; j < m; j++) {
					arr[i-n/2][j] = arr[i][j];
				}
			}
			//4->3 이동
			for(int i = n/2; i < n; i++) {
				for(int j = 0; j < m/2; j++) {
					arr[i][j+m/2] = arr[i][j];
				}
			}
			//1->4 이동
			for(int i = n/2; i < n; i++) {
				for(int j = 0; j < m/2; j++) {
					arr[i][j] = tmpArr[i-n/2][j];
				}
			}
			return;
		}
				
	}
	
}
