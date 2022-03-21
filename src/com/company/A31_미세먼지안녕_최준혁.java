package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class A31{
    int x;
    int y;
    A31(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class A31_미세먼지안녕_최준혁 {

    static int r,c ,t  ;
    static int[][] map;
    static int[][] cleaner = new int[2][2];
    static Queue<A31> q = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int[] updy = {0,1,0,-1};
    static int[] updx = {-1,0,1,0};
    static int[] downdy ={0,1,0,-1};
    static int[] downdx = {1,0,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        map = new int[r][c];
        int temp = 0;		//공기 청정기 위치 저장을 위한 변수
        for(int i = 0 ; i<r ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j<c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    cleaner[temp][0] = i;
                    cleaner[temp][1]= j;
                    temp++;
                } else if(map[i][j] != 0){
                    q.add(new A31(i, j));
                }
            }
        }
        //t초만큼 반복하면서 확산 시켜야함.
        while(t-->0){
            map = pro();
        }
        int ans = 0;
        for(int i = 0 ; i<r; i++){
            for(int j = 0 ; j<c; j++){
                if(map[i][j] == -1)  continue;
                ans+=map[i][j];
            }
        }
        System.out.println(ans);


    }

    static int[][] pro(){
        int qsize = q.size();
        int[][] temp = new int[r][c];
        //boolean[][] visit = new boolean[r][c];       //똑같은 곳을 또 q에 넣지 않게하기위해서.
        temp[cleaner[0][0]][cleaner[0][1]] = -1;	//-1 로 셋팅
        temp[cleaner[1][0]][cleaner[1][1]] = -1;
        for(int t = 0 ; t<qsize; t++){
            A31 cur = q.poll();
            int rx = cur.x;
            int ry = cur.y;
            int cnt = 0;
            for(int i = 0 ; i<4 ;i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx<0 || nx>=r || ny<0 ||ny >=c || map[nx][ny] == -1 ) continue;
                //확산 가능한 장소면
                temp[nx][ny] += (map[rx][ry]/5);	//확산 시키고
                cnt++;							//확산 갯수 체크
            }
            temp[rx][ry] += map[rx][ry] - (map[rx][ry]/5) * cnt;
            //map[rx][ry] -= (map[rx][ry]/5) *cnt;

        }
        //temp 배열에 들어가 있음.
        //위에 청소기
        int idx = 0;
        int x = cleaner[0][0];
        int y = cleaner[0][1];
        while(idx < 4){
            int nx = x + updx[idx];
            int ny = y + updy[idx];
            if(nx>=0 && nx <=cleaner[0][0] && ny>=0 && ny<c){	//범위 안이면
                if(temp[x][y] == -1) {	//진공 청소기를 만났으면
                    temp[nx][ny] = 0;	//청소기로 사라짐
                    x = nx;				//값 갱신
                    y = ny;
                } else if(temp[nx][ny] == -1){  //공기 청정기에서 바람나오는거면
                    temp[x][y] = 0;
                    x = nx;
                    y = ny;
                }
                else{					//안만났으면
                    temp[x][y] = temp[nx][ny];	//값 땡겨옴
                    x = nx;
                    y = ny;
                }
            } else{
                idx++;
            }
        }

        //아래 청소기

        idx = 0;
        x = cleaner[1][0];
        y = cleaner[1][1];
        while(idx < 4){
            int nx = x + downdx[idx];
            int ny = y + downdy[idx];
            if(nx>=cleaner[1][0] && nx <r && ny>=0 && ny<c){	//범위 안이면
                if(temp[x][y] == -1) {	//진공 청소기를 만났으면
                    temp[nx][ny] = 0;	//청소기로 사라짐
                    x = nx;				//값 갱신
                    y = ny;
                } else if(temp[nx][ny] == -1){  //공기 청정기에서 바람나오는거면
                    temp[x][y] = 0;
                    x = nx;
                    y = ny;
                }else{					//안만났으면
                    temp[x][y] = temp[nx][ny];	//값 땡겨옴
                    x = nx;
                    y = ny;
                }
            } else{
                idx++;
            }
        }
        //값이 있는 곳들에 대해서 다시 q에 넣어줘야함
        for(int i = 0 ; i<r; i++){
            for(int j = 0 ; j<c; j++){
                if(temp[i][j] != -1 && temp[i][j] !=0 ) q.offer(new A31(i,j));
            }
        }
        //temp를 반환해줘야함
        return temp;
    }

}
