package design.principles.demo2.before;

public class RectangleDemo {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setLength(20);

        resize(rectangle);
        printLengthAndWidth(rectangle);

    }

    // 拓宽方法
    public static void resize(Rectangle rectangle){
        while(rectangle.getWidth() < rectangle.getLength()){
            rectangle.setWidth(rectangle.getWidth()+1);

        }

    }

    // 打印长方型的长和宽
    public static void printLengthAndWidth(Rectangle rectangle){
        System.out.println(rectangle.getWidth());
        System.out.println(rectangle.getLength());
    }

}
