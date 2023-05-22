import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n,m,hd=0;

        n = sc.nextInt();
        m = sc.nextInt();

        String[] input = new String[n];

        for(int i =0; i< n; i++){
            input[i] = sc.next();
        }

        for(int i =0; i< m; i++){
            int[] acgt = new int[4];

            for(int j =0 ; j< n; j++){
                switch(input[j].charAt(i)){
                    case 'A':
                        acgt[0]++;
                        break;
                    case 'C':
                        acgt[1]++;
                        break;
                    case 'G':
                        acgt[2]++;
                        break;
                    case 'T':
                        acgt[3]++;
                        break;
                }
            }

            int idx =-1, max=-1;

            for(int k=0; k<4; k++){
                if(acgt[k]>max){
                    idx= k;
                    max = acgt[k];
                }
            }

            hd += n-acgt[idx];
            if(idx ==0) sb.append("A");
            if(idx ==1) sb.append("C");
            if(idx ==2) sb.append("G");
            if(idx ==3) sb.append("T");
        }

        System.out.println(sb);
        System.out.println(hd);

    }
}