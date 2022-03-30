package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class P17398{       //연결된 것들 저장
    int x;
    int y;
    P17398(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P17398_통신망_분할 {

    static int n,m,q;
    static ArrayList<P17398> arr = new ArrayList<>();   //연결된 순서를 저장하기 위한 배열
    static int[] p ;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        Arrays.fill(p ,-1);
        for(int i = 0 ; i<m ;i++){

        }
    }

    static int find(int x){
        if(p[x] == -1 ) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return;
        p[y] = x;
    }


}
