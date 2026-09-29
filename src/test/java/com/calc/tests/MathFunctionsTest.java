package com.calc.tests;

import com.calc.steps.CalculatorSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Калькулятор")
@Feature("Математические функции")
@DisplayName("Математические функции: pow, sqrt")
public class MathFunctionsTest {

  private final CalculatorSteps steps = new CalculatorSteps();

  @Test
  @Story("Возведение в степень")
  @Severity(SeverityLevel.NORMAL)
  @Owner("Anna Pritulya")
  @Description("Проверяем возведение 3 в 5-ю степень — ожидаем 243")
  @DisplayName("3 ^ 5 = 243")
  @Link(name = "Возведение в степень - школьная программа", url = "https://onlineschool-1.ru/encyclopedia/7-klass/algebra/vozvedenie-v-stepen-proizvede3/article/")
  void testPower() {
    double result = steps.power(3, 5);
    steps.verifyResult(result, 243);
  }

  @Test
  @Story("Квадратный корень")
  @Severity(SeverityLevel.CRITICAL)
  @Owner("Anna Pritulya")
  @Description("Проверяем, что sqrt от отрицательного числа выбрасывает ArithmeticException")
  @DisplayName("sqrt(-1) → ArithmeticException")
  @Issue("BUG-3")
  void testSqrtNegative() {
    steps.verifySqrtNegativeThrows(-1);
  }

  @Test
  @Story("Квадратный корень")
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("sqrt(16) = 4")
  void testSqrtPositive() {
    double result = steps.sqrt(16);
    steps.verifyResult(result, 4);
  }
}