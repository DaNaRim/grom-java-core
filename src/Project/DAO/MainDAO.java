package Project.DAO;

import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.exception.NoAccessException;
import Project.model.MainModel;

import java.io.*;
import java.util.LinkedList;

public abstract class MainDAO<T extends MainModel> {
    private String path;

    public MainDAO(String path) {
        this.path = path;
    }

    public T findById(long id) throws IOException, InternalServerException, NoAccessException {
        for (T t : getFromFile()) {
            if (t.getId() == id) return t;
        }
        throw new InternalServerException("findById failed: missing object with id: " + id);
    }

    public LinkedList<T> getFromFile() throws BrokenFileException, IOException, NoAccessException {
        validate(path);

        LinkedList<T> t = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            int lineIndex = 1;
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    t.add(map(line));
                } catch (Exception e) {
                    throw new BrokenFileException("getFromFile failed: broken line: " + lineIndex +
                            " in file: " + path);
                }
                lineIndex++;
            }
        } catch (IOException e) {
            throw new IOException("getFromFile failed: reading from file: " + path + " failed");
        }
        return t;
    }

    public T addToFile(T t) throws IOException, BrokenFileException, NoAccessException {
        validate(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            if (getFromFile() != null) bw.append("\r\n");
            bw.append(t.toString());
        } catch (IOException e) {
            throw new IOException("addToFile failed: writing to file: " + path + " failed");
        }
        return t;
    }

    public void deleteFromFile(Long id) throws IOException, BrokenFileException, NoAccessException {
        LinkedList<T> newToWrite = getNewContent(id);
        deleteFileContent();
        writeNewContent(newToWrite);
    }

    public abstract T map(String line) throws Exception;

    private void validate(String path) throws FileNotFoundException, NoAccessException {
        File file = new File(path);

        if (!file.exists())
            throw new FileNotFoundException("validate failed: file: " + path + " does not exist");

        if (!file.canRead())
            throw new NoAccessException("validate failed: file " + path + " does not have permissions to read");

        if (!file.canWrite())
            throw new NoAccessException("validate failed: file " + path + " does not have permissions to write");
    }

    private void deleteFileContent() throws IOException, NoAccessException {
        validate(path);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            throw new IOException("deleteFileContent failed: delete from file: " + path + " failed");
        }
    }

    private LinkedList<T> getNewContent(Long id) throws IOException, BrokenFileException, NoAccessException {
        LinkedList<T> newToWrite = new LinkedList<>();

        for (T t1 : getFromFile()) {
            if (!t1.getId().equals(id)) newToWrite.add(t1);
        }
        return newToWrite;
    }

    private void writeNewContent(LinkedList<T> newToWrite) throws NoAccessException, BrokenFileException, IOException {
        for (T t1 : newToWrite) {
            addToFile(t1);
        }
    }
}