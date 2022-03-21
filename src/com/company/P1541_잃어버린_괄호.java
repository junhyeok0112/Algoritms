package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1541_잃어버린_괄호 {
    static String plus = "";
    static String minus = "";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int ans = 0;
        String str =  br.readLine();
        int chk = -1;            //부호 전까지 만나는 숫자
        for(int i = 0 ; i<str.length();i++){
            // - 만나면 그 뒤에 모든 부호 - 로 바꿔서 계산
            if(str.charAt(i) == '-') {
                chk = i;
                break;
            }
        }
        if(chk == -1 ){              //+밖에 없을경우
            plus = str;
            ans+=calPlus(plus);
            System.out.println(ans);
            return;
        } else {        //-로 시작하면 chk = 0 이되어서 더할거 없음 -> -로 시작할 수없음
            plus = str.substring(0, chk);
        }
        ans +=calPlus(plus);

        minus = str.substring(chk+1 , str.length());
        //System.out.println(minus);
        minus = minus.replace("+" , "-");           //-뒤에는 모두 -이므로 바꿔줌
        //System.out.println(minus);
        ans -= calMinus(minus);
        System.out.println(ans);
    }

    static int calPlus(String str){
        int result = 0;
        if(str.length() == 0) return result;    //비어있으면 0
        String[] numbers = str.split("\\+");
        for(int i = 0 ; i<numbers.length;i++){
            result += Integer.parseInt(numbers[i]);
        }
        return result;
    }

    static int calMinus(String str){
        int result = 0;
        if(str.length() == 0) return result;    //비어있으면 0
        String[] numbers = str.split("-");
        for(int i = 0 ; i<numbers.length;i++){
            result += Integer.parseInt(numbers[i]);
        }
        return result;
    }

}
