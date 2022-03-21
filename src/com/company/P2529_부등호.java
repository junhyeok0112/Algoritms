package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2529_부등호 {

    static int[] arr = new int[10];     //idx번째에 들어갈 숫자들을 저장하는 배열
    static long min = Long.MAX_VALUE;
    static long max = Long.MIN_VALUE;
    static String sMin = "" , sMax="";
    static boolean[] select = new boolean[10];
    static char[] com ;
    static int k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        com = new char[k];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<k ;i++){
            com[i] = st.nextToken().charAt(0);
        }
        pro(0,0);
        System.out.println(sMax);
        System.out.println(sMin);

    }

    static void pro(int cnt, int idx){
        if(cnt >= k+1){ //다 채워졌을 경우
            String temp = "";
            for(int i = 0 ; i<idx; i++){
                temp+=arr[i];
            }
            long num = Long.parseLong(temp);
            if(min > num){
                min = num;
                sMin = temp;
            }
            if(max < num){
                max = num;
                sMax = temp;
            }
            return;
        }

        for(int i = 0 ; i<= 9 ;i++){
            if(!select[i]){
                if(idx == 0 ){  //인덱스가 0 이면 일단 넣어야함
                    select[i] = true;
                    arr[idx] = i;
                    pro(cnt+1 , idx+1);
                    select[i] = false;
                    arr[idx] = 0;
                } else{     //검증해야함
                    if(com[idx-1] == '>'){
                        if(arr[idx-1] > i){ //가능한 경우
                            select[i] = true;
                            arr[idx] = i;
                            pro(cnt+1 , idx+1);
                            select[i] = false;
                            arr[idx] = 0;
                        }
                    } else{
                        if(arr[idx-1] < i){
                            select[i] = true;
                            arr[idx] = i;
                            pro(cnt+1 , idx+1);
                            select[i] = false;
                            arr[idx] = 0;
                        }
                    }
                }
            }
        }
    }
}
