package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class A06_괄호짝짓기_최준혁 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st ;
        int t = 0;
        while(t++<10){
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            Stack<Character> sta = new Stack<>();
            for(int i = 0 ; i< s.length(); i++){
                if(s.charAt(i) == '(' ||s.charAt(i) == '{' || s.charAt(i) == '[' || s.charAt(i) == '<'){
                    sta.push(s.charAt(i));
                }else{  //닫는 괄호면
                    if(sta.isEmpty()) { //비어있으면
                        sb.append("#"+t+" ");
                        sb.append(0).append("\n");
                        break;
                    }else if(s.charAt(i) == ')'){
                        if(sta.peek() == '('){
                            sta.pop();
                        } else{
                            sb.append("#"+t+" ");
                            sb.append(0).append("\n");
                            break;
                        }
                    }else if(s.charAt(i) == ']'){
                        if(sta.peek() == '['){
                            sta.pop();
                        } else{
                            sb.append("#"+t+" ");
                            sb.append(0).append("\n");
                            break;
                        }
                    }else if(s.charAt(i) =='}'){
                        if(sta.peek() == '{'){
                            sta.pop();
                        } else{
                            sb.append("#"+t+" ");
                            sb.append(0).append("\n");
                            break;
                        }
                    }else if(s.charAt(i) =='>'){
                        if(sta.peek() == '<'){
                            sta.pop();
                        } else{
                            sb.append("#"+t+" ");
                            sb.append(0).append("\n");
                            break;
                        }
                    }
                }

            }
            if(sta.isEmpty()){      //비어있으면 유효
                sb.append("#"+t+" ");
                sb.append(1).append("\n");
            }
//            else{                 //안비어있으면 이상한거
//                sb.append("#"+t+" ");
//                sb.append(0).append("\n");
//            }
        }
        System.out.println(sb.toString());
    }
}
