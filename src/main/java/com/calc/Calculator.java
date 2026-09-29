package com.calc;

public class Calculator {

  public double add(double a, double b) { return a + b; }

  public double subtract(double a, double b) { return a - b; }

  public double multiply(double a, double b) { return a * b; }

  public double divide(double a, double b) {
    if (b == 0) throw new ArithmeticException("Деление на ноль");
    return a / b;
  }

  public double power(double base, double exponent) {
    return Math.pow(base, exponent);
  }

  public double sqrt(double value) {
    if (value < 0) throw new ArithmeticException("Корень из отрицательного числа");
    return Math.sqrt(value);
  }
}
