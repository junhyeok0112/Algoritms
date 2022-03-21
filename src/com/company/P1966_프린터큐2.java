package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class P1966{
    int idx;
    int value;
    P1966(int idx , int value){
        this.idx = idx;
        this.value = value;
    }
}

public class P1966_프린터큐2 {

    static int t,n,m;
    static ArrayList<P1966> arr ;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-->0){
            arr = new ArrayList<>();
            int ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());       //arr[m]이 몇번째로 출력되는지 알아야함
            st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i<n;i++){
                int val = Integer.parseInt(st.nextToken());
                arr.add(new P1966(i,val));
            }

            while(!arr.isEmpty()){
                P1966 cur = arr.get(0);
                boolean chk = false;        //나보다 우선순위가 큰게 있는지 확인
                int arrSize = arr.size();
                for(int i = 1 ;i<arrSize;i++){
                    if(cur.value < arr.get(i).value){   //중요도가 큰게 존재할때
                        arr.remove(0);              //앞에꺼 제거해서
                        arr.add(cur);                   //뒤로 넣고
                        chk = true;                     //존재하니까 빼지마
                        break;
                    }

                }

                if(!chk){       //내 중요도가 제일 클때
                    ans++;
                    if(arr.get(0).idx == m){  //뺄거가 찾는 값이면
                        break;
                    }
                    arr.remove(0);

                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }
}
