package gromcode.main.lesson34.homework1;

import java.io.*;

public class Solution {

    public static void transferFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);
        writeToFile(fileToPath, readFromFile(fileFromPath));
        deleteFileContent(fileFromPath);
    }

    private static void deleteFileContent(String path) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            System.err.println("Delete from file " + path + "failed");
        }
    }

    private static StringBuffer readFromFile(String path) {
        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.append(line);
                res.append("\r\n");
            }
            if (res.length() > 1) res.replace(res.length() - 1, res.length(), "");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + "failed");
        }
        return res;
    }

    private static void writeToFile(String path, StringBuffer contentToWriter) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            if (!readFromFile(path).toString().equals("")) bw.append("\r\n");
            bw.append(contentToWriter);
        } catch (IOException e) {
            System.err.println("Writing to file " + path + " failed");
        }
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        checkFile(fileFrom);
        checkFile(fileTo);

        if (!fileFrom.canRead()) {
            throw new Exception("File " + fileFrom + " does not have permissions to read");
        }

        checkToWrite(fileFrom);
        checkToWrite(fileTo);
    }

    private static void checkFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file + " does not exist");
        }
    }

    private static void checkToWrite(File file) throws Exception {
        if (!file.canWrite()) {
            throw new Exception("File " + file + " does not have permissions to written");
        }
    }
}