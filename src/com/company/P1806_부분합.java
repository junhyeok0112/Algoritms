package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


//투 포인터 문제 : 화살표 2개에 의미를 부여해서 탐색 범위를 압축하는 방법
//L이 나보다 앞에서 시작했을 때 합이 넘지 못한 범위는 R로 볼 필요가 없다
//즉 그런 경우 R을 마지막으로 찾은 곳부터 보자 -> 합이 못 넘을 경우 증가
//이 방법을 이용해서 탐색 범위를 줄임
public class P1806_부분합 {
    static int N , S;
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<N ;i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }


        System.out.println(cal());


    }

    static int cal(){   //계산하는 함수
        int R = -1 ; int sum = 0; int res = N +1;
        for(int L = 0 ; L <N ;L++){ //L을 범위 하나 더 크게하는 버릇 들이기 만약 크게하면 L[0] = 0 이므로 그냥 빼도됨
            if(L != 0){ //만약 L이 시작지점이 아니면 다음꺼 볼때마다 전에 l 을 빼면서 봐야함
                //L-1 구간에서 제외하기
                sum -= arr.get(L-1);
            }
            while(R+1<N && sum <S){  //sum이 S보다 클떄까지 R증가
                //R의 범위도 while문에서 걸러줌
                R++;
                sum+= arr.get(R);   // 실제로 더하는 건 R의 역할 , L은 빼주는 역할
            }
            if(sum>=S) { //가능한 경우에 RES 계산
                res = Math.min(res, R - L+1);   //0부터 시작 했으므로 L~R 사이의 개수 구해야함
                //즉 L =0 R =4 이면 0 1 2 3 4 5 5개이므로 R-L +1
            }

        }
        if(res == N+1){ //N+1이면 갱신된게 없으므로 가능하지 않다 -> 즉 불가능
            //따로 함수 만들필요없음
            res= 0;
        }
        return res;
    }


}
