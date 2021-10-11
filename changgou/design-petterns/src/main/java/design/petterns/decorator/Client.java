package design.petterns.decorator;

public class Client {
    public static void main(String[] args) {
//        //点一份炒饭
//        FastFood fastFood = new FriedRice();
//        // 花费的价格
//        System.out.println(fastFood.getDesc() + "   " + fastFood.cost() + "元");
//
//        System.out.println("=================");
        //点一份加鸡蛋的炒饭
        FastFood food1 = new FriedRice();

        // 加一个鸡蛋
        food1 = new Egg(food1);
        System.out.println(food1.getDesc() + " " + food1.cost() + "元");

        // 再加一个鸡蛋
        food1 = new Egg(food1);
        System.out.println(food1.getDesc() + " " + food1.cost() + "元");

        // 加一个培根
        food1 = new Bacon(food1);
        System.out.println(food1.getDesc() + " " + food1.cost() + "元");
        food1 = new Bacon(food1);
        System.out.println(food1.getDesc() + " " + food1.cost() + "元");

    }
}
