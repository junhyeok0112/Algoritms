package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class P14267_회사문화1 {

    static int N,M;
    static ArrayList<Integer>[] tree;
    static boolean[] visit;
    static int[] Allscore;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N+1];
        Allscore = new int[N+1];
        visit = new boolean[N+1];
        for(int i =1 ; i<=N ;i++) tree[i] = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i =1; i<=N ;i++){
            int num = Integer.parseInt(st.nextToken());
            if(i == 1 ) continue;
            tree[num].add(i); //이어져 있는 사람과 그 사람의 점수 표현
            //tree[num].add(new Emp(i,0));
            //상사번호는 자신보다 작음
        }

        //칭찬받은 start 직원 index에 Allscore 배열에 칭찬 점수 저장
        //그 밑에 부하직원들은 위에 상사의 점수를 그대로 상속 , 만약 추가로 칭찬받은 점수가 있으면 더해서 추가해주
        for(int i = 0; i<M ;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            Allscore[start] += score; //2번 직원이 2점을 받았다 . 그리고 나중에 dfs를 통해서 2번 밑에 직원들이 2점 + 자기점수를 받음
        }

        dfs(1, 0);

        for(int i =1 ;i <=N ;i++){
            sb.append(Allscore[i]).append(" ");
        }
        System.out.println(sb);
    }

    //이렇게 하면 dfs한번에 끝낼 수 있음
    //핵심은 상사의 점수를 상속받는다
    static void dfs(int start , int score){         //score는 여태까지 받은 점수
        visit[start] = true;
        Allscore[start] +=score;                    //들어온 곳에 여태까지 받은 칭찬 점수 더해줌
        for(int cur : tree[start]){
            if(!visit[cur]){
                dfs(cur,  Allscore[start]);        //부하직원이 가지고 있는 점수에다가 상사가 가진 점수 더해줘서 부하직원 점수 갱신
            }
        }
    }
}
