package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arrA = new int[N];
        int[] arrB = new int[M];
        ArrayList<Integer> res = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ;i<N ;i++){
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        //B입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ;i<M ;i++){
            arrB[i] = Integer.parseInt(st.nextToken());
        }
        int A = 0;
        int B = 0;
        while(A<N && B<M){
            if(arrA[A] <= arrB[B]){
                res.add(arrA[A]);
                A++;
            } else{
                res.add(arrB[B]);
                B++;
            }
        }
        //끝나면 남은거 넣어야함
        if(A<N){    //남은거 있다는 뜻 , 둘중 하나만 남은거 있음
            for(int i = A ; i<N ;i++){
                res.add(arrA[i]);
            }
        }
        if(B<M){
            for(int i = B ;i<M; i++){
                res.add(arrB[i]);
            }
        }


        for(int i = 0 ;i <res.size(); i++){
            sb.append(res.get(i)).append(" ");
        }
        System.out.println(sb);
    }
}

//
//public class P11728_배열합치기 {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int[] A = new int[N];
//        int[] B = new int[M];
//        ArrayList<Integer> res = new ArrayList<>();
//        //A 입력
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0 ;i<N ;i++){
//            A[i] = Integer.parseInt(st.nextToken());
//            res.add(A[i]);
//        }
//
//        //B입력
//        st = new StringTokenizer(br.readLine());
//        for(int i = 0 ;i<M ;i++){
//            B[i] = Integer.parseInt(st.nextToken());
//            res.add(B[i]);
//        }
//
//        Collections.sort(res);
//        for(int i = 0 ;i <res.size(); i++){
//            sb.append(res.get(i)).append(" ");
//        }
//        System.out.println(sb);
//    }
//}
