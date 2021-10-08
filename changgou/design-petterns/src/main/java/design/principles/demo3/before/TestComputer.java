package design.principles.demo3.before;

public class TestComputer {

    public static void main(String[] args) {
        IntelCpu intelCpu = new IntelCpu();
        KingstonMemory kingstonMemory = new KingstonMemory();
        XiJieHardDisk xiJieHardDisk = new XiJieHardDisk();

        Computer computer = new Computer();
        computer.setHardDisk(xiJieHardDisk);
        computer.setIntelCpu(intelCpu);
        computer.setKingstonMemory(kingstonMemory);

        computer.run();
    }
}
