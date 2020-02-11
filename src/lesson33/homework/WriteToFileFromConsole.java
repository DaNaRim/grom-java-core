package lesson33.homework;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class WriteToFileFromConsole {
    public static void writeToFileFromConsole(String path) {
        FileWriter fileWriter = null;
        BufferedWriter bw = null;

        try {
            fileWriter = new FileWriter(path, true);
            bw = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println("File with path " + path + " not found");
            IOUtils.closeQuietly(fileWriter);
            IOUtils.closeQuietly(bw);
            return;
        }

        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(streamReader);

        System.out.println("Enter file content to write in the file:");

        try {
            String line;
            while (!(line = br.readLine()).equals("wr")) {
                bw.append(line);
            }
        } catch (IOException e) {
            System.err.println("Can't write to file with path " + path);
        } finally {
            IOUtils.closeQuietly(fileWriter);
            IOUtils.closeQuietly(bw);
            IOUtils.closeQuietly(streamReader);
            IOUtils.closeQuietly(br);
        }
    }
}
