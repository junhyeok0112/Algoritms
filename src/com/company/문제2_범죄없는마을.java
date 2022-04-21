package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//마을에 경찰서를 지음, 마을과 각 마을에서 경찰서까지의 최소 값들의 합 ..
//모든 마을에서 다익스트라 한다음 합을 구하고 작은 순서대로 출력

class algo implements Comparable<algo>{
    int y;
    int dis ;
    algo(int y, int dis){
        this.y = y;
        this.dis = dis;
    }

    @Override
    public int compareTo(algo o) {
        return this.dis - o.dis;
    }
}

class Difdis implements Comparable<Difdis>{
    int num ;
    int dis ;
    Difdis(int num, int dis){
        this.num = num;
        this.dis = dis;
    }
    @Override
    public int compareTo(Difdis o) {
        return this.dis - o.dis;
    }
}

public class 문제2_범죄없는마을 {

    //v 마을 개수 , p 경찰서 개수
    static int tc ,v,p,l;
    static int[] vil ;      //마을 위치 저장하는 배열
    static ArrayList<algo>[] map;    //마을간의 거리 저장한 배열

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0 ;
        while (t++ <tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st= new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            p = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            vil = new int[v+1];     //마을
            map = new ArrayList[1001];
            for(int i = 0 ; i<=1000 ;i++) map[i] = new ArrayList<>();        //최대 100개의 마을
            st = new StringTokenizer(br.readLine());
            for(int i = 1 ; i<= v; i++){
                vil[i] = Integer.parseInt(st.nextToken());
            }

            //마을 간의 거리 구해서 저장하기
            for(int i = 1; i<v; i++){
                for(int j = i+1 ; j<=v;j++){
                    int dis = Math.min(Math.abs(vil[i] - vil[j]), l - Math.abs(vil[i] - vil[j]));
                    map[i].add(new algo(j,dis));
                    map[j].add(new algo(i,dis));
                }
            }

            int dist[][] = new int[1001][1001];
            for(int i = 0 ; i<=1000; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i] = 0;
            }
            //마을 당 최단거리 구하기
            PriorityQueue<algo> pq = new PriorityQueue<>();
            for(int i = 0 ; i<v ;i++){
                boolean[] visit = new boolean[1001];
                visit[vil[i]] = true;       //시작점 셋팅
                pq.add(new algo(vil[i],0 ));
                while (!pq.isEmpty()){
                    algo r = pq.poll();
                    if(visit[r.y]) continue;
                    visit[r.y] = true;
                    for(algo next : map[r.y]){
                        if(dist[vil[i]][next.y] >= dist[vil[i]][r.y] + next.dis){
                            dist[vil[i]][next.y] = dist[vil[i]][r.y] + next.dis;
                            pq.add(new algo(next.y, dist[vil[i]][next.y]));
                        }
                    }
                }
            }

            PriorityQueue<Difdis> difDis = new PriorityQueue<>();
            for(int i = 0 ; i<v; i++){
                int total = 0;
                for(int j = 0; j<1001; j++){
                    if(dist[vil[i]][j] == Integer.MAX_VALUE) continue;
                    total+=dist[vil[i]][j];
                }
                difDis.add(new Difdis(vil[i],total));
            }

            ArrayList<Integer> ans = new ArrayList<>();
            for(int i = 0 ;i<p ;i++){
                ans.add(difDis.poll().num);     //p개 선택
            }
            Collections.sort(ans);
            int min = 0;
            for(int i = 0 ; i<v;i++){
                //하나의 마을에서 경찰서 까지 갈 수 있는 최소값
                int tempMin = Integer.MAX_VALUE;
                for(int j = 0 ;j<ans.size() ; j++){
                    tempMin = Math.min(tempMin, dist[vil[i]][ans.get(j)]);
                }
                min+=tempMin;
            }
            System.out.println(min);
            for(int i = 0 ; i<ans.size();i++){
                System.out.println(ans.get(i)+" ");
            }
        }
    }
}
