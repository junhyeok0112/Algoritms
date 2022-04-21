package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//5656. [모의 SW 역량테스트] 벽돌 깨기
class P5656 {
    int x;
    int y;
    int val;

    P5656(int x, int y, int val) {
        this.x = x;
        this.y = y;
        this.val = val;
    }
}

public class 실습09_벽돌깨기 {

    static int n, w, h, min;   //map[h][w]로 구현
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            int[][] map = new int[h][w];            //지도
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            rec(0, map);

            System.out.println("#" + tc + " " + min);

        }
    }

    //하나의 map으로 계속 갱신하면서 할 수 없으므로 map을 가지고 다녀야함 -> newMap으로 하면 ? 생각해보자 혼자할때
    static boolean rec(int cnt, int[][] map) {  //중복순열을 이용하여 구슬 던지기, 벽돌이 다 부셔졋으면 true 아니면 false
        int result = getCount(map);
        if (result == 0) {        //만약 모든 벽돌이 부셔졌다면
            min = 0;
            return true;        //어떤 걸 해도 0보다 작을 수 없으므로 계산 끝
        }

        //n번까지 전부 던졌을 경우 종료
        if (cnt == n) {
            min = Math.min(min, result);     //남은 벽돌의 최소값 갱신
            return false;                   //지금보다 더 적은 벽돌 갯수가 나올 확률 존재 ->따라서 추가적으로 탐색해야함
        }

        int[][] newMap = new int[h][w];
        //0~ w-1열까지 구슬 던져보기
        for (int y = 0; y < w; y++) {
            //구슬에 맞는 벽돌 찾기
            int x = 0;
            while (x < h && map[x][y] == 0) ++x;       //빈공간이면 계속해서 아래로
            //해당열은 벽돌이 없음
            if (x == h) continue;      //벽돌이 있는 곳에먼저 구슬 던질거야

            //배열 상태를 백업
            copy(map, newMap);

            bfs(newMap, x, y);               //해당 벽돌이 있는 위치에 구슬 던져서 벽돌 깨기

            //벽돌 내리기
            down(newMap);

            //다음 구슬로 가서 던지기 , 다음 구슬 던지러 갔는데 전부 깨져서 true가 호출되면 재귀 끝내야함
            if (rec(cnt + 1, newMap)) return true;

        }
        return false;       //어떻게 해도 다 부수는 경우가 없더라
    }

    static void bfs(int[][] map, int x, int y) {      //x,y위치에서 가능한 벽돌 깨기
        Queue<P5656> q = new LinkedList<>();
        if (map[x][y] > 1) {      //벽돌 크기가 2 이상이면 큐에 넣어서 사방 탐색 1이면 자신만 제거하면됨
            q.offer(new P5656(x, y, map[x][y]));
        }
        map[x][y] = 0;      //자신은 제거 처리 -> 큐에 넣든 안넣든 , visit 처리와 같은 역할

        while (!q.isEmpty()) {
            P5656 r = q.poll();
            int rx = r.x;
            int ry = r.y;
            int rVal = r.val -1;
            for (int i = 0; i < 4; i++) {
                int nx = rx;
                int ny = ry;
                for (int k = 0; k < rVal; k++) {    //벽돌 크기 - 1 만큼 반복
                    nx += dx[i];
                    ny += dy[i];
                    if (nx >= 0 && nx < h && ny >= 0 && ny < w && map[nx][ny] > 0) {
                        if (map[nx][ny] > 1) {          //크기 2 이상인 벽돌 -> 주변 벽돌에 영향
                            q.offer(new P5656(nx, ny, map[nx][ny]));
                        }
                        //0으로 다시 처리하므로 visit 체크가 필요가 없다
                        map[nx][ny] = 0;            //다른 경우(빈공간 or 크기 1) or 2이상이여도 0으로 변경해야하므로 0으로 변경
                    }
                }
            }
        }
    }

    static ArrayList<Integer> list = new ArrayList<>();

    static void down(int[][] map) {                 //떠있는 벽돌 내리기
        //방법 1 열 고정시켜놓고 전부 하나씩 올라가면서 체크 , swap하면 조금 간단
//        for(int y = 0 ; y<w; y++){
//            int x = h-1;    //시작 행 -> 가장 아래행
//            while (x>0){
//                if(map[x][y] == 0 ){    //올라가다가 빈칸이면 , 내릴 벽돌 찾기
//                    int nx = x -1;
//                    while (nx>0 && map[nx][y] == 0) nx--;       //계속 빈칸이면 행 줄이기 , 맨 위에 가거나 벽돌을 만나면 그만하기
//                    map[x][y] = map[nx][y];                     //현재 빈칸에 내가 찾은 벽돌 or 칸 넣기
//                    map[nx][y] = 0;                             //옮겼으니 비워둠
//                }
//                x--;                                            //빈칸 아니면 그 위에 보기
//            }
//        }

        //방법 2 , 자료구조 Queue나 Stack을 선언해서 빈공간이 아닌 벽돌들을 넣은 뒤 다시 뿌려줌 ->너무좋은데 ?
        for (int y = 0; y < w; y++) {
            int x = h - 1;    //시작 행 -> 가장 아래행
            while (x >= 0) {
                if (map[x][y] > 0) {    //올라가다가 빈칸이면 , 내릴 벽돌 찾기
                    list.add(map[x][y]);        //벽돌이면 벽돌 크기 저장
                    map[x][y] = 0;
                }
                x--;                                            //빈칸 아니면 그 위에 보기
            }       //부서지지 않고 남아있는 벽돌 리스트에 다 담기, 벽돌이 있던 자리는 빈공간으로 처리

            x = h - 1;        //제일 아래부터 채우기
            for (int val : list) {
                map[x][y] = val;
                x--;
            }
            list.clear();
        }
    }

    static int getCount(int[][] map) {             //벽돌 갯수 세기\
        int cnt = 0;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] >= 1) cnt++;       //0이 아닌 것들의 개수를 세야함
            }
        }
        return cnt;
    }

    static void copy(int[][] map, int[][] newMap) {                 //하나의 배열로 할 수 없으므로 배열 복사해놔야함
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                newMap[i][j] = map[i][j];
            }
        }
    }

}
