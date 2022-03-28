package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class P17090{
    int x,y;
    P17090(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P17090_미로탈출하기 {

    static int n,m;
    static char[][] map;
    static boolean[][] visit;
    static boolean[][] possible;
    static int[] dx = {-1,0,1,0};       //U,R,D,L
    static int[] dy = {0,1,0,-1};
    static Stack<P17090> sta = new Stack<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visit = new boolean[n][m];
        possible = new boolean[n][m];
        for(int i = 0 ;i<n; i++){
            String str = br.readLine();
            for(int j = 0 ;j<m;j++){
                map[i][j] = str.charAt(j);
            }
        }
        //DFS로 탐색 후 예전 결과 저장해놔야함
        //유니온 파인드? ? 어떻게 써
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ;j<m;j++){
                if(visit[i][j]) continue;
                dfs(i,j);
            }
        }

        int ans = 0;
        for(int i=  0 ;i<n ;i++){
            for(int j = 0 ; j<m;j++){
                if(possible[i][j]) ans++;           //가능한 경우 전부 세어줌
            }
        }
        System.out.println(ans);
    }

    private static void dfs(int i, int j) {
        if(i<0 || i>=n || j <0 ||j>= m){        //범위 벗어났으면
            //스택에 있는거 뺴면서
            while (!sta.isEmpty()){
                P17090 r = sta.pop();
                possible[r.x][r.y] = true;      //가능하게 바꿈
            }
            return;
        }
        if(visit[i][j]) {                   //이미 방문한 곳이면 -> 싸이클 생겨서 불가능
            while (!sta.isEmpty()){
                P17090 r = sta.pop();
                possible[r.x][r.y] = possible[i][j];      //불가능하게 바꿈
            }
            return;
       }
        visit[i][j] = true;
        sta.push(new P17090(i,j));
        if(map[i][j] == 'U'){
            dfs(i-1,j);
        } else if(map[i][j] =='R'){
            dfs(i,j+1);
        } else if(map[i][j] =='D'){
            dfs(i+1,j);
        } else if(map[i][j] =='L'){
            dfs(i,j-1);
        }
    }


}
