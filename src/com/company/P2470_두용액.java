package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P2470_두용액 {

    static int N;
    static int[] arr;
    static int ansMin = Integer.MAX_VALUE;  //합이 작은 값 갱신
    static int ans1  = 0 ;      //mas가 갱신되었을 때의 용액 정보 저장
    static int ans2 = 0;



    static int find(int[] arr , int num ,int left , int right){
        //이분탐색으로 배열안에 있는 수의 반대부호 수를 구한 뒤
        //그 값과 가장 가까이 있는 배열의 값 찾기
        //즉 arr[L...R]에서 -arr[i]과 가장 가까운 값 찾기
        //A[L....R]에서 X이상의 수 중 제일 왼쪽 인덱스를 return 하는 함수
        //만약 그런게 없다면 R + 1을 return한다.   ->그런게 없다 -> result가 범위를 벗어난다 ->즉 제일 큰 값보다 찾는 값이 더크다
        int result = right +1 ;
        int fnum = -num;    //왼쪽에서 고른 용액의 부호 바꿈
        //합해서 0과 가장 가까우려면 -arr[left]와 가까워야함
        //따라서 left+1 부터 N까지 -> 즉 고른것의 오른쪽에 있는 놈들의 값중 찾아야함
        //따라서 fnum이상의 원소 중 가장 왼쪽에 있는 원소를 result라고 하고
        //fnum과 가까운 원소는 arr[result] 또는 arr[result -1]중 존재한다
        //fnum이상의 원소 중 본 것 이므로 result보다 크면 funm과 더 멀어진다 ->result가 큰 것중 제일 가까운값
        //result -1이 작은것중 제일 가까운 값 -> 따라서 2개비교 ( 단 둘다 left +1  ~ N 사이에 존재해야함
        while(left <=right){
            int mid = (left + right) / 2;
            if(arr[mid] >= fnum){   //찍었을때 내가 찾고자하는 수보다 크거나 같은경우 그 mid자리부터는 내가 찾을 수 보다 큰값들 따라서 result 를 mid로 갱신
                //우리는 찾는 값이상인 갑중 가장 왼쪽 index 찾는 것이므로 큰 범위들을 result로 갱신
                result = mid;
                right = mid - 1;
            } else if(arr[mid] < fnum){
                //찾는게 더 큰경우 -> result를 left값으로 갱신
                left = mid + 1;
            }
        }
        return result;  //구한 result값 리턴 -> 값 비교와 갱신은 main에서

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1 ; i<=N ;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr , 1 , N+1); //실수한 부분 : 정렬범위 지정-> 1부터했으므로 0포함하면안됨

        for(int left = 1 ; left <= N -1; left++){       //left +1부터 봐야하므로  left = N 이면 indexError
            int res = find(arr, arr[left] , left+1, N); //result를 리턴하는 함수 실행

            //갱신해주는 작업 -> res가 left +1 ~ N까지 있어야함
            if(left +1 <=res -1 && res -1 <=N && Math.abs(arr[res-1] + arr[left]) < ansMin){    //범위안에 있고 max보다 작으면 갱신
                ansMin = Math.abs(arr[res-1] + arr[left]);
                ans1 = arr[left];
                ans2= arr[res-1];
            }
            //arr[result] 와 arr[result -1 ] 2개 값 봐야 하므로 if 문 2개
            if(left +1 <=res && res <=N && Math.abs(arr[res] + arr[left]) < ansMin){
                ansMin = Math.abs(arr[res] + arr[left]);
                ans1 = arr[left];
                ans2 = arr[res];
            }
        }

        System.out.println(ans1 + " " + ans2);

    }
}


