package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P1039{        //num이라는 숫자가 cnt 번 교환되어서 만들어졌다.
    String num;
    int cnt;
    P1039(String num ,int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}
//일반 2차원 배열의 bfs가 아니면 몇번쨰 방문했는지 체크하는 2차원 visit를 만들생각을 하자
public class P1039_교환_re {

    static int n, k;
    static String num = "";
    static Queue<P1039> q = new LinkedList<>();
    static int max = -1;
    static boolean[][] visit = new boolean[1000001][11];      //인덱스에 해당하는 숫자를 몇번째 교환끝에 방문했는지
    //특정 1번 실행했을 때 A라는 숫자가 나온것과 2번 실행했을때 A라는 숫자가 나온것은 전혀 다르다. 따라서 2차원 배열로 방문체크를 해야한다.
    //K 번 이용해서 -> 라는 문제 나오면 2차원 배열 이용해보자 -> BFS,DFS 원숭이 말 문제 같이.
    //최대 6자리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        num = String.valueOf(n);
        q.add(new P1039(num,0));    //시작지점 셋팅
        visit[n][0] = true;
        bfs();

    }
    //반례를 보면서 다시 생각해보자 -> 원래 풀려던 식으로 모든 경우를 보면 메모리초과가 남 -> 줄여야함
    //1111 을 돌린다 쳤을 때 , 2번 바꿔서 1111 인 상태의 수를 추가로 한번 더 교환하면 3번 바꾼 1111이 된다.
    //이 때 중복 체크(visit를 이용해서 [K]번 바꿨다)를 하지 않으면 3번 바꾼 1111을 여러개 큐에 넣게된다. 이러면 메모리초과 및 시간초과 발생
    //따라서 같은 숫자를 같은 교환횟수로 1번만 봐야한다 이를 위헤 visit 배열 선언
    static void bfs(){
        while (!q.isEmpty()){
            P1039 r = q.poll();
            if(r.cnt == k){     //K번 교한한 값들이 나왔으면 -> 더 교환할 필요없음
                int num = Integer.parseInt(r.num);
                max = Math.max(max,  num);
                continue;               //마지막 숫자까지 체크
            }

            int len = r.num.length();       //0~len까지
            for (int i = 0;i < len - 1; i++) {  //모든 자릿수 바꿔보기
                for (int j = i + 1; j < len; j++) {
                    String temp = "";
                    for (int t = 0; t < len; t++) {
                        if (t == i) temp += r.num.charAt(j);
                        else if (t == j) temp += r.num.charAt(i);
                        else temp += r.num.charAt(t);
                    }
                    if (check(temp) && !visit[Integer.parseInt(temp)][r.cnt+1]) {    //시작지점이 0이 아니고 ,temp라는 숫자를 1번 증가시켜서 방문했을때 방문 안했으면
                        visit[Integer.parseInt(temp)][r.cnt+1] = true;
                        q.offer(new P1039(temp,r.cnt+1));
                    }
                }
            }
        }
        System.out.println(max);
    }


    private static boolean check(String temp) { //시작점이 0이면 false 리턴
        if (temp.charAt(0) == '0') return false;
        return true;
    }

}
