package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2941_크로아티아알파베 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        st = st.replace("c=","1");
        st = st.replace("c-","2");
        st = st.replace("dz=","3");
        st = st.replace("d-","4");
        st = st.replace("lj","5");
        st = st.replace("nj","6");
        st = st.replace("s=","7");
        st = st.replace("z=","8");
        System.out.println(st.length());

    }
}
