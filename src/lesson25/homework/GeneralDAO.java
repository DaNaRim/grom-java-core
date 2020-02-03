package lesson25.homework;

public class GeneralDAO<T> {
    @SuppressWarnings("unchecked")
    private T[] database = (T[]) new Object[10];

    public T save(T t) throws Exception {
        validate(t);
        int index = 0;
        for (T el : database) {
            if (el == null) {
                database[index] = t;
                return database[index];
            }
            index++;
        }
        return null;
    }

    public T[] getAll() {
        return database;
    }

    private void validate(T t) throws Exception {
        if (t == null) throw new Exception("Object is empty");

        for (T el : database) {
            if (t.equals(el)) throw new Exception("The object is already in the database");
        }

        int count = 0;
        for (T el : database) {
            if (el != null) count++;
        }
        if (count == database.length) throw new Exception("There is no space for the object");
    }
}
