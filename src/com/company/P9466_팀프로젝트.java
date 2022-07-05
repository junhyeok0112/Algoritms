package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P9466_팀프로젝트 {

    static int n,t, cnt;
    static int[] arr;
    static boolean[] visit;      //해당 학생을 방문 했는지 체크 여부
    static boolean[] done;      // 해당 학생이 탐색이 종료되었는지 확인 -> 만약 visit가 true 인데 done 이 false면 싸이클을 이루는 것
    //왜냐하면 방문했는데 종료가 안되었다는 것은 돌고 돌아서 결국 자기 자신으로 돌아왔다는 것이기 때문이다.

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        int tc = 0 ;
        while (tc++ < t){
            n = Integer.parseInt(br.readLine());
            arr = new int[n+1];
            visit = new boolean[n+1];
            done = new boolean[n+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 1 ;i<=n ; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            cnt = 0;
            //각 지점에서 dfs 실행
            for(int i = 1 ; i<=n ;i++){
                dfs(i);
            }

            System.out.println(n - cnt);        //cnt는 팀을 이룬 사람의 수
        }
    }

    static void dfs(int now){
        if(visit[now]) return;      //방문한 곳이면 체크 안함
        visit[now] = true;          //방문 체크
        int next = arr[now];        //다음을 가리킴
        if(!visit[next]){           //다음이 방문안한 지점이면 그 지점으로 가서 탐색
            dfs(next);
        } else{                     //만약 다음이 탐색한 지점이면
            if(!done[next]){        //다음 지점이 방문은 했는데 탐색이 안 끝났으면 싸이클을 이루는 곳이다.
                //따라서 싸이클을 이루므로 현재 now부터 싸이클을 이루는 팀원들 세주어야함
                cnt++;  //now 카운트
                while (next != now){    //next == now면 싸이클 다 돌아서 자기 자신에게 온 것
                    cnt++;
                    next = arr[next];   //이어지는 다음 학생 체크
                }
            }
        }
        done[now] = true;           //해당 학생 탐색 끝
    }
}
