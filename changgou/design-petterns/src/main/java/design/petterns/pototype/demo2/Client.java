package design.petterns.pototype.demo2;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        Citation citation = new Citation();
        Citation c1 = citation.clone();
        c1.setName("zhao");
        System.out.println(c1.getName());

        Citation c2 = citation.clone();
        c2.setName("jin");
        System.out.println(c2.getName());

        c1.show();
        c2.show();


    }
}
