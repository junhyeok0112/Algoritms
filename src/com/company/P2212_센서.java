package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2212_센서 {

    static int n ,k;
    static int[] arr;
    static int[] dif ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n];
        dif = new int[n];
        for(int i = 0 ; i<n ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);       //오름차순으로 정렬
        int len = 0;
        //집중국 갯수 (K)가 센서 갯수 (N) 보다 크거나 같으면 각 센서마다 집중국 설치해서 모든 경우 커버가능
        //따라서 이때는 0이다.
        if(k >=n){
            sb.append(0);
            System.out.println(sb.toString());
            return;
        }

        //dif 배열 구하기 : 각 센서들의 거리의 차이를 저장하는 배열입니다.
        for(int i = 0; i<n-1 ;i++){
            dif[i] = arr[i+1] - arr[i];     //i+1과 i의 차이가 dif[i]에 저장
        }
        Arrays.sort(dif);                   //dif 배열 정렬

        //dif배열의 N - K번째 인덱스까지 값 더하기
        //집중국을 놓을 때 두 센서를 하나의 구역에 포함시키지 않고 분리했으므로 두 센서 사이 간격은 무시
        //가장 간격이 높은 값을 기준으로 분리시켜 구역을 만들면 최소합이나옵니다.
        //즉 [1,3] , [6,9] 에서 3,6 사이의 간격을 무시하는 것 입니다.
        //이 간격을 무시하는 방법이 N-K 번째 인덱스까지 더하며 됩니다.
        //Why - > N- K ?

        int ans = 0;
        for(int i = 0 ; i<=n-k; i++){
            ans += dif[i];
        }
        sb.append(ans);
        System.out.println(sb.toString());
    }
}
