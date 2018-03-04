package com.company;

public class Fraction {

    private int numerator;
    private int denominator;
    private int numToLowest;
    private int denToLowest;

    public Fraction() {
        this.numerator = 0;
        this.denominator = 1;
        this.numToLowest = numerator;
        this.denToLowest = denominator;
        toLowestTerms();
    }

    public Fraction(int numerator) {
        this.numerator = numerator;
        this.denominator = 1;
        this.numToLowest = numerator;
        this.denToLowest = denominator;
        toLowestTerms();
    }

    public Fraction(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException();
        }
        this.numerator = numerator;
        this.denominator = denominator;
        if ( (this.numerator < 0 && this.denominator < 0) || (this.numerator > 0 && this.denominator < 0)) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
        this.numToLowest = numerator;
        this.denToLowest = denominator;
        toLowestTerms();
    }

    public int getNumerator() {
        return this.numerator;
    }

    public int getDenominator() {
        return this.denominator;
    }

    public int getNumToLowest() {
        return this.numToLowest;
    }

    public int getDenToLowest() {
        return this.denToLowest;
    }

    public String toString() {
        StringBuilder str = new StringBuilder(Integer.toString(this.numerator));
        if (this.denominator > 1 && this.numerator != 0) {
            str.append("/" + Integer.toString(this.denominator));
        }
        return str.toString();
    }

    public double toDouble() {
        return (numerator)/(double)(denominator);
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b; // % is remainder
            a = temp;
        }
        return a;
    }

    private int lcm(int a, int b) {
        return a * (b / Fraction.gcd(a,b));
    }

    public Fraction add(Fraction other) {
        int newDenominator = lcm(this.denominator,other.getDenominator());
        int newNumerator = this.numerator*(newDenominator/this.denominator) + other.getNumerator()*(newDenominator/other.getDenominator());
        return new Fraction(newNumerator,newDenominator);
    }

    public Fraction subtract(Fraction other) {
        int newDenominator = lcm(this.denominator,other.getDenominator());
        int newNumerator = this.numerator*(newDenominator/this.denominator) - other.getNumerator()*(newDenominator/other.getDenominator());
        return new Fraction(newNumerator,newDenominator);
    }

    public Fraction multiply(Fraction other) {
        Fraction f1 = new Fraction(this.numerator*other.getNumerator(),this.denominator*other.getDenominator());
        f1.toLowestTerms();
        return f1;
    }

    public Fraction divide(Fraction other) {
        Fraction f1 = new Fraction(this.numerator * other.getDenominator(), this.denominator * other.getNumerator());
        f1.toLowestTerms();
        return f1;
    }

    public boolean equals(Object obj) {
        Fraction f = Fraction.class.cast(obj);
        toLowestTerms();
        f.toLowestTerms();
        return ((this.numToLowest == f.getNumToLowest()) && (this.denToLowest == f.getDenToLowest()));
    }

    public void toLowestTerms() {
        int gcd = Fraction.gcd(this.numToLowest, this.denToLowest);
        this.numToLowest /= gcd;
        this.denToLowest /= gcd;
        if (this.numToLowest == this.denToLowest) {
            this.numToLowest = this.denToLowest = 1;
        }
    }
}
