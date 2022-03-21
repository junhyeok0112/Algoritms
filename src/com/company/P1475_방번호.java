package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1475_방번호 {

    static int[] arr = new int[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        for(int i =0 ;i<str.length();i++){
            arr[(str.charAt(i)-'0')]++;
        }
        int ex =0 ;
        if(((arr[6] + arr[9]) % 2 ) == 1){
            //6과 9를 합쳐서 홀수면 예를들어 5개면 총 3세트 필요
            //따라서 1 더해줌
            ex = ((arr[6] + arr[9]) / 2 ) + 1;

        } else{
            ex = ((arr[6] + arr[9]) / 2 );
        }
        //6과 9는 혼용 가능 따라서 1세트에 2개 들어있다 .
        //따라서 2로 나눠준 값들을 배열에 넣어줘서 필요한 개수 확인
        //단 합쳐서 5면 2로나누면 2 이므로 +1해줘서 총 3세트 필요하다 체크
        arr[6]=arr[9] = ex;
        int max = -1;
        for(int i =0;i<=9;i++){

            max = Math.max(max,arr[i]);
        }
        System.out.println(max);
    }
}
