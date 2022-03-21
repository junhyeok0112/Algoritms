package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2096_내려가기 {

    //슬라이딩 윈도우와 DP

    static int N;
    static int[] maxdp = new int[3];           //최대값들 메모리 제한 때문에 슬라이딩 윈도우 형식으로 2줄씩 본다
    static int[] mindp = new int[3];           // mindp[0][2] 는 위엣줄의 3번째 칸에 있는거 의미
    static int[] map = new int[3];
    static int[] temp = new int[3];             //계산을 위한 임시 배열

    //첫번째 칸에 올 수 있는건 그 위에 층에서 첫번째 or 두번째 칸의 값이 올 수 있다.
    //두번째 칸에 올 수 있는건 그 위에 줄에서 첫번째 , 두번째 세번째 칸의 값이 올 수 있다
    //세번째 칸에 올 수 있는건 그 위에 줄에서 두번째 , 세번재 칸의 값이 올 수 있다.
    //즉 첫번째 칸에 오는게 최대 이려면 위엣줄에서 최대 값이 내려오면된다 . 이후 3가지 수를 비교해서 찾으면됨
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = Integer.parseInt(st.nextToken());
            map[0] = num1;
            map[1] = num2;
            map[2] = num3;
            if(i == 1){
                maxdp[0] = map[0];
                maxdp[1] = map[1];
                maxdp[2] = map[2];

                mindp[0] = map[0];
                mindp[1] = map[1];
                mindp[2] = map[2];

                continue;
            }
            //최대값 부터 갱신 , 첫번째 올 수 있는 값의 최대는 입력받은 첫번째 값 + 위층에서 오는 값중 최대값
            temp[0] = Math.max(maxdp[0] , maxdp[1]) + map[0];          //maxDP의 값들이 변하므로 뒤에 값 계산을 위해 임시 배열 사용
            temp[1] = Math.max(maxdp[0], Math.max(maxdp[1], maxdp[2])) + map[1];
            temp[2] = Math.max(maxdp[1] , maxdp[2]) + map[2];

            maxdp[0] = temp[0];
            maxdp[1] = temp[1];
            maxdp[2] = temp[2];

            //최소값 구하기 위의 방식과 동일 , 내가 있는 곳이 최소이려면 위에서 최소값이 와야함
            temp[0] = Math.min(mindp[0] , mindp[1]) + map[0];          //maxDP의 값들이 변하므로 뒤에 값 계산을 위해 임시 배열 사용
            temp[1] = Math.min(mindp[0], Math.min(mindp[1], mindp[2])) + map[1];
            temp[2] = Math.min(mindp[1] , mindp[2]) + map[2];

            mindp[0] = temp[0];
            mindp[1] = temp[1];
            mindp[2] = temp[2];
        }
        int max = -1 ; int min = Integer.MAX_VALUE;
        for(int res : maxdp){
            max = Math.max(max, res);     //3개의 수중 최대값 찾기
        }

        for(int res : mindp){
            min = Math.min(min, res);     //3개의 수중 최대값 찾기
        }
        System.out.println(max+ " " + min);
   }
}
