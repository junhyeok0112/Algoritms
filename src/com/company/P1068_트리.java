package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1068_트리 {

    //DP를 이용해서 다른 풀이방법으로 풀 수 있음
    static int N;
    static int[] par;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] visit;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        par = new int[N];
        visit = new boolean[N];
        for(int i = 0 ; i<N;i++){
            tree.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0 ;i<N;i++){
            par[i] = Integer.parseInt(st.nextToken());
            if(par[i] == -1) continue;
                  //tree.get(i) 사이즈가 1 이면 leaf 노드
            tree.get(i).add(par[i]);
            tree.get(par[i]).add(i);

        }
        int eraseNum = Integer.parseInt(br.readLine());
        if(par[eraseNum] != -1) {   //삭제시키는게 root가 아닐때
            tree.get(par[eraseNum]).remove(Integer.valueOf(eraseNum));  //remove(int)하면 int번째 인덱스 삭제 따라서 Integer로 주어서 그 수를 찾아서 삭제
            tree.get(eraseNum).remove(Integer.valueOf(par[eraseNum]));
            //삭제할 노드와 그 부모의 연결 끊고 dfs 하면됨
            for (int i = 0; i < N; i++) {
                if (par[i] == -1)
                    dfs(i);
            }
            System.out.println(cnt);

        } else{ //삭제시키는게 부모면 모두 삭제
            System.out.println(0);
        }


    }


    static void dfs(int n){
        visit[n] = true;
        if(par[n] == -1 && tree.get(n).size() == 0){    //root노드 하나밖에 안남았을경우
            cnt++;
        }
        if(tree.get(n).size() == 1 && par[n] != -1){        //자식이 없고 부모가 아니여야함
            cnt++;
            return;
        }
        for(int k : tree.get(n)){
            if(!visit[k])
                dfs(k);
        }
    }
}
