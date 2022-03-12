package main.lesson19.homework;

public class Controller {

    public void put(Storage storage, File file) throws Exception {
        validatePut(storage, file);

        putFile(storage, file);
    }

    public void delete(Storage storage, File file) throws Exception {
        if (!isFileExistsInStorage(storage, file.getId())) {
            throw new Exception(String.format("Cannot delete file %d from storage %d: File not found",
                    file.getId(), storage.getId()));
        }

        deleteFile(storage, file);
    }

    public void transferAll(Storage storageFrom, Storage storageTo) throws Exception {
        validateTransferAll(storageFrom, storageTo);

        for (File file : storageFrom.getFiles()) {
            putFile(storageTo, file);
            deleteFile(storageFrom, file);
        }
    }

    public void transferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file = validateTransferFile(storageFrom, storageTo, id);

        putFile(storageTo, file);
        deleteFile(storageFrom, file);
    }

    private void validatePut(Storage storage, File file) throws Exception {

        if (!isFileFormatValidInStorage(storage, file)) {
            throw new Exception(String.format(
                    "Cannot put file %d in storage %d: Unsuitable format",
                    file.getId(), storage.getId()));
        }
        if (getFilesSize(storage) + file.getSize() > storage.getStorageSize()) {
            throw new Exception(String.format(
                    "Cannot put file %d in storage %d: No storage space",
                    file.getId(), storage.getId()));
        }

        for (File file1 : storage.getFiles()) {
            if (file1 != null && file.getId() == file1.getId()) {
                throw new Exception(String.format(
                        "Cannot put file %d in storage %d: File already exists",
                        file.getId(), storage.getId()));
            }
        }
    }

    private void validateTransferAll(Storage storageFrom, Storage storageTo) throws Exception {
        for (File file : storageFrom.getFiles()) {
            if (file == null || isFileFormatValidInStorage(storageTo, file)) continue;
            throw new Exception(String.format(
                    "Cannot transfer files from storage %d to storage %d: Unsuitable file format %d",
                    storageFrom.getId(), storageTo.getId(), file.getId()));
        }
        if (getFilesSize(storageFrom) + getFilesSize(storageTo) > storageTo.getStorageSize()) {
            throw new Exception(String.format(
                    "Cannot transfer files from storage %d to storage %d: No storage space",
                    storageFrom.getId(), storageTo.getId()));
        }
        for (File file : storageFrom.getFiles()) {
            if (isFileExistsInStorage(storageTo, file.getId())) {
                throw new Exception(String.format(
                        "Cannot transfer files from storage %d to storage %d: File %d already exists",
                        storageFrom.getId(), storageTo.getId(), file.getId()));
            }
        }
    }

    private File validateTransferFile(Storage storageFrom, Storage storageTo, long id) throws Exception {
        File file;

        try {
            file = findById(storageFrom, id);
        } catch (Exception e) {
            throw new Exception(String.format(
                    "Cannot transfer file %d from storage %d to storage %d: File not found",
                    id, storageFrom.getId(), storageTo.getId()));
        }

        if (isFileExistsInStorage(storageTo, id)) {
            throw new Exception(String.format(
                    "Cannot transfer file %d from storage %d to storage %d: File with this id already exists",
                    id, storageFrom.getId(), storageTo.getId()));
        }
        if (isFileFormatValidInStorage(storageTo, file)) {
            throw new Exception(String.format(
                    "Cannot transfer file %d from storage %d to storage %d: Unsuitable format",
                    id, storageFrom.getId(), storageTo.getId()));
        }
        if (getFilesSize(storageTo) + file.getSize() > storageTo.getStorageSize()) {
            throw new Exception(String.format(
                    "Cannot transfer file %d from storage %d to storage %d: No storage space",
                    id, storageFrom.getId(), storageTo.getId()));
        }
        return file;
    }

    private int getFilesSize(Storage storage) {
        int size = 0;
        for (File file : storage.getFiles()) {
            if (file != null) size += file.getSize();
        }
        return size;
    }

    private boolean isFileExistsInStorage(Storage storage, long id) {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id) return true;
        }
        return false;
    }

    private boolean isFileFormatValidInStorage(Storage storage, File file) {
        boolean isValidFormat = false;
        for (String str : storage.getFormatsSupported()) {
            if (file.getFormat().equals(str)) {
                isValidFormat = true;
                break;
            }
        }
        return isValidFormat;
    }

    private File findById(Storage storage, long id) throws Exception {
        for (File file : storage.getFiles()) {
            if (file != null && file.getId() == id) return file;
        }
        throw new Exception("The file is missing from the repository");
    }

    private void putFile(Storage storage, File file) {
        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] == null) {
                files[i] = file;
                storage.setFiles(files);
                return;
            }
        }
    }

    private void deleteFile(Storage storage, File file) {
        File[] files = storage.getFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i] != null && files[i].equals(file)) {
                files[i] = null;
                return;
            }
        }
    }
}
