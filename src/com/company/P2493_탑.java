package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Top{
    int index;
    int height;
    Top(int index , int height){
        this.index = index;
        this.height = height;
    }
}

public class P2493_탑 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        Stack<Top> s1 = new Stack<>();
        Stack<Top> s2 = new Stack<>();  //번호를 저장할 것
        int[] arr = new int[N+1];   //정답을 저장할 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            s1.add(new Top(i,Integer.parseInt(st.nextToken())));
        }
        s2.push(s1.pop());  //처음꺼 셋팅
        while(!s1.isEmpty()){
           Top temp = s1.pop();
           while(!s2.isEmpty() && temp.height >= s2.peek().height ) { //들어오는게 더 작을떄까지 반복 -> 즉 안걸릴 때까지
               arr[s2.pop().index] = temp.index;
//               s2.pop();
//               sb.append(temp.index).append(" ");
           }
           s2.push(temp);
        }

        for(int i = 1 ; i<=N;i++){
            sb.append(arr[i]+" ");
        }
        System.out.print(sb);

    }
}
