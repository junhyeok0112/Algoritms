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
        //중간층은 0~9까지 합으로 규칙적 0~9구간까지 늘리거나 줄이면서 등장횟수 체크한다. ->짜투리 처리
        //중간층은 0~9까지 row 개수만큼 반복
        //일의자리 처리후 십의 자리부터는 10번씩 나왔음
        //ex > 40 41 42 43 44 45 46 47 48 49 에서 0~9까지 1번씩 나오고 4는 10번씩 추가로나옴,.

        long place = 1;
        //즉 while 문 1번당 1자리 수씩 봄 -> 첫번째 while문 -> 일의 자리만 , 두번째 while문 ->10의 자리 ->10의자리는 10번씩 나옴 -> 세번째 while..
        while (start<=end){
            //앞쪽 짜투리 처리
            while (start%10 != 0 && start<=end){
                parse(start , place);
                start++;
            }
            //뒤쪽 짜투리 처리
            while (end%10 != 9 && start <= end){
                parse(end, place);
                end--;
            }
            //종료조건
            if(start > end ) break;
            //중간 규칙성 처리
            start/=10;          //row 개수 구하기 위해서 10으로 나눔
            end /=10;
            for(int i = 0; i<=9 ; i++){
                number[i] += (end - start +1 )* place;          //자리수에 해당하는 갯수
            }

            //다음 자리수 계산을 위한 갱신.
            place*= 10;
        }
        for(int i = 0 ; i<=9 ;i++){
            System.out.print(number[i]+ " ");
        }
    }

    static void parse(long x ,long place){
        //각 자리수의 갯수를 더해주는 함수
        while (x>0){
            number[(int)x%10] += place;         //place번째 자리수이면 그 자리수 만큼 더해줘야한다 -> 10의 자리면 10번이 나오므로 ->
            x/=10;
        }
    }
}
