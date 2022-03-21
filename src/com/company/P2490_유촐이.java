package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2490_유촐이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //0이 배 ,1 이 등
        for(int k = 0 ; k<3 ;k++) {
            int total = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < 4; i++) {
                total += Integer.parseInt(st.nextToken());
            }
            if (total == 0) {//윷
                System.out.println("D");
            } else if (total == 1) {//걸
                System.out.println("C");
            } else if (total == 2) {
                System.out.println("B");
            } else if (total == 3) {
                System.out.println("A");
            } else if (total == 4) {
                System.out.println("E");
            }
        }
    }
}
