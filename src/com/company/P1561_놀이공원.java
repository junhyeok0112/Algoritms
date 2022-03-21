package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Park{
    int idx ;
    int time;
    Park(int idx , int time){
        this.idx = idx;
        this.time = time;
    }
}

public class P1561_놀이공원 {

    static int N,M;
    static int[] map;
    static ArrayList<Park> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        map = new int[M+1];
        //arr.add(new Park(0,0));
        for(int i =1; i<=M; i++){
            map[i] = Integer.parseInt(st.nextToken());
            arr.add(new Park(1,map[i]));
        }


    }
}
