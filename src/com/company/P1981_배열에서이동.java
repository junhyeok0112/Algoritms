package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//매개 변수 탐색과 비슷
//이분 탐색에서 mid 값을 최대 - 최소로 하고 이 mid값을 만족시키는 경로가 있는지 체크해야함
//이 mid값이 가장 작을떄가 정답
//즉 mid가 2이면 최소값 , 최소값 + 2 인 수들만 밟아서 BFS를 만족 시키는지 체크해야함
public class P1981_배열에서이동 {

    static int n;
    static int[][] map;
    static int min = Integer.MAX_VALUE;
    static int max = -1;
    static int ans = Integer.MAX_VALUE;     //정답 출력 , 최소의 값을 출력해야하므로 MAX로 초기화
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]); //최대값 저장
                min = Math.min(min, map[i][j]); //최소값 저장
            }
        }

        //min , max를 기반으로 이분 탐색하기
        //즉 이분탐색에서 mid 값이 정답 (최대 - 최소 ) 즉 start = 0 , end = max - min으로 이분탐색
        binarySearch(min, max);

        System.out.println(ans);

    }


    static void binarySearch(int min, int max) {
        int left = 0;
        int right = max - min;

        while (left <= right) {
            int mid = (left + right) / 2;    //차이가 mid라고 가정 -> 이 가정이 적합한지 체크
            boolean flag = false;
            for (int i = min; i + mid <= max; i++) { // i 를 이용해서 차이가 mid인 경로들의 숫자 범위 지정 , 즉 i ~ i+mid까지 숫자들로만 이루어져있는 경로가 있는지 확인
                //i ~ i+mid 까지의 숫자로 이루어진 경로가 존재하면 mid라는 정답 후보를 만족시키는 숫자가 존재
                //아래 두 변수가 시작지점부터 만족해야함 -> 만약
                int start = i;  //만족 시킬 수 있는 최소값
                int end = i + mid;  //만족 시킬 수 있는 최대값
                flag = bfs(start, end);
                if (flag) break;     //만족시키는게 있으면 멈춤
            }

            //만족 시킬 경우
            if (flag) {
                ans = Math.min(ans, mid);   //정답 후보
                right = mid - 1;            //더 작은 값 있나 확인
            } else {                         //더 큰 (mid)차이로 범위 넓혀서 다시 탐색
                left = mid + 1;
            }

        }
    }


    static boolean bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visit = new boolean[n + 1][n + 1];
        if (start > map[1][1] || map[1][1] > end) return false;
        q.add(1);
        q.add(1);
        visit[1][1] = true;
        while (!q.isEmpty()) {
            int rx = q.poll();
            int ry = q.poll();
            if(rx == n && ry == n) return true; //끝에 도달했을시
            for(int i = 0 ; i<4 ;i++){
                int nx = rx + dx[i];
                int ny = ry + dy[i];
                if(nx<=0 || nx>n || ny<=0 || ny>n || visit[nx][ny] || map[nx][ny] <start || map[nx][ny] >end) continue; //범위를 넘어갔을 경우 , start ~ end 사이에 없을 경우
                q.offer(nx);
                q.offer(ny);
                visit[nx][ny] = true;
            }
        }
        return false;   // 다 돌았는데 n,n 도착 못했을 경우
    }


}
