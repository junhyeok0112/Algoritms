package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class P1967{
    int y;
    int dis;
    P1967(int y, int dis){
        this.y = y;
        this.dis = dis;
    }
}
//가장 긴 지름을 만드는 노드 node1 과 node2가 있다고 가정한다면 임의의 노드 1개에서 가장 먼 노드는 node1이나 node2일 것입니다.
//이유 : node1 ,node2가 만드는 원에 포함되는 점이므로  - >지름은 모든 경로 중 가장 긴 것이므로 어떠한 경로에 있는 점도 지름보다 작습니다.
//따라서 어떠한 점을 선택해서 가장 먼 노드를 찾으면 지름을 이루는 노드입니다.
//만약 가장 먼 노드가 지름을 이루는 노드가 아니면 , 그 2개의 노드가 지름이 되어야하므로 , 반드시 지름을 이루는 노드를 만나게됩니다.
//따라서 임의의 점에서 가장 먼 노드를 찾으면 그것이 지름을 이루는 노드 , 그 노드에서 가장 먼 노드를 찾으면 지름을 이루는 다른 점입니다.
//이렇게 거리를 구할 수 있습니다.

public class P1967_트리의지름 {

    static int n;
    static ArrayList<P1967>[] tree;
    static boolean[] visit ;
    static int max = -1;            //정답 출력하는 변수
    static int max_node = 1;        //거리가 가장 먼 노드를 저장하는 변수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];
        for(int i = 0 ;i<=n ;i++) tree[i] = new ArrayList<>();
        for(int i = 0 ; i<n-1 ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());
            tree[parent].add(new P1967(child , dis));
            tree[child].add(new P1967(parent,dis));
        }

        visit = new boolean[n+1];
        dfs(1,0);       //임의의 점에서 가장 먼 노드를 찾으면 되므로 루트에서 시작

        //찾은 max_node 가장 먼 노드로 다시 가장 먼 거리 찾아야함
        visit = new boolean[n+1];
        dfs(max_node, 0);
        System.out.println(max);
    }

    //dfs에서 시작점과 가장 먼 노드와 그 거리를 리턴하게합니다.
    public static void dfs(int start , int sum){
        visit[start] = true;
        for(P1967 next : tree[start]){
            if(visit[next.y]) continue;
            dfs(next.y , sum + next.dis);   //y가 목표인 지점에서 거리 갱신
        }
        if(max <= sum){
            max = sum;
            max_node = start;
        }
    }
}
