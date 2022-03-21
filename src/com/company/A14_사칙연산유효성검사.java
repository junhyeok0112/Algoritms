package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class A14_사칙연산유효성검사 {

    static int TC = 10;
    static String [] tree;
    static int size;

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        for(int tc = 1; tc <= TC; tc++) {
            size = Integer.parseInt(br.readLine());
            tree = new String [size+1];
            //tree 구조로 만들기
            for(int i = 1; i <= size; i++) {
                String [] tmp = br.readLine().split(" ");
                tree[i] = tmp[1];
            }

            //후위연산으로 확인하기
            if(check(1)) sb.append("#"+tc+" 1").append("\n");
            else sb.append("#"+tc+" 0").append("\n");
        }
        System.out.println(sb+"\n");
    }

    private static boolean check(int cur) {
        if(cur*2 > size) {//리프 노드이면
            String node = tree[cur];
            if(node.equals("+") || node.equals("-") || node.equals("*") || node.equals("/")) return false;
            return true;
        }

        boolean a = (cur*2<=size)&&check(cur*2);
        boolean b = (cur*2+1<=size)&&check(cur*2+1);
        boolean c = false;
        if(tree[cur].equals("+") || tree[cur].equals("-") || tree[cur].equals("*") || tree[cur].equals("/")) {
            c = true;
        }
        return a&&b&&c;
    }
}