package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2608_로마숫자 {

    static int res = 0;
    static String resStr= "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
       // System.out.println(StringToInt(str1));
        String str2=  br.readLine();
        //System.out.println(StringToInt(str2));
        int sum = StringToInt(str1) + StringToInt(str2);
        System.out.println(sum);
        System.out.println(IntToString(sum));

    }

    static int StringToInt(String str){
        int strlen = str.length();
        int result = 0;
        for(int i = 0 ; i<strlen; i++){
            char now = str.charAt(i);
            if(now == 'I'){
                if(i+1 <strlen){    //범위 안이면
                    if(str.charAt(i+1) == 'V'){
                        result += 4;
                        i++;
                    } else if(str.charAt(i+1) == 'X'){
                        result += 9;
                        i++;
                    } else{
                        result +=1;
                    }
                } else{
                    result+=1;
                }
            } else if(now == 'V'){
                result+=5;
            }else if(now == 'X'){
                if(i+1 <strlen){    //범위 안이면
                    if(str.charAt(i+1) == 'L'){
                        result += 40;
                        i++;
                    } else if(str.charAt(i+1) == 'C'){
                        result += 90;
                        i++;
                    } else{
                        result +=10;
                    }
                } else{
                    result+=10;
                }
            } else if(now == 'L'){
                result+=50;
            } else if(now == 'C'){
                if(i+1 <strlen){    //범위 안이면
                    if(str.charAt(i+1) == 'D'){
                        result += 400;
                        i++;
                    } else if(str.charAt(i+1) == 'M'){
                        result += 900;
                        i++;
                    } else{
                        result +=100;
                    }
                } else{
                    result+=100;
                }
            }else if(now == 'D'){
                result+=500;
            } else if(now == 'M'){
                result += 1000;
            }
        }

        return result;

    }

    static String IntToString(int num){
        String res = "";
        int mok = 0;
        for(int i = 1000 ; i>0 ; i = i/10){     //i가 나누는 수
            if(i == 100){
                if(num >= 500){
                    if(num>=900){
                        num-= 900;
                        res +="CM";
                    }else {
                        num -= 500;
                        res += "D";
                    }
                } else if(num >= 400){   //400대 일때
                    num -= 400;
                    res += "CD";
                }
            } else if( i == 10){
                if(num >= 50){
                    if(num>=90){
                        num-= 90;
                        res +="XC";
                    }else {
                        num -= 50;
                        res += "L";
                    }
                } else if(num >= 40){   //400대 일때
                    num -= 40;
                    res += "XL";
                }
            } else if( i == 1){
                if(num >= 5){
                    if(num>=9){
                        num-= 9;
                        res +="IX";
                    }else {
                        num -= 5;
                        res += "V";
                    }
                } else if(num >= 4){   //400대 일때
                    num -= 4;
                    res += "IV";
                }
            }
            int cnt = 0;
            int mod = 0;
            cnt = num/i;
            mod = num%i;
            if(i == 1000){
                for(int j = 0; j<cnt; j++){
                    res +="M";
                }
            } else if(i == 100){
                for(int j = 0; j<cnt; j++){
                    res +="C";
                }
            } else if( i == 10){
                for(int j = 0; j<cnt; j++){
                    res +="X";
                }
            } else if( i == 1){
                for(int j = 0; j<cnt; j++){
                    res +="I";
                }
            }
            num = mod;  //남은거
        }
        return res;
    }
}
