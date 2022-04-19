package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class P14891_톱니바퀴 {

    static ArrayList<Integer>[] arr;
    static boolean[] visit;
    static int k;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 1; i <= 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                arr[i].add(str.charAt(j) - '0');
            }
        }
        k = Integer.parseInt(br.readLine());
        //입력받을 때마다 동작
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());      //1이면 시계 , -1이면 반시계
            visit = new boolean[5];
            move(num, dir);
        }

        System.out.print(cal());

    }


    //1번 톱니의 2번과 2번 톱니의 6번 맞닿아있음
    //2번 톱니의 2번과 3번 톱니의 6번 맞닿아있음
    //3번 톱니의 3번과 4번 톱니의 6번 맞닿아있음
    //list.add로 원하는 위치에 입력 가능
    //list.remove로 원하는 위치에 제거 가능
    //move 1번에 의해 똑같은게 2번 움직일 순 없다 . 따라서 visit로 체크
    static void move(int num, int dir) {          //cnt는
        if (visit[num]) return;                      //이미 돌렸으면 return 해서 안돌림
        if (num == 1) {           //들어온 값이 1 일때
            //1-2 와 2-6 같은지 확인.
            //1번이 움직인 적이 없으면 -> 다른거에 영향을 받아 2번 움직이는게 아니라는뜻
            if (arr[1].get(2) != arr[2].get(6)) {     //같으면 1번만 회전
                moveNow(num , dir );
                move(2, dir * -1);             //2번도 회전시켜라
            } else{
                moveNow(num,dir);
            }
        } else if (num == 2) {     //들어온 값이 2 일때
            //2-2와 3-6 , 2-6과 1-2 봐야함
            //2번이 움직인 적이 없으면 -> 다른거에 영향을 받아 2번 움직이는게 아니라는뜻

            if (arr[2].get(2) != arr[3].get(6) && arr[2].get(6) == arr[1].get(2)) {        //3번이랑만 같으면 . 1,2반민 덜ㄹ;ㅁ
                moveNow(num , dir );
                move(3,dir*-1);
            } else if (arr[2].get(2) == arr[3].get(6) && arr[2].get(6) != arr[1].get(2)) {        //1번이랑만 같으면 -> 2,3번만 돌림
                moveNow(num , dir );
                move(1,dir*-1);
            } else if(arr[2].get(2) != arr[3].get(6) && arr[2].get(6) != arr[1].get(2)){                 //둘다 다르면 -> 둘다 이동
                moveNow(num , dir);
                move(1,dir*-1);
                move(3,dir*-1);
            } else{
                moveNow(num, dir);
            }
        } else if (num == 3) {     //들어온 값이 3일떄

            if (arr[3].get(2) != arr[4].get(6) && arr[2].get(2) == arr[3].get(6)) {        //4번과 다르고 2번과 같을 경우 4번이 움직임
                moveNow(num , dir );
                move(4,dir*-1);
            } else if (arr[3].get(2) == arr[4].get(6) && arr[2].get(2) != arr[3].get(6)) {        //1번이랑만 같으면 -> 2,3번만 돌림
                moveNow(num , dir );
                move(2,dir*-1);
            } else if(arr[3].get(2) != arr[4].get(6) && arr[2].get(2) != arr[3].get(6)){                 //둘다 다르면 -> 둘다 이동
                moveNow(num , dir );
                move(2,dir*-1);
                move(4,dir*-1);
            } else{
                moveNow(num,dir);
            }
        } else {     //들어온 값이 4일때
            if (arr[4].get(6) != arr[3].get(2)) {
                moveNow(num , dir );
                move(3,dir*-1);
            } else{
                moveNow(num,dir);
            }
        }
    }

    //정답 계산하는 함수
    static int cal(){
        int sum = 0;
        for(int i = 1; i<=4; i++){
            sum+=arr[i].get(0)*Math.pow(2,i-1);
        }
        return sum;
    }

    static void moveNow(int num , int dir){
        if (dir == 1) {                   //시계 방향일 떄
            int temp = arr[num].get(7);   //맨 뒤에꺼 빼서 맨 앞으로 넣으면 시계방향
            arr[num].remove(7);
            arr[num].add(0, temp);
        } else {                         //반 시계 방향일때  , 맨 앞에꺼 빼서 맨뒤에다 넣으면 반시계
            int temp = arr[num].get(0);
            arr[num].remove(0);
            arr[num].add(7, temp);
        }
        visit[num] = true;                //한번 움직였다고 체크
    }
}

