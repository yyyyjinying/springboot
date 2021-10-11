package design.petterns.singleton.abstractdemo1;

/**
 * 抽象类中可以有抽象方法和非抽象方法
 * 抽象类不可以实力化
 */
public abstract class Preson {
    private String name;

    public Preson(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public abstract String getDescription();
}
