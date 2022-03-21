package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1764_듣보잡 {
    //set 쓰면 더 쉽게 구현가능  ,set.contains의 시간복잡도는 O(1)

    static int N,M;
    static PriorityQueue<String> pq = new PriorityQueue<>();
    static ArrayList<String> arr1;
    static ArrayList<String> arr2;

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new ArrayList<>();
        arr2 = new ArrayList<>();
        for(int i = 0 ; i<N;i++){
            arr1.add(br.readLine());
        }
        for(int i = 0 ; i<M ;i++)
        {
            arr2.add(br.readLine());
        }

        Collections.sort(arr2);                 //이분 탐색을 위해 정렬
        for(int i = 0 ; i<N;i++){
            if(Collections.binarySearch(arr2 , arr1.get(i)) >=0){          //포함하고 있으면 index 번호 아니면 음수
                pq.add(arr1.get(i));
            }
        }

        int pqsize= pq.size();
        System.out.println(pqsize);
        for(int i = 0 ; i<pqsize;i++){
            System.out.println(pq.poll());
        }

    }
}
