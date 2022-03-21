package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2920_음계 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[8];
        int[] ascending = {1,2,3,4,5,6,7,8};
        int[] descending = {8,7,6,5,4,3,2,1};
        boolean achk = true;
        boolean dchk = true;
        for(int i = 0 ; i<8;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0 ; i<8 ;i++){
            if(ascending[i] != arr[i] ){
                achk =false;
            }
            if(descending[i] != arr[i]){
                dchk = false;
            }
        }
        if(achk){
            System.out.println("ascending");
        } else if(dchk){
            System.out.println("descending");
        } else{
            System.out.println("mixed");
        }
    }
}
