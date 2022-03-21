package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10951_A더하기B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str;
        StringTokenizer st;
        while((str = br.readLine()) != null){
            st = new StringTokenizer(str," ");
            int A,B;
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            sb.append(A+B).append('\n');
        }
        System.out.println(sb.toString());
    }

}
