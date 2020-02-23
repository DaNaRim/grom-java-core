package Project.DAO;

import Project.model.MainModel;

import java.io.*;
import java.util.LinkedList;

public abstract class MainDAO<T extends MainModel> {
    private String path;

    LinkedList<T> getFromFile() { //TODO Exception
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

    T addToFile(T t) { //TODO Exception
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            if (getFromFile() != null) bw.append("\n");
            bw.append(t.toString());
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Writing to file failed");
        }
        return t;
    }

    void deleteFromFile(Long id) {
        LinkedList<T> newToWrite = new LinkedList<>();

        for (T t1 : getFromFile()) {
            if (!t1.getId().equals(id)) newToWrite.add(t1);
        }
        deleteFileContent();

        for (T t1 : newToWrite) {
            addToFile(t1);
        }
    }

    abstract T map(String line) throws Exception; //TODO Exception

    private void deleteFileContent() { //TODO Exception
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            System.err.println("Delete from file failed");
        }
    }

    //TODO access modifiers
}