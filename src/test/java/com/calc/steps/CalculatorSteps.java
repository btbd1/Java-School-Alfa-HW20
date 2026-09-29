package com.calc.steps;

import com.calc.Calculator;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorSteps {

  private final Calculator calculator = new Calculator();

  @Step("Сложить {a} + {b}")
  public double add(double a, double b) {
    double result = calculator.add(a, b);
    attachCalculation(a, "+", b, result);
    return result;
  }

  @Step("Вычесть {a} - {b}")
  public double subtract(double a, double b) {
    double result = calculator.subtract(a, b);
    attachCalculation(a, "-", b, result);
    return result;
  }

  @Step("Умножить {a} × {b}")
  public double multiply(double a, double b) {
    double result = calculator.multiply(a, b);
    attachCalculation(a, "*", b, result);
    return result;
  }

  @Step("Разделить {a} / {b}")
  public double divide(double a, double b) {
    double result = calculator.divide(a, b);
    attachCalculation(a, "/", b, result);
    return result;
  }

  @Step("Возвести {base} в степень {exponent}")
  public double power(double base, double exponent) {
    double result = calculator.power(base, exponent);
    Allure.addAttachment(
        "Вычисление степени",
        "text/plain",
        base + " ^ " + exponent + " = " + result,
        ".txt"
    );
    return result;
  }

  @Step("Извлечь квадратный корень из {value}")
  public double sqrt(double value) {
    double result = calculator.sqrt(value);
    Allure.addAttachment(
        "Вычисление корня",
        "text/plain",
        "sqrt(" + value + ") = " + result,
        ".txt"
    );
    return result;
  }

  @Step("Проверить, что результат {actual} равен ожидаемому {expected}")
  public void verifyResult(double actual, double expected) {
    assertEquals(expected, actual, 1e-9,
        "Результат не совпал с ожидаемым");
  }

  @Step("Проверить, что при делении на ноль выбрасывается ArithmeticException")
  public void verifyDivideByZeroThrows(double a, double b) {
    try {
      calculator.divide(a, b);
      throw new AssertionError("Ожидалось ArithmeticException, но его не было");
    } catch (ArithmeticException ex) {
      Allure.addAttachment(
          "Перехваченное исключение",
          "text/plain",
          ex.getClass().getName() + ": " + ex.getMessage(),
          ".txt"
      );
    }
  }

  @Step("Проверить, что sqrt({value}) выбрасывает ArithmeticException")
  public void verifySqrtNegativeThrows(double value) {
    try {
      calculator.sqrt(value);
      throw new AssertionError("Ожидалось ArithmeticException для sqrt(" + value + ")");
    } catch (ArithmeticException ex) {
      Allure.addAttachment(
          "Перехваченное исключение",
          "text/plain",
          ex.getClass().getName() + ": " + ex.getMessage(),
          ".txt"
      );
    }
  }

  private void attachCalculation(double a, String op, double b, double result) {
    Allure.addAttachment(
        "Вычисление",
        "text/plain",
        a + " " + op + " " + b + " = " + result,
        ".txt"
    );
  }
}
