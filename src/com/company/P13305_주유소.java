package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class P13305_주유소 {

    static int n;
    static long[] map ;                //도시끼리의 거리를 나타내는 2차원배열
    static long[] arr ;                  //i번째 도시의 리터당 기름값을 나타내는 뱅열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        map = new long[n+1];            //2차원이면 메모리 초과
        arr = new long[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        long totalCost = 0;
        for(int i = 2; i<=n; i++){    //도시간 거리
            map[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i =1 ; i<= n;i++){
            //i번째 도시의 리터당 거리
            arr[i] = Long.parseLong(st.nextToken());
        }

//        int now = 1;        //현재 위치해있는 도시
//        while(now < n){    //마지막 도시에 도착할때까지 반복
//            int len = 0;   //가야하는 거리
//            int cost = arr[now];    //해당 마을에서 필요한 비용
//            for(int i = now+1 ; i <= n; i++){   //다음 마을부터 체크
//                if(arr[now] >= arr[i]){          //비용이 더 적은 마을을 찾으면
//                    len += map[i-1][i];         //가야할 거리 저장
//                    now = i;                    //해당 마을 도착 갱신
//                    totalCost += len * cost;    //그 마을까지 가는데 걸리는 비용계산
//                    break;
//                } else{
//                    len += map[i-1][i];         //비용이 더 크거나 같으면 비용만 갱신
//                }
//            }
//        }

        long len = 0;
        long cost = arr[1];               //처음 코스트
        for(int i =2 ; i<=n ;i++){
            if(arr[i] < cost || i == n){     //이전 마을보다 비용이 적거나 맨 오른쪽에 도달했을경우
                len += map[i];             //해당까지 가는 거리 갱신
                totalCost += len * cost;       //여태까지 cost * 거리
                cost = arr[i];                  //코스트 최소값으로 갱신
                len = 0;                        //거리 처음부터 세기 위해 0 갱신
            } else{
                len += map[i];
            }
        }

        sb.append(totalCost);
        System.out.println(sb.toString());

    }
}
