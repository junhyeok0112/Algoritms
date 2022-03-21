package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P5430_AC {
    static int T , N;
    static String p="";

    //직접 뒤집을 필요가 없음 deque 써서 방향에 대한 boolean 변수 선언
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T>0){
            p = br.readLine();
            N = Integer.parseInt(br.readLine());
            String str = br.readLine(); //문자열
            str = str.substring(1,str.length()-1);
            String[] res = str.split(",");
            Deque<String> dq = new LinkedList<>();
            for(int i = 0 ; i<res.length;i++){
                if(res[i].equals("")) continue;         //비어있는거면 넘어감
                dq.addLast(res[i]);
            }
            boolean isRight = false;
            boolean chk = true;
            for(int i = 0 ; i<p.length();i++){
                if(p.charAt(i) == 'D'){ //빼야할때
                    if(dq.isEmpty()){   //비었으면
                        sb.append("error").append("\n");
                        chk = false;
                        break;
                    }
                    if(!isRight){       //앞쪽 방향일 때
                        dq.pollFirst();
                    } else{
                        dq.pollLast();
                    }
                }else{
                    isRight = !isRight;
                }
            }
            //결과 출력
            if(chk) {
                sb.append("[");
                if (!dq.isEmpty()) {  //안 비어 있을경우
                    if (!isRight) {   //왼쪽부터 시작일 경우
                        sb.append(dq.pollFirst());
                        int dqize = dq.size();
                        for (int i = 0; i < dqize; i++) {
                            sb.append("," + dq.pollFirst());
                        }
                    } else {
                        sb.append(dq.pollLast());
                        int dqize = dq.size();
                        for (int i = 0; i < dqize; i++) {
                            sb.append("," + dq.pollLast());
                        }
                    }
                }
                sb.append("]").append("\n");     //닫는 괄호
            }
            T--;
        }
        System.out.print(sb.toString());
    }


}
