package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class P2632_피자판매 {

    static ArrayList<Integer> Ap = new ArrayList<>();
    static ArrayList<Integer> Bp = new ArrayList<>();
    static ArrayList<Integer> Asum = new ArrayList<>();         //A의 누적합들을 저장한 배열 -> 어떤 값으로 이루어져 있는지는 알 필요가 없다 -> 어떤 값을 알아야하는지 확실하게 체크 필요
    static ArrayList<Integer> Bsum = new ArrayList<>();         //B의 누적합들을 저장한 배열
    static int m,n, size;
    static boolean[] Avisit;
    static boolean[] Bvisit;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        size = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        for(int i = 0 ;  i<m;i++){
            Ap.add(Integer.parseInt(br.readLine()));
        }

        for(int i = 0 ; i <n; i++){
            Bp.add(Integer.parseInt(br.readLine()));
        }
// 누적합을 구한 뒤 정렬하는게 포인트 , 이 후 이 정렬된 A와 정렬된 B를 각각 왼쪽 , 오른쪽 끝에서부터 투포인터처럼 탐색하여서 해당 값을 찾으면된다.
//이 때 A나 B를 안쓸 수도 있으므로 0도 정렬할때 넣어줘야함

        //A의 누적합 구하기, 단 시작지점을 바꿔가며 구해야 모든 경우의 수를 구할 수 있다 -> 1번에서 시작한 누적합 , 2번에서 시작한 누적합 모두 다르기 때문.. 이런 간단한 문제를
        for(int i = 0 ; i <m ;i++){
            //i번째부터 누적합 구하기
            if(Ap.get(i) > size) continue;
            Avisit = new boolean[m];    //매번 초기화 해줘야함
            Asum.add(Ap.get(i));        //시작 점 셋팅
            Avisit[i] = true;
            int sum = Ap.get(i);
            int next = i+1;
            while (true) {
                if (next == Ap.size()) next = 0; //한바퀴 돌 경우
                if(i != 0 && next == i-1) break;        //전체 피자 판을 여러번 더하는 경우 방지 -> 예외 처리 안하면 전체 피자판을 매 시작점 마다 더하므로 틀린다.
                if (Avisit[next] || sum + Ap.get(next) > size)
                    break;         //방문한 곳이면 더이상 보지 않음 , 해당 값의 합이 구하려는 값보다 크면 탐색하지 않음
                Avisit[next] = true;
                sum += Ap.get(next);
                Asum.add(sum);
                next++;

            }
        }

        //B로만 이루어진 경우
        for(int i = 0 ; i <n ;i++){
            //i번째부터 누적합 구하기
            if(Bp.get(i) > size) continue;
            Bvisit = new boolean[n];    //매번 초기화 해줘야함
            Bsum.add(Bp.get(i));        //시작 점 셋팅
            Bvisit[i] = true;
            int sum = Bp.get(i);
            int next = i+1;
            while (true) {
                if (next == Bp.size()) next = 0; //한바퀴 돌 경우
                if(i != 0 && next == i-1) break;        //전체 피자 판을 여러번 더하는 경우 방지 -> 예외 처리 안하면 전체 피자판을 매 시작점 마다 더하므로 틀린다.
                if (Bvisit[next] || sum + Bp.get(next) > size)
                    break;         //방문한 곳이면 더이상 보지 않음 , 해당 값의 합이 구하려는 값보다 크면 탐색하지 않음
                Bvisit[next] = true;
                sum += Bp.get(next);
                Bsum.add(sum);
                next++;

            }
        }
        Asum.add(0);        //B만 사용할 경우를 위해
        Bsum.add(0);        //A만 사용할 경우를 위해
        //투포인터로 서로 비교하기 위하여 정렬
        Collections.sort(Asum);
        Collections.sort(Bsum);

        int ans = 0;
        int left = 0;
        int right = Bsum.size()-1;
        while (left <Asum.size() && right>=0){
            int A = Asum.get(left);
            int B = Bsum.get(right);
            int sum = A+B;
            if(sum == size){
                //안될 때 까지 left 움직인 수 * 안될 때까지 right 움직인 수  하면 총 개수가 나옴
                int lc = 0; //움직인 수
                int rc = 0; //움직인 수
                while (left<Asum.size() && Asum.get(left) + B == size){     //A만 움직여서 size 만큼 벗어났을 경우
                    lc++;
                    left++;
                }
                while (right>= 0 && Bsum.get(right) + A == size){
                    rc++;
                    right--;
                }
                ans += lc*rc;
            }

            if(sum < size) left++;
            if(sum > size) right--;
        }
        System.out.println(ans);

    }
}
