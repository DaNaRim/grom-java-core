package lesson24.exercise;

public class Sys {
    private int fileCount;
    private String location;

    public Sys(int fileCount, String location) {
        this.fileCount = fileCount;
        this.location = location;
    }

    @Override
    public String toString() {
        return "System{" +
                "fileCount=" + fileCount +
                ", location='" + location + '\'' +
                '}';
    }
}
