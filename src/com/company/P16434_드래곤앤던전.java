package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class P16434{
    int num;        //1이면 몹 , 2이면 포션
    int a;
    int h;
    P16434(int num ,int a, int h){
        this.num = num;
        this.a = a;
        this.h = h;
    }
}
public class P16434_드래곤앤던전 {

    static int n;
    static int atk;
    static P16434[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        atk = Integer.parseInt(st.nextToken());
        arr = new P16434[n+1];          //n번째 방까지 존재

        for(int i = 1; i<=n ;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            arr[i] = new P16434(num ,a ,h);
        }

        long left = 0 ;
        //long right =Long.MAX_VALUE;     //그냥 Long의 최대 범위로
        long right =(long) Math.pow(1000000,2)*n + 1;
        long ans = 0;
        while(left <= right){
            long mid = (left + right) / 2;
            //mid 라는 hp 가지고 던전 꺨 수 있는지 체크
            boolean chk = true;
            //던전 탐색
            long tempAtk = atk;      //임시 공격력
            long tempHP = mid;       //최대 체력
            for(int i = 1; i<=n ;i++){
                if(arr[i].num == 1){            //몬스터가 있을 경우
                    long user = 0;              //user 가 몇번 맞아야 죽는지
                    long mob = 0 ;              //몹이 몇번 맞아야 죽는지

                    if(tempHP % arr[i].a != 0){    //나머지가 있으면
                        user = (tempHP / arr[i].a) + 1;  // 몫 + 나머지 까지
                    } else user = tempHP / arr[i].a;

                    if(arr[i].h % tempAtk != 0){
                        mob = (arr[i].h / tempAtk) + 1;
                    } else mob = (arr[i].h / tempAtk);

                    if(user>= mob){     //유저가 이기는 경우
                        tempHP -= (mob-1) * arr[i].a;        // HP에서 유저가 때린 수 -1 * 몹의 공격력 만큼 HP 달음
                    } else{             //몹이 이기는 경우
                        chk = false;
                        break;
                    }
                } else{                         //포션이 있을 경우
                    tempAtk+=arr[i].a;
                    tempHP = Math.min(mid , tempHP + arr[i].h);
                }
            }
            //만약 던전 꺨 수 있으면 right 를 mid - 1로 해서 더 적은 HP 구함
            if(chk){
                ans = mid;
                right = mid -1;
            }
            //만약 던전 못 깨면 left 를 mid +1 로 해서 더 큰 HP 구함
            else{
                left = mid + 1;
            }
        }
        System.out.println(ans);
    }
}
