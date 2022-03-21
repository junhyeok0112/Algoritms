package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P23082_균형삼진법 {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        boolean minus = false;
        if(N < 0 ) minus = true;
        N = Math.abs(N);    //일단 양수로 바꾸기
        //진법 계산후 2인곳에서 1 올려주고 T로 교체
        if(N == 0) {
            System.out.println(0);
            return;
        }
        String s = "";
        while (N > 0) {
            s+= N%3;    //나머지 붙이고
            N /= 3;
        }

        int[] arr = new int[s.length()+1];    //s를 각각 저장할 배열
        for(int i = 0 ; i<s.length();i++){
            arr[i] = s.charAt(i) - '0'; //정수로 저장
        }
        for(int i = 0 ; i<arr.length; i++){
            if(arr[i] >= 2){    //2면 증가시킴
                arr[i+1]++;
            }
            if(arr[i] >= 3){   //3이 되면 -1이 아닌 0으로 . 2일때 -1이므로
                arr[i] = 0;
            }
        }

        String answer = "";

        for(int i = 0 ;  i < arr.length-1; i++){//s 는 3진법의 역으로 나타낸 것 즉 뒤에서부터 본다
           if(arr[i] == 2){
               answer = "T" + answer;
           }else{
               answer = arr[i] + answer;
           }
        }

        if(arr[s.length()] != 0){
            answer = arr[s.length()] + answer;
        }

        if(minus){  //만약 - 였으면 1과 T바꿔줌
            for(int i = 0 ; i<answer.length(); i++){
                if(answer.charAt(i) == '1'){
                    System.out.print('T');
                } else if(answer.charAt(i) == 'T'){
                    System.out.print('1');
                }else{
                    System.out.print(answer.charAt(i));
                }
            }
            return;
        }


        System.out.println(answer);

    }


}
