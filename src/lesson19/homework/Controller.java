package lesson19.homework;

public class Controller {

    public static void put(Storage storage, File file) throws Exception {
        if (!checkFormat(storage, file))
            throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": Unsuitable format");
        if (!checkSize(storage, file))
            throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": No storage space");
        if (!checkFile(storage, file))
            throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": This file already exists");

        putFile(storage, file);
    }

    public static void delete(Storage storage, File file) throws Exception {
        if (checkFile(storage, file))
            throw new Exception("Cannot delete file " + file.getId() + " from storage " + storage.getId() + ": No such file exists");

        deleteFile(storage, file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        checkFilesFormat(storageFrom, storageTo);
        checkSize(storageFrom, storageTo);
        checkFile(storageFrom, storageTo);

        transferAllFiles(storageFrom, storageTo);
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = findById(storageFrom, id);

        if (file == null)
            throw new Exception("Cannot transfer files with id " + id + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": The file with this id is missing from the repository");
        if (!checkFormat(storageTo, file))
            throw new Exception("Cannot transfer file " + file.getId() + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": Unsuitable format");
        if (!checkSize(storageTo, file))
            throw new Exception("Cannot transfer file " + file.getId() + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": No storage space");
        if (!checkFile(storageTo, file))
            throw new Exception("Cannot transfer file " + file.getId() + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": This file already exists");

        putFile(storageTo, file);
        deleteFile(storageFrom, file);
    }

    private static boolean checkFormat(Storage storage, File file) {
        for (String str : storage.getFormatsSupported()) {
            if (file != null && file.getFormat().equals(str)) return true;
        }
        return false;
    }

    private static void checkFilesFormat(Storage storageFrom, Storage storageTo) throws Exception{
        for (File file : storageFrom.getFiles()) {
            if (file != null && !checkFormat(storageTo, file))
                throw new Exception("Cannot transfer files from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": Unsuitable format");
        }
    }

    private static boolean checkSize(Storage storage, File file) {
        int overallSize = 0;
        for (File fl : storage.getFiles()) {
            if (fl != null) overallSize += fl.getSize();
        }

        return storage.getStorageSize() > overallSize + file.getSize();
    }

    private static void checkSize(Storage storageFrom, Storage storageTo) throws Exception{
        int overallSize1 = 0;
        for (File fl : storageFrom.getFiles()) {
            if (fl != null) overallSize1 += fl.getSize();
        }

        int overallSize2 = 0;
        for (File fl : storageTo.getFiles()) {
            if (fl != null) overallSize2 += fl.getSize();
        }

        if (!(storageTo.getStorageSize() > overallSize1 + overallSize2))
            throw new Exception("Cannot transfer files from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": No storage space");
    }

    private static boolean checkFile(Storage storage, File file) {
        for (File file1 : storage.getFiles()) {
            if (file != null && file1 != null)
                if (file.equals(file1) || file.getId() == file1.getId()) return false;
        }
        return true;
    }

    private static void checkFile(Storage storageFrom, Storage storageTo) throws Exception {
        for (File file : storageFrom.getFiles()) {
            if (!checkFile(storageTo, file))
                throw new Exception("Cannot transfer files from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + "toStorage already has file from fromStorage");
        }
    }

    private static File findById(Storage storage, long id) {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id) return file;
        }
        return null;
    }

    private static void putFile(Storage storage, File file) {
        File[] files = storage.getFiles();

        int index = 0;
        for (File fl : files) {
            if (fl == null) {
                files[index] = file;
                storage.setFiles(files);
                break;
            }
            index++;
        }
    }

    private static void deleteFile(Storage storage, File file) {
        File[] files = storage.getFiles();

        int index = 0;
        for (File fl : files) {
            if (fl != null && fl.equals(file)) {
                files[index] = null;
                storage.setFiles(files);
                break;
            }
            index++;
        }
    }

    private static void transferAllFiles(Storage storageFrom, Storage storageTo) {
        for (File file : storageFrom.getFiles()) {
            putFile(storageTo, file);
            deleteFile(storageFrom, file);
        }
    }
}