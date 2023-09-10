class Solution {
    public static int n, m, lockhole;
    public static int[][] mylock;
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        n = lock.length;
        m = key.length;
        lockhole = 0;
        //lock에 뚫린구멍개수 찾기.
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(lock[i][j] == 0) lockhole++;
            }
        }
        mylock = new int[n+(m-1)+(m-1)][n+(m-1)+(m-1)];
        
  
        
        //lock 값 mylock에 옮기기
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < n; j++){
                mylock[i+(m-1)][j+(m-1)] = lock[i][j]; 
            }
        }
        // //mylock출력
        // for(int i = 0; i < n+(m-1)+(m-1) ;i++){
        //     for(int j = 0; j< n+(m-1)+(m-1); j++){
        //         System.out.print(mylock[i][j]+" ");
        //     }
        //     System.out.println();
        // }
        //모든칸에 대해 탐색 시작
        for(int i = 0; i < mylock.length; i++){
            for(int j = 0; j < mylock.length; j++){
                answer = check(i, j, key);
                if(answer)
                    return answer;
                    
            }
        }
        return answer;
    }
    public static boolean check(int curY, int curX, int[][] key){
        // System.out.println("check시작");
        // if(curY == 3 && curX ==3){
        //     System.out.println("check 정답부분 시작----------------------");
        // }
        
        int curKey[][] = key;
        for(int dir = 0; dir < 4; dir++){ // 4번의 회전에 대해 탐색하기
            int tmp[][] = new int[m][m];
            //회전시켜서 입력. 주연이가알려준대로 죽죽 긋기로해보자. 회전갱신해야함.인지
            for(int i = 0; i < m; i++) {
                for(int j = 0; j< m; j++){
                    tmp[j][(m-1)-i] = curKey[i][j];
                }
            }
            //출력
            // System.out.println("현재 key모양");
            // for(int i = 0; i < m; i++){
            //     for(int j = 0; j < m; j++){
            //         System.out.print(tmp[i][j] +" ");
            //     }
            //     System.out.println();
            // }
            //홈 fit한지 검색
            boolean isFit = true;
            int holeLeft = lockhole;
            for(int i = 0; i < m; i++){
                for(int j = 0; j < m; j++){
                    if(curY + i < m-1 ||
                       curX + j < m-1 || 
                       curY + i > n + (m-1) -1 || 
                       curX + j > n + (m-1) -1) //기존 자물쇠벗어나면 탐색안함
                        continue;
                    if(tmp[i][j] == mylock[curY +i][curX + j]){
            //             if(curY == 3 && curX ==3){
            // System.out.println("정답부분 isfitfalse!---------------------");
        // }
                        isFit = false;
                        break;
                    }
                    else if(mylock[curY+i][curX+j] == 0) {
                        holeLeft--;
                        // if(curY == 3 && curX ==3){
                        //  System.out.println("정답부분 isholeleft감소!---------------------");
                        // }
                    }
                }
            }
           if(holeLeft == 0){
                // if(curY == 3 && curX ==3){
                //  System.out.println("정답부분 isholeleft찾음 return true!---------------------");
                // }
                return true;
            }
            curKey = tmp;//curKey 갱신
        }
        return false;
    }   
}
