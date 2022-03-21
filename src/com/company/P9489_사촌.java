package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P9489_사촌 {
    static int N, K;
    static int[] arr ;
    static int[] par;

    // Hint) 사촌 := 부모의 부모는 동일하나 부모는 다른 정점의 개수
    // Hint) par 배열을 가득 채워보자.

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            if(N == 0 && K ==0) break;
            arr = new int[N + 1];
            par = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            //0번은 안쓰므로 부모를 -1 , 1번은 루트 노드이므로 부모 없으니까 0 으로 셋팅
            par[0] = -1;
            par[1] = 0;
            // Hint) 사촌 := 부모의 부모는 동일하나 부모는 다른 정점의 개수
            // Hint) par 배열을 가득 채워보자.
            //즉 각 집합마다 임의의 부모를 할당하고 부모의 부모는 같으면서 부모는 다른 점들을 찾으면된다
            int group = 1;      //이번에 자식 정점들을 찾을 노드의 인덱스 즉 , group의 index에 해당하는 자식들을 찾음
            for(int i = 2 ;i<=N;i++){
                while(i<=N){
                    par[i] = group;                     //그룹 저장하고
                    if(i<N && arr[i] +1 != arr[i+1]) break;    //연속이 아니면 멈춤
                    i++;
                }
                group++;
            }

            int kIndex = 0;
            for(int i =1  ; i<=N ;i++) if(arr[i] ==K){
                kIndex = i;
                break;
            }

            //사촌은 부모의 부모가 같고 부모가 다름
            int answer = 0;
            for(int i=1 ;i<=N ; i++){
                if(par[par[i]] == par[par[kIndex]] && par[i] != par[kIndex]) answer++;
            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }

}
