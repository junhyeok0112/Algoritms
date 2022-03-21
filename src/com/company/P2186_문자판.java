package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2186_문자판 {

    static int ans = 0;
    static int k,n,m;
    static String s;
    static char[][] map ;
    static int[][][] dp ;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    //DP 문제이다 , 메모이제이션 문제 , 거기까지 방문했을때 가능한지를 체크하는 것 ..
    //그래도 너무 어렵다 .
    //2차원 배열의 메모이제네이션 문제는 3차원 DP를 만들어야한다고 생각하자.


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i = 0 ; i<n ;i++){
            String str = br.readLine();
            for(int j = 0 ;j<m ;j++){
                map[i][j] = str.charAt(j);
            }
        }

        s = br.readLine();                    //찾을 문자열
        //System.out.println(s.length());
        dp = new int[n][m][s.length()];     //n,m지점에서 찾을 문자열의 index번째 를 확인할 때 가능한지 체크
        //dp가 의미하는 바는 n,m 지점에서 index번째로 도착했을 때 정답으로 가능한 갯수이다.

        for(int i = 0; i<n ;i++){
            for(int j = 0 ;j<m;j++){
                for(int k= 0; k<s.length(); k++){
                    dp[i][j][k] = -1;               //-1로 초기화
                }
            }
        }

        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<m ;j++){
                if(map[i][j] == s.charAt(0)){           //모든 점에서 체크합니다 . 이 때 dp배열은 따로 갱신할 필요가 없습니다.
                    ans += dfs(i,j,0);
                }
            }
        }

        System.out.println(ans);


    }

    static int dfs(int x , int y , int index){
        if(index == s.length()-1){              //끝까지 봤을 때 가능하면 1 추가 , 이미 가능한 경우만 봄
            return dp[x][y][index] = 1;
        }
        if(dp[x][y][index] != -1){              //이미 방문한 곳이면
            return dp[x][y][index];
        }

        dp[x][y][index] = 0;                    //방문 처리

        for(int t = 1; t<=k;t++){
            for(int i = 0; i<4; i++){
                int nx = x + dx[i] * t;         //최대 K만큼 탐색
                int ny = y + dy[i] * t;
                if(nx >=0 && nx< n && ny>= 0 && ny<m && map[nx][ny] == s.charAt(index+1)){         //다음에 볼곳이 범위 안이고 찾는 문자면
                    dp[x][y][index] += dfs(nx,ny,index+1);              //nx,ny를 통해 가능한 정답 개수들을 x,y dp에 저장
                }
            }
        }

        return dp[x][y][index];

    }


}
