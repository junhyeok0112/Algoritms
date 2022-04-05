package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

//삼성 아카데미 1767 프로세서 연결하기
public class 과제03_프로세서연결하기_최준혁 {

    static int n, max,totalCnt, min ,map[][];
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for(int tc = 1; tc<=TC; tc++){
            n = Integer.parseInt(br.readLine());        //셀의 크기
            map = new int[n][n];
            list = new ArrayList<>();               //가장 자리가 아닌 코어 리스트
            max = 0;    //최대 연결 코어수
            min = Integer.MAX_VALUE; //최소 전선길이의합
            totalCnt = 0;       //가장자리가 아닌 코어의 개수
            for(int i = 0 ; i<n ; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0 ; j<n ;j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if( (i==0 || i ==n-1 ||j ==0 ||j ==n-1) && map[i][j] == 1)continue;       //가장자리 코어일 경우
                    if(map[i][j] == 1) {
                        list.add(new int[] {i,j});       //가장자리가 아닌 코어 추가
                        totalCnt++;
                    }
                }
            }
            //list에 가장자리가 아닌 코어들만 저장되어 있음
            go(0,0);

            System.out.println("#"+tc+" "+min);
        }
    }

    static void go(int index , int cCnt){   //부분집합으로 코어 놓기 시도 , cCnt : 전원과 연결된 코어 수 , index : 시도할 코어

        //기저조건
        if(index == totalCnt){  //전부 연결됐으면
            int res = getLenght();
            if(max<cCnt){       //기존 최대 코어 연결 수 보다 현재 코어 연결수가 크면
                max =cCnt;
                //코어를 최대로 연결했을 때 , 전선의 최소 값을 구해야함.
                min = res;
            }else if(max == cCnt){      //최대 연결 코어수가 같다면
                if(min >res) min = res;
            }
            return;
        }


        int[] core = list.get(index);       //처리해야하는 코어 꺼내기
        int r = core[0];
        int c = core[1];
        //전선을 놓아보기l(4방향으로)
        for(int d = 0; d< 4; d++){
            if(isAvailable(r,c,d)){     //r,c위치에서 d방향으로 놓는게 가능하다면 전선을 놓아보자
                setStatus(r,c,d,2);   //2로 값을 채우면서 전선 놓기 작업
                go(index+1 , cCnt+1);   //다음 코어 보기 -> index 증가시키고 이전에 연결했으니 cCnt 증가
                setStatus(r,c,d,0);             //끝까지 가서 처리가 끝나면 놓았던 전선 회수해서 다음꺼 봐야함 -> 백트래킹 형식
            }
        }

        //전선 놓지 않기
        go(index+1 , cCnt);     //안놓고 다음 코어 확인해보기
    }

    static boolean isAvailable(int r, int c, int d){//r,c위치에서 d 방향으로 전선을 놓을 수 있는지 체크
        int nx = r , ny = c;
        while (true){
            nx +=dx[d];
            ny +=dy[d];
            if(nx< 0 ||nx>=n ||ny<0 ||ny>=n)break;
            //다른 코어나 전선을 만나면 return false;
            if(map[nx][ny] >= 1 ) return false;
        }
        return true;    //만나는 애 없어서 가능한 상황
    }

    static void setStatus(int r, int c, int d, int s){  //r,c위치에서 d방향으로 전선을 놓거나 지우거나 , 다른뱡향으로 놓으려면 기존껄 지워야함
        int nx = r , ny =c;
        while (true){
            nx += dx[d];
            ny += dy[d];
            if(nx<0 || nx>= n||ny<0 || ny>=n) break;
            map[nx][ny] = s;
        }
    }
    static int getLenght(){         //놓아진 전선의 길이의 합 계산산
        int lCnt = 0;
        for(int i = 0 ;i<n ;i++){
            for(int j =0; j<n; j++){
                if(map[i][j] == 2) lCnt++;
            }
        }
        return lCnt;
    }

}
