package com.company;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 빌런호석 {

    static int[][] map = {
            {0,4,3,3,4,3,2,3,1,2},
            {4,0,5,3,2,5,6,1,5,4},
            {3,5,0,2,5,4,3,4,2,3},
            {3,3,2,0,3,2,3,2,2,1},
            {4,2,5,3,0,3,4,3,3,2},
            {3,5,4,2,3,0,1,4,2,1},
            {2,6,3,3,4,1,0,5,1,2},
            {3,1,4,2,3,4,5,0,4,3},
            {1,5,2,2,3,2,1,4,0,1},
            {2,4,3,1,2,1,2,3,1,0}};

    static int N,K,P,X;
    static String sX;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        sX = st.nextToken();
        X = Integer.parseInt(sX);
        int xsize = sX.length();
        int KminusX = K - xsize;
        char[] problem = new char[K];
        for(int i = 0 ; i<K;i++){
            if(i<KminusX){
                problem[i] = '0';
            }else{
                problem[i] = sX.charAt(i-KminusX);
            }
        }

        for(int i = 1 ; i<=N;i++){
            String temp = "";
            int chk = 0 ;   //바꾼 개수
            int t = i;
            int size = 0;  //자리수
            while(t != 0){
                size++;
                t/=10;
            }
            int dif = K - size; //0이 추가되어야 하는 개수
            //앞에 0추가해서 K자리수로 만들어 주기
            while(dif!=0){
                temp+="0";
                dif--;
            }
            temp+=Integer.toString(i);
            for(int j = 0 ; j<temp.length();j++){
                chk+=map[temp.charAt(j)-'0'][problem[j]-'0'];   //바꿀때 필요한 개수 계산
            }
            if(1<=chk && chk<=P){
                cnt++;
            }
        }

        System.out.println(cnt);




    }
}
