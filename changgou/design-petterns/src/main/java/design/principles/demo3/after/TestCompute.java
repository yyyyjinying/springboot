package design.principles.demo3.after;



public class TestCompute {
    public static void main(String[] args) {
        Cpu cpu = new IntelCpu();
        HardDisk hardDisk = new XiJieHardDisk();
        Memory memory = new KingstonMemory();

        Compute compute = new Compute();
       compute.setHardDisk(hardDisk);
        compute.setCpu(cpu);
        compute.setMemory(memory);

        compute.run();

    }
}
