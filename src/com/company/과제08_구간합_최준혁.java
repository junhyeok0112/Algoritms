package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 과제08_구간합_최준혁 {

    static long tc,start,end,mul ;
    static long[] number;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Long.parseLong(br.readLine());
        int t = 0;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            start = Long.parseLong(st.nextToken());
            end = Long.parseLong(st.nextToken());
            long sum = 0;
            number = new long[10];
            mul = 1;                    //몇번째 자리수를 계산하고 있는지에 대한 수
            if(start == 0) start = 1;       //시작점 1올려주기
            while (start<=end){
                while (start%10 != 0 && start<=end){
                    cal(start);
                    start++;
                }
                if(start>end) break;
                while (end%10 != 9 && start<=end){
                    cal(end);
                    end--;
                }
                long diff = end/10 - start/10 +1 ;
                for(int i = 0 ;i<10 ;i++) number[i]+=diff*mul;
                mul*=10;
                start/=10;
                end/=10;
            }
            for(int i = 1; i<10 ;i++){
                sum+=(i*number[i]);
            }
            sb.append(sum).append("\n");

        }
        System.out.println(sb.toString());

    }
    static void cal(long a){
        for(long i = a; i>0 ;i /=10){
            String s = Long.toString(i);
            int t = s.charAt(s.length()-1)-'0';
            number[t] += mul;
        }
    }
}
