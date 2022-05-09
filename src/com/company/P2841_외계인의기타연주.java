package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;


public class P2841_외계인의기타연주 {

    static int n,p;
    static Stack<Integer>[] arr = new Stack[7];
    //N 타임에 ->
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());
        for(int i = 0 ;i<=6 ;i++) arr[i] = new Stack();
        //arr = new int[7];     //1~6 번줄에서 가장 높게 눌린 프렛 번호 저장
        int cnt = 0;
        for(int i = 0 ; i <n; i++){
            st = new StringTokenizer(br.readLine());
            int line = Integer.parseInt(st.nextToken());    //줄
            int prat = Integer.parseInt(st.nextToken());    //프렛
            if(arr[line].size() == 0 ){
                arr[line].push(prat);
                cnt++;
                continue;
            }
            if(arr[line].peek() == prat) continue;
            else if(arr[line].peek() < prat) {
                arr[line].push(prat);
                cnt++;
            } else{
                while(!arr[line].isEmpty() && arr[line].peek() > prat){
                    arr[line].pop();
                    cnt++;
                }
                if(!arr[line].isEmpty() && arr[line].peek() == prat) continue;  //같으면 넘김
                arr[line].push(prat);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

