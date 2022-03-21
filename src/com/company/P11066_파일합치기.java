package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class P11066_파일합치기 {
    //시작과 끝의 구간을 정해줘야하는 DP 문제이다.
    //짧은 구간부터 문제를 풀어서 채워나가야함
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T>0){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N+1];
            for(int i = 1; i<=N ;i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[N+1][N+1]; //i<=j 이고 i번 ~ j번 파일을 하나로 합치는 최소비용 -> 시작점이 1이 아님
            int[][] sum = new int[N+1][N+1];//i~j까지의 합을 저장하는 배열 dp 쓸때 사용
            for(int i =1 ; i<=N ;i++){
                for(int j = i; j<=N ;j++){
                    sum[i][j] = sum[i][j-1] + arr[j];
                    //i~j까지의 합은 i~ j-1까지의 합 + j의 합
                }
            }
//            for(int i = 1 ; i<=N ;i++){
//                for(int j =1 ; j<=N ;j++){
//                    System.out.print(sum[i][j] + " ");
//                }
//                System.out.println();
//            }

            //dp[i][j]가 i~j까지의 합치는데 드는 비용중 최소값이므로 구하는건 dp[i][N]
            //파일이 하나밖에 없을 땐 비용이 들지않음
            for(int i =1 ; i<=N;i++){
                dp[i][i] = 0;           //비용안들어서 초기값
            }

            //dp[i][j]의 최소값은 i~(k)까지의 최소값 + k+1~j 까지의 최소값 + i~j까지의 합으로 나타낼 수 있다.
            //dp가 마지막에 어떤게 올수 있냐로 나눠서 파티션하므로 마지막에 1개로 합치고 난 뒤, 2개로 합치고 난 뒤 , 3개 합치고 난뒤 이런식으로 나눠준다
            //또 i~j까지의 합을 더하는 이유는 dp[i][k] + dp[k+1][j]의 값들을 또 합치는 비용을 체크해주기 위해 sum[i][j]를 더해준다.
            //dp[i][j] = dp[i][K] + dp[k+1][j] + sum[i][j];
            //범위가 짧은 것 부터 채워나가야함 ->테이블 그려보면됨 -> len = 2 부터 (1일때는 이미 초기값으로 0으로 정해줌)
            for(int len =2 ;len<=N ; len++){    //합칠 범위 ->몇개 합칠지 나타냄
                for(int i =1 ; i<=N ;i++){      //합칠부분의 시작점 -> 시작점을 바꿔가면서 범위(길이) 짧은 순서부터 채워가자
                    int j = i + len -1;          //합칠부분의 마지막 점
                    if(j>N) continue;           //끝범위가 범위 넘어가면
                    dp[i][j] = Integer.MAX_VALUE;   //최소값으로 경신해주기위해서 처음에 최대값 셋팅
                    for(int k = i; k<j ;k++){      //i~j를 1개 범위로 ,2개범위로 3개범위로 나눠주는 dp 배열 j-1까지로 나눔
                        dp[i][j] = Math.min(dp[i][j],
                                dp[i][k] + dp[k+1][j] +sum[i][j]);  //구하는값은 최소값
                    }
                }
            }
            sb.append(dp[1][N]).append("\n");
            T--;
        }
        System.out.println(sb);
    }
}

//public class P11066_파일합치기 {
//    //시작과 끝의 구간을 정해줘야하는 DP 문제이다.
//    //짧은 구간부터 문제를 풀어서 채워나가야함
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int T = Integer.parseInt(br.readLine());
//        while(T>0){
//            int N = Integer.parseInt(br.readLine());
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int[] arr = new int[N+1];
//            int[][] dp = new int[N+1][N+1]; //i<=j 이고 i번 ~ j번 파일을 하나로 합치는 최소비용 -> 시작점이 1이 아님
//            //즉 구하는 값은 dp[1][N]의 값
//            int[][] sum = new int[N+1][N+1];
//            for(int i =1 ; i<=N; i++){
//                sum[i][i] = arr[i];
//            }
//
//
//
//            //초기값
//            for(int i = 1; i<=N ;i++){
//                dp[i][i] = 0;               //파일이 i부터 i까지면 하나뿐 ->합칠 수 없음음
//           }
//
//            //i ~ j 까지의 합을 dp 형식으로 구하기 위해서 i~ k까지의 최소 + (k+1) ~j 까지의 최소 + i~j까지의 총량을 구하면된다.
//            //구간의 길이가 짧은 것부터
//            for(int len = 2 ; len<=N ; len++){          //구하려는 거리 len = 1일경우는 초기값으로 이미 다 채워줌
//                for(int i =1; i<=N-len+1 ; i++){        //범위의 시작점
//                    int j = i+len -1 ;                  //범위의 끝나는 지점
//                    dp[i][j] = Integer.MAX_VALUE;       //초기값 없어서 셋팅
//                    for(int k = i ; k<j ;k++){
//                        dp[i][j] = Math.min(
//                                dp[i][j],
//                                dp[i][k] + dp[k+1][j] + sum[i][j]
//                        );
//                    }
//                }
//            }
//
//            T--;
//        }
//    }
//}
