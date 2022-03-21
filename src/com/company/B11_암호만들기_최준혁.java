package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B11_암호만들기_최준혁 {

    static int l,c;
    static ArrayList<String> arr = new ArrayList<>();
    static boolean[] selected;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<String> ans = new PriorityQueue<>();   //정답을 저장할 pq

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        selected = new boolean[c];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<c ;i++){
            arr.add(st.nextToken());
        }
        rec(0,"",0,0);

        int pqsize = ans.size();
        for(int i = 0 ; i<pqsize ;i++){
            sb.append(ans.poll()).append("\n");
        }
        System.out.println(sb.toString());

    }
    //cnt : 몇개가 뽑혔는지, now : 몇번째 알파벳을 보고 있는지  , str ->만들고 있는 문자열
    static void rec(int cnt , String str ,int mo , int ja){   //mo : 모음 개수 , ja :자음 개수
        if(cnt == l ){  //다 뽑았으면
            if(mo <1 || ja <2) return;  //모음 자음이 모자르면 리턴
            //오름차순인지 확인해야함
            ans.add(str);               //안모자르면 추가
            return;
        }

        for(int i = 0 ; i<c; i++){
            if(selected[i]) continue;       //선택된거면 넘어가
            if(str.length() >0 && str.substring(str.length() - 1).compareTo(arr.get(i)) > 0) continue;       //오름차순이 아니면 건너띄워야함
            selected[i] = true;
            str+=arr.get(i);                //문자열 이어붙히기
            if(arr.get(i).equals("a") ||arr.get(i).equals("e") ||arr.get(i).equals("i") ||arr.get(i).equals("o") ||arr.get(i).equals("u")){ //모음이면
                rec(cnt+1 , str , mo+1 ,ja);
            }else{
                rec(cnt+1,str,mo,ja+1);
            }
            selected[i] = false;
            str = str.substring(0,str.length()-1);    //마지막 문자열자르기
        }
    }
}
