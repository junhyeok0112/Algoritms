package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class B08 implements Comparable<B08> {
    int start ;
    int end;
    B08(int start ,int end){
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(B08 o) {
        if(this.end > o.end){
            return 1;
        } else if(this.end < o.end){
            return -1;
        } else{
            return this.start - o.start;
        }
    }
}

public class B08_냉장고_최준혁 {

    static int n;
    static ArrayList<B08> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for(int i = 0; i<n ;i++){
            String[] temp = br.readLine().split(" ");
            arr.add(new B08(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
        }
        int ans = n;        //그룹 생길때마다 하나씩 줄어든다.

        Collections.sort(arr);
        //MaxNum을 기준으로 현재 어디까지 진행된지를 나타낸다
        int maxNum = arr.get(0).end;    //첫번째의 최고 온도

        for(int i= 1; i<n ;i++){
            if(maxNum >= arr.get(i).start){
                //겹치는 부분이 있으면
                ans--;
            }  else{
                //겹치는부분이 없으면 , 새로운 maxNum을 갱신해주어야한다. -> 새로운 냉장고의 범위를 찾기위해서
                maxNum = arr.get(i).end;
            }
        }
        System.out.println(ans);

    }
}
