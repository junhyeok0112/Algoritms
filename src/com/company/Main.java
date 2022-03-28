//package com.company;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentHashMap;
//
//
//
//public class Main {
//    static String[] sheet = {"team_name" , ":" ,"","application_name" ,":","","error_level" ,":" ,"","message" ,":" ,""};
//    static String str=  "team_name : recommend application_name : recommend error_level : info message : RecommendSucces11";
//    public int main(String[] args) {
//        int answer = 0;
//        //하나의 String[i]에 한셋트의 로그가 들어옴. 즉 로그마다 보면됨
//        String[] logs = new String[1];
//        logs[0] = str;
//        int len = logs.length;
//        for(int i = 0 ; i<len; i++){
//            if(logs[i].length() >100){ //길이 100 넘어갈 경우
//                answer++;
//                continue;
//            }
//            //앞 뒤에 공백이 있을경우
//            if(logs[i].charAt(0) ==' ' || logs[i].charAt(logs[i].length()-1) == ' '){
//                answer++;
//                continue;
//            }
//            //공백이 연속으로 있을 경우
//            boolean chk = false;
//            for(int t = 0 ;t <logs[i].length()-1; t++){
//                if(logs[i].charAt(t) == ' ' && logs[i].charAt(t+1) == ' '){ //공백연속이면 체크
//                    answer++;
//                    chk = true;
//                    break;
//                }
//            }
//            if(chk) continue;   //공백 체크하고 나왔으면 넘김
//
//            String[] temp = logs[i].split(" ");
//            if(temp.length != 12){
//                answer++;
//                continue;
//            }
//            //제대로된 형식인지 확인
//
//            for(int t = 0 ; t<temp.length;t++){
//                System.out.print(temp[t] + " ");
//            }
//            boolean chk2 = false;
//            for(int j=  0 ; j <12 ;j++){
//                System.out.println(i);
//                if(j == 2 ||j ==5 || j==8 || j==11){    //에러메세지들
//                    //알파벳소문자 ,대문자로만
//                    System.out.println(j);
//                    for(int t=0; t<temp[j].length(); t++) {
//                        if (String.valueOf(temp[j].charAt(t)).matches("[^a-zA-Z0-9\\s]")) { //특수문자 인 경우
//                            answer++;
//                            chk2= true;
//                            System.out.println(i+"특");
//                            break;
//                        }
//                        else { //특수문자가 아닌 경우
//                            if(Character.isDigit(temp[j].charAt(t))) { //숫자인 경우
//                                answer++;
//                                chk2 = true;
//                                System.out.println(i + " 숫");
//                                break;
//                            }
//                        }
//                    }
//                }else{
//                    if(!temp[i].equals(sheet[i])){
//                        answer++;
//                        break;
//                    }
//                }
//                if(chk2) break;
//            }
//        }
//        return answer;
//    }
//}
//
//
