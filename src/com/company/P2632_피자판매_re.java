package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.BatchUpdateException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P2632_피자판매_re {

    static int needs,m,n;
    static ArrayList<Integer> Ap = new ArrayList<>();
    static ArrayList<Integer> Bp = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        needs = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for(int i = 0 ; i<m; i++){
            Ap.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 0 ; i< n; i++){
            Bp.add(Integer.parseInt(br.readLine()));
        }


    }
}
