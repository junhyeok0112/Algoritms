package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class P11403_경로찾기 {

    static int N;
    static int[][] map;
    static boolean[] visit;
    static int[][] ans ;
    static ArrayList<Integer>[] graph;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = new int[N][N];
        visit = new boolean[N];
        graph = new ArrayList[N];
        for(int i = 0 ; i<N ;i++) graph[i] = new ArrayList<>();

        Queue<Integer> q = new LinkedList<>();  //1인 지점 저장
        for(int i = 0 ; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<N ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    q.add(i);
                    q.add(j);
                    graph[i].add(j);
                }
            }
        }

        floyd();
//        while(!q.isEmpty()){
//            int root = q.poll();
//            int x = q.poll();
//            dfs(root, x);
//            Arrays.fill(visit,false);   //visit 초기화
//        }

        for(int i = 0 ; i<N ;i++){
            for(int j = 0 ; j<N ;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


    }

    static void floyd(){
        for(int k = 0 ; k<N; k++){
            for(int i = 0; i<N ;i++){
                for(int j = 0 ; j<N ;j++){
                   //갈수 있는 경로가 존재하는지 체크
                    if(map[i][k] == 1 && map[k][j] == 1){   //k 를 거쳐서 i부터 j 까지 갈 수 있으면 i와 j는 연결되어있다.
                        map[i][j] = 1;
                    }
                }
            }
        }
    }

//    static void dfs(int root , int x){
//        visit[x] = true;
//        ans[root][x] = 1;
//        for(int i = 0 ; i<N ;i++){
//            if(map[x][i] == 1 && !visit[i]){
//                dfs(root, i);
//            }
//        }
//    }


}
