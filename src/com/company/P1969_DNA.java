package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1969_DNA {

    static int n,m;
    static char[][] arr;
    static String ans = "";
    static int ansNum = 0;
    static int[][] dna ;      //A,C,G,T 순으로
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        dna = new int[m][4];        //m번째자리에 A,C,G,T 순
        for(int i = 0 ; i<n ;i++){
            String str = br.readLine();
            for(int j= 0; j<m ;j++){
                arr[i][j] = str.charAt(j);
            }
        }

        //M번째 자리 수에서 제일 많이 나온거 찾아야함
        for(int i =0 ; i<m ;i++){
            for(int j = 0; j< n; j++){
                if(arr[j][i] == 'A'){
                    dna[i][0]++;
                } else if(arr[j][i] == 'C'){
                    dna[i][1]++;
                } else if(arr[j][i] == 'G'){
                    dna[i][2]++;
                } else{
                    dna[i][3]++;
                }
            }
        }

        for(int i = 0 ; i<m ;i++){
            int max = -1;
            int maxIndex= -1;
            for(int j = 0 ;j<4 ;j++){
                if(dna[i][j] > max){
                    max = dna[i][j];
                    maxIndex = j;
                }
            }
            if(maxIndex == 0){
                ans+="A";
            } else if(maxIndex == 1){
                ans+="C";
            } else if(maxIndex == 2){
                ans+="G";
            } else{
                ans+="T";
            }
        }
        System.out.println(ans);
        for(int i = 0 ; i<n ;i++){
            for(int j= 0; j<m ;j++){
                if(arr[i][j] != ans.charAt(j)){
                    ansNum++;
                }
            }
        }
        System.out.println(ansNum);
    }
}
