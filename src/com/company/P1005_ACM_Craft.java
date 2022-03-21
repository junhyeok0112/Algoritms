package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P1005_ACM_Craft {

    static int T,N,K;
    static int[] time;
    static int[] indeg;
    static int[] dp ;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T> 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            time = new int[N+1];
            indeg = new int[N+1];
            adj = new ArrayList[N+1];
            dp = new int[N+1];                                                      //dp배열 선언
            for(int i = 0 ; i<=N;i++) adj[i] = new ArrayList<>();                   //연관 관계 넣을 그래프
            st = new StringTokenizer(br.readLine());                                //건물 짓는 시간 체크
            for(int i = 1; i<=N;i++){                                               //시간 셋팅
                time[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 0 ; i<K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                adj[X].add(Y);
                indeg[Y]++;
            }
            int W =Integer.parseInt(br.readLine());                             //지으면 이기는 건물 W
            int cntTime = 0 ;                                                   //걸린 시간
            Queue<Integer> q = new LinkedList<>();
            for(int i = 1 ; i<=N ;i++) {
                if (indeg[i] == 0)
                {
                    dp[i] = time[i];
                    q.add(i);                                                   //후보군들 저장
                }
            }

            while (!q.isEmpty()){
                int X = q.poll();
                for(int Y : adj[X]){
                    indeg[Y]--;
                    dp[Y] = Math.max(dp[Y] , time[Y]+dp[X]);            //내가 지금 만들어지는데 걸리는 시간
                    // 내가 만들어야 하는 건물 추가했을 때 내시간(tiem[Y]) + 그 건물이 걸리는시간(dp[X])의 합중 큰 시간을 Y가 만들어지는 시간으로 설정
                    //예를들면 예제에서 4를 만들기 위해 2가 먼저 지어졌으면 11초면 Y를 짓는데 그 뒤 3이 만들어져서 총 11과 110을 비교해서 더 큰 110을 Y가 만들어지는데
                    //필요한 시간이라고 저장
                    if(indeg[Y] == 0 ) q.add(Y);
                }
            }

            sb.append(dp[W]).append("\n");   //정답인 W가 지어지는데 걸리는 최소시간

            //앞에 만들어져야 하는 것들이 있어서 정렬시켜야 하므로 위상정렬 , 그 뒤 시간 계산해야하므로 dp이용


            T--;
        }
        System.out.println(sb);
    }
}
