package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class P10816_숫자카드2 {

    static int N,M ;
    static int[] have;
    static int[] find;
    static int[] res;
    //find의 값을 have에서 찾는거

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        have = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            have[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(have ,1 ,N+1);                  //toIndex 전까지 정렬
        M = Integer.parseInt(br.readLine());
        find = new int[M+1];
        res = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1 ; i<=M ;i++){
            find[i] = Integer.parseInt(st.nextToken());
            //System.out.println(uppuer_bound(find[i])+" "+ lower_bound(find[i]));
            res[i] = uppuer_bound(find[i]) - lower_bound(find[i]);      //find[i] 에 관한 upper와 loewr 찾기 범위 안에 값이 없으면 둘이 같은 값이 나오므로 0
            //만약 찾는 값이 정렬된 수 중 제일 큰 값이면 upper는 배열 개수 +1 이 return 되어서 -값은 나오지 않음
        }

       for(int i = 1 ;i<=M ;i++){
           sb.append(res[i]).append(" ");
       }

        System.out.println(sb);

    }
    static int lower_bound(int value){
        //loewr_bound는 찾는 값과 같은 값이 처음 나오는 index 출력, 같은값이 없으면 같은 값보다 큰값이 처음 나오는 index 출력
        int left = 1;
        int right = N+1;                //lower,upper 바운드의 오른쪽값은
        int mid = 0;
        while (left <right){
            mid = (left + right) / 2;
            if(value<=have[mid]){       //찾는 값이 중간 값보다 작으면 mid의 왼쪽범위에서 찾는 값을 탐색하거나 , 중간값과 찾는 값이 같으면 더 앞쪽에 찾는 값이 나오는지 알기위해 앞에범위에서 탐색
                right = mid;            //단 , 같을경우 앞에 더 같은 값이 안나올 수도 있으므로 , 같을경우의 index를 right로 저장해서 기억해둠
            } else{                     //중간 값보다 클경우 더 큰범위에서 재탐색
                left = mid +1;
            }
        }
        return left;                    //while문 끝에서 left의 값이 찾는 값과 같거나 큰 값의 시작점의 index
    }

    static int uppuer_bound(int value){
        int left = 1;
        int right= N+1;
        int mid = 0;
        while(left < right){
            mid = (left + right) / 2;
            if(value>=have[mid]){           //내가 찾는 값보다 큰 값을 찾아야하므로 중간 값이 같거나 작으면 더 큰 값이 있는범위에서 탐색
                left = mid + 1;
            }else{                         //내가 찾는 값보다 중간 값이 크면 더 작은 수들이 있는 범위에서 내 중간값보다 큰값을 다시 탐색해서 처음 큰 값이 나오는 위치 탐색
                right = mid;
            }
        }
        return left;
    }

}
