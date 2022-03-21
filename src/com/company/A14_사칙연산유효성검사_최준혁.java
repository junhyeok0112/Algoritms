package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A14_사칙연산유효성검사_최준혁 {

    static int n;
    static String[] arr;
    static String ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = 0;
        while (tc++ < 10){
            n = Integer.parseInt(br.readLine());
            arr = new String[n+1];
            ans ="";
            StringTokenizer st ;
            for(int i = 1 ; i<=n ;i++){
                st = new StringTokenizer(br.readLine());
                arr[Integer.parseInt(st.nextToken())] = st.nextToken();
            }

            //중위 순회
            boolean flag = false;
            makeString(1);
            for(int i = 0 ; i<ans.length()-1;i++){
                if(ans.charAt(i) == '/' ||ans.charAt(i) == '+' ||ans.charAt(i) == '-' ||ans.charAt(i) == '*'){      //연산자가 연속으로 나오면
                    if(ans.charAt(i+1) == '/' ||ans.charAt(i+1) == '+' ||ans.charAt(i+1) == '-' ||ans.charAt(i+1) == '*'){
                        flag = true;
                        break;
                    }
                }
            }
            if(flag){
                System.out.println("#"+tc+" " + 0);
            }else{
                System.out.println("#"+tc+" " + 1);
            }
        }
    }

    static void makeString(int idx){
        if(idx > n) return;
        makeString(idx*2);  //왼쪽자식
        ans+=arr[idx];      //내꺼 붙혀주기
        makeString(idx*2+1);    //오른쪽자식
    }
}
