package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;


public class P2042_구간합구하기 {

    static int N,M,K;
    static long[] arr; //입력받은 수들을 저장하는 배열
    static long[] tree ;        //구간 합을 저장하는 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new long[N+1];
        tree = new long[4*N+1];
        for(int i = 1 ;i <=N ;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        build();        //세그먼트 트리 생성
        for(int i = 0 ; i<M+K ; i++){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            long num3 = Long.parseLong(st.nextToken());       //바꾸는 값이 int 넘길 수도 있어서 long을 해야함
            if(num1 == 1){
                Update(num2,num3);
            } else{
                sb.append(Query(num2,num3)).append("\n");
            }
//            for(long cur : arr){
//                System.out.print(cur + " ");
//            }
//            System.out.println();
        }
        System.out.println(sb);

    }

    static long merge(long left ,long right){
        return left + right;
    }

    static void build(){
        buildRec(1,1,N);        //1부터 N까지가 원래 배열에서의 범위
    }

    static long buildRec(int node , int nodeLeft, int nodeRight){
        if(nodeLeft == nodeRight) return tree[node] = arr[nodeLeft];

        int mid = nodeLeft + (nodeRight - nodeLeft) / 2;
        long leftVal = buildRec(node*2, nodeLeft , mid);
        long rightVal = buildRec(node*2+1 , mid+1 , nodeRight);
        return tree[node] = merge(leftVal , rightVal);
    }

    static long UpdateRec(int index , long newVal , int node , int nodeLeft , int nodeRight){
        if(index < nodeLeft || nodeRight < index) return tree[node];        //범위 밖이면 그값 리턴 , node가 세그먼트의 번호 ->즉 몇번째 구간인지

        if(nodeLeft == nodeRight) return tree[node] = newVal;

        int mid = nodeLeft + ((nodeRight - nodeLeft) / 2);
        long leftVal = UpdateRec(index, newVal ,node*2, nodeLeft , mid);
        long rightVal = UpdateRec(index , newVal, node*2+1 , mid+1 , nodeRight);
        return tree[node] = merge(leftVal , rightVal);
    }

    static void Update(int index , long newVal){
        UpdateRec(index , newVal , 1 ,1,N);
    }

    static long Query(int left ,long right){
        return QueryRec(left,right,1,1,N);
    }

    static long QueryRec(int left ,long right , int node ,int nodeLeft ,int nodeRight){     //nodeLeft , nodeRight 는 지금 node번호의 끝과 끝
        if(right < nodeLeft || nodeRight < left) return 0;               //범위를 넘어가면 결과값과에 지정 없는 값 리턴해줘야함

        if(left <= nodeLeft && nodeRight <= right) return tree[node];   //구하려는 범위 안에 node의 범위가 포함되면 node값 리턴

        int mid = nodeLeft + ((nodeRight - nodeLeft) / 2);
        return merge(QueryRec(left ,right, node*2 , nodeLeft , mid),
                QueryRec(left ,right, node*2 +1 , mid+1 ,nodeRight ));
    }



}
