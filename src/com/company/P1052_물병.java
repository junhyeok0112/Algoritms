package com.company;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1052_물병 {

    static int n, k ,cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int temp = 0;       //2진수로 선언될거
        cnt = 0;
        while (n>0){
            int num = n%2;
            temp = (temp | (num<<cnt));
            cnt++;
            n/=2;
        }
        //처음에 확인
        int ans = 0;
        if(chk(temp)){
            System.out.println(ans);
            return;
        }

        //이후에 물병 채워주는 작업
        for(int i = 0 ; i<cnt; i++){
            //물병 채우고 확인하고
            if((temp & (1<<i)) != (1<<i)) continue;     //해당칸이 비어있으면 continue
            temp += Math.pow(2,i);          //2^i 개만큼 채움
            ans += Math.pow(2,i);
            //채웠는데 전부다 사라질 경우 -> 이게 2^cnt 보다 크거나 같을경우이다 -> 왜냐면 2^cnt-1 까지의 값이 최대로 1로 채워져있을텐데 1 더해서 전부 사라지면 2^cnt 만 1이고 나머지 0이므로
            //또는 남아있지만 k개 보다 적을경우 -> 모두 chk 함수로 처리가능
            if(chk(temp)) {
                System.out.println(ans);
                return;
            }
        }
        //다 봤는데 안됐읅 ㅕㅇ우 불가 -> -1출력
        System.out.println(-1);
    }

    //물병이 K보다 많이 남았는지 적게남았는지 확인하는 변수
    static boolean chk(int temp){
        int kCnt = 0;
        for(int i = 0 ; i<cnt;i++){
            if((temp & (1<<i)) == (1<<i)){
                kCnt++;
            }
        }
        if(kCnt <= k) return true;
        else return false;
    }
}
