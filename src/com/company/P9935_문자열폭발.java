package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Stack;

public class P9935_문자열폭발 {
    static Stack<Character> s = new Stack<>();
    static Stack<Character> s2 = new Stack<>();
    static String str;
    static String boom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        str = br.readLine();
        String boom = br.readLine();
        //sb.append(str); //stringBuiledr에 붙여넣기
        int bsize = boom.length();
        for(int i = 0 ; i<str.length();i++){
            s.push(str.charAt(i));
            if(s.size() < bsize){
                continue;
            }
            if(s.peek() == boom.charAt(bsize-1)){   //마지막꺼와같으면
                for(int j = 0 ; j<bsize;j++){
                    s2.push(s.pop());
                }
                String tmp = "";
                while(!s2.isEmpty()){
                    tmp+=s2.pop();
                }
                if(tmp.equals(boom)){   //같으면
                    continue;   //다음거
                } else{//다르면
                    for(int k = 0; k<tmp.length(); k++){
                        s.push(tmp.charAt(k));  //다르면 다시 집어넣기
                    }
                }
            }
        }

        if(s.isEmpty()){
            //비었으면
            System.out.println("FRULA");
            return;
        }

        while (!s.isEmpty()){
            sb.append(s.pop());
        }
        System.out.println(sb.reverse());



//        for(int i = 0 ; i<str.length();i++){
//            temp +=str.charAt(i);
//            if(temp.length() < bsize){
//                continue;
//            }
//            String temp2 = temp.substring(temp.length() - bsize , temp.length());
//            if(temp2.equals(boom)){
//                temp = temp.substring(0,temp.length() - bsize );
//            }
//        }
//        if(temp.equals("")){
//            System.out.println("FRULA");
//        } else{
//            System.out.println(temp);
//        }





//        while(str.contains(boom)){
//            int idx = str.indexOf(boom);
//            str = str.substring(0,idx) + str.substring(idx+bsize);
//            if(str.equals("")){
//                sb.append("FRULA");
//            }
//        }
//        sb.append(str);
//        if(str.equals("")){
//            System.out.println(sb.toString());
//        } else{
//            System.out.println(sb.toString());
//        }

    }
}
