package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class P1477 implements Comparable<P1477>{
    int start;
    int end ;
    int dis ;
    P1477(int start ,int end ,int dis){
        this.start = start;
        this.end = end;
        this.dis = dis;
    }

    //거리가 먼 것이 앞으로 오게
    @Override
    public int compareTo(P1477 o) {
        return o.dis - this.dis;
    }
}

public class P1477_휴게소세우기 {

    static int n , m,l;
    static int[] arr;
    static boolean[] visit;
    static PriorityQueue<P1477> pq = new PriorityQueue<>();

    //휴게소를 추가로 지었을 때, 휴게소들의 거리가 최소인 값을 구하여라.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[n];                       //휴게소 위치들이 저장되어 있음
        visit = new boolean[l+1];               //1~l까지 거리 중 휴게소가 있는 거리인지 체크
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
            visit[arr[i]] = true;
        }
        visit[0] = true;
        visit[l] = true;
        Arrays.sort(arr);       //세워진 순으로 정렬
        for(int i = 0 ;i<n-1 ;i++){
            int dis = arr[i+1] - arr[i];    //서로의 거리
            pq.offer(new P1477(arr[i], arr[i+1] , dis));
        }
        pq.offer(new P1477(0,arr[0],arr[0]));   //고속도로 시작점과 맨 처음휴게소
        pq.offer(new P1477(arr[n-1],l,l-arr[n-1])); //고속도로 끝점과 맨 마지막 휴게소

        //M개의 휴게소 세우기
        for(int k = 0 ; k< m ;k++){
            P1477 cur = pq.poll();      //최대 거리인거 빼기
            int start = cur.start;
            int end = cur.end;
            int tempStart = start;
            int tempEnd = end;
            while (tempStart <= tempEnd){
                int mid = (tempStart + tempEnd) / 2;        //휴게소를 지을 위치
                if(visit[mid]){
                    //방문 한 곳이면
                    tempEnd = mid -1;
                } else{     //방문 안했으면
                    pq.offer(new P1477(start ,mid, mid-start));
                    pq.offer(new P1477(mid,end,end - mid));
                    break;
                }
            }

        }
        System.out.println(pq.poll().dis);
    }
}
