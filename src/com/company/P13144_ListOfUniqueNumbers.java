package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P13144_ListOfUniqueNumbers {
    static long fact(int n){
        long res = 0;
        for(int i =1 ; i<=n; i++){
            res +=i;
        }
        return res;
    }
    //왼쪽 시작 L 결정 ->O(N)
    //오른쪽 끝을 R을 이전의 R부터 시작해서 이동
    //R을 이동해서 추가된 원소가 [L,R-1]안에 있는지 확인 ->O(1) 따라서 총 O(N)이다.
    public static void main(String[] args) throws IOException {
        //N개의 수열에서 가능한 모든 부분수열들의 개수 합 구하는 문제이다 .
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        int[] visit = new int[100001]; //수열에 들어가 있는지 체크할 배열 ->이 방법이 매우중요
        long ans = 0 ;   //정답 저장할 변수 - > N이 최대 10만인데 최악의 경우 N + (N-1) + (N-2) + ... +2 + 1 = 50억이므로 long 선언
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =1 ; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //R이 안되는걸 만났을떄 R-1까지는 문제없음
        int R = 0;
        for(int L = 1; L<=N;L++){
            //만약 L = 2이고 R = 4 이면 L이 2인 지점을 시작으로 만들 수 있는 연속수열의 개수는 3개이다.
            visit[arr[L-1]] = 0;//전에꺼 빼고 계산
            while(R<=N && visit[arr[R]] == 0){
                visit[arr[R]] = 1;   //갱신해줌
                R++;
            }   // R-1까지 체크한것 ->즉 R자리에는 중복 따라서 [L..R-1]까지 연속된 수열 개수 찾으면됨
            ans += (R-L);
        }

        //이 방식으로도 가능 내가 시작한것과 R체크만 다름 ->딱 R까지개수 세면됨
//        int R= 0 ;
//        for(int L = 1; L <=N;L++){
//            while (R+1 <=N && visit[arr[R+1]] == 0){  R에서 다음꺼 갈 수 있는지 확인 후 이동
//                R++;
//                visit[arr[R]] = 1;
//            }
//            ans+=R -L +1;
//            visit[arr[L]]--;
//        }
        System.out.println(ans);


    }
}
