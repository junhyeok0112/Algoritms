package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068_트리_DP버전 {

    static int N ,erase , root;
    static ArrayList<Integer>[] child;
    static int[] leaf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        child = new ArrayList[N];
        leaf = new int[N];
        for(int i = 0 ; i<N;i++){
            child[i] = new ArrayList<>();
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ;i<N;i++){
            int par = Integer.parseInt(st.nextToken());
            if(par == -1){
                root = i;
                continue;
            }
            child[par].add(i);  //입력받은 부모의 자식으로 현재 index 저장 -> 둘다 저장해서 트리로 만들필요없이 자식만 저장하면됨
        }
        erase = Integer.parseInt(br.readLine());
        pro();
    }

    static void pro(){
        //노드 삭제하는 과정
        for(int i = 0; i<N;i++) {
            if (child[i].contains(erase)) {
                child[i].remove(child[i].indexOf(erase));
            }
        }

        if(root != erase){  //루트는 예외처리따라서 루트 아닐경우만 DFS 실행 루트면 그냥 0이됨
            dfs(root);
        }
        System.out.println(leaf[root]); //root = erase 면 위에 과정 없이 leaf = 0으로 출력됨


    }

    static void dfs(int n){
        if(child[n].isEmpty()){
            leaf[n] = 1;
        }
        for(int y : child[n]){
            dfs((y));
            leaf[n] += leaf[y];         //subtree의 leaf결과를 이용 ->이게 dp 형식
        }
    }

}
