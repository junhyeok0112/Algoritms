package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11663_선분위의점 {

    static int n,m;
    static int[] point;     //점 들의 위치
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        point = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<n ;i++){
            point[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(point);
        //인덱스끼리 빼면 선분위의 값 나옴
        for(int i = 0 ;i <m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            //start보다 같거나 큰 (right) 점과 end 보다 작거나 같은 (left) 위치 구하면됨 -> start는 일반적인 이분탐색으로 , end는 이분탐색의 결과가 본인보다 큰 점이므로 -1 해서 범위내에 포함시켜준다.

            sb.append(binary(end ,false) - binary(start,true) ).append("\n");

        }
        System.out.println(sb.toString());
    }
    //갯수를 구하는게 포인트
    //끝점보다 작거나 같은 것들의 갯수 - 시작점보다 작은 것들의 개수 = 사이에 존재하는 점들의 개수 -> 개수를 세는게 포인트입니다.
    static int binary(int num , boolean flag){        //flag는 start점이 들어왔는지 ,end점이 들어왔는지
        int L = 0;
        int R = n-1;
        while (L<=R){                   //같을 때까지 돌면 num이 크면 L 증가 즉 , R 범위 부터 , num이 작으  R 이 감소하면서 범위 넘어감 이때도 R 부터 보면 작거나 같은 거 볼 수 있음
            int mid = (L+R) /2;
            if(point[mid] == num){
                if(flag) return mid;    //시작점
                else return mid+1;      //끝점 -> 갯수
            }
            else if(point[mid] < num){  //찾는 숫자가 더 오른쪽이면
                 L = mid +1;             //L 값을 늘리면서 범위 감소
            } else{
                R = mid -1;
            }
        }
        //리턴을 인덱스가 아닌 점들의 개수를 리턴합니다 . 예를들어 R = 6이고 끝점이면 인덱스 6까지 즉 0~6까지 총 7개 이므로 끝점일때 R +1 리턴
        //시작점이면 시작점보다 작은 점들의 개수 => L 의 인덱스 값이 갯수입니다.  예를들어 1 4 7 에서 6이면 L이 7에 위치합니다 L은 따라서 2 이므로 6보다 작은 점의 갯수는 2개 입니다. 따라서 L 리턴
        if(flag) return L;      //시작점
        else return R+1;        //끝점
    }

}
