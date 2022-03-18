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

    public final T findById(long id) throws InternalServerException, BadRequestException {
        for (T object : getAll()) {
            if (object.getId() == id) return object;
        }
        throw new BadRequestException("findById failed: missing object with id: " + id);
    }

    public final boolean isExists(long id) throws InternalServerException {
        for (T object : getAll()) {
            if (object.getId() == id) return true;
        }
        return false;
    }

    public final TreeSet<T> getAll() throws InternalServerException {
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

    public final T save(T object) throws InternalServerException {
        validateDAO(path);

        try {
            if (object.getId() == null) object.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);

            TreeSet<T> objects = getAll();
            objects.add(object);

            deleteDAOContent();
            writeObjectsToDAO(objects);
            return object;
        } catch (InternalServerException e) {
            throw new InternalServerException("addToFile failed: " + e.getMessage());
        }
    }

    public final void delete(T deletableObject) throws InternalServerException {
        validateDAO(path);

        try {
            TreeSet<T> objects = new TreeSet<>();
            for (T object : getAll()) {
                if (object.getId().equals(deletableObject.getId())) continue;
                objects.add(object);
            }

            deleteDAOContent();
            writeObjectsToDAO(objects);
        } catch (InternalServerException e) {
            throw new InternalServerException("deleteObjectFromDAO failed: " + e.getMessage());
        }
    }

    public final void delete(long id) throws InternalServerException, BadRequestException {
        delete(findById(id));
    }

    public final T update(T updatableObject) throws InternalServerException {
        try {
            delete(updatableObject);
            save(updatableObject);
            return updatableObject;
        } catch (InternalServerException e) {
            throw new InternalServerException("updateObjectInDAO failed: " + e.getMessage());
        }
    }

    protected abstract T map(String line);

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
