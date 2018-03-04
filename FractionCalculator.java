package com.company;

import java.util.Scanner;

public class FractionCalculator {

    private FractionCalculator() {}

    private boolean isValidOperation(char c) {
        if (c == 'q' || c == 'Q') {return true;}
        if (c == '+' || c == '-') {return true;}
        if (c == '/' || c == '*') {return true;}
        if (c == '=') {return true;}
        return false;
    }

    private String getOperation(Scanner input) {
        System.out.print("Please enter an operation (+,-,*,/,= or Q to quit): ");
        String st = input.nextLine();
        st.replaceAll("\\s","");
        char c = st.charAt(0);
        boolean isValid = isValidOperation(c) && (st.length() == 1);
        while (!isValid) {
            System.out.print("Invalid input (+,-,*,/,= or Q to quit): ");
            st = input.nextLine();
            st.replaceAll("\\s","");
            c = st.charAt(0);
            isValid = isValidOperation(c) && (st.length() == 1);
        }
        return Character.toString(c);
    }

    private boolean validFraction(String input) {
        String[] numbers = input.split("/");
        int a = 0, b = 1;
        if (numbers.length > 2 ) {
            return false;
        }
        if (numbers.length == 1) {
            try {
                a = Integer.parseInt(numbers[0]);
            }
            catch(NumberFormatException e) {
                return false;
            }
        }
        else {
            try {
                a = Integer.parseInt(numbers[0]);
                b = Integer.parseInt(numbers[1]);
                if (b <= 0)
                    return false;
            }
            catch(NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private Fraction getFraction(Scanner input) {
        System.out.print("Please enter a fraction (a/b) or integer (a): ");
        String st = input.nextLine();
        st.replaceAll("\\s","");
        String[] numbers = st.split("/");
        int numerator = 0, denominator = 1;
        boolean isValidFraction = validFraction(st);

        while (!isValidFraction) {
            System.out.print("Invalid fraction. Please enter (a/b) or (a), where a and b are integers and b is not zero: ");
            st = input.nextLine();
            st.replaceAll("\\s","");
            numbers = st.split("/");
            isValidFraction = validFraction(st);
        }

        numerator = Integer.parseInt(numbers[0]);
        if (numbers.length == 2) {
            denominator = Integer.parseInt(numbers[1]);
        }
        return new Fraction(numerator,denominator);
    }

    public static void main(String[] args) {
        FractionCalculator fc = new FractionCalculator();
        Scanner input = new Scanner(System.in);

        System.out.println("This program is a fraction calculator");
        System.out.println("It will add, subtract, multiply and divide fractions until you type Q to quit");
        System.out.println("Please enter your fractions in the form a/b, where a and b are integers");
        System.out.println("------------------------------------------------------------------------------");

        String operator = fc.getOperation(input);

        while (operator.charAt(0) != 'Q' && operator.charAt(0) != 'q') {
            Fraction f1 = fc.getFraction(input);
            Fraction f2 = fc.getFraction(input);
            Fraction f;
            char c = operator.charAt(0);

            switch (c) {
                case '+':
                    f = f1.add(f2);
                    System.out.println(f1.toString() + " + " + f2.toString() + " = " + f.toString());
                    break;

                case '-':
                    f = f1.subtract(f2);
                    System.out.println(f1.toString() + " - " + f2.toString() + " = " + f.toString());
                    break;

                case '*':
                    f = f1.multiply(f2);
                    if (f.getDenToLowest() > 1) {
                        System.out.println(f1.toString() + " * " + f2.toString() + " = " + f.toString());
                    }
                    else {
                        System.out.println(f1.toString() + " * " + f2.toString() + " = " + f.getNumToLowest());
                    }
                    break;

                case '/':
                    try {
                        f = f1.divide(f2);
                        if (f.getDenToLowest() > 1) {
                            System.out.println(f1.toString() + " / " + f2.toString() + " = " + f.toString());
                        }
                        else {
                            System.out.println(f1.toString() + " / " + f2.toString() + " = " + f.getNumToLowest());
                        }
                    }
                    catch(IllegalArgumentException i) {
                        System.out.println(f1.toString() + " / " + f2.toString() + " = Undefined");
                    }
                    break;

                case '=':
                    boolean res = f1.equals(f2);
                    if (res) {
                        System.out.println(f1.toString() + " = " + f2.toString() + " is true");
                    }
                    else {
                        System.out.println(f1.toString() + " = " + f2.toString() + " is false");
                    }
                    break;

                default:
                    break;
            }
            operator = fc.getOperation(input);
        }
    }
}