package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class P1167 {
    int end;
    int dis;

    P1167(int end, int dis) {
        this.end = end;
        this.dis = dis;
    }
}

public class P1167_트리의지름 {

    static int v;
    static ArrayList<P1167>[] tree;
    static boolean[] visit;
    static int max = -1;    //정답 트리의 지름
    static int max_node = 0;    //지름을 이루는 노드 중 하나

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        v = Integer.parseInt(br.readLine());
        tree = new ArrayList[v + 1];
        for (int i = 0; i <= v; i++) tree[i] = new ArrayList<>();
        visit = new boolean[v + 1];
        for (int i = 1; i <= v; i++) {
            String[] temp = br.readLine().split(" ");
            int len = temp.length;
            int start = Integer.parseInt(temp[0]);
            for (int t = 1; t < len; t += 2) {
                if (temp[t].equals("-1")) break;
                int end = Integer.parseInt(temp[t]);
                int dis = Integer.parseInt(temp[t + 1]);
                tree[start].add(new P1167(end, dis));
                tree[end].add(new P1167(start, dis));
            }
        }

        //임의의 점 1에서 가장 먼 노드 찾기
        dfs(1,0);   //위치와 해당 점과의 거리
        //max_node에 지름을 이루는 노드 중 하나 저장 -> 이걸로 다시 dfs
        Arrays.fill(visit , false); //방문 초기화
        max = -1;                       //거리 초기화 해서 다시 진행
        dfs(max_node, 0);           //지름을 이루는 노드에서 반대쪽 노드 찾기
        System.out.println(max);
    }

    static void dfs(int cur , int sum){
        visit[cur] = true;
        //연결되어 있는 점으로 dfs
        for(P1167 next : tree[cur]){
            if(visit[next.end]) continue;   //방문 한 곳이면 넘어감
            dfs(next.end, sum + next.dis);
        }
        if(sum >= max){
            max = sum ;
            max_node = cur;
        }
    }
}
