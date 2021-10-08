package design.principles.demo3.after;

public class XiJieHardDisk implements HardDisk{

    public void save(String data){
        System.out.println("使用XiJieHardDisk存储数据");

    }

    public String get(){
        System.out.println("使用XiJieHardDisk取数据");
        return "数据";
    }

}
