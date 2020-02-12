package lesson19.homework;

public class Controller {

    public static void put(Storage storage, File file) throws Exception {
        try {
            checkFileFormat(storage, file);
            checkSize(storage, file);
            checkFile(storage, file);
        } catch (Exception e) {
            throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": " + e.getMessage());
        }

        putFile(storage, file);
    }

    public static void delete(Storage storage, File file) throws Exception {
        try {
            findById(storage, file.getId());
        } catch (Exception e) {
            throw new Exception("Cannot delete file " + file.getId() + " from storage " + storage.getId() + ": " + e.getMessage());
        }

        deleteFile(storage, file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        try {
            checkFilesFormat(storageFrom, storageTo);
            checkSize(storageFrom, storageTo);
            checkFiles(storageFrom, storageTo);
        } catch (Exception e) {
            throw new Exception("Cannot transfer files from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": " + e.getMessage());
        }

        transferAllFiles(storageFrom, storageTo);
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file;
        try {
            file = findById(storageFrom, id);

            checkFileFormat(storageTo, file);
            checkSize(storageTo, file);
            checkFile(storageTo, file);
        } catch (Exception e) {
            throw new Exception("Cannot transfer file " + id + " from storage " + storageFrom.getId() + " to storage " + storageTo.getId() + ": " + e.getMessage());
        }

        putFile(storageTo, file);
        deleteFile(storageFrom, file);
    }

    private static void checkFileFormat(Storage storage, File file) throws Exception {
        for (String str : storage.getFormatsSupported()) {
            if (file.getFormat().equals(str)) return;
        }
        throw new Exception("Unsuitable format");
    }

    private static void checkFilesFormat(Storage storageFrom, Storage storageTo) throws Exception {
        for (File file : storageFrom.getFiles()) {
            if (file != null)
                checkFileFormat(storageTo, file);
        }
    }

    private static void checkSize(Storage storage, File file) throws Exception {
        int storageFilesSize = 0;
        for (File fl : storage.getFiles()) {
            if (fl != null) storageFilesSize += fl.getSize();
        }
        if (storage.getStorageSize() < storageFilesSize + file.getSize())
            throw new Exception("No storage space");
    }

    private static void checkSize(Storage storageFrom, Storage storageTo) throws Exception {
        int storageFromFilesSize = 0;
        for (File fl : storageFrom.getFiles()) {
            if (fl != null) storageFromFilesSize += fl.getSize();
        }

        int storageToFilesSize = 0;
        for (File fl : storageTo.getFiles()) {
            if (fl != null) storageToFilesSize += fl.getSize();
        }

        if (storageTo.getStorageSize() < storageFromFilesSize + storageToFilesSize)
            throw new Exception("No storage space");
    }

    private static void checkFile(Storage storage, File file) throws Exception {
        for (File file1 : storage.getFiles()) {
            if (file1 != null && file.getId() == file1.getId())
                throw new Exception("File already exists");
        }
    }

    private static void checkFiles(Storage storageFrom, Storage storageTo) throws Exception {
        for (File file : storageFrom.getFiles()) {
            checkFile(storageTo, file);
        }
    }

    private static File findById(Storage storage, long id) throws Exception {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id) return file;
        }
        throw new Exception("The file is missing from the repository");
    }

    private static void putFile(Storage storage, File file) {
        File[] files = storage.getFiles();

        int index = 0;
        for (File fl : files) {
            if (fl == null) {
                files[index] = file;
                storage.setFiles(files);
                return;
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
                return;
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