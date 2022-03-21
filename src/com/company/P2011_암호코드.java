package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class P2011_암호코드 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] dp = new int[str.length() + 1] ;
        int cnt = 0;
        int ans = 1;
        if(str.charAt(0) == '0'){   //첫번째꺼 봤을 때
            System.out.println(0);
            return;
        }else{
            dp[1] = 1;  //첫자리가 0이 아니면 무조건 1
            dp[0] = 1;  //만약 두번째 자리에서 2개의 숫자로도 만들 수 있을 경우를 대비해 i-2 = 0 일때 값을 1개로 가져갈수 있게하기위해
        }
       // System.out.println(str.charAt(str.length()-1) + " " + str.charAt(str.length()-2));
        if(str.charAt(str.length()-1) == '0' && str.charAt(str.length()-2 ) != '1' && str.charAt(str.length()-2 ) != '2'){
            //마지막이 0이고 그 앞이 1이나 2가 아니면 30 ~알파벳 범위 벗어나므로 불가능
            System.out.println(0);
            return;
        }
        for(int i = 2; i<=str.length();i++) {   //0이 2개 붙어나오는 경우는 알아서 걸러짐
            int tempNum = str.charAt(i-1) - '0';
            if(tempNum>=1) {    //한자리수로 가능하면 dp[i-1]의 가지수와 동일하다
                dp[i] = dp[i-1] % 1000000;
            }
            tempNum = Integer.parseInt(str.substring(i-2,i));
            if(tempNum>=10 && tempNum <=26){    //만약 2개의 숫자를 봤을 때 알파벳 범위 안으로 가능하면 2개의 숫자를 하나의 알파벳으로 보아서 dp[i-2]의 가지수와 동일하다
                //따라서 위에서 구한 한자리수로 가능할때와 두자리수로 가능할때 모두 값을 더하면 dp[i]의 값을 알 수 있다.
                dp[i] =(dp[i] + dp[i-2]) % 1000000;
            }

        }
        System.out.println(dp[str.length()]);



    }
}
