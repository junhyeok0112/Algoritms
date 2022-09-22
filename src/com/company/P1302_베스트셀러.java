package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class P1302_베스트셀러 {

    static int n;
    static HashMap<String, Integer> hm = new HashMap<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0 ; i< n; i++){
            String name = br.readLine();
            if(hm.containsKey(name)){
                hm.replace(name, hm.get(name) + 1);
            } else{
                hm.put(name , 1);
            }
        }

        int max = -1;
        String max_name = "";
        for(String cur : hm.keySet()){
            if(max == hm.get(cur) && max_name.compareTo(cur) > 0){
                max = hm.get(cur);
                max_name = cur;
            } else if(max < hm.get(cur)){
                max = hm.get(cur);
                max_name = cur;
            }
        }
        System.out.println(max_name);

    }
}
