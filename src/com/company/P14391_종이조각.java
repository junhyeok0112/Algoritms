package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class P14391_종이조각 {

    static int n,m;         //n,m은 4이하의 값
    static int[][] map;
    static boolean[][] visit;
    static int max = 0;     //최대값
    //하나의 자리에서 그 수를 세로로 셀지 , 가로로 셀지 결정할 수 있다 -> visit 배열의 true, false로 구분가능
    //true면 가로로 놓여진 수 , false 면 세로로 놓여진 수.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];
        for(int i = 0 ;i<n ;i++){
            String str = br.readLine();
            for(int j = 0 ;j<m; j++){
                map[i][j] = str.charAt(j) - '0';
            }
        }
        //0,0에서부터 시작
       // pro(0,0);
        cal2();
        System.out.println(max);

    }

    static void pro(int x , int y ){     //x,y가 시작 위치  ,cnt 는 행의 수 , 재귀를 하나의 행을 다보면 다음 행으로 넘어가게 한다.
        //마지막 행까지 다 봤을 경우
        if(x >= n){
            cal();  //총 합을 계산하는 함수
            return;
        }
        //마지막 열까지 다 봤을 경우 -> 다음 행으로 진행
        if(y>=m){
            pro(x+1,0);     //다음 행의 처음부터 보기
            return;
        }

        //해당 위치를 가로로 셀거다라고 체크 -> true , 이 후 다음 열 확인
        visit[x][y] = true;
        pro(x,y+1);
        //해당 위치를 세로로 셀거다라고 체크 -> false , 이 후 다음 열 확인
        visit[x][y] = false;
        pro(x,y+1);
    }

    private static void cal() {
        //총 합을 계산하는 함수 .
        int sum = 0;            //총합
        //visit 가 true면 가로로 계산 , 가로로 계산하면서 끊어져 있으면 거기까지만 계산 후 더함
        for(int i = 0 ; i<n ;i++){  //가로로 보면서 가로로 놓여진 수인지 확인
            int temp = 0;
            for(int j = 0 ; j<m ;j++) {
                //자리수 생각하면서 계산해줘야함, 먼저 나온게 자리수가 큼
                if(visit[i][j]){     //가로로 세는 수이면
                    temp *= 10;     //자리수 증가
                    temp+=map[i][j];
                } else{             //세로로 증가해야하는 수이면 계산 안하고 , 여태 계산 된 값을 sum 에 더해야함
                    sum+= temp;
                    temp = 0;
                }
            }
            //한 줄 다봤으면 더해줘야함
            sum+=temp;
            temp = 0;
        }

        //visit가 false 면 세로로 계산 , 세로로 계산하면서 끊어져 있으면 거기까지만 계산 후 더함
        for(int i = 0 ; i<m; i++){      //세로로 보면서 세로에 놓여진 수인지 확인
            int temp = 0;
            for(int j = 0 ; j<n ;j++){
                //자리수 생각하면서 계산해 줘야함, 먼저 나온게 자리수가 큼
                if(!visit[j][i]){       //세로로 세는 수이면
                    temp*=10;           //이전 값들의 자리수 증가
                    temp+=map[j][i];
                } else{                 //가로로 증가해야하는 수이면 계산 안하고, 여태 계산 된 값을 sum에 더해야함.
                    sum+=temp;
                    temp = 0;
                }
            }
            //한 줄 다봤으면 더해줘야함
            sum+=temp;
            temp = 0;
        }

        max = Math.max(sum , max);      //최대값 갱신
    }

    //총 합 계산을 비트 마스킹으로 할 수 있지 않을까 ?
    //비트 마스킹을 이용하면 visit 배열을 사용할 필요가 없다.
    //n*m 배열을 하나의 비트로 나타난 값으로 보면된다, 예를들어
    //0000
    //0000
    //0001  이라면 이 2차원 배열을 0000 0000 0001 이라는 하나의 숫자로 나타낼 수 있다. 이때 0은 가로 ,1은 세로로 세어질 수라고 보자.
    //이렇듯 0~ 1<<(n*m) 까지의 숫자들로 체크할 수 있는 모든 경우의 수를 체크할 수 있다.
    static void cal2(){
        for(int s = 0 ; s<(1<<(n*m)) ; s++){        //s를 0000 .. ~ 1111.1111.. 까지 보겠다 . 이떄 0이면 가로 , 1이면 세로값이다.
            int sum = 0;
            //가로 확인하기 , visit 썼을 떄와 동일
            for(int i = 0 ; i<n ;i++){
                int temp = 0 ;
                for(int j = 0 ; j<m; j++){
                    if((s & (1<<(i*m+j))) != (1<<(i*m+j))){     //s를 2진수로 나타냈을 떄 i*m + j 번째의 값이 0이면 가로계산 , i가 행을 나타냄 -> 즉 i = 1, j= 1 이면 m개를 확인 하고 1개 추가 이므로 i*m
                        temp *= 10;
                        temp+=map[i][j];
                    } else{
                        sum += temp;
                        temp =0;
                    }
                }
                sum += temp;
                temp=0;
            }

            //세로 확인하기 , visit 썼을 떄와 동일
            for(int i = 0 ; i<m ;i++){
                int temp = 0 ;
                for(int j = 0 ; j<n; j++){
                    if((s & (1<<(j*m+i))) == (1<<(j*m+i))){     //s를 2진수로 나타냈을 떄 i*j + i 번째의 값이 1이면 세로 계산 , 비트 위치 계산 중요
                        temp *= 10;
                        temp+=map[j][i];
                    } else{
                        sum += temp;
                        temp =0;
                    }
                }
                sum += temp;
                temp=0;
            }
            max = Math.max(sum,max);
        }
    }

}
