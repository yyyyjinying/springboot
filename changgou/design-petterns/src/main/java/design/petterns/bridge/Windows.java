package design.petterns.bridge;

public class Windows extends OperatingSystemVersion {


    public Windows(VideoFile videoFile) {
        super(videoFile);
    }

    public void play(String fileName) {
        videoFile.decode(fileName);

    }
}
