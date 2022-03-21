//package com.company;
//
//import java.util.*;
//
//class Now{
//    int value;
//    int block;      //몇번 블록인지
//    Now(int value , int block){
//        this.value = value;
//        this.block = block;
//    }
//}
//
//class Solution {
//    public String[] solution(String[] code) {
//        String[] answer = {};
//        ArrayList<String> ans = new ArrayList<>();
//        ArrayList<Stack<Now>> box = new ArrayList<>();
//        Stack<Integer> block = new Stack<>();
//        for(int i = 0 ; i<26 ; i++){
//            box.add(new Stack<Now>());
//        }
//        //System.out.println(countChar(code[6], '.'));
//        for(int i = 0 ; i<code.length; i++){
//            String s = code[i];
//            int cnt = countChar(s , '.');       //점의 개수
//            if(block.isEmpty()) block.add(cnt); //비어있으면 넣기
//            else{                               //비어 있지않아
//                if(block.peek() == cnt)  continue; //같으면 이전 블록과같음
//                else if(block.peek() < cnt) block.push(cnt);   //포함되는 경우
//                else{
//                    while(!block.isEmpty() && block.peek() >cnt){                   //같은 block 나올떄까지 pop
//                        block.pop();
//                    }
//                    for(Stack<Now> sta : box){                  //이전 블락에 있던 변수도 모두 제거
//                        while(!sta.isEmpty() && sta.peek().block >cnt){                   //같은 block 나올떄까지 pop
//                            sta.pop();
//                        }
//                    }
//                }
//            }
//            //block 계산 끝 -> 이제 명령어 처리
//            s = s.substring(cnt , s.length());        //명령어만 남기고 처리
//            if(s.contains("print")){
//                char valName = s.charAt(s.length()-1);    //출력할 변수명
//                if(box.get(valName - 'a').isEmpty()){   //만약 비어있으면
//                    ans.add("error");
//                    continue;
//                }
//                int val = box.get(valName - 'a').peek().value;   //변수값 변수명에 해당하는 변수의 가장 나중 값
//                ans.add(valName+"="+val);
//            }else{                                      //변수 생성 명령어면
//                char valName = s.charAt(0);
//                int val = Integer.parseInt(s.substring(2, s.length()));
//                box.get(valName - 'a').push(new Now(val,i));    //값과 몇번째 block인지
//            }
//        }
//        int ansSize = ans.size();
//        answer = new String[ansSize];
//        for(int i = 0 ; i<ansSize;i++){
//            answer[i] = ans.get(i);
//        }
//
//        return answer;
//    }
//
//
//    public int countChar(String str, char ch) {
//        return str.length() - str.replace(String.valueOf(ch), "").length();
//    }
//
//}
//
//public static void main(String[] args){
//
//}