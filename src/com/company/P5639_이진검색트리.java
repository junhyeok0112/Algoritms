package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class P5639_이진검색트리 {

    static int[][] tree = new int[2][1000001];  //tree[0] 왼쪽자식 , tree[1] 오른쪽자식
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s ;
        while ((s = br.readLine()) != null){
            int cur = Integer.parseInt(s);
            arr.add(cur);
        }

        //앞에서 부터 보다가 본인보다 큰게 나오면 그때부터 오른쪽 자식들이다.
        //따라서 큰거 왼쪽들은 전부 왼쪽 노드들이고 , 후위순회는 왼쪽 - 오른쪽 - 루트 순이다
        //따라서 큰거를 기준으로 왼쪽 출력 오른쪽 출력 루트 출력해주면된다.
        //이때 왼쪽과 오른쪽에서도 트리구조이므로 위와 같은 방식으로 큰게 나오면 오른쪽 노드로 구분해주어야한다
        preorder(0,arr.size()-1);

    }

    static void preorder(int start , int end){  //end는 배열 사이즈 , 즉 end-1 까지 index존재
        if(start > end) return;                 //start = end 면 그 값 출력, end가 더 크면 리턴
        int cur = start +1;                     //루트를 제외하고 그 다음꺼부터 큰 값 있는지 확인
        while(cur<=end && arr.get(cur)<arr.get(start)){
            cur++;
        }
        preorder(start+1 , cur-1);        //어짜피 end -1 범위 까지 보므로
        preorder(cur , end);
        System.out.println(arr.get(start));

    }
}
