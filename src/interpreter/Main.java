package interpreter;

import java.util.HashMap;
import java.util.Map;

// Abstract Expression 공통의 추상화된 연산을 구현
interface Expression {
    // 주어진 문맥에 따라 표현식을 평가한다는 의미
    boolean interpret(Map<String, Boolean> context);
}

// Terminal Expression : 리프노드 즉 가장 말단노드에 해당되는 연산을 함
class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    } // 변수의 이름을 사용하여 문맥에서 해당 변수의 값을 반환한다.

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return context.get(name);
    }
}

// Non-terminal Expression for AND operation: 중간 노드에 해당하는 연산을 함
class AndExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

// Non-terminal Expression for OR operation : 중간 노드에 해당하는 연산을 함
class OrExpression implements Expression {
    private Expression expr1;
    private Expression expr2;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

// Non-terminal Expression for NOT operation : 중간 노드에 해당하는 연산을 함
// 리프노드 연산클래스와의 차이는 다른 표현식을 멤버변수로 포함한다. --ㅂ
class NotExpression implements Expression {
    private Expression expr; // 이런식으로 --ㅂ

    public NotExpression(Expression expr) {
        this.expr = expr;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return !expr.interpret(context); // 그리고 이런식으로 논리연산을 진행한다. --ㅂ
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        // Create variables
        Expression varA = new Variable("A");
        Expression varB = new Variable("B");

        // Create expressions
        Expression expr1 = new AndExpression(varA, varB); // A AND B
        Expression expr2 = new OrExpression(varA, new NotExpression(varB)); // A OR NOT B

        // Create context
        Map<String, Boolean> context = new HashMap<>();
        context.put("A", true);
        context.put("B", false);

        // Interpret expressions
        System.out.println("A AND B: " + expr1.interpret(context)); // Output: false
        System.out.println("A OR NOT B: " + expr2.interpret(context)); // Output: true
    }
}