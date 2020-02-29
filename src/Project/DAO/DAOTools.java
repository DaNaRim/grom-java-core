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
            throw new InternalServerException("getObjectsFromDAO failed: reading from file: " + path + " failed");
        } catch (BrokenFileException e) {
            throw new InternalServerException("getObjectsFromDAO failed: broken line: " + lineIndex + " in file: " +
                    path + " : " + e.getMessage());
        }
    }

    public final T addObjectToDAO(T t) throws InternalServerException {
        validateDAO(path);

        try {
            t.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

            LinkedList<T> objects = getObjectsFromDAO();
            objects.add(t);

            deleteDAOContent();
            writeObjectsToDAO(objects);
            return t;
        } catch (InternalServerException e) {
            throw new InternalServerException("addToFile failed: " + e.getMessage());
        }
    }

    public final void deleteObjectFromDAO(T deletableObject) throws InternalServerException {
        validateDAO(path);

        try {
            LinkedList<T> objects = getObjectsFromDAO();
            objects.remove(deletableObject);

            deleteDAOContent();
            writeObjectsToDAO(objects);
        } catch (InternalServerException e) {
            throw new InternalServerException("deleteObjectFromDAO failed: " + e.getMessage());
        }
    }

    public final T updateObjectInDAO(T updatableObject) throws InternalServerException {
        validateDAO(path);

        try {
            deleteObjectFromDAO(updatableObject);
            addObjectToDAO(updatableObject);
            return updatableObject;
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

    private void writeObjectsToDAO(LinkedList<T> objects) throws InternalServerException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (T object : objects) {
                bw.append(object.toString());
            }
        } catch (IOException e) {
            throw new InternalServerException("writing to file: " + path + " failed");
        }
    }

    private void deleteDAOContent() throws InternalServerException {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            throw new InternalServerException("delete from file: " + path + " failed");
        }
    }
}