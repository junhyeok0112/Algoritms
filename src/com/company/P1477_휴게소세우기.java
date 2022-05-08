package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class P1477_휴게소세우기 {

    static int n , m,l;
    static int[] arr;
    static ArrayList<Integer> arr2 = new ArrayList<>();

    //휴게소를 추가로 지었을 때, 휴게소들의 거리가 최소인 값을 구하여라.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        arr = new int[n];                       //휴게소 위치들이 저장되어 있음
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);       //세워진 순으로 정렬

        for(int i = 0 ;i<n-1 ;i++){
            int dis = arr[i+1] - arr[i]-1;    //서로의 거리
            arr2.add(dis);
        }
        if(n != 0 ){
            arr2.add(arr[0]-1);   //시작점
            arr2.add(l-arr[n-1]-1);   //끝점 -> 왜 -1 ? ?
        } else{
            arr2.add(l-1);      //n이 0일때 시작지점과 끝점까지의 거리를 구해야한다.
        }
        //이분탐색으로 휴게소의 구간의 최대값을 우선 구함
        //가능하면 그 최대값을 줄여가면서 최소값을 구하는 방식으로
        int left = 1;
        int right = l-1;
        int ans = Integer.MAX_VALUE;
        int arrSize= arr2.size();
        while(left <=right){
            int mid = (left + right) / 2;
            //구하는 값이 mid 이다 ! 이 mid 값
            //m개를 세우는 방법 구해야함.
            int cnt = 0;
            //거리를 mid로 나눴을때 몫들의 값을 더해야한다 -> 이 값이 0보다 크면 mid 인 값을 만족하려면 몫만큼의 휴게소를 세워야한다는 뜻이다
            //이 휴게소 갯수들을 구한 뒤 m과 비교한다 -> 같으면 답이 될 수 있고 더 최소값을 구해보기 위해 right 갱신
            //갯수들이 더 작으면 mid 값을 줄여서 휴게소를 m개 설치해야함 -> right = mid -1;
            //갯수들이 더 크면 휴게소를 적게 설치해야한다 그러려면 mid 값을 늘려서 만족시켜야하므로 left 갱신
            for(int i = 0 ; i<arrSize;i++){
                cnt+=(arr2.get(i) / mid);
            }
            if(cnt <= m){       //cnt가 작을 경우 어떻게 추가적으로 m 개를 채워서 아무데나 세워도 되므로 답이 될 수 있다.
                ans = mid;
                right = mid -1;
            } else{
                left = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
