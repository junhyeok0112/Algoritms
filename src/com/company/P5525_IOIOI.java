package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class P5525_IOIOI {
    static int N,M;
    static String s ;
    static Stack<Character> st  = new Stack<>();
    static int res = 0;    //n의 최대 개수
    //cnt - n +1 개
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        s = br.readLine();
        //int size = 2*N +1;
        boolean chk = false ; //false면 아직 시작안한상태
        int cnt = 0;
        for(int i = 0 ; i<s.length();i++){
           if(s.charAt(i) == 'I'){  //I왔을때
               if(chk == true){ //이미 I였으면
                   chk = false; //false하고 다시 세어야함
                   if(cnt == 0 ) continue;
                   int temp = cnt / 2;  //한번 IOI ... 묶음 끝났을 때 Ptemp 의 값 이때 우리가 구하는 개수는 temp - n +1 이다
                   if(temp >= N){
                      // System.out.println(temp);
                       res+= (temp -N +1);
                       cnt = 0;
                   }else{
                       cnt= 0;
                   }
                   if(i == s.length() -1 ) continue;//마지막이면 그냥 끝냄
                   if(s.charAt(i+1) == 'O'){
                       //System.out.println("체크"+ (i+1));
                       chk = true;
                       cnt++;
                   }
               }else{//그 전에꺼가 O였으면
                   cnt++;
                   chk =true;   //I왔었다는 뜻
               }
           }
           else{//O과왔음
               if(chk == false){ //이미 O이였다가 다시 0이 들어온경우
                   chk = false; //false하고 다시 세어야함
                   if(cnt == 0) continue;   //처음 나오는 0이면 넘어감
                   int temp = (cnt-1) / 2;  //그 전의 0을뺴고 IOI...세트 체크
                   if(temp >= N){
                       res+= (temp -N +1);
                       cnt = 0;
                   }else{
                       cnt= 0;
                   }

               }else{//그 전에꺼가 O였으면
                   cnt++;
                   chk =false;   //I왔었다는 뜻
               }
           }
        }

        if(cnt >=3){
            if(chk) {
                if (cnt / 2 >= N)
                    res += (cnt / 2 - N + 1);
            } else{
                if ((cnt / 2)-1 >= N)
                    res += ((cnt / 2)-1 - N + 1);
            }
        }

        System.out.println(res);
    }
}
