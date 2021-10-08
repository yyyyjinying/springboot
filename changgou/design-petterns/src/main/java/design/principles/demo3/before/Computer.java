package design.principles.demo3.before;

public class Computer {
    private XiJieHardDisk hardDisk;
    private IntelCpu intelCpu;
    private KingstonMemory kingstonMemory;

    public XiJieHardDisk getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(XiJieHardDisk hardDisk) {
        this.hardDisk = hardDisk;
    }

    public IntelCpu getIntelCpu() {
        return intelCpu;
    }

    public void setIntelCpu(IntelCpu intelCpu) {
        this.intelCpu = intelCpu;
    }

    public KingstonMemory getKingstonMemory() {
        return kingstonMemory;
    }

    public void setKingstonMemory(KingstonMemory kingstonMemory) {
        this.kingstonMemory = kingstonMemory;
    }

    public void run(){
        System.out.println("计算机开始运行");
        intelCpu.run();
        kingstonMemory.save();
        String s = hardDisk.get();
        System.out.println("从硬盘上获取数据："+s);
    }
}
