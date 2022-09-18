package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//일반 크루스칼에서 남 -> 여 인지 구분도 추가해야함
class P14621 implements Comparable<P14621> {
    int u, v, d;

    P14621(int u, int v, int d) {
        this.u = u;
        this.v = v;
        this.d = d;
    }

    @Override
    public int compareTo(P14621 o) {
        return this.d - o.d;
    }
}

public class P14621_나만안되는연애 {

    static int n, m;
    static char[] arr;
    static int[] p;
    static PriorityQueue<P14621> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n + 1];
        p = new int[n + 1];
        Arrays.fill(p, -1);
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = st.nextToken().charAt(0);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            if (arr[u] == arr[v]) continue;      //같으면 추가할 필요없음 -> 즉 이성학교로 안가는 경로는 제외
            pq.add(new P14621(u, v, d));
        }

        int cnt = 0;
        int ans = 0;

        while (!pq.isEmpty()) {
            P14621 r = pq.poll();
            int ru = r.u;
            int rv = r.v;
            int rd = r.d;
            if(merge(ru,rv)){
                ans += rd;
                cnt++;
                if(cnt == n-1) break;
            }
        }
        if(cnt != n-1) System.out.println(-1);
        else System.out.println(ans);
    }

    static int find(int x){
        if(p[x] < 0) return x;
        return p[x] = find(p[x]);
    }

    static boolean merge(int x , int y){
        x = find(x);
        y = find(y);
        if(x == y) return false;
        p[x] = y;
        return true;
    }
}
