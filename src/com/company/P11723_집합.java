package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11723_집합 {

    static int m;
    static boolean[] arr;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //있으면 1 없으면 0 ?
        arr = new boolean[21];
        m = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String msg = st.nextToken();
            if(msg.equals("all") || msg.equals("empty")){
                command(msg,0);
            } else {
                int num = Integer.parseInt(st.nextToken());
                command(msg, num);
            }
        }
        System.out.println(sb.toString());
    }

    static void command(String msg, int num){
        if(msg.equals("add")){
            arr[num] = true;
        }else if(msg.equals("remove")){
            arr[num] = false;
        }else if(msg.equals("check")){
            if(arr[num]) sb.append(1).append("\n");
            else sb.append(0).append("\n");
        }else if(msg.equals("toggle")){
            if(arr[num]) arr[num] = false;
            else arr[num] = true;
        }else if(msg.equals("all")){
            for(int i =1 ; i<=20 ;i++){
                arr[i] = true;
            }
        }else{      //empty
            for(int i =1 ; i<=20 ;i++){
                arr[i] = false;
            }
        }
    }

}
