//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
////
////class Tree{
////    int idx;
////    int num;
////    Tree(int idx, int num){
////        this.idx = idx;
////        this.num = num;
////    }
////}
//
//public class 트리디자이너호석 {
//
//    static int res = 0 , N;
//    static Tree[] arr;
//    static ArrayList<ArrayList<Tree>> adj = new ArrayList<>();
//    static boolean[] visit;
//    static ArrayList<Integer> cntCount;
//    static int number ;
//    static int ans = 0;
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        arr = new Tree[N+1];
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        for(int i = 0 ; i<=N;i++){
//            adj.add(new ArrayList<>());
//        }
//        for(int i = 1 ; i<=N;i++){
//            arr[i] = new Tree(i,Integer.parseInt(st.nextToken()));
//        }
//        for(int i = 0 ; i<N-1; i++){
//            st = new StringTokenizer(br.readLine());
//            int num1 = Integer.parseInt(st.nextToken());
//            int num2 = Integer.parseInt(st.nextToken());
//            adj.get(num1).add(arr[num2]);
//            adj.get(num2).add(arr[num1]);
//        }   //트리 완성
//
//        //모든 점에서 dfs하면될 것같음 dfs해서 오름차순으로 가능한 개수 구한후 그거를 수열로 나타냄
//        cntCount = new ArrayList<>();
//        //for(int i =1 ; i<=N;i++){
//            visit = new boolean[N+1];
//            dfs(1,1);
//       // }
//        //cntCount에 들어있는 개수들을 1~n까지 놔서 백트래킹
////        System.out.println("체크");
////        System.out.println(cntCount.size());
//        for(int i = 0 ; i<cntCount.size();i++){
//            fun(cntCount.get(i));
//            System.out.println("넘버 : "+ number);
//            ans +=number % 1000000007;
//        }
//        System.out.println(ans);
//    }
//    static void dfs(int start , int cnt){
//        visit[start] = true;
//        if(adj.get(start).size() == 1 && start != 1){ //이파리에 도착하면 그리고 root가 아니면
//            System.out.println(start);
//            cntCount.add(cnt);
//            return;
//        }
//        for(Tree t : adj.get(start)){
//            if(!visit[t.idx]){  //방문안했으면 idx가 방문한 곳 체크
//                if(t.num >=arr[start].num){ //연결된게 start보다 크면
//                    dfs(t.idx , cnt+1);
//                } else{
//                    dfs(t.idx, cnt);
//                }
//            }
//        }
//    }
//
//    static void fun(int n){     //1을 포함해서 부분수열만들면됨
//        boolean[] chk = new boolean[n+1];
//        chk[1] = true;
//        number = 0; //한번 돌릴때마다 number값 저장
//        rec_fun(2,n,chk);
//
//    }
//
//    static void rec_fun(int k , int limit , boolean[] chk ){ //k가 지금 보는 수 limit가 n값으로 n까지보면 종료
//        if(k == limit+1){
//            return;
//        }
//        else{
//            for(int cand = k;cand<=limit;cand++){   //한번만 하면되나 ?
//                visit[cand] = true;
//                System.out.println("실행");
//                number = (number +1) % 1000000007;
//                rec_fun(k+1, limit,chk );
//                visit[cand] = false;
//            }
//        }
//
//
//    }
//}
