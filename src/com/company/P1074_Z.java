package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1074_Z {

    static int n,r,c;
   // static int[][] map;       //배열을 만드는 순간 이미 메모리 초과 -> 배열 안만들어야함
    static int cnt = 0;
    static int ans = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        int len = (int) Math.pow(2,n) -1;           //-1 지점까지 ,0 부터시작했으므로
        //map = new int[len+1][len+1];

//        if(0<= r && r <=len/2 && 0<=c && c<=len/2 ){
//            //1사분면이면
//            pro(0,0,len/2,len/2);
//        } else if(0<= r && r <=len/2 && (len/2)+1<=c && c<=len){
//            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1);
//            pro(0,(len/2)+1, len/2,len);
//        }else if((len/2)+1<= r && r <=len && 0<=c && c<=len/2){
//            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1) * 2;
//            pro((len/2)+1,0, len,len/2);
//        }else {
//            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1) * 3;
//            pro((len/2)+1 ,(len/2)+1, len,len);
//        }
        pro(0,0,len,len,n);
        System.out.println(ans);
    }

    static void pro(int x1, int y1, int x2 ,int y2,int n){
        if(x1==x2 && y1 == y2 ){            //배열을 만들 필요없이 1씩 증가시키다가 그 1 증가시키는 칸이 r,c 와 동일하면 정답으로 갱신
            if(x1 == r && y1 == c){
                ans = cnt;
            }
            cnt++;
            return;
        }

        int len = (int) Math.pow(2,n) ;           //-1 지점까지 ,0 부터시작했으므로
        //map = new int[len+1][len+1];

        if(x1<= r && r <=x1+len/2-1 && y1<=c && c<=y1+len/2-1 ){
            //1사분면이면
            pro(x1,y1,x1+len/2-1,y1+len/2-1,n-1);
        } else if(x1<= r && r <=x1+len/2-1 && (len/2)+y1<=c && c<=y1+len-1){
            //2 사분면
            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1);
            pro(x1,(len/2)+y1, x1+len/2-1,len-1,n-1);
        }else if((len/2)+x1<= r && r <=x1+len-1 && y1<=c && c<=y1+len/2-1){
            //3사분면
            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1) * 2;
            pro((len/2)+x1,y1, x1+len-1,y1+len/2-1,n-1);
        }else {
            //4사분면
            cnt+=Math.pow(2,n-1) * Math.pow(2,n-1) * 3;
            pro((len/2)+x1 ,(len/2)+y1, x1+len-1,y1+len-1,n-1);
        }
    }
}
