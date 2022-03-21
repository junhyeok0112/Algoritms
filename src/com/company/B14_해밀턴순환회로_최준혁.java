package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14_해밀턴순환회로_최준혁 {

    static int n ;
    static int[][] map;
    static boolean[] select;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        select = new boolean[n+1];
        for (int i = 1 ; i<=n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j<=n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //백트래킹으로 현재까지 구한 비용이 구해놓은 비용보다 크면 종료시키는 형식으로 진행.
        dfs(1,0,0);
        System.out.println(ans);
    }

    static void dfs(int cur , int cnt,  int cost ){ //cur : 출발 위치 ㅡ cnt 방문한 갯수  , cost 비용
        if(cost >= ans){
            return;
        }
        //1을 제외하고 2~ n개까지니까 n-1이 맞음
        if(cnt >= n-1){       //모든 곳 확인 했으면 최소값 갱신 , 단 이때 1로 다시 돌아가는 거리 추가
            //다시 회사로 돌아오는 길이 있는지 체크해야함
            if(map[cur][1] != 0){
                ans = Math.min(ans , cost+map[cur][1]);
            }
            return;
        }
        //1부터 시작하므로 모든 순서를 볼 수 있음
        //만약 cur 부터 보면 순서를 신경안쓰는 조합 , 1부터보면 순서가 중요한 순열
        //1번은 출발지이므로 항상 고정되어 있음 -> 즉 2~ n까지 보고 1로 돌아오는 길을 찾아야함.
        for(int i = 2; i<= n;i++){
            if(select[i] || map[cur][i] == 0) continue;        //방문했으면 넘어감 또 못가는 곳이면 넘어감
            select[i] = true;
            dfs(i,cnt+1, cost+map[cur][i]);
            select[i] = false;
        }

    }

}
