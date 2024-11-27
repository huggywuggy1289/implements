package interpreter;

import java.util.HashMap;
import java.util.Map;

// Abstract Expression
interface Expression {
    boolean interpret(Map<String, Boolean> context);
}

// Terminal Expression
class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return context.get(name);
    }
}

// Non-terminal Expression for AND operation
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

// Non-terminal Expression for OR operation
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

// Non-terminal Expression for NOT operation
class NotExpression implements Expression {
    private Expression expr;

    public NotExpression(Expression expr) {
        this.expr = expr;
    }

    @Override
    public boolean interpret(Map<String, Boolean> context) {
        return !expr.interpret(context);
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