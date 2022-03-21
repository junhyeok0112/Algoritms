package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2231_분해합 {

    static int n;
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 1; i<=n;i++){
            int num = 1000000;
            int t = i;
            int total = i;
            while(num>0){   //0하나씩 줄여가면서 계산해줌
                total += (t /num);    //한자리씩 더해줌
                t %= num;
                num/=10;
            }
            if(total == n){
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
}
