package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A03_상호의배틀필드_최준혁 {

    static int t,h,w,n;       //n은 명령어 개수
    static char[][] map;
    static char[] command;
    static int[] dx = {-1,1,0,0};       //U D L R 상하좌우로 움직임
    static int[] dy = {0,0,-1,1};
    static int startX = 0;     //전차의 시작 위치 저장
    static int startY = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        int tc = 0;
        while(tc++ <t){
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h][w];
            for(int i = 0 ; i<h ;i++){
                String str = br.readLine();
                for(int j = 0 ;j<w ;j++){
                    map[i][j] = str.charAt(j);
                    //전차의 초기 위치 저장.
                    if(map[i][j] == '^' || map[i][j] == 'v' || map[i][j] =='<' ||map[i][j] == '>'){
                        startX = i;
                        startY = j;
                    }
                }
            }
            n = Integer.parseInt(br.readLine());
            command = new char[n];
            String str = br.readLine();         //명령어 입력
            for(int i = 0 ; i<n; i++){
                command[i] = str.charAt(i);     //명령어들 배열에 입력받음
                play(command[i] ,startX ,startY);               //명령어 실행. 전차의 위치를 가지고 진행해야함.
            }


            sb.append("#"+tc+" ");
            for(int i = 0 ;i<h ;i++) {
                for (int j = 0; j < w; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void play(char command ,int x,int y){
        if(command == 'U'){
            move(0,x,y);   //전차 움직이는 함수
        }else if(command == 'D'){
            move(1,x,y);
        }else if(command == 'L'){
            move(2,x,y);
        }else if(command == 'R'){
            move(3,x,y);
        }else{      //S 포 쐇을 경우
            if(map[x][y] == '^'){   //위로 쐈을 경우
                x = x + dx[0];
                y = y + dy[0];
                while (x>=0 && x<h && y>=0 && y<w){       //맵 안에 있고 평지거나 물일때 계속 진행
                    if( map[x][y] == '.' || map[x][y] =='-'){
                        x = x + dx[0];
                        y = y + dy[0];              //움직여
                        continue;
                    } else if(map[x][y] == '*'){        //벽돌로 이루어진 곳이면..?
                        map[x][y] = '.';                //평지로 바꾸고 그만
                        break;
                    } else if(map[x][y] == '#'){
                        break;
                    }
                }

            } else if(map[x][y] == 'v'){
                x = x + dx[1];
                y = y + dy[1];
                while (x>=0 && x<h && y>=0 && y<w){       //맵 안에 있고 평지거나 물일때 계속 진행
                    if( map[x][y] == '.' || map[x][y] =='-'){
                        x = x + dx[1];
                        y = y + dy[1];              //움직여
                        continue;
                    } else if(map[x][y] == '*'){        //벽돌로 이루어진 곳이면..?
                        map[x][y] = '.';                //평지로 바꾸고 그만
                        break;
                    } else if(map[x][y] == '#'){
                        break;
                    }
                }
            }else if(map[x][y] == '<'){
                x = x + dx[2];
                y = y + dy[2];
                while (x>=0 && x<h && y>=0 && y<w){       //맵 안에 있고 평지거나 물일때 계속 진행
                    if( map[x][y] == '.' || map[x][y] =='-'){
                        x = x + dx[2];
                        y = y + dy[2];              //움직여
                        continue;
                    } else if(map[x][y] == '*'){        //벽돌로 이루어진 곳이면..?
                        map[x][y] = '.';                //평지로 바꾸고 그만
                        break;
                    } else if(map[x][y] == '#'){
                        break;
                    }
                }
            }else{              //오른쪽으로 쐈을 경우
                x = x + dx[3];
                y = y + dy[3];
                while (x>=0 && x<h && y>=0 && y<w){       //맵 안에 있고 평지거나 물일때 계속 진행
                    if( map[x][y] == '.' || map[x][y] =='-'){
                        x = x + dx[3];
                        y = y + dy[3];              //움직여
                        continue;
                    } else if(map[x][y] == '*'){        //벽돌로 이루어진 곳이면..?
                        map[x][y] = '.';                //평지로 바꾸고 그만
                        break;
                    } else if(map[x][y] == '#'){
                        break;
                    }
                }
            }
        }
    }

    static void move(int dir ,int x, int y){        //x,y는 현재 전차의 위치
        int nx = x + dx[dir];
        int ny = y + dy[dir];               //범위 안이고 , 평지인지 체크
        switch (dir){
            case 0:
                map[x][y] = '^';                    //위로보게 바꿈
                if(!check(dir,x,y)) break;          //범위 밖이면
                if(map[nx][ny] == '.'){             //평지이면
                    map[nx][ny] = '^';              //위로 가고
                    startX = nx;
                    startY = ny;                    //현재 전차 위치 갱신
                    map[x][y] = '.';                //원래 위치 평지로 바꿈
                    break;
                }
                break;                              //평지 아니면 못가므로 그냥 멈춤
            case 1:
                map[x][y] = 'v';                    //아래로보게 바꿈
                if(!check(dir,x,y)) break;          //범위 밖이면
                if(map[nx][ny] == '.'){             //평지이면
                    map[nx][ny] = 'v';              //아래로보게 가고
                    startX = nx;
                    startY = ny;                    //현재 전차 위치 갱신
                    map[x][y] = '.';                //원래 위치 평지로 바꿈
                    break;
                }
                break;                              //평지 아니면 못가므로 그냥 멈춤

            case 2:
                map[x][y] = '<';                    //왼쪽으로보게 바꿈
                if(!check(dir,x,y)) break;          //범위 밖이면
                if(map[nx][ny] == '.'){             //평지이면
                    map[nx][ny] = '<';              //왼쪽으로보게 가고
                    startX = nx;
                    startY = ny;                    //현재 전차 위치 갱신
                    map[x][y] = '.';                //원래 위치 평지로 바꿈
                    break;
                }
                break;                              //평지 아니면 못가므로 그냥 멈춤

            case 3:
                map[x][y] = '>';                    //오른쪽으로보게 바꿈
                if(!check(dir,x,y)) break;          //범위 밖이면
                if(map[nx][ny] == '.'){             //평지이면
                    map[nx][ny] = '>';              //른쪽으로보게 가고
                    startX = nx;
                    startY = ny;                    //현재 전차 위치 갱신
                    map[x][y] = '.';                //원래 위치 평지로 바꿈
                    break;
                }
                break;                              //평지 아니면 못가므로 그냥 멈춤

        }


    }

    static boolean check(int dir ,int x, int y){
        int nx = x +dx[dir];
        int ny = y +dy[dir];
        if(nx>=0 && nx <h && ny>=0 && ny<w) {        //가능한 범위 안이면
            return true;
        }
        return false;
    }
}
