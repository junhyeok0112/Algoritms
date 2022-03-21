//package com.company;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//class Node{
//    char val;
//    Node leftchild;
//    Node rightchild;
//    Node(char val){
//        this.val = val;
//    }
//}
//
//class Tree{
//    Node root;
//    ArrayList<Node> arr ;
//
//    Tree(char val){
//       this.root = new Node(val);
//        arr = new ArrayList<>();
//        arr.add(root);
//    }
//
//
//    void addLeft(char pVal , char leftVal){
//        Node left = new Node(leftVal);
//        for(Node cur : arr){
//            if(cur.val == pVal){
//                cur.leftchild = left;
//                arr.add(left);
//                return;
//            }
//        }
//    }
//
//
//    void addRight(char pVal ,  char rightVal ){
//        Node right = new Node(rightVal);
//        for(Node cur : arr){
//            if(cur.val == pVal){
//                cur.rightchild = right;
//                arr.add(right);
//                return;
//            }
//        }
//    }
//    void preOrder(Node start){
//        if(start != null) {
//            System.out.print(start.val);
//            preOrder(start.leftchild);
//            preOrder(start.rightchild);
//        }
//    }
//
//    void postOrder(Node start){
//        if(start != null) {
//            postOrder(start.leftchild);
//            postOrder(start.rightchild);
//            System.out.print(start.val);
//        }
//
//    }
//
//    void inOrder(Node start){
//        if(start != null) {
//            inOrder(start.leftchild);
//            System.out.print(start.val);
//            inOrder(start.rightchild);
//        }
//    }
//}
//
//
//public class P1991_트리순회 {
//
//    static int N;
//
//
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        Tree tr = new Tree('A');    //트리 생성
//        for(int i = 0 ; i<N; i++){
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            char root = st.nextToken().charAt(0);
//            char left = st.nextToken().charAt(0);
//            char right = st.nextToken().charAt(0);
//            if(left != '.') tr.addLeft(root, left);
//            if(right != '.') tr.addRight(root , right);
//        }
//
//        tr.preOrder(tr.root);
//        System.out.println();
//        tr.inOrder(tr.root);
//        System.out.println();
//        tr.postOrder(tr.root);
//
//
//    }
//
//
//}
