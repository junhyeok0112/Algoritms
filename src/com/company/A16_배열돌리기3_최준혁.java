package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class A16_배열돌리기3_최준혁 {

    static int n, m, r;
    static int[][] arr;
    static ArrayList<Integer> command = new ArrayList<>();//명령어 저장


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            command.add(Integer.parseInt(st.nextToken()));
        }
        for(int cur : command){
            com(cur);
        }

        for(int i = 0 ; i<n;i++){
            for(int j = 0 ; j<m ;j++){
                System.out.print(arr[i][j]+ " ");
            }
            System.out.println();
        }

    }

    //N과 M은 짝수
    static void com(int t ) {
        int[][] temp;
        if (t == 1) {         //상하 반전
            temp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[n - 1 - i][j];
                }
            }
        } else if (t == 2) {
            //좌우 반전
            temp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = arr[i][m - 1 - j];
                }
            }
        } else if (t == 3) {   //오른쪽90도회전 -> 좀따 다시공부
            temp = new int[m][n];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    temp[i][j] = arr[n - 1 - j][i];
                }
            }
            int swap = n;
            n =m;
            m= swap;
        } else if (t == 4) {     //왼쪽 90도는 오른쪽 270도와 동일 , 좀따 왼쪽 90도도 연습
            temp = new int[m][n];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    temp[i][j] = arr[j][m - 1 - i];
                }
            }
            int swap = n;
            n =m;
            m= swap;
        } else if (t == 5) {
            temp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i < n / 2) {
                        if (j < m / 2) {
                            temp[i][j] = arr[i + n / 2][j];
                        } else {
                            temp[i][j] = arr[i][j - m / 2];
                        }
                    } else {
                        if (j < m / 2) {
                            temp[i][j] = arr[i][j + m / 2];
                        } else {
                            temp[i][j] = arr[i - n / 2][j];
                        }
                    }
                }
            }
        } else {      //6일때
            temp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i < n / 2) {
                        if (j < m / 2) {
                            temp[i][j] = arr[i][j + m / 2];
                        } else {
                            temp[i][j] = arr[i + n / 2][j];
                        }
                    } else {
                        if (j < m / 2) {
                            temp[i][j] = arr[i-n/2][j];
                        } else {
                            temp[i][j] = arr[i][j - m / 2];
                        }
                    }
                }
            }

        }
        arr= temp;
    }
}
