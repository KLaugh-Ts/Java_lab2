import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    void calculation() {
        assertEquals(calculator.calculation(2,4,"+"),6);
        assertEquals(calculator.calculation(2,6,"-"),4);
        assertEquals(calculator.calculation(2,4,"*"),8);
        assertEquals(calculator.calculation(2,4,"/"),2);
        assertEquals(calculator.calculation(0,4,"/"),-1);
        assertEquals(calculator.calculation(2,4,"^"),16);
    }

    @Test
    void isOperator() {
        assertEquals(calculator.isOperator("/"),true);
        assertEquals(calculator.isOperator("7"),false);
    }

    @Test
    void operatorPriority() {
        assertEquals(calculator.operatorPriority("+"), 1);
        assertEquals(calculator.operatorPriority("*"), 2);
        assertEquals(calculator.operatorPriority("^"), 3);
    }

    @Test
    void infixToPostfix() {
        String infix = "4+3*6/9-(1+1)^2";
        String expected = "4 3 6 * 9 / + 1 1 + 2 ^ - ";
        String actual = calculator.infixToPostfix(infix);
        assertEquals(expected, actual);
    }

    @Test
    void postfixCalculation() {
        String postfix = "4 3 6 * 9 / + 1 1 + 2 ^ - ";
        double expected = 2;
        calculator.postfixCalculation(postfix);
        double actual = calculator.getResult();
        assertEquals(expected, actual);
    }
}