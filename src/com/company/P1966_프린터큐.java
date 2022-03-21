package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class P1966_프린터큐 {

    //public안에 선언하면 static으로 선언해야함
    static class Point{
        int pr;
        int idx;
        Point(int pr ,int idx){
            this.pr = pr;
            this.idx = idx;
        }
    }

    static int T,N,M;
    static ArrayList<Point> arr ;
    static Queue<Integer> q = new LinkedList<>();
    static LinkedList<Integer> l = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int cnt = 0 ; //몇개가 출력되었는지 저장하는 변수
            arr = new ArrayList<>();
            //i = M 인 문서가 몇번째로 출력되었는지 확인하면됨
            for(int i = 0 ; i< N ;i++){
                int priory = Integer.parseInt(st.nextToken());
                arr.add(new Point(priory,i));
            }
            while(!arr.isEmpty()){  //총 100번을 볼것
               int arrsize = arr.size();
               Point p = arr.get(0);
               boolean chk = false; //나보다 큰게 있으면 true;
               for(int i = 1 ; i <arrsize;i++){
                   if(p.pr < arr.get(i).pr){
                       //맨 앞의 중요도가 작으면 뒤로보냄
                      arr.remove(0);
                      arr.add(p);
                      chk = true;
                      break;
                   }
               }

               if(!chk){ //다 봤을때 나보다 큰게 없으면 출력
                   arr.remove(0);
                   cnt++;
                   if(p.idx == M ){ //출력한게 찾는 값이면
                        System.out.println(cnt);
                        break;
                   }
               }
            }

            T--;

        }
    }
}
