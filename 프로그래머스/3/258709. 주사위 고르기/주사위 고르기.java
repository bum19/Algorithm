class Solution {
    
    public static int[] aArr; // aArr[10] a주사위들을 던져서 10점이 나오는 모든 경우의수를 저장
    public static int[] bArr;
    public static int[] answer; //답 배열
    public static int[] aDice;  //a주사위들 idx 배열
    public static boolean[] isA; //A인지 아닌지
    
    public static int[] seq;
    public static int n, curVictory;
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        answer = new int[n/2];
        aDice = new int[n/2];
        isA = new boolean[n];
        seq = new int[n/2];

        choose(0,0, dice);
        return answer;
    }
    
    private void choose(int start, int depth, int[][] dice){
        if(depth == n/2){
            
            aArr = new int[501];
            bArr = new int[501];
            //A,B에 대해 모든 경우의수로 arr 만들기
            make(0, dice);
            
            //누적합으로 바꾸기
            toAccum();
            
            // 승리수 구하기
            int victory = getVictory();
            
            //승리수 더 높으면, 현재 조합 갱신
            if(curVictory < victory){
                curVictory = victory;
                for(int i = 0; i < n/2; i++){
                    answer[i] = aDice[i];
                }
            }
            return;
        }
        
        for(int i = start; i < n; i++){
            isA[i] = true;
            aDice[depth] = i+1;
            choose(i+1, depth+1, dice);
            isA[i] = false;
        }
        
    }
    
    private static void make(int depth, int[][] dice){
        if(depth == n/2){
            int aScore = 0;
            int aCnt = 0;
            int bScore = 0;
            int bCnt = 0;
            for(int i = 0; i < n; i++){
                if(isA[i]){
                    aScore += dice[i][seq[aCnt++]];
                }
                else{
                    bScore += dice[i][seq[bCnt++]];
                }
            }
            aArr[aScore]++;
            bArr[bScore]++;
            
            return;
        }
        
        for(int i = 0; i < 6; i++){
            seq[depth] = i;
            make(depth+1, dice);
        }

    }
    
    private static void toAccum(){
        for(int i = 1; i <= 500; i++){
            aArr[i] += aArr[i-1];
            bArr[i] += bArr[i-1];
        }
    }
    
    private static int getVictory(){
        int victory = 0;
        for(int i = 1; i < 500; i++){
            victory += (aArr[i]-aArr[i-1]) * bArr[i-1];
        }
        
        return victory;
    }
}