package main.lesson24.exercise;

public class GeneralDAO<T> {

    @SuppressWarnings("unchecked")
    private T[] array = (T[]) new Object[5];

    public T save(T t) {
        int index = 0;
        for (T el : array) {
            if (el == null) {
                array[index] = t;
                return array[index];
            }
            index++;
        }
        return null;
    }

    public T[] getAll() {
        return array;
    }
}
