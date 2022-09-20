package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P13913_숨바꼭질4 {

    static int n,k;
    static int[][] arr;     //얼마의 시간으로 방문했는지와 어디서 방문했는지 체크

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[100001][2];
        //시작지점이 0일 수도 있으므로 구분하기 위하여 -1로 초기화
        //초기화 하지 않으면 경로에서 0이 나오지 않음
        for(int i = 0 ;i<=100000; i++){
            arr[i][1] = -1;
        }
        //BFS를 이용하여서 탐색 , 시작이 1이므로 -1 해야함
        System.out.println(bfs(n)-1);
        //위치가 같을 경우
        if(n == k){
            System.out.println(n);
            return;
        }
        //어떤 순서로 접근했는지 알려주는 함수
        find_road(k);
    }

    static int bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        arr[start][0] = 1;      //얼마만에 도착했는지 시간 저장
        while (!q.isEmpty()){
            int r = q.poll();
            if(r == k) return arr[r][0];
            if(r+1 >= 0 && r+1<=100000 && arr[r+1][0] == 0){
                q.offer(r+1);
                arr[r+1][0] = arr[r][0] + 1;
                arr[r+1][1] = r;        //어디서 왔는지 저장
            }

            if(r-1 >= 0 && r-1<=100000 && arr[r-1][0] == 0){
                q.offer(r-1);
                arr[r-1][0] = arr[r][0] + 1;
                arr[r-1][1] = r;        //어디서 왔는지 저장
            }

            if(2*r >= 0 && 2*r<=100000 && arr[2*r][0] == 0){
                q.offer(2*r);
                arr[2*r][0] = arr[r][0] + 1;
                arr[2*r][1] = r;        //어디서 왔는지 저장
            }
        }
        return -1;
    }

    static void find_road(int start){
        Stack<Integer> sta = new Stack<>();
        int num = start;
        while (num != -1){
            sta.push(num);
            num = arr[num][1];
        }
        int staSize = sta.size();
        for(int i = 0 ; i<staSize; i++){
            System.out.print(sta.pop()+" ");
        }
    }
}
