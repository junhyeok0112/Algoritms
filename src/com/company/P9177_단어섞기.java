package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P9177 {
    int first_len;
    int second_len;

    P9177(int f, int s) {
        first_len = f;
        second_len = s;
    }
}

public class P9177_단어섞기 {

    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= t; tc++) {
            sb.append("Data set ").append(tc).append(": ");
            String[] str = br.readLine().split(" ");

            //첫번째 단어와 두번째 단어를 인덱스 순서대로 잘 섞어서 세번째 단어가 가능한지 검사해야함
            //BFS로 (0,0) ,(0,1) , (1,0) ... 순으로 (n,m) 까지 순차적으로 가능한지 넣어봐야함함
            sb.append(bfs(str)).append("\n");

        }
        System.out.println(sb.toString());
    }

    static String bfs(String[] str) {
        int first_len = str[0].length();
        int second_len = str[1].length();
        int final_len = str[2].length();
        Queue<P9177> q = new LinkedList<>();        //BFS를 위해 (첫번째 단어 인덱스 , 두번째 단어 인덱스) 로 저장하여서 체크
        boolean[][] visit = new boolean[first_len + 1][second_len + 1];     //해당 지점을 방문했는지 확인
        q.offer(new P9177(0, 0));                                   //1이 첫번째 글자 , 단 idx 로는 charAt(0)
        visit[0][0] = true; //방문 체크
        while (!q.isEmpty()) {
            P9177 r = q.poll();
            int f_len = r.first_len;
            int s_len = r.second_len;
            if (f_len + s_len == final_len) return "yes";        //끝까지 탐색했을 때 가능한 경우이면
            //다음 인덱스 하나씩 늘려서 체크해봐야함
            //첫번째 단어를 넣었을 경우
            if (f_len + 1 <= first_len && !visit[f_len + 1][s_len] && str[0].charAt(f_len) == str[2].charAt(f_len + s_len)) {
                q.offer(new P9177(f_len + 1, s_len));
                visit[f_len + 1][s_len] = true;
            }
            if (s_len + 1 <= second_len && !visit[f_len][s_len + 1] && str[1].charAt(s_len ) == str[2].charAt(f_len + s_len )) {
                q.offer(new P9177(f_len, s_len + 1));
                visit[f_len][s_len + 1] = true;
            }
        }
        return "no";
    }
}
