package com.company;

import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
//시간 복잡도 터지면 줄일방법 생각 -> 이분탐색이 제일 무난
public class P2295_세수의합 {
    //1000 개중 3개 뽑는 방식으로하면 N^3 으로 시간 초과가 남
    //따라서 N^2LogN 으로 하기 위해 이분탐색을 이용해야함
    //이 때 a[x] + a[y] + a[z] = a[k] 를 만족해야하는데 이는
    //a[x]+a[y] = a[k] - a[y]와 동일 따라서 두 수의 합을 저장하는 배열을 새로 만들고
    //그 값중 a[k]] - a[y]를 만족하는지 체크 ->이 때 체크를 이분탐색으로하면 logN에 가능
    static int n;
    static int[] arr;
    static ArrayList<Integer> sum;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        sum = new ArrayList<>();
        for(int i =1 ; i<=n ;i++){
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }
        //1000 개중 3개 뽑는 방식으로하면 N^3 으로 시간 초과가 남
        for(int i = 1 ; i<=n;i++){
            for(int j = i; j<=n ;j++){
                sum.add(arr[i] + arr[j]);   //두 수의 합을 저장하는 배열 생성
            }
        }
        Arrays.sort(arr);
        Collections.sort(sum);

        //a[k] - a[z] 할 차례 큰걸 골라야 하므로 k를 역순으로
        for(int i = n; i>=1 ; i--){
            for(int j = 1; j<=n; j++){
                //arr[i] - arr[j] 가 Sum 안에 있는지 체크 -> 있으면 정답 !
                if(binarySearch(arr[i] - arr[j])){
                    System.out.println(arr[i]);
                    return;
                }
            }
        }

    }

    static boolean binarySearch(int num){
        int start = 0;
        int end = sum.size();
        while (start <= end){
            int mid = (start + end) / 2;
            if (sum.get(mid) < num) {
                start = mid + 1;
            } else if(sum.get(mid) > num){
                end = mid - 1;
            } else{
                return true;
            }
        }
        return false;
    }
}
