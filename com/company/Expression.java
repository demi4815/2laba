package com.company;

import java.util.ArrayList;
import java.util.Stack;

public class Expression
{
    String Str;

    Expression(String Str)
    {
        this.Str = '(' + Str + ')';
    }

    Expression()
    {
        Str = null;
    }

    Stack<Double> Operands = new Stack<>();
    Stack<Character> Functions = new Stack<>();
    int pos = 0;
    Object Token = new Object();
    Object PrevToken = new Object();


    void PopFunction(Stack<Double> Operands, Stack<Character> Functions)
    {
        double B = Operands.pop();
        double A = Operands.pop();
        switch (Functions.pop()) {
            case '+' -> Operands.push(A + B);
            case '-' -> Operands.push(A - B);
            case '*' -> Operands.push(A * B);
            case '/' -> Operands.push(A / B);
        }
    }

    int GetPriority(char op) throws Exception {
        switch (op)
        {
            case '(':
                return -1; // не выталкивает сам и не дает вытолкнуть себя другим
            case '*': case '/':
            return 1;
            case '+': case '-':
            return 2;
            default:
                throw new Exception("Invalid operation");
        }
    }


    boolean CanPop(char op1, Stack<Character> Functions) throws Exception {
        if (Functions.empty())
            return false;
        int p1 = GetPriority(op1);
        int p2 = GetPriority(Functions.peek());

        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }




}
