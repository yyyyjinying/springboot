package design.petterns.interpreter;

public class Variable extends AbstractExpression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    public int interpret(Context context) {
        return context.getValue(this);
    }

    public String toString(){
        return name;
    }
}
