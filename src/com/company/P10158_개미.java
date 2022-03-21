package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P10158_개미 {

    static int w, h ,p,q,t;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(br.readLine());

        int difP = w-p;     //벽에 닿으려면 몇칸 남았는지
        int difQ = h -q;
        if(difP >= t){      //벽에 안닿으면 시간만큼 가기
            p +=t;
        } else{
            if(((t-difP) / w) % 2 == 1 ){       //왼쪽에서 오른쪽으로 갈때
                p = (t-difP) % w;
            }else{
                p = w - ((t-difP) % w);
            }
        }

        if(difQ >= t){
            q += t;
        }else{
            if(((t-difQ) / h) % 2 == 1 ){       //왼쪽에서 오른쪽으로 갈때
                q = (t-difQ) % h;
            }else{
                q = h - ((t-difQ) % h);
            }
        }

        int moveP = (p + t); //총 이동 거리 + 0부터 이동한
        int moveQ = (q + t); //총 이동 거리
        //튕겨진 후 최종 위치 구하기
        //튕겨진 횟수를 통해 현재 위치 파악 가능
        //0인 경우에는 무수한 왕복 후 X의 위치가 0부터 나머지 만큼의 위치이다.
        //1인 경우에는 무수한 왕복 후 W부터 나머지 까지의 위치이다.
        int x = (moveP/w)%2 == 0 ? moveP%w: w - moveP%w;
        int y = (moveQ/h)%2 == 0 ? moveQ%h: h - moveQ%h;

        sb.append(p).append(" ").append(q);
        System.out.println(sb.toString());
    }
}
