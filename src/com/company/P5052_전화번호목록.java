package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class P5052_전화번호목록 {

    static int t, n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-->0){
            n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            HashSet<String> hs = new HashSet<>();
            for(int i = 0 ; i<n ; i++){
                arr[i] = br.readLine();
                hs.add(arr[i]);
            }
            boolean flag = true;
            //String을 정렬 , 정렬하면 123 125 1253 3333 처럼 정렬이 된다 -> 즉 접두어가 비슷한 것끼리 서로 붙어있게된다
            //따라서 양 옆을 접두어로 비교만 해주면 쉽게 O(N)만에 처리할 수 있다. -> 만약 바로 옆에꺼가 접두어가 아니면 두번쨰꺼는 사전 정렬순이므로 절대 접두어가 아니라 고려하지 않아도됨
            //즉 옆에것만 고려
            Arrays.sort(arr);

            for(int i = 0 ; i<n-1 ;i++){
                if(arr[i+1].startsWith(arr[i])){        //startsWith는 해당 문자열로 시작하는가? 를 판별한다.
                    sb.append("NO").append("\n");
                    flag = false;
                    break;
                }
            }
            if(flag) sb.append("YES").append("\n");


//            for(int i = 0 ; i<n ;i++){
//                int len = arr[i].length();
//                for(int j = 1 ; j<len; j++){
//                    if(hs.contains(arr[i].substring(0,j))){     //len -1 까지 이므로 어짜피 len-1 까지 잘랐을 경우 마지막 글자 자르므로 자기 자신 안봄
//                        sb.append("NO").append("\n");
//                        flag = false;
//                        break;
//                    }
//                }
//                if(!flag) break;
//            }
//            if(flag) sb.append("YES").append("\n");

        }
        System.out.println(sb.toString());
    }
}
