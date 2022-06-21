package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P17281_야구 {

    static int n;
    static int[][] map;
    static int[] order = new int[10];          //몇번 타자에 몇번 선수가 배정될지 , 1번 선수는 항상 4번 타자
    static boolean[] select = new boolean[10];  //몇번 타자가 이미 뽑혔는지 체크합니다.
    static int ans = -1;                        //최대 점수

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][10];
        for(int i = 1; i<=n ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=9; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        order[4] = 1;       //1번 타자가 항상 4번 타자 이므로
        select[1] = true;   //1번 타자는 항상 지명되어있습니다.
        pro(1);
        System.out.println(ans);
    }

    //완전 탐색으로 모든 경우를 봅니다. 8! 약 4만번의 연산이 걸립니다
    //idx 번째 타석을 고릅니다.
    static void pro(int idx) {
        if(idx == 4){   //4번 타자는 이미 고정
            pro(idx+1);
            return;
        } else if(idx>=10){ //다 채웠을 경우
            //최대 점수 계산
            int ining = 1;  //진행되고 있는 이닝
            int order_num = 1;      //현재 타석의 위치
            int out_count = 0;      //3개면 ining증가
            int[] base = new int[4];
            int score = 0;
            while (ining<=n){   //N이닝 까지
                Arrays.fill(base , 0);
                while (out_count<3){
                    if(map[ining][order[order_num]] == 0){  //order[order_num]] 해야지 현재 타석에 몇번 선수가 있는지 확인가능
                        out_count++;
                        order_num++;
                        if(order_num>=10) order_num = 1;
                        continue;
                    }
                    // 1,2,3,4 일 때
                    if(map[ining][order[order_num]] == 4){
                        for(int i = 0 ; i<4 ;i++){
                            if(base[i] >= 1 ) {
                                score++;
                                base[i] = 0;    //홈런이라 있는 사람들 전부 들어옴
                            }
                        }
                        score++;    //출발 주루
                    } else{
                        for(int k = 1 ; k<= map[ining][order[order_num]] ; k++){
                            for(int i = 3; i>= 1; i--){
                                base[i] = base[i-1];
                            }
                            if(base[3] >= 1 ){  //홈으로 들어왔을 경우1
                                score++;
                                base[3] = 0;
                            }
                            base[0] = 0;
                        }
                        base[map[ining][order[order_num]]-1] = 1; //출발한 주자 셋팅.
                    }

                    order_num++;
                    if(order_num>=10) order_num = 1;
                }
                out_count = 0;  //이닝 끝나고 초기화
                ining++;
            }
            ans = Math.max(ans , score);
            return;
        }
        for(int i = 2 ; i<=9 ; i++){    //2번 타자 부터
            if(!select[i]){             //선택 안되어 있으면
                order[idx] = i;         //해당 선수 idx 번째  타석에 배치
                select[i] = true;
                pro(idx+1);
                order[idx] = 0;
                select[i] = false;
            }
        }
    }
}
