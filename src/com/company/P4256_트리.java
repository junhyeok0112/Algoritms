package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P4256_트리 {

    static int n;
    static int[] pre;
    static int[] in;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        while (tc-->0){
            n = Integer.parseInt(br.readLine());
            pre = new int[n];
            in = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<n;i++){
                pre[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<n ;i++){
                in[i] = Integer.parseInt(st.nextToken());
            }

            post(0,0,n);
            sb.append("\n");

        }
        System.out.println(sb.toString());

    }
    //postOrder는 왼쪽서브트리 끝까지 갔을떄 , 오른쪽 서브트리 끝까지 갔을때 그 이후 출력
    //root는 preOrder에서의 idx로 사용 -> 이 값이 후위순회에서는 마지막에 출력된다
    static void post(int root , int s ,int e){      //s~e까지 의 범위 중 root인 점을 기준으로 왼쪽 , 오른쪽 서브트리를 나눠라 ! -> 그 후 root는 마지막에 출력해라.
        if(root >= n) return;
        int rootVal = pre[root];
        for(int i = s ;i<e ;i++){       //s~e까지의 범위중 root에 해당하는 값을 기준으ㅇ로 서브트리 자르고, 그 서브트리에서 또 순회해라
            if(in[i] == rootVal){
                //i를 기준으로 왼쪽이 왼쪽서브트리 ,오른쪽이 오른쪽 서브트리가 됩니다.
                post(root+1 , s, i);      //왼쪽 서브트리의 root는 preOrde에서 처음 이후 , 오른쪽 서브트리의 루트는 처음 이후에 i만큼 건너뛰어야함 -> 루트 왼쪽서브트리루트 ... 오른쪽서브트리 루트 식으로 구성
                //전위 순회에서는 리프노드 전까지가 왼쪽 서브 트리 이고 이후가 오른쪽 서브트리이다.
                post(root+1+i-s,i+1,e);             //s만큼 배줘야함 안빼주면 넘어감 ,s를 시작점으로 i 만큼 떨어져 있는 것 이므로
               sb.append(rootVal+ " ");            //후위 순회 값 출력
            }
        }

    }
}
