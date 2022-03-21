package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class A27_친구네트워크_최준혁 {
    static int N, F;
    static int[] p;
    static int[] cnt;
    static HashMap<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(N-->0){
            F = Integer.parseInt(br.readLine());
            p = new int[2*F+1];
            cnt = new int[2*F+1];
            map = new HashMap<>();	//케이스마다 처리할 map 선언
            int nowIndex = 1; //입력받은 이름과 P배열에 저장될 인덱스가 일치하게 하기위하여 선언
            //즉 최준혁 ,1번인덱스 이런식으로

            for(int i = 0 ; i<F;i++){
                String s = br.readLine();
                StringTokenizer st = new StringTokenizer(s);
                String a = st.nextToken();
                String b = st.nextToken();
                //String 타입이여도 StringTokenizer를 통해서 띄워쓰기로 구분 가능
                //Integer뿐만아니라 가능
                int aindex, bindex;	//입력 받은 2개의 인덱스
                //map이용해서 이미 입력받았던건지 아닌건지 구별
                if(!map.containsKey(a)){
                    cnt[nowIndex] =1;
                    p[nowIndex] = nowIndex;
                    map.put(a, nowIndex++);
                }
                if(!map.containsKey(b)){
                    cnt[nowIndex] = 1;
                    p[nowIndex] = nowIndex;
                    map.put(b, nowIndex++);
                }
                aindex = map.get(a);
                bindex = map.get(b);
                merge(aindex,bindex);
                sb.append(cnt[find(bindex)]).append("\n");
                //부모의 cnt 값출력
            }

        }
        System.out.println(sb.toString());
    }

    static int find(int x){
        if(p[x] == x)
            return x;
        return p[x] = find(p[x]);
    }

    static void merge(int a , int b){
        a = find(a);
        b = find(b);
        if( a== b){	//같으면 아무것도 하지않고 리턴
            return ;
        }
        p[a] = b;	//대표를 a로 설정 , 그러면 cnt값은 a값갱신
        //이유 : b의 부모의 p 값을 a의 대표로 바꾸었으므로 b의 find값이 a로 됨
        //따라서 a가 대표
        cnt[b] += cnt[a];
        //서로 떨어져있는거 합치고 , cnt ->네트워크에 속해있는 갯수
        //cnt값을 갱신 ->즉 a 1, b 1이였으면 둘이 합쳐졌으므로 b =2가 됨
        //즉 cnt[b] = 2로 네트워크에 2명이있다 -> 이값출력
    }
}
