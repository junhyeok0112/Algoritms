package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 실습13_활주로건설_최준혁 {

    static int tc,n,x;
    static int[][] map;
    static int[][] rmap;
    static StringBuilder sb = new StringBuilder();
    static int ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        tc =Integer.parseInt(br.readLine());
        int t = 0;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            map = new int[n][n];                            //map[0][i] 와 map[i][0]에 그 줄의 최대값 저장
            rmap = new int[n][n];
            ans = 0;
            for(int i = 0 ; i<n ;i++){
                st = new StringTokenizer(br.readLine());
                for(int j =0 ;j<n ;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    rmap[j][i] = map[i][j];         //배열 돌린거 -> 함수 하나로 처리할 수 있게하기 위해서
                }
            }

            ans = sol();

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static public int sol(){
        int answer = 0;
        for(int i = 0 ;i<n ;i++){
            answer+= check(map[i]);
            answer+= check(rmap[i]);
        }
        return answer;
    }

    static int check(int[] arr) {
        boolean[] visit = new boolean[n];       //해당 칸에 경사가 설치되어 있는지 확인하는 메소드 -> true면 알맞는 경사가 설치되어 있음

        for(int i = 0 ; i<n-1;i++){
            int cur = arr[i];       //현재 값
            int next = arr[i+1];     //다음값
            if(Math.abs(cur - next) >=2) return 0;
            if(visit[i+1] || cur == next) continue;     //평지이거나 이미 경사가 설치되어 있다면 넘어감
            //내려갈때
            if(cur > next){
                for(int j = i+1 ;j<=i+x; j++){      //next 지점 부터 x 만큼 경사로를 설치할 수 있는지 확인
                    //next와 값이 일정해야함 또 n의 범위 안에 있어야함 , 경사로가 설치되어있지 않아야함
                    if( j>=n|| arr[j] != next || visit[j]) return 0;

                    if(j != i+x) arr[j] = next +1 ;     //경사로 설치로 높이 증가했으니 셋팅 ,단 맨마지막 칸은 그 다음칸과의 비교를 위해 그대로 냅두어야함
                    //예를들어 3 3 3 2 2 2 1 1 일때 2 2 2에서 마지막 2도 3으로 갱신하면 3 -> 1이여서 불가능하다 판단
                    //이를 방지하기 위해서 마지막 2는 유지

                    visit[j] = true;                    //해당 위치에 경사로 설치!
                }

            } else{     //올라갈때
                for(int j = i ; j> i-x;j--){
                    if(j <0 || arr[j]!= cur || visit[j] ) return 0;      //같은 값이 아니거나 범위 밖이거나 이미 설치되어있거나
                    if(j != i+x) arr[j] = cur+1;                            //한칸 높힘
                    visit[j] = true;
                }
            }

        }
        return 1;
    }
}
