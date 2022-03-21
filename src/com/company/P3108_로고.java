package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;


class P3108{
    int x1;
    int y1;
    int x2;
    int y2;
    P3108(int x1, int y1 ,int x2 , int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }
}

public class P3108_로고 {

    static int n;
    static int[] p;
    static P3108[] arr ;
    static boolean start0= false;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        p = new int[n];
        arr = new P3108[n];
        Arrays.fill(p , -1);
        int ans = 0;
        for(int i = 0 ; i< n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            arr[i] = new P3108(x1,y1,x2,y2);

            //0,0을 지나는 경우
            if(y1 == 0 && x1 * x2 <= 0 || y2 == 0 && x1 * x2 <= 0 || x1 == 0 && y1 * y2 <= 0 || x2 == 0 && y1 * y2 <= 0) {
                start0 = true;
            }

        }
        ans = n;
        for(int i = 0 ; i<n ;i++){
            for(int j = 0; j<n; j++){
                if(i == j) continue;
                if(find(i) != find(j)){ //서로 다르면 겹치는지 확인 해야함
                    if(chk(arr[i] , arr[j])){   //겹치면
                        merge(i,j);
                        ans--;
                    }
                }
            }
        }

        //집합의 개수로 세었으므로 큰집합 1개만 있어도 ans = 1이다. 따라서 처음시작할떄 펜을 때야하면 이미 +1 된 경우이므로 계산이되어있다 판단한다..
        if(start0) ans--;
        System.out.println(ans);
    }

    static int find(int x){
        if(p[x] < 0) return x;
        return p[x] = find(p[x]);
    }

    static void merge(int x, int y){
        x = find(x);
        y = find(y);
        if( x==y ) return;
        p[y] = x;           //y의 부모를 x로
    }

    static boolean chk(P3108 r ,P3108 n){   //겹치는지 확인하는 함수
        if(r.x1 >n.x2 || r.y1 > n.y2 || r.x2<n.x1 || r.y2 < n.y1){ //아예 떨어져있을때
            return false;
        }
        if(r.x1>n.x1 && r.x2<n.x2 && r.y1>n.y1 && r.y2<n.y2){   //안에 있을 때
            return false;
        }
        if(n.x1>r.x1 && n.x2<r.x2 && n.y1>r.y1 && n.y2<r.y2){
            return false;
        }

        return true;
    }
}
