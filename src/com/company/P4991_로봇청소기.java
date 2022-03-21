package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Dirty{
    int x;
    int y;
    boolean chk ;
    Dirty(int x, int y){
        this.x = x;
        this.y =y;
        chk = false;
    }
}

public class P4991_로봇청소기 {

    static int w,h;
    static char[][] map;
    static int[][] visit;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static int min ;
    static int[] dustBack;

    //청소기와 먼지들의 거리를 모두 구하고 순열을 통해서 그 거리합의 최소값을 구하면된다
    //즉 입력받았을떄 그들끼리의 최소값을 구하면됨
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w ==0 && h ==0) break;
            map = new char[h][w];
            min = Integer.MAX_VALUE;
            boolean possible = true;
            ArrayList<Dirty> arr = new ArrayList<>();   //더러운 위치 저장
            for(int i = 0 ; i<h ;i++){
                String s = br.readLine();
                for(int j = 0 ; j<w ;j++){
                    map[i][j] = s.charAt(j);
                    if(map[i][j] == 'o'){
                        arr.add(0,new Dirty(i,j));    //시작위치 저장 , 맨앞에오게 저장
                    } else if(map[i][j] == '*'){    //더러운 곳이면 저장
                       arr.add(new Dirty(i,j));
                    }
                }
            }
            if(arr.size() == 1 ){       //청소할 곳이 없을때
                System.out.println(0);
                continue;
            }
            int[][] dis = new int[arr.size()][arr.size()];  //거리를 저장할 배열 arr[0][1] 은 청소기와 1번 먼지간의 거리
            for(int i = 0 ; i<arr.size();i++){
                Dirty cur = arr.get(i);
                bfs(cur , i ,arr , dis);  //거리들을 구할 시작점, 몇번째인지 , arr 같이 넘겨줌
            }
            for(int i = 1 ; i<dis[0].length; i++){
                if(dis[0][i] == -1){ //만약 청소기가 방문 못하는 곳 있으면 -1 출력 후 종료 , 방문 못한곳의 visit = 0 이고 -1 해서 dis에 저장했으므로 -1 이다
                    possible = false;//청소기가 모든점 방문가능한지 체크
                    break;
                }
            }
            if(possible){   //청소기가 모든점 방문 가능할떄
                //각 각 지점끼리의 거리 다 구했음 -> 순열을 이용해 최소값 찾아야함
                int dirtyCnt = arr.size()-1;   //먼지 개수들
                dustBack = new int[dirtyCnt];   //백트래킹에서 채워넣을 배열
                back(0,dirtyCnt , arr, dis);
                System.out.println(min);
            } else{
                System.out.println(-1);
            }
        }
    }

    //각 지점들의 거리를 구해줌
    static void bfs(Dirty cur , int index , ArrayList<Dirty> arr , int[][] dis){
        Queue<Dirty> q = new LinkedList<>();
        visit = new int[h][w];
        q.add(cur);
        int curx = cur.x;
        int cury = cur.y;
        visit[curx][cury] = 1;
        while (!q.isEmpty()){
            Dirty r = q.poll();
            int rx = r.x;
            int ry = r.y;
            for(int i = 0 ; i<4 ; i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx >= 0 && nx< h && ny>=0 && ny<w){
                    if(visit[nx][ny] == 0 && map[nx][ny] != 'x'){   //방문 안했고 가구가 아닐때 방문
                        visit[nx][ny] = visit[rx][ry] + 1;
                        q.add(new Dirty(nx,ny));
                    }
                }
            }
        }   //전부 다 둘러봤을 경우
        for(int i = 0 ; i<arr.size(); i++){
            Dirty temp = arr.get(i);    //index번째의 지점과 거리를 구할 먼지가 있는 지점
            dis[index][i] = visit[temp.x][temp.y] -1;
        }
    }

    //방문할 순서를 구해주는 백트래킹
    static void back(int index , int dirtyCnt,  ArrayList<Dirty> arr , int[][] dis){
        if(index >= dirtyCnt){   //전부다 구했을 경우
            //dustBack에 순서대로 몇번인덱스인지 찼음
            int sum = 0;
            for(int i = 0 ; i <dustBack.length -1 ; i++){   //마지막꺼는 안봐도되므로
                sum+= dis[dustBack[i]][dustBack[i+1]];  //i번쨰로 볼 장소와 i+1번째 장소와의 거리 더하기
            }
            sum += dis[0][dustBack[0]]; //청소기가 맨 처음 방문한곳 까지의 거리 구하기
            min = Math.min(sum, min);   //최소값 갱신
            return;
        }
        for(int i = 1 ; i<=dirtyCnt ; i++){  //백트래킹
            if(!arr.get(i).chk) {       //i번째 지점을 방문안했으면
                dustBack[index] = i;   //dustBack의 index 번째에 i 번째 위치의 먼지를 저장하겠다
                arr.get(i).chk = true;//방문 체크로 바꿈
                back(index + 1, dirtyCnt, arr, dis);
                dustBack[index] = 0;    //다 보고 그 지점을 배열에서 뺴줌
                arr.get(i).chk = false;
            }
        }
    }
}
