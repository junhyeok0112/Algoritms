package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class P11652_카드 {

    static int n;
    static HashMap<Long, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<n ;i++){
            long num = Long.parseLong(br.readLine());
            if(hm.containsKey(num)){
                hm.replace(num , hm.get(num)+1);    //1 개 증가
            } else{                                 //처음이면
                hm.put(num,1);
            }
        }
        //HM에 들어가 있는 것 중 최대
        int max = -1;       //갖고 있는 갯수
        long max_num = Long.MAX_VALUE;  //max 값을 갖고있는 것중 작은 것

        for(long num : hm.keySet()){
            if(max == hm.get(num) && max_num > num){
                max = hm.get(num);
                max_num = num;
            } else if(max < hm.get(num)){
                max = hm.get(num);
                max_num = num;
            }
        }

        System.out.println(max_num);
    }
}
