package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test {
    static int t,x,y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        while (tc++<t){
            StringTokenizer st= new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int tiny = Math.min(x,y);       //더 작은거
            double max = -1;
            for(int h = 1; h<tiny ; h++){
                int temp = (x-h) * (y-h) * h;
                max = Math.max(max,temp);
            }

            sb.append("#").append(tc).append(" ").append(max);

        }
        System.out.println(sb.toString());

    }
}
