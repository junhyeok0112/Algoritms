package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;


class Info{
    ArrayList<Integer> arr;
    String name;
    Info(String name , ArrayList<Integer> arr){
        this.name = name;
        this.arr = arr;
        Collections.sort(arr,Collections.reverseOrder());
    }

    void plus(ArrayList<Integer> temp){ //num개수 더해줌
        int tempsize = temp.size();
        for(int i = 0 ; i<tempsize; i++){
            arr.add(temp.get(i));
        }
        Collections.sort(arr,Collections.reverseOrder());
    }

    long out(int n){
        long res = 0;
        if(n<=arr.size()) {
            for (int i = 0; i < n; i++) {
                res += arr.get(0);
                arr.remove(0);
            }
        } else{
            int arrsize =arr.size();
            for (int i = 0; i < arrsize; i++) {
                res += arr.get(0);
                arr.remove(0);
            }

        }
        return res;
    }

}

class Wait{
    String name;
    int num;
    Wait(String name, int num){
        this.name = name;
        this.num = num;
    }
}

public class 정보상인호석 {

    static int N ;
    static long ans ;
    static HashMap<String, Info> hm = new HashMap<>();
    static HashMap<String ,Integer> Wait = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        for(int i = 0 ; i<N ;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 1){
                ArrayList<Integer> temp = new ArrayList<>();
                String name = st.nextToken();
                int count = Integer.parseInt(st.nextToken());
                for(int j = 0 ;j<count;j++){
                    temp.add(Integer.parseInt(st.nextToken()));
                }
                Info one = new Info(name , temp);
                if(hm.containsKey(name)){
                    hm.get(name).plus(temp);
                }else {
                    hm.put(name, one);
                }
            } else{
                String name = st.nextToken();
                int get = Integer.parseInt(st.nextToken());
                if(hm.containsKey(name)) {
                    ans += hm.get(name).out(get);
                    if(hm.get(name).arr.size() == 0){
                        hm.remove(name);
                    }
                }   //가지고 있을경우 만약 안가지고있으면?
                else{
                    Wait.put(name,get);
                }

            }

            //만약 이전에 산다고 기다리고 있을경우

        }
        System.out.println(ans);
    }

}
