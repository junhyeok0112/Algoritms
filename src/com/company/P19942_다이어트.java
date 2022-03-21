package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Food{
    int mp;
    int mf;
    int ms;
    int mv;
    int price;
    Food(int mp , int mf, int ms, int mv , int price){
        this.mp = mp;
        this.mf = mf;
        this.ms = ms;
        this.mv = mv;
        this.price = price;
    }
}


public class P19942_다이어트 {

    static int n;
    static int[] standard = new int[4]; //넘어야하는 기준점
    static Food[] arr;
    static int min = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        arr = new Food[n];
        for(int i = 0 ; i <4 ;i++){
            standard[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0 ; i<n ; i++){  //첫번째 들어가는게 맨 마지막 비트로
            st = new StringTokenizer(br.readLine());
            int[] temp = new int[5];
            for(int j = 0 ; j<5; j++){  //5개 입력받기
                temp[j] = Integer.parseInt(st.nextToken());
            }
            arr[i] = new Food(temp[0],temp[1],temp[2],temp[3] , temp[4]);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();  //정답 저장

        //비트마스킹 이용해서 구하기 , 모든 경우의 수 체크
        for(int i = 0 ; i<(1<<n); i++){
            int mp = 0;
            int mf = 0;
            int ms = 0;
            int mv = 0;
            int price = 0;
            ArrayList<Integer> temp = new ArrayList<>();
            for(int j = 0 ; j< n ;j++){     //j번째음식이 들어가있는지 확인하기
                if((i & (1<<j)) == (1<<j)){      //j번쨰에 있으면
                    temp.add(n-j);          //j번째 음식 저장
                    mp += arr[n-j-1].mp;
                    mf += arr[n-j-1].mf;
                    ms += arr[n-j-1].ms;
                    mv += arr[n-j-1].mv;
                    price += arr[n-j-1].price;
                }
            }

            //숫자가 오름차순으로 확인하므로 같은 가격이여도 나중에 찾아지는게
            //사전순으로 빠르다 단 , 같은 자리수면 -> ex > i = 4 이면 1
            if(mp>=standard[0] && mf>=standard[1] && ms >= standard[2] && mv >= standard[3]){
                //기준을 넘었으면
                if(price < min){       //기준 넘었을때 값이 최소값이면
                    min = price;
                    pq.clear();
                    pq.addAll(temp);
                }
                else if(price == min){
                    ArrayList<Integer> com = new ArrayList<>(); //비교할 배열
                    com.addAll(pq);     //순서대로 되냐 ?
                    Collections.sort(com);
                    Collections.sort(temp);
                    String sCom = "";
                    String sTemp = "";
                    for(int num : com){
                        sCom += Integer.toString(num);
                    }
                    for(int num : temp){
                        sTemp += Integer.toString(num);
                    }


                    String[] ans = new String[2];
                    ans[0] = sCom;
                    ans[1] = sTemp;
                    Arrays.sort(ans);
                    if(ans[0].equals(sCom)){        //기존이 더 빠르면 아무것도 안함
                        continue;
                    } else{
                        pq.clear();
                        pq.addAll(temp);
                    }

                }
            }

        }

        if(pq.isEmpty()){
            System.out.println(-1);
        }else{
            System.out.println(min);
            int pqsize = pq.size();
            for(int i =0 ;i<pqsize; i++){
                System.out.print(pq.poll()+ " ");
            }
        }
        //가격 같을떄 오름차순 사전순만 처리해주면됨
    }
}
