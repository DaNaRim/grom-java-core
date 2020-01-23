package lesson19.homework;

public class Controller {

    public static void put(Storage storage, File file) throws Exception {
        if (!checkFormat(storage, file)) callPutFileFormatException(storage, file);
        if (!checkSize(storage, file)) callPutFileSizeException(storage, file);
        if (!checkFile(storage, file)) callPutFileFileException(storage, file);

        putFile(storage, file);
    }

    public static void delete(Storage storage, File file) throws Exception {
        if (checkFile(storage, file)) callDeleteFileException(storage, file);

        deleteFile(storage, file);
    }

    public static void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        if (!checkFilesFormat(storageFrom, storageTo)) callTransferAllFilesFormatException(storageFrom, storageTo);
        if (!checkSize(storageFrom, storageTo)) callTransferAllSizeException(storageFrom, storageTo);
        if (!checkFile(storageFrom, storageTo)) callTransferAllFileException(storageFrom, storageTo);

        transferAllFiles(storageFrom, storageTo);
    }

    public static void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = findById(storageFrom, id);

        if (file == null) callTransferFileNullException(storageFrom, storageTo, id);
        if (!checkFormat(storageTo, file)) callTransferFileFormatException(storageFrom, storageTo, file);
        if (!checkSize(storageTo, file)) callTransferFileSizeException(storageFrom, storageTo, file);
        if (!checkFile(storageTo, file)) callTransferFileFileException(storageFrom, storageTo, file);

        putFile(storageTo, file);
        deleteFile(storageFrom, file);
    }

    private static boolean checkFormat(Storage storage, File file) {
        for (String str : storage.getFormatsSupported()) {
            if (file != null && file.getFormat().equals(str)) return true;
        }
        return false;
    }

    private static boolean checkFilesFormat(Storage storageFrom, Storage storageTo) {
        File[] files = storageFrom.getFiles();

        for (File file : files) {
            if (file != null && !checkFormat(storageTo, file)) return false;
        }
        return true;
    }

    private static boolean checkSize(Storage storage, File file) {
        int overallSize = 0;
        for (File fl : storage.getFiles()) {
            if (fl != null) overallSize += fl.getSize();
        }

        return storage.getStorageSize() > overallSize + file.getSize();
    }

    private static boolean checkSize(Storage storageFrom, Storage storageTo) {
        int overallSize1 = 0;
        for (File fl : storageFrom.getFiles()) {
            if (fl != null)
                overallSize1 += fl.getSize();
        }

        int overallSize2 = 0;
        for (File fl : storageTo.getFiles()) {
            if (fl != null)
                overallSize2 += fl.getSize();
        }

        return storageTo.getStorageSize() > overallSize1 + overallSize2;
    }

    private static boolean checkFile(Storage storage, File file) {
        for (File file1 : storage.getFiles()) {
            if (file != null && file1 != null)
                if (file.equals(file1) || file.getId() == file1.getId()) return false;
        }
        return true;
    }

    private static boolean checkFile(Storage storageFrom, Storage storageTo) {
        for (File file : storageFrom.getFiles()) {
            if (!checkFile(storageTo, file)) return false;
        }
        return true;
    }

    private static File findById(Storage storage, long id) {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id)
                return file;
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

    private static void callPutFileFormatException(Storage storage, File file) throws Exception {
        throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": Unsuitable format");
    }

    private static void callPutFileSizeException(Storage storage, File file) throws Exception {
        throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": No storage space");
    }

    private static void callPutFileFileException(Storage storage, File file) throws Exception {
        throw new Exception("Cannot put file " + file.getId() + " in storage " + storage.getId() + ": This file already exists");
    }

    private static void callDeleteFileException(Storage storage, File file) throws Exception {
        throw new Exception("Cannot delete file " + file.getId() + " from storage " + storage.getId() + ": No such file exists");
    }

    private static void callTransferAllFilesFormatException(Storage FromStorage, Storage ToStorage) throws Exception {
        throw new Exception("Cannot transfer files from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": Unsuitable format");
    }

    private static void callTransferAllSizeException(Storage FromStorage, Storage ToStorage) throws Exception {
        throw new Exception("Cannot transfer files from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": No storage space");
    }

    private static void callTransferAllFileException(Storage FromStorage, Storage ToStorage) throws Exception {
        throw new Exception("Cannot transfer files from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + "toStorage already has file from fromStorage");
    }

    private static void callTransferFileNullException(Storage FromStorage, Storage ToStorage, long id) throws Exception {
        throw new Exception("Cannot transfer files with id " + id + " from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": The file with this id is missing from the repository");
    }

    private static void callTransferFileFormatException(Storage FromStorage, Storage ToStorage, File file) throws Exception {
        throw new Exception("Cannot transfer file " + file.getId() + " from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": Unsuitable format");
    }

    private static void callTransferFileSizeException(Storage FromStorage, Storage ToStorage, File file) throws Exception {
        throw new Exception("Cannot transfer file " + file.getId() + " from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": No storage space");
    }

    private static void callTransferFileFileException(Storage FromStorage, Storage ToStorage, File file) throws Exception {
        throw new Exception("Cannot transfer file " + file.getId() + " from storage " + FromStorage.getId() + " to storage " + ToStorage.getId() + ": This file already exists");
    }
}