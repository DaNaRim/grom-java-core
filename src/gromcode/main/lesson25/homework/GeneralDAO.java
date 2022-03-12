package gromcode.main.lesson25.homework;

public class GeneralDAO<T> {

    @SuppressWarnings("unchecked")
    private final T[] database = (T[]) new Object[10];

    public T save(T t) throws Exception {
        validate(t);

        for (int i = 0; i < database.length; i++) {
            if (database[i] != null) continue;
            database[i] = t;
            break;
        }
        return t;
    }

    public T[] getAll() {
        return database;
    }

    private void validate(T t) throws Exception {
        if (t == null) throw new Exception("Object is empty");

        for (T el : database) {
            if (t.equals(el)) throw new Exception("The object is already in the database");
        }

        boolean isFull = true;
        for (T el : database) {
            if (el == null) {
                isFull = false;
                break;
            }
        }
        if (isFull) throw new Exception("There is no space for the object");
    }
}
