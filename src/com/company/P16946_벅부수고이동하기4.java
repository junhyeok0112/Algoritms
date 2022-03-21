package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P16946{
    int x;
    int y;
    P16946(int x, int y){
        this.x = x;
        this.y = y;
    }
}

class Group{
    int sum ;   //그룹내의 개수
    int num;    //그룹 번호

    Group(int sum ,int num){
        this.sum = sum;
        this.num = num;
    }
}

public class P16946_벅부수고이동하기4 {

    static int n,m;
    static int[][] map;
    static boolean[][] visit;
    static Group[][] answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        answer = new Group[n][m];
        visit = new boolean[n][m];

        for(int i = 0 ; i< n ;i++){
            String str = br.readLine() ;
            for(int j = 0 ; j< m ;j++){
                map[i][j] = str.charAt(j) -'0';
            }
        }

        int gnum = 1;
        for(int i = 0 ; i <n ; i++){
            for(int j = 0 ; j< m; j++){
                if(map[i][j] == 0 && !visit[i][j]){
                    bfs(i,j,gnum);
                    gnum++;
                }
            }
        }


        //그룹번호 , 그룹 내의 갯수 2가지를 이용하면 중복된 그룹인지 판별해서 해결가능
        for(int i = 0 ; i<n ;i++){
            for(int j = 0 ; j<m ; j++){
                if(map[i][j] == 0){             //0이면 0출력
                    sb.append(0);
                } else{                         //1이라 벽이면
                    HashSet<Integer> hs = new HashSet<>();      //그룹 번호 넣을 Hash선언
                    int sum = 1;                                //벽이 부셔졌을때 총 갯수 , 본인 포함하므로 1부터시작
                    for(int t = 0 ; t<4 ;t++){
                        int nx = i + dx[t];
                        int ny = j + dy[t];
                        if(nx>= 0 && nx< n && ny>=0 && ny<m){
                            if(map[nx][ny] == 0){               //범위 안인데 , 0이면
                                if(!hs.contains(answer[nx][ny].num)){       //만약 이미 계산 한 부분이 아니면
                                    hs.add(answer[nx][ny].num);
                                    sum += answer[nx][ny].sum;
                                }
                            }
                        }
                    }
                    sb.append(sum%10);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());


    }

    static void bfs(int x, int y , int gnum){
        Queue<P16946> q = new LinkedList<>();
        Queue<P16946> temp = new LinkedList<>();        //0들 위치에 저장할 배열
        q.add(new P16946(x,y));
        temp.add(new P16946(x,y));
        visit[x][y] = true;
        while (!q.isEmpty()){
            P16946 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            for(int i = 0 ; i< 4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx>=0 && nx< n && ny>= 0 &&ny<m){
                    if(!visit[nx][ny] && map[nx][ny] ==0){
                        visit[nx][ny] = true;
                        q.add(new P16946(nx,ny));
                        temp.add(new P16946(nx,ny));
                    }
                }
            }
        }
        int sum = temp.size();      //한부분이 몇개인지
        while (!temp.isEmpty()){
            P16946 r = temp.poll();
            answer[r.x][r.y] = new Group(sum ,gnum );     //그 뭉텅이 갯수로 셋팅
        }
    }


}
