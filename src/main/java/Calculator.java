import java.util.Stack;
import java.util.StringTokenizer;
import java.util.InputMismatchException;

public class Calculator {
    /**
     * @author KlaughTs, Kretova Ksenia, AMM, 3rd year, 3rd group
     */

    private double result = 0;

    /**
     * Constructor
     */
    public Calculator() {
        this.result = 0;
    }
    /**
     * Set-method for result
     * @param rez value
     */
    public void setResult(double rez) {
        this.result = rez;
    }
    /**
     * Get-method for result
     * @return result
     */
    public double getResult() {
        return this.result;
    }

    /**
     * Result calculation method
     * @param op1 first operand
     * @param op2 second operand
     * @param operator arithmetic operation
     * @return result of calculation
     */
    public double calculation(double op1, double op2, String operator) {
        switch (operator) {
            case "+": return op1 + op2;
            case "-": return op2 - op1;
            case "*": return op1 * op2;
            case "/":
                if (op1 == 0) {
                    System.out.println("Division by zero");
                    return -1;
                }
                else return op2 / op1;
            case "^": return Math.pow(op2,op1);
        }
        return -1;
    }

    /**
     * Operator check method
     * @param str Sting
     * @return true if str is arithmetic operation, else - false
     */
    public boolean isOperator(String str) {
        return (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/") || str.equals("^")|| str.equals("(") || str.equals(")"));
    }

    /**
     * Computation priority method
     * @param operator arithmetic operation
     * @return priority
     */
    public int operatorPriority(String operator) {
        if (operator.equals("+") || operator.equals("-"))
            return 1;
        else if (operator.equals("*") || operator.equals("/"))
            return 2;
        else if (operator.equals("^"))
            return 3;
        else return -1;
    }

    /**
     * Converting infix expression to postfix
     * @param infixStr infix expression
     * @return postfix expression
     */
    public String infixToPostfix(String infixStr) {
        StringTokenizer infix = new StringTokenizer(infixStr, "+-*/^()", true);
        Stack<String> stack = new Stack<>();
        StringBuilder postfixStr = new StringBuilder();

        while (infix.hasMoreTokens()) {
            String str = infix.nextToken();
            if (!isOperator(str))
                postfixStr.append(str + " ");
            else if (str.equals("("))
                stack.push(str);
            else if (str.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("("))
                    postfixStr.append(stack.pop() + " ");
                if (!stack.isEmpty() && !stack.peek().equals("("))
                    return null;
                else if (!stack.isEmpty())
                    stack.pop();
            }
            else {
                while (!stack.isEmpty() && (operatorPriority(str) <= operatorPriority(stack.peek())))
                    postfixStr.append(stack.pop() + " ");
                stack.push(str);
            }
        }

        while (!stack.isEmpty())
            postfixStr.append(stack.pop() + " ");

        return postfixStr.toString();
    }

    /**
     * Postfix expression calculation method
     * @param postfixStr postfix expression
     */
    public void postfixCalculation(String postfixStr) {
        try {
            StringTokenizer postfix = new StringTokenizer(postfixStr, " ");
            Stack<Double> stack = new Stack<>();
            while (postfix.hasMoreTokens()) {
                String str = postfix.nextToken();
                if (!isOperator(str))
                    stack.push(Double.valueOf(String.valueOf(str)));
                else {
                    double op1 = stack.pop();
                    double op2= stack.pop();
                    double result = calculation(op1, op2, str);
                    stack.push(result);
                }
            }
            setResult(stack.pop());
        } catch (ArithmeticException e) {
            System.out.println("Division by zero");
        } catch (InputMismatchException e) {
            System.out.println("Error in input" +
                    "");
        }
    }
}
