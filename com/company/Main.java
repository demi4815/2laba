package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println(Expression.Calculate("2+2-1"));
        System.out.println(Expression.Calculate("2+2*1"));
        System.out.println(Expression.Calculate("2+(2*1+1)"));
        System.out.println(Expression.Calculate("2+(2*1+(1-6/3))"));
    }
}
