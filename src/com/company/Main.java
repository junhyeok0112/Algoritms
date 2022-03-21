package com.company;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


class Employee{
    String name =" hit";
    public String toString(){return name;}
}

class PartEmployee extends Employee{
    void work(){
        System.out.println("w2");
    }
}

public class Main {

    public static void main(String[] args) {
        Set<String> s = new TreeSet<>();
        s.add("서울");
        s.add("구미");
        s.add("광주");
        s.add("대전");
        s.add("부울경");
        s.add("서울");
        System.out.println(s);
       }


}


class Pair implements Comparable<Pair>{
    int num;
    ArrayList<Integer> arr ;

    Pair(int num , ArrayList<Integer> arr){
        this.num = num;
        this.arr = arr;

    }
    @Override
    public int compareTo(Pair o) {
        if(this.num > o.num){
            return 1;
        } else if(this.num < o.num){
            return -1;
        } else{
            if(this.arr.get(0) > o.arr.get(0)){
                return -1;
            } else if(this.arr.get(0) < o.arr.get(0)){
                return 1;
            } else{
                if(this.arr.get(1) > o.arr.get(1)){
                    return -1;
                } else if(this.arr.get(1) < o.arr.get(1)){
                    return 1;
                } else{
                    if(this.arr.get(2) > o.arr.get(2)){
                        return -1;
                    } else if(this.arr.get(2) < o.arr.get(2)){
                        return 1;
                    } else{
                        if(this.arr.get(3) > o.arr.get(3)){
                            return -1;
                        } else if(this.arr.get(3) < o.arr.get(3)){
                            return 1;
                        } else{
                            if(this.arr.get(4) > o.arr.get(4)){
                                return -1;
                            } else if(this.arr.get(4) < o.arr.get(4)){
                                return 1;
                            } else{
                                if(this.arr.get(5) > o.arr.get(5)){
                                    return -1;
                                } else if(this.arr.get(5) < o.arr.get(5)){
                                    return 1;
                                } else{
                                    if(this.arr.get(6) > o.arr.get(6)){
                                        return -1;
                                    } else if(this.arr.get(6) < o.arr.get(6)){
                                        return 1;
                                    } else{
                                        if(this.arr.get(7) > o.arr.get(7)){
                                            return -1;
                                        } else if(this.arr.get(7) < o.arr.get(7)){
                                            return 1;
                                        } else{
                                            if(this.arr.get(8) > o.arr.get(8)){
                                                return -1;
                                            } else if(this.arr.get(8) < o.arr.get(8)){
                                                return 1;
                                            } else{
                                                if(this.arr.get(9) > o.arr.get(9)){
                                                    return -1;
                                                } else if(this.arr.get(9) < o.arr.get(9)){
                                                    return 1;
                                                } else{
                                                    if(this.arr.get(10) > o.arr.get(10)){
                                                        return -1;
                                                    } else{
                                                        return 1;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

class Solution {


    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        ArrayList<Integer> ans = new ArrayList<>();

        PriorityQueue<Pair> res = new PriorityQueue<>();

        for (int a = n; a >= 0; a--) {      //a 부터 0점
            for (int b = n - a; b >= 0; b--) {
                for (int c = n - a - b; c >= 0; c--) {
                    for (int d = n - a - b - c; d >= 0; d--) {
                        for (int e = n - a - b - c - d; e >= 0; e--) {
                            for (int f = n - a - b - c - d - e; f >= 0; f--) {
                                for (int g = n - a - b - c - d - e - f; g >= 0; g--) {
                                    for (int h = n - a - b - c - d - e - f - g; h >= 0; h--) {
                                        for (int i = n - a - b - c - d - e - f - g - h; i >= 0; i--) {
                                            for (int j = n - a - b - c - d - e - f - g - h - i; j >= 0; j--) {
                                                for (int k = n - a - b - c - d - e - f - g - h - i - j; k >= 0; k--) {
                                                    int lion = 0, apeach = 0;
                                                    if (a + b + c + d + e + f + g + h + i + j + k != n)
                                                        continue;
                                                    if (info[0] >= k && info[0] != 0) {
                                                        apeach += 10;
                                                    } else {
                                                        if (k != 0)
                                                            lion += 10;
                                                    }
                                                    if (info[1] >= j && info[1] != 0) {
                                                        apeach += 9;
                                                    } else {
                                                        if (j != 0)
                                                            lion += 9;
                                                    }
                                                    if (info[2] >= i && info[2] != 0) {
                                                        apeach += 8;
                                                    } else {
                                                        if (i != 0)
                                                            lion += 8;
                                                    }
                                                    if (info[3] >= h && info[3] != 0) {
                                                        apeach += 7;
                                                    } else {
                                                        if (h != 0)
                                                            lion += 7;
                                                    }
                                                    if (info[4] >= g && info[4] != 0) {
                                                        apeach += 6;
                                                    } else {
                                                        if (g != 0)
                                                            lion += 6;
                                                    }
                                                    if (info[5] >= f && info[5] != 0) {
                                                        apeach += 5;
                                                    } else {
                                                        if (f != 0)
                                                            lion += 5;
                                                    }
                                                    if (info[6] >= e && info[6] != 0) {
                                                        apeach += 4;
                                                    } else {
                                                        if (e != 0)
                                                            lion += 4;
                                                    }
                                                    if (info[7] >= d && info[7] != 0) {
                                                        apeach += 3;
                                                    } else {
                                                        if (d != 0)
                                                            lion += 3;
                                                    }
                                                    if (info[8] >= c && info[8] != 0) {
                                                        apeach += 2;
                                                    } else {
                                                        if (c != 0)
                                                            lion += 2;
                                                    }
                                                    if (info[9] >= b && info[9] != 0) {
                                                        apeach += 1;
                                                    } else {
                                                        if (b != 0)
                                                            lion += 1;
                                                    }
                                                    if (lion > apeach) {
                                                        ArrayList<Integer> tmp = new ArrayList<>();
                                                        tmp.add(a);
                                                        tmp.add(b);
                                                        tmp.add(c);
                                                        tmp.add(d);
                                                        tmp.add(e);
                                                        tmp.add(f);
                                                        tmp.add(g);
                                                        tmp.add(h);
                                                        tmp.add(i);
                                                        tmp.add(j);
                                                        tmp.add(k);
                                                        res.add(new Pair(lion , tmp));
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (res.isEmpty())
            ans.add(-1);
        else {
            ArrayList<Integer> v = res.poll().arr;
            for (int i = 10; i >= 0; i--) {
                ans.add(v.get(i));
            }
        }

        int ansSize = ans.size();
        answer = new int[ansSize];
        for(int i= 0 ; i<ansSize; i++){
            answer[i] = ans.get(i);
        }
        return answer;
    }
}