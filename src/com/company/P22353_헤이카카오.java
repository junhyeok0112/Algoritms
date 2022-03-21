package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P22353_헤이카카오 {

    static int a;
    static double d, k;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        d = Double.parseDouble(st.nextToken());
        k = Double.parseDouble(st.nextToken());
        //System.out.println(d/100.0);
        double reuslt = 0;
        while(d<100){
            reuslt += a * (d/100.0);
            d += d * (k/100.0);
            System.out.println();
        }
        System.out.println(reuslt);


    }
}
