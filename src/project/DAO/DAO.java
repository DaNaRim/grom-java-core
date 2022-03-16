package project.DAO;

import project.exception.BadRequestException;
import project.exception.InternalServerException;
import project.model.BaseModel;

import java.io.*;
import java.util.TreeSet;
import java.util.UUID;

public abstract class DAO<T extends BaseModel> {

    private final String path;

    public DAO(String path) {
        this.path = path;
    }

    public abstract T map(String line);

    public final T findById(long id) throws InternalServerException, BadRequestException {
        for (T object : getObjectsFromDAO()) {
            if (object.getId() == id) return object;
        }
        throw new BadRequestException("findById failed: missing object with id: " + id);
    }

    public final TreeSet<T> getObjectsFromDAO() throws InternalServerException {
        validateDAO(path);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            TreeSet<T> objects = new TreeSet<>();
            String line;
            while ((line = br.readLine()) != null) {
                objects.add(map(line));
            }
            return objects;
        } catch (IOException e) {
            throw new InternalServerException("getObjectsFromDAO failed: reading from file: " + path + " failed");
        }
    }

    public final T addObjectToDAO(T object) throws InternalServerException {
        validateDAO(path);

        try {
            if (object.getId() == null) object.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

            TreeSet<T> objects = getObjectsFromDAO();
            objects.add(object);

            deleteDAOContent();
            writeObjectsToDAO(objects);
            return object;
        } catch (InternalServerException e) {
            throw new InternalServerException("addToFile failed: " + e.getMessage());
        }
    }

    public final void deleteObjectFromDAO(T deletableObject) throws InternalServerException {
        validateDAO(path);

        try {
            TreeSet<T> objects = new TreeSet<>();
            for (T object : getObjectsFromDAO()) {
                if (object.getId().equals(deletableObject.getId())) continue;
                objects.add(object);
            }

            deleteDAOContent();
            writeObjectsToDAO(objects);
        } catch (InternalServerException e) {
            throw new InternalServerException("deleteObjectFromDAO failed: " + e.getMessage());
        }
    }

    public final T updateObjectInDAO(T updatableObject) throws InternalServerException {
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

        if (!file.exists()) {
            throw new InternalServerException("validate failed: file: " + path + " does not exist");
        }
        if (!file.canRead()) {
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to read");
        }
        if (!file.canWrite()) {
            throw new InternalServerException("validate failed: file " + path + " does not have permissions to write");
        }
    }

    private void writeObjectsToDAO(TreeSet<T> objects) throws InternalServerException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            for (T object : objects) {
                bw.append(object.toString());
                bw.append("\r\n");
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
