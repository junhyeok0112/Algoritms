package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P15732_도토리숨기기 {

    static int n,k,d;
    static int[][] rules;
    static int[] box;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        rules = new int[k][3];
        box = new int[n+1];
        for(int i = 0 ; i<k ;i++){
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }
        //N개의 상자들에 하나 당 몇개씩 들어가는지 계산해야함
        //이분탐색을 이용해서 N 번쨰 상자까지 몇개까지 들어갈 수 있는지 확인합니다.
        //그래서 갯수가 D개를 넘어선 순간이 정답.
        //매개변수 탐색과 비슷
        int left = 0 ;
        int right = n;
        while(left <= right){
            int mid = (left + right) / 2;
            //cnt 는 매 mid 마다 초기화 해줘야한다 -> mid까지 몇개가 들어가 있는지 확인하는 것이므로
            long cnt = 0;           //mid 상자까지 봤을때 들어가있는 도토리의 수 -> 최대 백만 * 만 이므로 long으로 선언
            //규칙들 확인해보기, 만약 rules[i][2] = B 가 mid 보다 작으면 그 범위 내에 모두 포함
            // rules[i][1] = A 가 mid 보다 크면 포함 X
            for(int i = 0 ;i <k; i++){
                if(rules[i][0] > mid) continue;
                if(mid >= rules[i][1]){
                    //mid 가 rules[i][1] 보다 크면 A~B 사이에서만 계산하면됨
                    cnt += ((rules[i][1] - rules[i][0]) / rules[i][2]) +1;
                } else {
                    //cnt는 mid - rules의 시작 / 간격(C)  + 1 하면 해당 rules 마다 mid 까지 몇 개의 도토리가 들어가 있는지 확인할 수 있다
                    cnt += ((mid - rules[i][0]) / rules[i][2]) + 1;
                }
            }
            if(cnt >= d){ //D개를 넣는데 더 적은 박스 번호에서 끝나는 경우  , mid 감소해야함
                right = mid -1;
            } else{
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}
