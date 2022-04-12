package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1019_책페이지 {

    static int n;
    static long[] number;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        number = new long[10];
        int start = 1;
        int end = n;
        //각 자리수가 몇번이나 반복되는지 횟수는 (end/10 - start/10 +1 )로 알 수 있습니다. 즉( 0~9까지 몇번이나 반복되는지 )
        //예를들어 10~99면 (9-1 +1)로 총 9번의 0~9의 횟수가 반복합니다. -> 마지막 자리수에 대하여.
        int place = 1;
        
    }
}
