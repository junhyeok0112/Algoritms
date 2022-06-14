package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P14890_경사로 {

    static int n, l;
    static int[][] map;
    static int[][] rmap;    //바뀌면서 이상한 값이 들어가는걸 방지하기 위해 열만 체크하는 rmap 생성
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        rmap = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rmap[j][i] = map[i][j];
            }
        }
        pro();
        System.out.println(ans);

    }

    static void pro() {

        for (int i = 0; i < n; i++) {
            //행 기준으로 확인하기
            ans += findRow(map[i]); //이러면 값이 바뀜 -> 따라서 다른 배열 만들어야함
            //열 기준으로 확인하기
            ans += findRow(rmap[i]);
        }

    }

    //입력받은 배열로 확인해보기.
    static int findRow(int[] arr) {

        int arrSize = arr.length;
        boolean[] visit = new boolean[n];   //그 위치에 경사로가 건설되어 있는지 체크
        for(int i = 0 ; i<n-1; i++){
            int cur = arr[i];
            int next = arr[i+1];
            if(Math.abs(cur - next) >= 2) return 0; //차이 2이 상이면 불가
            if(visit[i+1] || cur == next) continue;   //i위치 다음에 경사로가 세어져있거나 다음것과 높이가 동일하면 continue , 경사로 설치되어있으면 설치 가능한 것이므로 넘어감
            else{
                if(cur > next){ //다음꺼가 더 작으면
                    for(int j = i+1 ;j<=i+l; j++){  //next 지점 부터 l길이의 경사로 세울 수 있는지 확인하기 위해 셋팅
                        if(j>=n || visit[j] || next != arr[j]) return 0; //범위 n 안에 있고 값이 일정해야하고 , 이미 경사로가 설치되어서 값이 변경된 곳이면 안됨
                        if(j != i+l) arr[j] = next + 1;      //경사로 설치한 곳은 높이를 cur 과 같게 해줘야함 , 단 마지막은 그 뒤에와의 비교를 위해 냅둠
                        visit[j] = true;                //경사로 설치할 수 있으므로 경사로 설치
                    }
                } else{     //다음꺼가 높을떄 , cur부터  l 만큼 경사로를 설치해야한다.
                    for(int j = i ; j>i-l; j--){
                        if(j <0 || visit[j] || arr[j] != cur) return 0;
                        if(j != i-l ) arr[j] = cur+1;       //한칸 높혀서  next +1 과 맞춤
                        visit[j] = true;
                    }

                }
            }
        }
        return 1;
    }

}
