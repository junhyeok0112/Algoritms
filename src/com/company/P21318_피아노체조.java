package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21318_피아노체조 {

    static int n,q;
    static int[] arr;
    static int[] prefix_sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        prefix_sum = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i<= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 1 ;i<=n ;i++){
            prefix_sum[i] = prefix_sum[i-1];        //이전에 실수했던 횟수 가져오고
            if(i == n ) break;
            if(arr[i] > arr[i+1]) prefix_sum[i]++;  //다음거보다 어려우면 실수 횟수 추가 -> 단 y번째에는 실수하지않음
        }

        q = Integer.parseInt(br.readLine());
        for(int i = 0 ; i<q; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int ans = prefix_sum[end] - prefix_sum[start-1];
            if(end <n && arr[end] > arr[end+1]) ans--;     //마지막 악보에선 실수 안하므로 end보다 end+1이 쉬우면 원래 prefix는 실수했다고 체크 -> 따라서 1감소키줘야함
            sb.append(ans);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
