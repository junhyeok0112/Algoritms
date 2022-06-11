package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class P18428{
    int x;
    int y;
    P18428(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class P18428_감시피하기 {

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static char[][] map;
    static int n;
    static ArrayList<P18428> teacher_position = new ArrayList<>();
    static ArrayList<P18428> x_position = new ArrayList<>();            //비어있는 위치 저장하는 변수 ->
    //X들의 위치를 일렬로 세운다가 새로운 아이디어.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for(int i = 0 ; i<n ;i++){
            String[] temp = br.readLine().split(" ");
            for(int j = 0 ; j<n ;j++){
                map[i][j] = temp[j].charAt(0);
                if(map[i][j] == 'T'){
                    teacher_position.add(new P18428(i,j));
                } else if(map[i][j] == 'X'){
                    x_position.add(new P18428(i,j));
                }
            }
        }

        //3중 for 문으로 장애물 세우기 -> 3중 for문으로 세우려면 위치들을 일렬로 세워야합니다
        //이를 위해서 위치들을 저장할 배열이 필요합니다. -> 따라서 x_position 이라는 배열을 선언해야합니다.
        int xSize = x_position.size();
        for(int i = 0 ; i< xSize - 2; i++){
            int firstX = x_position.get(i).x;
            int firstY = x_position.get(i).y;
            map[firstX][firstY] = 'O';
            for(int j = i+1 ; j<xSize -1 ; j++){
                int secondX = x_position.get(j).x;
                int secondY = x_position.get(j).y;
                map[secondX][secondY] = 'O';
                for(int k = j+1 ; k<xSize; k++){
                    int thirdX = x_position.get(k).x;
                    int thirdY = x_position.get(k).y;
                    map[thirdX][thirdY] = 'O';
                    if(find() == 0){    //모두 숨을 수 있으면
                        System.out.println("YES");
                        return;
                    }
                    map[thirdX][thirdY] = 'X';
                }
                map[secondX][secondY] = 'X';
            }
            map[firstX][firstY] = 'X';
        }
        System.out.println("NO");
    }

    //선생님 위치에서 학생들 만날 수 있는지 찾기
    private static int find() {
        int tSize = teacher_position.size();
        for(int i = 0 ; i< tSize; i++){
            int x = teacher_position.get(i).x;
            int y=  teacher_position.get(i).y;
            for(int t = 0 ; t<4 ; t++){
                int rx = x;
                int ry = y;
                while (true){
                    int nx = rx + dx[t];
                    int ny = ry + dy[t];
                    if(nx< 0 || nx>=n || ny< 0 || ny>=n || map[nx][ny] == 'O' || map[nx][ny] == 'T') break;   //범위 벗어났거나 장애물 만나거나 선생님 만나면 멈춤
                    if(map[nx][ny] == 'S') return 1;        //학생만나면 리턴 1
                    rx = nx;
                    ry = ny;
                }
            }
        }
        return 0;
    }
}
