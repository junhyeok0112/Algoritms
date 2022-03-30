//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.*;
//
//class P17398{       //연결된 것들 저장
//    int x;
//    int y;
//    P17398(int x, int y){
//        this.x = x;
//        this.y = y;
//    }
//}
//
//public class P17398_통신망_분할 {
//
//    static int n,m,q;
//    static ArrayList<P17398> arr = new ArrayList<>();   //연결된 순서를 저장하기 위한 배열
//    static int[] p ;
//    static int[] bucket;
//
//
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        n = Integer.parseInt(st.nextToken());
//        m = Integer.parseInt(st.nextToken());
//        q = Integer.parseInt(st.nextToken());
//        p = new int[n+1];
//        bucket = new int[n+1];          //i 그룹에 몇개가 들어있는지
//        Arrays.fill(bucket , 1);    //자기 자신 모두 채움
//        Arrays.fill(p ,-1);
//
//        for(int i = 0 ; i<m ;i++){
//            st = new StringTokenizer(br.readLine());
//            int x = Integer.parseInt(st.nextToken());
//            int y = Integer.parseInt(st.nextToken());
//            arr.add(new P17398(x,y));               //i-1번째에 들어가 있음
//        }
//        //연결 안 끊기는 것들 모두 연결한뒤 , 끊길 것들을 마지막부터 체크하면서 연결해줌 ->> 연결 전에 집합 개수 세어서 저장
//
//        Stack<Integer> sta = new Stack<>();
//        HashSet<Integer> hs = new HashSet<>();
//        for(int i =0 ;i<q;i++){
//            int num = Integer.parseInt(br.readLine()) - 1;
//            //num에 해당하는 셋트를 해제해야함
//            sta.push(num);
//            hs.add(num);
//        }
//
//        int arrsize =arr.size();
//        for(int i = 0; i<arrsize; i++){     //arr에 있는 애들 순서대로 합침 ->
//            if(hs.contains(i)) continue;  //연결 끊을 거면 나중에 연결 ->
//            int x = arr.get(i).x;
//            int y = arr.get(i).y;
//            if(x < y ){
//                int px = find(x);
//                int py = find(y);
//                if(merge(x,y)){
//                    bucket[px]+=bucket[py];
//                    bucket[py] = 0;
//                }
//            } else {
//                int px = find(x);
//                int py = find(y);
//                if(merge(y,x)){     //합쳐졌을때만 갱신
//                    bucket[py] += bucket[px];
//                    bucket[px] = 0;        //집합 갯수 갱신
//                }
//            }
//        }
//        //bucket과 p는 1~ n까지
//        //num은 0~ n-1까지 , arr도 0~n-1까지 ->즉 인덱스는 0~n-1
//
//        //스택에서 꺼내면서 연결 시키면서 계산 ->문제와 역으로 진행
//        int ans = 0;
//        while (!sta.isEmpty()){
//            int num = sta.pop();
//            int x = arr.get(num).x;
//            int y = arr.get(num).y;
//            if(find(x) == find(y)) continue;                //이미 같은 집합에 있으면 0이므로 건너뜀
//            ans += bucket[find(x)] * bucket[find(y)];       //분리되어 있는 비용을 더함
//            //그 다음 연결
//            if(x < y ){
//                int px = find(x);
//                int py = find(y);
//                if(merge(x,y)){
//                    bucket[px]+=bucket[py];
//                    bucket[py] = 0;
//                }
//            } else {
//                int px = find(x);
//                int py = find(y);
//                if(merge(y,x)){     //합쳐졌을때만 갱신
//                    bucket[py] += bucket[px];
//                    bucket[px] = 0;        //집합 갯수 갱신
//                }
//            }
//        }
//        System.out.println(ans);
//    }
//
//    static int find(int x){
//        if(p[x] == -1 ) return x;
//        return p[x] = find(p[x]);
//    }
//
//    static boolean merge(int x, int y){
//        x = find(x);
//        y = find(y);
//        if(x==y) return false;
//        p[y] = x;
//        return true;    //성공하면 true;
//    }
//
//
//}
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class P17398{       //연결된 것들 저장
    int x;
    int y;
    P17398(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P17398_통신망_분할 {

    static int n,m,q;
    static ArrayList<P17398> arr = new ArrayList<>();   //연결된 순서를 저장하기 위한 배열
    static int[] p ;
    static int[] bucket;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        bucket = new int[n+1];          //i 그룹에 몇개가 들어있는지
        Arrays.fill(bucket , 1);    //자기 자신 모두 채움
        Arrays.fill(p ,-1);

        for(int i = 0 ; i<m ;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr.add(new P17398(x,y));               //i-1번째에 들어가 있음
        }
        //연결 안 끊기는 것들 모두 연결한뒤 , 끊길 것들을 마지막부터 체크하면서 연결해줌 ->> 연결 전에 집합 개수 세어서 저장

        Stack<Integer> sta = new Stack<>();
        HashSet<Integer> hs = new HashSet<>();
        for(int i =0 ;i<q;i++){
            int num = Integer.parseInt(br.readLine()) - 1;
            //num에 해당하는 셋트를 해제해야함
            sta.push(num);
            hs.add(num);
        }

        int arrsize =arr.size();
        for(int i = 0; i<arrsize; i++){     //arr에 있는 애들 순서대로 합침 ->
            if(hs.contains(i)) continue;  //연결 끊을 거면 나중에 연결 ->
            int x = arr.get(i).x;
            int y = arr.get(i).y;
            int px = find(x);
            int py = find(y);
            if(merge(x,y)){
                bucket[px]+=bucket[py];
                bucket[py] = 0;
            }
        }
        //bucket과 p는 1~ n까지
        //num은 0~ n-1까지 , arr도 0~n-1까지 ->즉 인덱스는 0~n-1

        //스택에서 꺼내면서 연결 시키면서 계산 ->문제와 역으로 진행
        long ans = 0;
        while (!sta.isEmpty()){
            int num = sta.pop();
            int x = arr.get(num).x;
            int y = arr.get(num).y;
            if(find(x) == find(y)) continue;                //이미 같은 집합에 있으면 0이므로 건너뜀
            ans += bucket[find(x)] * bucket[find(y)];       //분리되어 있는 비용을 더함
            //그 다음 연결
            int px = find(x);
            int py = find(y);
            if(merge(x,y)){
                bucket[px]+=bucket[py];
                bucket[py] = 0;
            }
        }
        System.out.println(ans);
    }

    static int find(int x){
        if(p[x] == -1 ) return x;
        return p[x] = find(p[x]);
    }

    static boolean merge(int x, int y){
        x = find(x);
        y = find(y);
        if(x==y) return false;
        p[y] = x;
        return true;    //성공하면 true;
    }


}

