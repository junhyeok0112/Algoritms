package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P6603_로또 {
    static int k;
    static int[] arr ;
    static boolean[] select;
    static int[] pick;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        while (!str.equals("0")){
            String[] temp = str.split(" ");
            k = Integer.parseInt(temp[0]);
            arr = new int[k];
            select = new boolean[k];
            pick = new int[6];
            for(int i = 1 ; i<=k ;i++){ //k개의 수
                arr[i-1] = Integer.parseInt(temp[i]);
            }

            pro(0,0,0);
            sb.append("\n");
            str = br.readLine();
        }
        System.out.println(sb.toString());
    }

    static void pro(int cnt , int idx ,int prev ){
        if(cnt >= 6) {
            for(int i = 0 ; i< 6; i++){
                sb.append(pick[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = prev ;i<k ;i++){
            if(!select[i]){
                select[i] = true;
                pick[idx] = arr[i];
                pro(cnt+1, idx+1 , i+1);
                select[i] = false;
                pick[idx] = 0;
            }
        }
    }
}
