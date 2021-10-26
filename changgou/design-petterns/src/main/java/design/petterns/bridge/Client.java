package design.petterns.bridge;

/**
 * * 桥接模式提高了系统的可扩充性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统。
 *
 *   如：如果现在还有一种视频文件类型wmv，我们只需要再定义一个类实现VideoFile接口即可，其他类不需要发生变化。
 * 实现细节对客户透明
 */
public class Client {
    public static void main(String[] args) {
        OperatingSystemVersion windows = new Windows(new AVIFile());
        windows.play("战狼");
        OperatingSystemVersion mac = new Mac(new REVBBFile());
        mac.play("琅琊榜");

    }
}
