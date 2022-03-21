package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1039_교환 {

    static String N;
    static int K , M;       //M은 자릿수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = st.nextToken();
        K = Integer.parseInt(st.nextToken());
        M = N.length();

        //연산 K번 할 수 없으면 -1을 출력
        if(find(N)) {
            System.out.println(-1);
            return;
        }



    }

    //맨앞 빼고 전부 0이여서 연산을 K번 할 수 없는 경우 확인
    static boolean find(String s){
        int ssize = s.length();
        for(int i = 1 ; i<ssize; i++){
            if(s.charAt(i) != '0'){
                return false;       //맨앞을 제외하고 하나라도 0이 아닌게 있으면 가능
            }
        }
        return true;
    }
}
