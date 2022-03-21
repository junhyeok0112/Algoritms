package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14500_테트로미노 {
    //DFS로 탐색하면 ㅗ 모양을 제외한 모든거 탐색 가능
    //이떄 depth를 4로 주어서 멈추게하면 탐색이됨.. 아니 세상에 ;

    static int[] ax= {0,0,1,-1};
    static int[] ay = {1,-1,0,0};
    static int N,M ;
    static int[][] arr ;
    static int[][] visited;
    static int max = -1;

    //ㅗ 모양을 제외하고 전부 dfs로 4개 체크하면 확인이 가능하다 .
    static void dfs(int rx, int ry , int sum ,int depth){
        //처음 시작할때부터 이미 1 더해진 상태
        if(depth == 4){ //depth4까지 봤으면 4개값 더한것 따라서 이떄 max값 갱신해주어야함
            max = Math.max(max,sum);
            return ;
        }
        //입력받은 곳부터 확인
        if(depth == 0)
            visited[rx][ry] = 1;
        int nsum = arr[rx][ry];
        for(int i = 0 ; i<4; i++){
            int nx = rx + ax[i];
            int ny = ry + ay[i];

            if(nx>= 0 && nx<N && ny>=0 && ny<M){    //범위 안이면
                //방문안했으면
                if(visited[nx][ny] == 0) {
                    visited[nx][ny] = 1;
                    dfs(nx, ny, sum + arr[nx][ny], depth + 1);
                    visited[nx][ny] = 0 ;
                    //4개보고 원래로 돌아와야하므로 백트래킹 형식으로 구현
                }
            }
        }
        visited[rx][ry] = 0;

    }

    //ㅗ모양 체크하기
    static void exp(int rx ,int ry){
        // + 모양에서 한쪽씩 없앤다음 가장 큰 값으로 갱신해주면됨
        int sum = arr[rx][ry];
        int min = Integer.MAX_VALUE;
        int wing = 4;

        for(int i = 0 ; i<4;i++){
            int nx = rx + ax[i];
            int ny = ry + ay[i];
            //nx, ny가 범위안에있어야함 즉 -> 범위안에서 가능한 것들로 해야함 ->범위 밖이면 날개 하나씩 제거 이떄 날개가 2개 이상제거하면 ㅗ 모양이안됨
            if(wing<=2){
                return;
            }
            if(nx>= 0 && nx<N &&ny>=0 && ny<M){ //범위 안이면
                sum +=arr[nx][ny];
                min = Math.min(min,arr[nx][ny]);
            } else{
                //범위 밖이면
                wing--;
            }

        }
        if(wing == 4) {
            //날객가 4개일때 하나 제거해야함 -> 만약에 위에서 걸려진상태면 날개가 3개란뜻 즉 -> 나올수있는 ㅗ 모양은 1개뿌이므로 min뺄필요 없음 이미 계산된 상태
            sum = sum - min;
        }
        max = Math.max(sum,max);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new int[N][M];
        for(int i = 0 ; i< N ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<M ;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i = 0; i<N; i++){
            for(int j = 0 ; j<M ;j++){
                dfs(i,j,0,0);
                exp(i,j);
            }
        }
        System.out.println(max);


    }
}
