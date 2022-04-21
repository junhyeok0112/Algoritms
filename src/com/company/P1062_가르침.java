package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class P1062_가르침 {

    static int n,k;
    static String[] arr;
    static int max = -1;
    static boolean[] visit = new boolean[26];

    //문자열들을 보면서 가능한 알파벳 개수르 세는게 아니라
    //가능한 K개의 알파벳들을 백트래킹으로 미리 뽑아두고 , 문자열들을 비교하면서 최대값을 구하는 것이다
    //내 생각과는 반대로 생각하는게 핵심 즉 26개의 알파벳 중에 K개 뽑는 경우를 구해야함... 이 K개 뽑힌 알파벳 경우의 수들로 문자열들 비교
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(k < 5){      //n이 5보다 작으면 어떠한 단어도 읽을 수 없음
            System.out.println(0);
            return;
        }
        k -=5;          //5개 a,n,t,i,c는 기본적으로 가지고 있어야함 visit 체크로 true로 바꿔줌
        visit['a'-'a'] = true;
        visit['n'-'a'] = true;
        visit['t'-'a'] = true;
        visit['i'-'a'] = true;
        visit['c'-'a'] = true;
        arr = new String[n];
        for(int i = 0 ; i<n ;i++){
            arr[i] = br.readLine();
        }

        back(0,0);
        System.out.println(max);
    }

    public static void back(int cnt , int idx){        //baseNum으로 들어가있는지 체크.
        //기저조건 , cnt == K일때 계산 , idx == n일때 계산 , cnt > k면 return;
        if(cnt >= k || idx >= 26 ){      //k개를 채웠거나 끝까지 봤을 경우
            //가능한 문자열 계산해야함
            int ans = 0;
            for(int i = 0 ; i <n ;i++){
                String cur = arr[i];
                int len = cur.length();
                boolean flag = true;
                for(int j = 0 ; j<len ;j++){
                    //고른 알파벳들로 만들 수 있는지 체크
                    if(!visit[cur.charAt(j) - 'a']){        //하나라도 불가능하면 탈출
                        flag = false;
                        break;
                    }
                }
                if(flag) ans++;                             //그 문자열 만들 수 있으면 갯수증가
            }
            max = Math.max(max, ans);       //최대 갯수 세기
            return;
        }

        //전부 다 확인해보기
        for(int i = idx ; i< 26;i++){
           if(i == 'a'-'a' || i=='n'-'a' || i =='t'-'a' || i=='i'-'a' || i =='c'-'a') continue;     //기본적으로 있는 값이면 넘어감
            if(!visit[i]){  //방문 안했으면
                visit[i] = true;
                back(cnt+1, idx+1);     //방문 체크하고 다음것부터봐라
                //다 보고 나오면
                visit[i] = false;
            }
        }
    }
}
