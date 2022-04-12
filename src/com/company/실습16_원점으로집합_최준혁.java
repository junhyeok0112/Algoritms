package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//i번째에 i만큼 움직이므로 총 i(i+1) /2 만큼 움직임
//총거리 - (가장 멀리 있는 점 ) 의 값이 짝수여야 모든 점들이 원점에 있을 수 있음 -> 수업시간
//모든 점들의 거리가 짝수 or 홀수로 일치해야한다. -> 그렇지 않으면 총거리 - 가장멀리있는 점 의 값이 짝수여도 홀수만큼 움직이는 점이 있어서 원점으로 일치할 수 없게된다.


public class 실습16_원점으로집합_최준혁 {

    static int tc, n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            n = Integer.parseInt(br.readLine());
            boolean allEven = false;
            boolean allOdd = false;
            int max = 0;
            for(int i = 0 ; i<n ;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int total = Math.abs(a) + Math.abs(b);
                if(total % 2 == 0) allEven = true;
                else allOdd = true;
                max = Math.max(max , total);
            }
            //짝수 홀수 섞여있으면 불가능
            if(allEven && allOdd){
                sb.append(-1).append("\n");
                continue;
            }

            int idx = 0;
            int sum = 0;
            while (true){
                sum+=idx;
                if(sum >= max && ((sum - max) % 2) ==0){
                    sb.append(idx).append("\n");
                    //flag = true;
                    break;
                }
                idx++;
            }

//            if(flag){
//                sb.append(idx).append("\n");
//            }else{
//                sb.append(-1).append("\n");
//            }
        }
        System.out.println(sb.toString());
    }
}
