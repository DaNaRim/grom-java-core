package Project.DAO;

import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.MainModel;

import java.io.*;
import java.util.LinkedList;

public abstract class MainDAO<T extends MainModel> {
    private String path;

    public MainDAO(String path) {
        this.path = path;
    }

    public T findById(long id) throws IOException, InternalServerException {
        for (T t : getFromFile()) {
            if (t.getId() == id) return t;
        }
        throw new InternalServerException("Missing object with id: " + id);
    }

    public LinkedList<T> getFromFile() throws BrokenFileException, IOException {
        validate(path);

        LinkedList<T> t = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineIndex = 1;
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    t.add(map(line));
                } catch (Exception e) {
                    throw new BrokenFileException("broken line: " + lineIndex + " in file: " + path);
                }
                lineIndex++;
            }
        } catch (IOException e) {
            throw new IOException("Reading from file: " + path + " failed");
        }
        return t;
    }

    public T addToFile(T t) throws IOException, BrokenFileException {
        validate(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            if (getFromFile() != null) bw.append("\n");
            bw.append(t.toString());
        } catch (IOException e) {
            throw new IOException("Writing to file: " + path + " failed");
        }
        return t;
    }

    public void deleteFromFile(Long id) throws IOException, BrokenFileException {
        LinkedList<T> newToWrite = new LinkedList<>();

        for (T t1 : getFromFile()) {
            if (!t1.getId().equals(id)) newToWrite.add(t1);
        }
        deleteFileContent();

        for (T t1 : newToWrite) {
            addToFile(t1);
        }
    }

    public abstract T map(String line) throws Exception;

    private void validate(String path) throws FileNotFoundException {
        if (!new File(path).exists())
            throw new FileNotFoundException("File: " + path + " does not exist");
    }

    private void deleteFileContent() throws IOException {
        validate(path);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            throw new IOException("Delete from file: " + path + " failed");
        }
    }
}