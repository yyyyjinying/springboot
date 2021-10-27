package design.petterns.interpreter;

public class Minus extends AbstractExpression {
    private AbstractExpression left;
    private AbstractExpression right;

    public Minus(AbstractExpression left, AbstractExpression right) {
        this.left = left;
        this.right = right;
    }

    public int interpret(Context context) {
        return this.left.interpret(context) - this.right.interpret(context);
    }

    public String toString() {
        return "(" + this.left.toString() + "-" + this.right.toString() + ")";
    }
}
