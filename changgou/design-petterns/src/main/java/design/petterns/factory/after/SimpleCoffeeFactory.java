package design.petterns.factory.after;

public class SimpleCoffeeFactory {
    public Coffee createCoffee(String type){
        // 声明Coffee类型的变量，根据不同类型创建不同的Coffee子类对象
        Coffee coffee = null;
        if("american" == type){
            coffee = new AmerkanCoffee();
        } else if("latte" == type){
            coffee = new LatteCoffee();

        }else{
            throw new RuntimeException("对不起，您所点的咖啡没有 ");
        }

        return coffee;
    }
}
