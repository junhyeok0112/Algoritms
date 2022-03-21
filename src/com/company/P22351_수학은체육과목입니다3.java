package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P22351_수학은체육과목입니다3 {

    static String s;
    static String[] arr = new String[1001];
    static int start = 0;
    static int end = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        int ssize = s.length();
        if(ssize >=3) {
            String temp = "";
            int one = Integer.parseInt(s.substring(0, 1));
            int curOne = one;
            while(one<1000){
                temp +=one;
                if(s.equals(temp)){
                    start = curOne;
                    end = one;
                    System.out.println(start + " "+ end);
                    return;
                }
                one++;
            }
            temp = "";
            int two = Integer.parseInt(s.substring(0, 2));
            int curTwo = two;
            while(two<1000){
                temp +=two;
                if(s.equals(temp)){
                    start = curTwo;
                    end = two;
                    System.out.println(start + " "+ end);
                    return;
                }
                two++;
            }
            temp = "";
            int three = Integer.parseInt(s.substring(0, 3));
            int curThree = three;
            while(three<1000){
                temp +=three;
                if(s.equals(temp)){
                    start = curThree;
                    end = three;
                    System.out.println(start + " "+ end);
                    return;
                }
                three++;
            }
        } else if(ssize == 2){
            String temp = "";
            int one = Integer.parseInt(s.substring(0, 1));
            int curOne = one;
            while(one<1000){
                temp +=one;
                if(s.equals(temp)){
                    start = curOne;
                    end = one;
                    System.out.println(start + " "+ end);
                    return;
                }
                one++;
            }
            temp = "";
            int two = Integer.parseInt(s.substring(0, 2));
            int curTwo = two;
            while(two<1000){
                temp +=two;
                if(s.equals(temp)){
                    start = curTwo;
                    end = two;
                    System.out.println(start + " "+ end);
                    return;
                }
                two++;
            }
        } else if (ssize ==1){
            start = Integer.parseInt(s);
            end = Integer.parseInt(s);
            System.out.println(start + " "+ end);

        }

    }



}
