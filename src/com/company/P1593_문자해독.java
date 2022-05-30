package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1593_문자해독 {

    static int len,slen;
    static int[] arr = new int[123];            //A : 65 ~ z : 122까지 존재
    static int[] arr2 = new int[123];           //S안에 l~r까지 어떤 문자가 몇개 존재하는지.
    static int answer = 0;

    public  static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        len = Integer.parseInt(st.nextToken());
        slen = Integer.parseInt(st.nextToken());
        String w = br.readLine();
        String s = br.readLine();
        //w가 어떤 글자들로 이루어져있는지 확인해야함
        for(int i = 0 ; i<len; i++){
            arr[w.charAt(i)-'A']++;
        }
        //슬라이딩 윈도우로 S안에 몇개가 있는지 세어줍니다. ->
        int l = 0;
        int cnt = 0;
        for(int r= 0; r<slen; r++){
            arr2[s.charAt(r)-'A']++;
            cnt++;
            if(cnt > len){
                arr2[s.charAt(l)-'A']--;
                l++;
                cnt--;
            }
            if(cnt == len){
                if(check()) answer++;
            }
        }

        System.out.println(answer);
    }
    //arr 과 arr2가 같은지 확인하는 함수
    static boolean check(){
        for(int i = 0 ; i<123; i++){
            if(arr[i] != arr2[i]) return false;
        }
        return true;
    }
}
