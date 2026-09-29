package com.calc.tests;


import com.calc.steps.CalculatorSteps;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Калькулятор")
@Feature("Арифметические операции")
@DisplayName("Арифметика: +, -, *, /")
public class ArithmeticTest {

  private final CalculatorSteps steps = new CalculatorSteps();

  @Test
  @Story("Сложение")
  @Severity(SeverityLevel.NORMAL)
  @Owner("Anna Pritulya")
  @Description("Проверяем базовое сложение двух положительных чисел")
  @DisplayName("2 + 3 = 5")
  void testAddPositiveNumbers() {
    double result = steps.add(2, 3);
    steps.verifyResult(result, 5);
  }

  @Test
  @Story("Сложение")
  @Severity(SeverityLevel.MINOR)
  @Owner("Anna Pritulya")
  @Description("Проверяем сложение положительного и отрицательного числа")
  @DisplayName("-5 + 3 = -2")
  void testAddWithNegativeNumber() {
    double result = steps.add(-5, 3);
    steps.verifyResult(result, -2);
  }

  @Test
  @Story("Вычитание")
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("10 - 4 = 6")
  void testSubtract() {
    double result = steps.subtract(10, 4);
    steps.verifyResult(result, 6);
  }

  @Test
  @Story("Умножение")
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("7 × 8 = 56")
  void testMultiply() {
    double result = steps.multiply(7, 8);
    steps.verifyResult(result, 56);
  }

  @Test
  @Story("Деление")
  @Severity(SeverityLevel.NORMAL)
  @DisplayName("15 / 3 = 5")
  void testDivide() {
    // Динамический шаг Allure.step()
    Allure.step("Подготовка данных: a=15, b=3", () -> {
      Allure.parameter("a", 15);
      Allure.parameter("b", 3);
    });

    double result = steps.divide(15, 3);
    steps.verifyResult(result, 5);
  }

  @Test
  @Story("Деление")
  @Severity(SeverityLevel.CRITICAL)
  @Owner("Anna Pritulya")
  @Description("Проверяем, что при делении на ноль выбрасывается ArithmeticException")
  @DisplayName("Деление на ноль → ArithmeticException")
  @Link(name = "BUG-1", url = "https://example.com/issue/BUG-1")
  @Issue("BUG-1")
  void testDivideByZero() {
    Allure.step("Вызываем деление 10 / 0 и ожидаем исключение", () -> {
      steps.verifyDivideByZeroThrows(10, 0);
    });
  }

  /**
   * Намеренно падающий тест, чтобы менеджер увидел, как выглядит FAIL в Allure.
   */
  @Test
  @Story("Сложение")
  @Severity(SeverityLevel.TRIVIAL)
  @DisplayName("Намеренно падающий: 2 + 2 ожидаем 5 (демо FAIL)")
  @Description("Демонстрационный FAIL: специально проверяем 2+2=5, " +
      "чтобы показать, как выглядит упавший тест в Allure")
  void testIntentionallyFailing() {
    double result = steps.add(2, 2);
    steps.verifyResult(result, 5);
  }
}
