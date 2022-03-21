package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//그래프적인 관점에서 푸는 걸 생각하는게 매우 어려움
//그래프인걸 알아도 어려운데 ?

//물통의 현재 상태와 물을 붓는 행위를 관리하는 구조체
class State{
    int[] X;
    State(int[] _X){
        X = new int[3];
        for(int i= 0 ; i<3;i++) X[i] = _X[i];
    }

    State move(int from , int to , int[] Limit){    //Limit가 주어진 A,B,C의 최대 용량
        //from물통에서 to 물통으로 물을 옮긴다
        int[] nX = new int[]{X[0] , X[1],X[2]};
        //2가지 경우로 나눔 1. to가 먼저 차냐 , 2. from이 먼저 비어지냐

        //1. to가 먼저 차는 경우
        if(X[from] + X[to] >=Limit[to]){    //합쳤을 때 Limit[to]보다 크면 to가 먼저 차는경우
            nX[from] -= Limit[to] - nX[to];  //Limit[to] - X[to]의 값이 물을 옮긴 양
            nX[to] = Limit[to];
        }else{  //2. from이 먼저 비어지는 경우
            nX[to] +=nX[from];  //from에 있던 물 전부 to에 부었을 경우
            nX[from] = 0;
        }

        return new State(nX);
    }
}
public class P2251_물통 {

    static int A,B,C;
    static int[] Limit ;
    static boolean[][][] visit;         //해당 상태를 방문했는지 체크하는 3차원 boolean배열
    static boolean[] possible;          //정답으로 가능한 수를 true로 체크하는 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Limit = new int[3];
        Limit[0] = Integer.parseInt(st.nextToken());
        Limit[1] = Integer.parseInt(st.nextToken());
        Limit[2] = Integer.parseInt(st.nextToken());
        visit = new boolean[205][205][205];
        possible = new boolean[205];

        //a각각 A,B,C 물통에 있는 물의 양은 최대 200 200 200 이다
        //따라서 물의 조합이 나올 수 있는 최대의 개수는 200*200*200 = 800만정도이다.(최대)
        //하나의 상태에서 총 6개의 상태로 변할 수 있음 단, 단방향그래프임

        bfs(0,0,Limit[2]);          //처음 C만 채워져있는 상태에서 탐색 시작
        for(int i = 0 ; i<=200 ;i++){
            if(possible[i]) System.out.print(i+" ");
        }

    }

    //물통 탐색 시작
    static void bfs(int x1,int x2 , int x3){
        Queue<State> q = new LinkedList<>();
        visit[x1][x2][x3] = true;
        int[] temp = new int[]{x1,x2,x3};
        q.add(new State(temp));
        while(!q.isEmpty()){
            State r = q.poll();
            if(r.X[0] == 0) possible[r.X[2]] = true; //만약 뽑았을 때 A가 0이면 그때의 C가 정답이므로 갱신
            for(int from = 0 ; from<3; from++){      //가능한 6개의 상태로 옮겨봄 ->Limit가 주어진 상태로 옮기는 것이므로 자동으로 가능한 상태만 생김
                for(int to = 0 ; to<3; to++){
                    if(from == to) continue;   //같은 곳에 물을 부을 수 없으므로 같으면 넘어감
                    State n = r.move(from, to ,Limit);  //옮긴 상태를 n에 저장
                    if(!visit[n.X[0]][n.X[1]][n.X[2]]){ //만약 새로운 상태를 방문한적이 없으면 방문해준다.
                        visit[n.X[0]][n.X[1]][n.X[2]] = true;
                        q.add(n);
                    }
                }
            }
        }
    }
}
