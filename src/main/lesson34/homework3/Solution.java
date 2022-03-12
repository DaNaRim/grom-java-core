package main.lesson34.homework3;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class Solution {

    public static void copyFileContent(String fileFromPath, String fileToPath) throws Exception {
        validate(fileFromPath, fileToPath);
        writeToFile(fileToPath, readFromFile(fileFromPath));
        deleteFileContent(fileFromPath);
    }

    public static void copyFileContentApacheIO(String fileFromPath, String fileToPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileFromPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileFromPath))) {
            IOUtils.copy(br, bw);
        } catch (IOException e) {
            System.err.println("Writing to file " + fileToPath + " failed");
        }
    }

    private static StringBuffer readFromFile(String path) {
        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.append(line);
                res.append("\n");
            }
            res.replace(res.length() - 1, res.length(), "");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + "failed");
        }
        return res;
    }

    private static void writeToFile(String path, StringBuffer contentToWriter) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            if (readFromFile(path).toString().equals("")) {
                bufferedWriter.append("\n");
            }
            bufferedWriter.append(contentToWriter);
        } catch (IOException e) {
            System.err.println("Writing to file " + path + " failed");
        }
    }

    private static void deleteFileContent(String path) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(path))) {
            br.write("");
        } catch (IOException e) {
            System.err.println("Delete from file " + path + "failed");
        }
    }

    private static void validate(String fileFromPath, String fileToPath) throws Exception {
        File fileFrom = new File(fileFromPath);
        File fileTo = new File(fileToPath);

        if (!fileFrom.exists()) {
            throw new FileNotFoundException("File " + fileFrom + " does not exist");
        }
        if (!fileTo.exists()) {
            throw new FileNotFoundException("File " + fileTo + " does not exist");
        }
        if (!fileFrom.canRead()) {
            throw new Exception("File " + fileFrom + " does not have permissions to read");
        }
        if (!fileFrom.canWrite()) {
            throw new Exception("File " + fileFrom + " does not have permissions to written");
        }
    }
}
