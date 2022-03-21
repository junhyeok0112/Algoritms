package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Truck{
    int weight;
    int w;
    Truck(int weight , int w){
        this.w = w;
        this.weight = weight;
    }
}

class Brdige{
    Queue<Truck> q = new LinkedList<>();
    int totalWeight  = 0;

    void addTruck(Truck truck){
        q.add(truck);
        totalWeight += truck.weight;
    }

    void move(){
        int qsize = q.size();
        for(int i = 0 ; i<qsize;i++){
            Truck cur = q.poll();
            cur.w--;
            if(cur.w >0) q.add(cur);    //갈거리 남았으면 다시 넣음
            else{
                totalWeight -= cur.weight;        //무게빼기
            }
        }
    }
}

public class P13335_트럭 {

    static int n ,w ,L;
    static Queue<Truck> q = new LinkedList<>();


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i<n; i++){
            q.add(new Truck(Integer.parseInt(st.nextToken()) , w));
        }
        Brdige brdige = new Brdige();
        int cnt = 0;

        while (!brdige.q.isEmpty() || !q.isEmpty()){
            if(!q.isEmpty() && brdige.totalWeight + q.peek().weight <=L ){//다리에 있는거 + 새로운게 작거나 같으면
                brdige.addTruck(q.poll());
            }
            brdige.move();
            cnt++;
        }
        cnt++;

        System.out.println(cnt);
    }
}
