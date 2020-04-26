package gromcode.main.lesson34.homework2;

import java.io.*;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath, fileToPath);

        StringBuffer sb = readFromFile(fileFromPath);
        deleteFileContent(fileFromPath);

        writeToFile(fileFromPath, newFileFromContent(sb, word));
        writeToFile(fileToPath, newFileToContent(sb, word));
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
            if (readFromFile(path).toString().equals("")) bufferedWriter.append("\n");
            bufferedWriter.append(contentToWriter);
        } catch (IOException e) {
            System.err.println("Writing to file " + path + " failed");
        }
    }

    private static StringBuffer newFileToContent(StringBuffer firstReader, String word) {
        StringBuffer res = new StringBuffer();
        for (String str : firstReader.toString().split(".")) {
            if (str.length() > 10 && str.contains(word)) {
                res.append(str);
                res.append(".");
            }
        }
        return res;
    }

    private static StringBuffer newFileFromContent(StringBuffer firstReader, String word) {
        StringBuffer res = new StringBuffer();
        for (String str : firstReader.toString().split(".")) {
            if (str.length() > 10 && !str.contains(word)) {
                res.append(str);
                res.append(".");
            }
        }
        return res;
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

        checkFile(fileFrom);
        checkFile(fileTo);

        if (!fileFrom.canRead()) {
            throw new Exception("File " + fileFrom + " does not have permissions to read");
        }
        if (!fileFrom.canWrite()) {
            throw new Exception("File " + fileFrom + " does not have permissions to written");
        }
    }

    private static void checkFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File " + file + " does not exist");
        }
    }
}