package lesson34.homewok2;

import java.io.*;

public class Solution {

    public static void transferSentences(String fileFromPath, String fileToPath, String word) throws Exception {
        validate(fileFromPath, fileToPath);

        StringBuffer sb = readFromFile(fileFromPath);
        deleteFileContent(fileFromPath);

        writeFile(fileFromPath, newFileFromContent(sb, word));
        writeFile(fileToPath, newFileToContent(sb, word));
    }

    private static StringBuffer readFromFile(String path) {
        StringBuffer res = new StringBuffer();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
            res.replace(res.length() - 1, res.length(), "");
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
        } catch (IOException e) {
            System.err.println("Reading from file " + path + "failed");
        }
        return res;
    }

    private static void writeFile(String path, StringBuffer contentToWriter) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path, true))) {
            if (readFromFile(path).toString().equals("")) bufferedWriter.append("\n");

            bufferedWriter.append(contentToWriter);
        } catch (IOException e) {
            System.err.println("Can`t write to file");
        }
    }

    private static StringBuffer newFileToContent(StringBuffer firstReader, String word) {
        StringBuffer res = new StringBuffer();
        for (String str : firstReader.toString().split(".")) {
            if (str.contains(word)) {
                res.append(str);
                res.append(".");
            }
        }
        return res;
    }

    private static StringBuffer newFileFromContent(StringBuffer firstReader, String word) {
        StringBuffer res = new StringBuffer();
        for (String str : firstReader.toString().split(".")) {
            if (!str.contains(word)) {
                res.append(str);
                res.append(" ");
            }
        }
        return res;
    }

    private static void deleteFileContent(String path) {
        StringBuffer res = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            res.replace(0, res.length(), "");
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
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