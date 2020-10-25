package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Expression
{

    static ArrayList<Character> simpleFunctions = new ArrayList<>(
            Arrays.asList( '/', '*', '+', '-')
    );

    static double Calculate(String expr) throws Exception
    {
        Stack<Double> Operands = new Stack<>();
        Stack<Character> Functions = new Stack<>();
        int pos = 0;
        char Token = ' ';
        char PrevToken = 'j';

        ArrayList<Character> expression = new ArrayList<>();

        for (int i = 0; i < expr.length(); i++) {
            expression.add(expr.charAt(i));
        }

        expression.add(0, '(');
        expression.add(expression.size(), ')');

        //pos = 1;
        //PrevToken =  expression.get(0);

        do
        {
            Token = expression.get(pos);

            if (PrevToken =='(' && Token == '-') {
                Operands.push(0.0);
            }


            if (Character.isDigit(Token))
            {
                Operands.push((double)Token);
            }

            if (simpleFunctions.contains(Token) || Token == '(' || Token == ')' )
            {
                if ((char)Token == ')')
                {
                    while (Functions.size() > 0 && Functions.peek() != '(')
                        PopFunction(Operands, Functions);
                    Functions.pop(); // Удаляем саму скобку "("
                }
                else
                {
                    while (CanPop((char)Token, Functions)) // Если можно вытолкнуть
                        PopFunction(Operands, Functions); // то выталкиваем
                    Functions.push((char)Token); // Кидаем новую операцию в стек
                }
            }
            PrevToken = Token;
            pos++;

        } while (pos + 1 <= expression.size());

        System.out.println(Operands.size());
        System.out.println(Functions.size());

        /*double B = Operands.pop();
        double A = Operands.pop();
        switch (Functions.pop()) {
            case '+' -> Operands.push(A + B);
            case '-' -> Operands.push(A - B);
            case '*' -> Operands.push(A * B);
            case '/' -> Operands.push(A / B);
        }*/

        /*System.out.println(Operands.pop());
        System.out.println(Operands.pop());
        System.out.println(Functions.pop());*/


        if (Operands.size() > 1 || Functions.size() > 0)
            throw new Exception("Ошибка в разборе выражения");

        return Operands.pop();


    }


    static void PopFunction(Stack<Double> Operands, Stack<Character> Functions)
    {
        double B = Operands.pop();
        double A = Operands.pop();
        switch (Functions.pop())
        {
            case '+': Operands.push(A + B);
                break;
            case '-': Operands.push(A - B);
                break;
            case '*': Operands.push(A * B);
                break;
            case '/': Operands.push(A / B);
                break;
        }
    }

    static int GetPriority(char op) throws Exception {
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


    static boolean CanPop(char op1, Stack<Character> Functions) throws Exception {
        if (Functions.empty())
            return false;
        int p1 = GetPriority(op1);
        int p2 = GetPriority(Functions.peek());

        return p1 >= 0 && p2 >= 0 && p1 >= p2;
    }




}
