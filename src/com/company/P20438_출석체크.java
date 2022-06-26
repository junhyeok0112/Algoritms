package com.company;

import javax.lang.model.type.IntersectionType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

//코드를 받은 학생이 배수인 모든학생에게 뿌림 -> 이떄 졸고있는애면 못받음
public class P20438_출석체크 {

    static int n,k,q,m; //n 학생 수 , k 졸고 있는 학생 수 , q 지한이가 출석 코드를 보낼 학생 수 , m 주어질 구간수
    static boolean[] visit; //출석 되어있는지
    static int[] prefix_sum;    //3~N 까지 출석체크 안되어 있는 학생들의 수
    static HashSet<Integer> sleep_student = new HashSet<>();//졸고있는 학생들의 목록

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new boolean[n+3];       //3~n+2 까지
        prefix_sum = new int[n+3];
        //졸고 있는 학생 입력받기
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<k; i++){
            sleep_student.add(Integer.parseInt(st.nextToken()));
        }

        //출석 코드 부여하기 -> 해당 애들의 배수 만큼
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i<q; i++){
            int code = Integer.parseInt(st.nextToken());
            if(sleep_student.contains(code)) continue;  //자고 있는 애면 배분 X
            int cnt = 1;
            int temp = cnt * code;
            while (temp <= n+2){
                if(sleep_student.contains(temp)) {
                    temp+=code;
                    continue;
                }
                visit[temp] = true;
                temp+=code;     //배수 구하기
            }
        }

        //prfix_sum에 3~N 까지 범위에 출석 체크 안한 학생 수 체크하기
        for(int i = 3; i<=n+2; i++){
            prefix_sum[i] = prefix_sum[i-1];
            if(!visit[i]) prefix_sum[i]++;
        }

        for(int i = 0 ; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(prefix_sum[end] - prefix_sum[start-1]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}
