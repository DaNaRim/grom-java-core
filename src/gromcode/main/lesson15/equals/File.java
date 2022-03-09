package gromcode.main.lesson15.equals;

import java.util.Objects;

public class File {

    private int size;
    private String path;
    private String extension;

    public File(int size, String path, String extension) {
        this.size = size;
        this.path = path;
        this.extension = extension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return path.equals(file.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path);
    }

    //    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//
//        if (o == null || getClass() != o.getClass())
//            return false;
//
//        File file = (File) o;
//
//        if (size != file.size) return false;
//        if (!path.equals(file.path)) return false;
//        if (!extension.equals(file.extension)) return false;
//        return true;
//    }


/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return size == file.size &&
                path.equals(file.path) &&
                extension.equals(file.extension);
    }

//    @Override
//    public int hashCode(){
//        int result = size;
//        result = 31 * result + path.hashCode();
//        result = 31 * result + extension.hashCode();
//        return result;
//    }

    @Override
    public int hashCode() {
        return Objects.hash(size, path, extension);
    }
    */
}
