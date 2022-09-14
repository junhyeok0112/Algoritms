package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//조합 이용하는 문제 -> 단 모든 경우에 안 입는 경우가 있으면 빼줘야함
//즉 하나의 종류에서 1 개 뽑는 경우 * 다른 종류에서 1개 뽑는 경우 등 전부 곱해 줌
//단 , 하나의 종류도 안 입을 경우도 고려해야 한다.
public class P9375_패션왕신해빈 {

    static int n,t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- >0){
            HashMap<String , ArrayList<String>> hm = new HashMap<>();
            n = Integer.parseInt(br.readLine());
            for(int i = 0 ; i<n ;i++){
                String str = br.readLine();
                String[] temp = str.split(" ");
                if(hm.containsKey(temp[1])){
                    hm.get(temp[1]).add(temp[0]);
                } else{
                    hm.put(temp[1] , new ArrayList<String>());
                    hm.get(temp[1]).add(temp[0]);
                }

            }
            int cnt = 0 ;       //종류의 갯수 만약 2개 이상이면 모두 NULL일 경우를 뺴줘야함
            int ans = 1;
            for(String key : hm.keySet()){
                ArrayList<String> entry = hm.get(key);
                ans *= (entry.size()+ 1);
                cnt++;
            }
            sb.append(--ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
