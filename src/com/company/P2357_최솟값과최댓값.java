package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2357_최솟값과최댓값 {

    static int N,M;
    static int[] arr;
    static int[] mintree;
    static int[] maxtree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        mintree = new int[4*N];
        maxtree = new int[4*N];
        for(int i =1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        build();        //입력받은 값으로 세그먼트 트리 생성

        for(int i  = 0 ; i<M ;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(QueryMin(a,b,1,1,N)).append(" ").append(QueryMax(a,b,1,1,N)).append("\n");
        }
        System.out.println(sb);
    }

    static int mergeMin(int left , int right){
        return Math.min(left,right);
    }

    static int mergeMax(int left ,int right){
        return Math.max(left , right);
    }

    static void build(){
        MinbuildRec(1,1,N);
        MaxbuildRec(1,1,N);
    }

    static int MaxbuildRec(int node, int nodeLeft , int nodeRight){
        if(nodeLeft == nodeRight) return maxtree[node] = arr[nodeLeft];

        int mid = nodeLeft + (nodeRight - nodeLeft )/ 2;
        int leftVal = MaxbuildRec(2*node , nodeLeft , mid);
        int rightVal = MaxbuildRec(2*node+1 , mid +1 , nodeRight);
        return maxtree[node] = mergeMax(leftVal, rightVal);
    }

    static int MinbuildRec(int node, int nodeLeft , int nodeRight){
        if(nodeLeft == nodeRight) return mintree[node] = arr[nodeLeft];

        int mid = nodeLeft + (nodeRight - nodeLeft )/ 2;
        int leftVal = MinbuildRec(2*node , nodeLeft , mid);
        int rightVal = MinbuildRec(2*node+1 , mid +1 , nodeRight);
        return mintree[node] = mergeMin(leftVal, rightVal);
    }

    static void Update(int index , int newVal){
            UpdateMax(index,newVal, 1, 1,N);
            UpdateMin(index , newVal ,1 ,1,N);
    }

    static int UpdateMin(int index , int newVal , int node ,int nodeLeft, int nodeRight){
        if(index < nodeLeft || nodeRight > index) return mintree[node]; //범위 밖이면 원래값 리턴
        if (nodeLeft == nodeRight) return mintree[node] = newVal;

        int mid = nodeLeft + (nodeRight  - nodeLeft) / 2;
        int leftVal = UpdateMin(index , newVal , 2*node , nodeLeft ,mid);
        int rightVal = UpdateMin(index,newVal , 2*node+1 , mid+1, nodeRight);
        return mintree[node] = mergeMin(leftVal,rightVal);
    }

    static int UpdateMax(int index , int newVal , int node ,int nodeLeft, int nodeRight){
        if(index < nodeLeft || nodeRight > index) return maxtree[node]; //범위 밖이면 원래값 리턴
        if (nodeLeft == nodeRight) return maxtree[node] = newVal;

        int mid = nodeLeft + (nodeRight  - nodeLeft) / 2;
        int leftVal = UpdateMax(index , newVal , 2*node , nodeLeft ,mid);
        int rightVal = UpdateMax(index,newVal , 2*node+1 , mid+1, nodeRight);
        return maxtree[node] = mergeMax(leftVal,rightVal);
    }

    static int QueryMin(int left ,int right, int node ,int nodeLeft ,int nodeRight){
        if(right < nodeLeft || nodeRight <left) return 1000000001;      //최소 값에 영향 없는 10억 1 출력
        if(left<=nodeLeft && nodeRight <= right) return mintree[node];

        int mid = nodeLeft + (nodeRight - nodeLeft ) / 2;
        return mergeMin(QueryMin(left, right, node*2 , nodeLeft , mid)
        ,QueryMin(left,right,node*2 +1 , mid +1 , nodeRight));
    }

    static int QueryMax(int left ,int right, int node ,int nodeLeft ,int nodeRight){
        if(right < nodeLeft || nodeRight <left) return -1;      //최소 값에 영향 없는 10억 1 출력
        if(left<=nodeLeft && nodeRight <= right) return maxtree[node];

        int mid = nodeLeft + (nodeRight - nodeLeft ) / 2;
        return mergeMax(QueryMax(left, right, node*2 , nodeLeft , mid)
                ,QueryMax(left,right,node*2 +1 , mid +1 , nodeRight));
    }



}
