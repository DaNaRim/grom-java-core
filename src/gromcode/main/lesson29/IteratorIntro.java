package gromcode.main.lesson29;

import java.util.HashSet;
import java.util.Set;

public class IteratorIntro {
    public static void main(String[] args) {
        Set<File> files = new HashSet<>();
        File file1 = new File("pict.txt", 100);
        File file2 = new File("home.txt", 178);
        File file3 = new File("home3333.txt", 340);
        files.add(file1);
        files.add(file2);
        files.add(file3);

        System.out.println(files);

//        Iterator<File> fileIterator = files.iterator();
//
//        while (fileIterator.hasNext()) {
//            System.out.println(fileIterator.next().getFileName());
//        }

        for (File file : files) {
            System.out.println(file.getFileName());
        }

    }
}
