package Project.DAO;

import java.io.*;
import java.util.LinkedList;

public abstract class MainDAO<T> {
    private String path;

    LinkedList<T> getFromFile() {
        LinkedList<T> t = new LinkedList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineIndex = 1;
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    t.add(map(line));
                } catch (Exception e) {
                    System.err.println("broken line: " + lineIndex);
                }
                lineIndex++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Reading from file failed");
        }
        return t;
    }

    void addToFile(T t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.append("\n");
            bw.append(t.toString());
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Writing to file failed");
        }
    }

    abstract T map(String line) throws Exception;
}