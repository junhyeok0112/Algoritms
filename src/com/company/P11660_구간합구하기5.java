package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660_구간합구하기5 {

    static int n,m;
    static int[][] map;
    static int[][] prefix_sum;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][n+1];
        prefix_sum = new int[n+1][n+1];
        for(int i = 1; i<= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<= n ;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1 ;i<=n ;i++){
            for(int j = 1; j<=n; j++){
                prefix_sum[i][j] = map[i][j] + prefix_sum[i-1][j] + prefix_sum[i][j-1] - prefix_sum[i-1][j-1];
            }
        }

        for(int i = 0 ; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int ans = prefix_sum[x2][y2] - prefix_sum[x1-1][y2] - prefix_sum[x2][y1-1] + prefix_sum[x1-1][y1-1];
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}
