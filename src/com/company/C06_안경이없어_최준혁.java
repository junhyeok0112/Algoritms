package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class C06_안경이없어_최준혁 {

    static int tc;
    static int[] chk = {1,2,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,0,0,0,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        tc = Integer.parseInt(br.readLine());
        int t = 0;
        while (t++<tc){
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken();
            String s2 = st.nextToken();
            boolean flag = false;
            if(s1.length() != s2.length()){
                sb.append("DIFF").append("\n");
                continue;
            }
            for(int i = 0 ; i<s1.length();i++){
                if(chk[s1.charAt(i)-'A'] != chk[s2.charAt(i)-'A']){
                    sb.append("DIFF").append("\n");
                    flag = true;
                    break;
                }
            }
            if(flag) continue;
            sb.append("SAME").append("\n");
        }
        System.out.println(sb.toString());
    }

}
