package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.MainModel;

import java.io.*;
import java.util.LinkedList;

public abstract class DAOTools<T extends MainModel> {
    private String path;

    public DAOTools(String path) {
        this.path = path;
    }

    public final T findById(long id) throws InternalServerException, BadRequestException {
        for (T t : getFromFile()) {
            if (t.getId() == id) return t;
        }
        throw new BadRequestException("findById failed: missing object with id: " + id);
    }

    public final LinkedList<T> getFromFile() throws InternalServerException {
        validate(path);

        LinkedList<T> t = new LinkedList<>();
        int lineIndex = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                t.add(map(line));
                lineIndex++;
            }
        } catch (IOException e) {
            throw new InternalServerException("getFromFile failed: reading from file: " + path + " failed");
        } catch (BrokenFileException e) {
            throw new InternalServerException("getFromFile failed: broken line: " + lineIndex + " in file: " + path);
        }
        return t;
    }

    public final T addToFile(T t) throws InternalServerException {
        validate(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            if (!getFromFile().toString().equals("[]")) bw.append("\r\n");
            bw.append(t.toString());
        } catch (IOException e) {
            throw new InternalServerException("addToFile failed: writing to file: " + path + " failed");
        }
        return t;
    }

    public final void deleteFromFile(Long id) throws InternalServerException {
        LinkedList<T> newToWrite = getNewContent(id);
        deleteFileContent();
        writeNewContent(newToWrite);
    }

    public abstract T map(String line) throws BrokenFileException;

    private void validate(String path) throws InternalServerException {
        File file = new File(path);

        if (!file.exists())
            throw new InternalServerException("validate failed: file: " + path + " does not exist");

        if (!file.canRead())
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to read");

        if (!file.canWrite())
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to write");
    }

    private void deleteFileContent() throws InternalServerException {
        validate(path);

        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            throw new InternalServerException("deleteFileContent failed: delete from file: " + path + " failed");
        }
    }

    private LinkedList<T> getNewContent(Long id) throws InternalServerException {
        LinkedList<T> newToWrite = new LinkedList<>();

        for (T t1 : getFromFile()) {
            if (!t1.getId().equals(id)) newToWrite.add(t1);
        }
        return newToWrite;
    }

    private void writeNewContent(LinkedList<T> newToWrite) throws InternalServerException {
        for (T t1 : newToWrite) {
            addToFile(t1);
        }
    }
}