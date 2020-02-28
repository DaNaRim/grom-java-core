package Project.DAO;

import Project.exception.BadRequestException;
import Project.exception.BrokenFileException;
import Project.exception.InternalServerException;
import Project.model.MainModel;

import java.io.*;
import java.util.LinkedList;
import java.util.UUID;

public abstract class DAOTools<T extends MainModel> {
    private String path;

    public DAOTools(String path) {
        this.path = path;
    }

    public abstract T map(String line) throws BrokenFileException;

    public final T findById(long id) throws InternalServerException, BadRequestException {
        for (T t : getObjectsFromDAO()) {
            if (t.getId() == id) return t;
        }
        throw new BadRequestException("findById failed: missing object with id: " + id);
    }

    public final LinkedList<T> getObjectsFromDAO() throws InternalServerException {
        validateDAO(path);

        int lineIndex = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            LinkedList<T> t = new LinkedList<>();
            String line;
            while ((line = br.readLine()) != null) {
                t.add(map(line));
                lineIndex++;
            }
            return t;
        } catch (IOException e) {
            throw new InternalServerException("getFromFile failed: reading from file: " + path + " failed");
        } catch (BrokenFileException e) {
            throw new InternalServerException("getFromFile failed: broken line: " + lineIndex + " in file: " + path);
        }
    }

    public final T addObjectToDAO(T t) throws InternalServerException {
        validateDAO(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            t.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

            LinkedList<T> objects = getObjectsFromDAO();
            objects.add(t);

            deleteDAOContent();
            for (T object : objects) {
                bw.append(object.toString());
            }
            return t;
        } catch (IOException e) {
            throw new InternalServerException("addToFile failed: writing to file: " + path + " failed");
        } catch (InternalServerException e) {
            throw new InternalServerException("addToFile failed: " + e.getMessage());
        }
    }

    public final void deleteObjectFromDAO(Long id) throws InternalServerException {
        validateDAO(path);

        try {
            LinkedList<T> newObjectsToWrite = getNewDAOContent(id);
            deleteDAOContent();
            writeNewDAOContent(newObjectsToWrite);
        } catch (InternalServerException e) {
            throw new InternalServerException("deleteObjectFromDAO failed: " + e.getMessage());
        }
    }

    public final void updateObjectInDAO(Long id, T updateObject) throws InternalServerException {
        validateDAO(path);

        try {
            deleteObjectFromDAO(id);
            addObjectToDAO(updateObject);
        } catch (InternalServerException e) {
            throw new InternalServerException("updateObjectInDAO failed: " + e.getMessage());
        }
    }

    private void validateDAO(String path) throws InternalServerException {
        File file = new File(path);

        if (!file.exists())
            throw new InternalServerException("validate failed: file: " + path + " does not exist");

        if (!file.canRead())
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to read");

        if (!file.canWrite())
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to write");
    }

    private void deleteDAOContent() throws InternalServerException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            throw new InternalServerException("deleteFileContent failed: delete from file: " + path + " failed");
        }
    }

    private LinkedList<T> getNewDAOContent(Long id) throws InternalServerException {
        LinkedList<T> newToWrite = new LinkedList<>();

        for (T t1 : getObjectsFromDAO()) {
            if (!t1.getId().equals(id)) newToWrite.add(t1);
        }
        return newToWrite;
    }

    private void writeNewDAOContent(LinkedList<T> newToWrite) throws InternalServerException {
        for (T t1 : newToWrite) {
            addObjectToDAO(t1);
        }
    }
}